package com.example.service.impl;

import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProcessDiagramService {

    @Resource
    private RuntimeService runtimeService;
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private HistoryService historyService;

    /**
     * 根据流程实例ID生成带高亮节点的BPMN流程图（适配Activiti 8.x.x）
     * @param processInstanceId 流程实例ID
     * @return 流程图输入流（PNG格式）
     */
    @SneakyThrows
    public InputStream getProcessDiagram(String processInstanceId) {
        // 1. 校验并获取流程实例（兼容运行中/已结束流程）
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();

        String processDefinitionId;
        if (processInstance != null) {
            // 运行中的流程
            processDefinitionId = processInstance.getProcessDefinitionId();
        } else {
            // 已结束的流程，从历史表查询流程定义ID
            HistoricActivityInstance historicInstance = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(processInstanceId)
                    .activityType("startEvent") // 获取开始节点实例
                    .singleResult();
            if (historicInstance == null) {
                throw new RuntimeException("流程实例ID不存在：" + processInstanceId);
            }
            processDefinitionId = historicInstance.getProcessDefinitionId();
        }

        // 2. 获取BPMN模型
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        if (bpmnModel == null) {
            throw new RuntimeException("未找到流程定义对应的BPMN模型：" + processDefinitionId);
        }

        // 3. 获取需要高亮的节点ID（当前活跃节点 + 历史已执行节点）
        List<String> highLightedActivityIds = new ArrayList<>();

        // 3.1 运行中的节点（仅运行中流程有）
        if (processInstance != null) {
            List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
            highLightedActivityIds.addAll(activeActivityIds);
        }

        // 3.2 历史已执行节点（兼容已结束流程）
        List<HistoricActivityInstance> historicActivityInstances = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceStartTime().asc()
                .list();
        List<String> historicActivityIds = historicActivityInstances.stream()
                .map(HistoricActivityInstance::getActivityId)
                .toList();
        highLightedActivityIds.addAll(historicActivityIds);

        // 4. 获取高亮连线ID
        List<String> highLightedFlows = getHighLightedFlows(historicActivityInstances, highLightedActivityIds, bpmnModel);

        // 5. 初始化8.x.x版本的流程图生成器（核心适配点）
        ProcessDiagramGenerator diagramGenerator = new DefaultProcessDiagramGenerator();

        // 6. 生成流程图（解决中文乱码 + 高亮节点）
        InputStream inputStream = diagramGenerator.generateDiagram(
                bpmnModel,                 // BPMN 模型
                highLightedActivityIds,    // 高亮节点 ID
                highLightedFlows,          // 高亮连线ID（可传空）
                "Arial",                   // 节点文字字体（解决中文乱码）
                "Arial",                   // 标签字体
                "Arial"                    // 注解字体
        );

        // InputStream inputStream = SvgToPngUtils.convert(inputStream); // 转PNG格式（可选）
        return inputStream;
    }

    /**
     * 通过历史节点执行顺序 + BPMN 模型结构推导连线 ID
     * @param historicActivityInstances 历史活动实例列表
     * @param highLightedActivityIds 高亮节点ID列表
     * @param bpmnModel BPMN模型
     * @return 高亮连线ID列表
     */
    private List<String> getHighLightedFlows(List<HistoricActivityInstance> historicActivityInstances, List<String> highLightedActivityIds, BpmnModel bpmnModel) {
        // 用Set去重，避免重复的连线ID
        Set<String> highLightedFlowSet = new HashSet<>();

        // 方式1. 遍历历史节点（按执行顺序），匹配相邻节点之间的连线
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {
            // 当前节点 ID
            String currentActivityId = historicActivityInstances.get(i).getActivityId();
            // 下一个节点 ID
            String nextActivityId = historicActivityInstances.get(i + 1).getActivityId();

            // 从 BPMN模型中获取当前节点的所有出连线
            FlowNode currentNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(currentActivityId);
            if (currentNode == null) continue; // 节点不存在则跳过

            // 遍历当前节点的出连线，找到指向“下一个节点”的连线
            for (SequenceFlow flow : currentNode.getOutgoingFlows()) {
                if (nextActivityId.equals(flow.getTargetRef())) {
                    highLightedFlowSet.add(flow.getId()); // 匹配到则加入高亮列表
                    break;
                }
            }
        }

        // 方式2：从BPMN模型中根据高亮节点，匹配节点之间的连线（补充方式）
        // 遍历所有流程节点，找到高亮节点之间的连线
        for (String activityId : bpmnModel.getProcesses().getFirst().getFlowElements().stream()
                .filter(flowElement -> flowElement instanceof FlowNode)
                .map(flowElement -> ((FlowNode) flowElement).getId())
                .toList()) {
            if (highLightedActivityIds.contains(activityId)) {
                FlowNode flowNode = (FlowNode) bpmnModel.getProcesses().getFirst().getFlowElement(activityId);
                // 获取该节点的出连线 ID
                List<SequenceFlow> outgoingFlows = flowNode.getOutgoingFlows();
                for (SequenceFlow flow : outgoingFlows) {
                    // 若连线的目标节点也是高亮节点，则加入高亮列表
                    if (highLightedActivityIds.contains(flow.getTargetRef())) {
                        highLightedFlowSet.add(flow.getId());
                    }
                }
            }
        }

        // 转成List 返回
        return new ArrayList<>(highLightedFlowSet);
    }

}
