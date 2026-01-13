package com.example.controller;

import com.example.dto.TaskDto;
import com.example.service.ProcessService;
import com.example.service.impl.ProcessDiagramService;
import com.example.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程管理接口（HTTP请求入口）
 * 接口前缀：http://localhost:8080/process
 */
@RestController
@RequestMapping("/process")
@RequiredArgsConstructor
public class ProcessController {

    private final ProcessService processService;
    private final ProcessDiagramService processDiagramService;
    private final SecurityUtils securityUtils;


    /**
     * 1. 部署流程（可选：Activiti会自动部署processes目录下的bpmn文件）<p>
     * 注意⚠️：在项目启动时，已经通过 CommandLineRunner 部署过流程文件，所以这里的接口可以省略，保留仅作演示。
     */
    @GetMapping("/deploy")
    public String deploy() {
        processService.deployProcess();
        return "流程部署成功！";
    }

    /**
     * 2. 发起请假流程 <p>
     * POST: /process/start <p>
     * 示例参数：{"initiator":"Make","approverA":"System","approverB":"游家纨绔","leaveDays":3,"leaveReason":"事假"}
     */
    @PostMapping("/start")
    public ResponseEntity<Map<String, Object>> startProcess(@RequestBody Map<String, Object> params) {
        // 模拟用户登录，同时同步 Spring Security 和 Activiti 的用户上下文，让两者身份一致
        securityUtils.logInAs(params.get("initiator").toString());
        // 流程定义ID
        String processKey = "leaveFlow";
        // 启动流程
        ProcessInstance instance = processService.startProcess(processKey, params);
        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "流程启动成功");
        result.put("processInstanceId", instance.getId()); // 流程实例ID
        result.put("processDefinitionId", instance.getProcessDefinitionId()); // 流程定义ID
        return ResponseEntity.ok(result);
    }

    /**
     * 3. 查询个人待办任务 <p>
     * GET: /process/todo/{assignee} <p>
     * 示例参数1：/process/todo/Tony （查询主管Tony的待办）<p>
     * 示例参数2：/process/todo/游家纨绔（查询经理 游家纨绔 的待办）
     */
    @GetMapping("/todo/{assignee}")
    public ResponseEntity<Map<String, Object>> getTodoTask(@PathVariable String assignee) {
        List<TaskDto> taskList = processService.getTodoTaskList(assignee);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "查询成功");
        result.put("count", taskList.size());
        result.put("data", taskList);
        return ResponseEntity.ok(result);
    }

    /**
     * 4. 执行人完成审批（核心：审批通过/驳回）<p>
     * POST: /process/complete/{taskId}  <p>
     * 示例参数1：{"initiator":"Make","approvalResultA":"approved","approveRemark":"同意请假"} <p>
     * 示例参数2：{"initiator":"Make","approvalResultA":"rejected","approveRemark":"拒绝请假"}
     */
    @PostMapping("/complete/{taskId}")
    public ResponseEntity<Map<String, Object>> completeApproval(@PathVariable String taskId, @RequestBody Map<String, Object> params) {
        // 模拟用户登录，同时同步 Spring Security 和 Activiti 的用户上下文，让两者身份一致
        securityUtils.logInAs(params.get("initiator").toString());
        // 执行审批任务
        processService.completeTask(taskId, params);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "任务处理成功");
        return ResponseEntity.ok(result);
    }

    /**
     * 5. 根据流程实例ID查询BPMN流程图
     * @param processInstanceId 流程实例ID
     * @return 图片响应（PNG 或 SVG 格式）
     */
    @GetMapping("/diagram/{processInstanceId}")
    public ResponseEntity<byte[]> getProcessDiagram(@PathVariable String processInstanceId) {
        try (
                InputStream inputStream = processDiagramService.getProcessDiagram(processInstanceId);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        ) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }

            // 设置响应头，返回SVG图片
            HttpHeaders headers = new HttpHeaders();
            // headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentType(MediaType.parseMediaType("image/svg+xml"));
            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("生成流程图失败：" + e.getMessage(), e);
        }
    }

}
