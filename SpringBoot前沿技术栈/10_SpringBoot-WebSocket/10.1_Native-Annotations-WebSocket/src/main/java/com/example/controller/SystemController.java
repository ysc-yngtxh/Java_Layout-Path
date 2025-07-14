package com.example.controller;

import com.example.websocket.WebSocketServer;
import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/socket")
public class SystemController {

	@Resource
	private WebSocketServer webSocket;

	// 推送数据接口
	@ResponseBody
	@RequestMapping("/push/{cid}")
	public Map<String, Object> pushToWeb(@PathVariable String cid,
	                                     @PathParam("message") String message) {
		Map<String, Object> result = new HashMap<>();
		try {
			WebSocketServer.sendInfo(message, cid);
			result.put("code", cid);
			result.put("msg", message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}


	// 进度条【功能：开始任务/取消任务】
	public static ExecutorService executors = Executors.newFixedThreadPool(1);
	public static Future<?> progressFuture;
	@RequestMapping("/progress")
	public ResponseEntity<String> progress() {
		progressFuture = executors.submit(() -> {
			String msg;
			int a = 0;
			for (int i = 0; i <= 100; i++) {
				msg = String.valueOf(a);
				try {
					TimeUnit.MILLISECONDS.sleep(100);
					if (a == 55 || a == 77 || a == 92) {
						TimeUnit.SECONDS.sleep(3);
					}
					webSocket.sendMessage(msg);
				} catch (InterruptedException | IOException e) {
					throw new RuntimeException(e);
				}
				a = a + 1;
			}
		});
		return ResponseEntity.ok("Progress started");
	}


	// 进度条【功能：开始任务/暂停任务/继续任务/取消任务】
	public static volatile TaskState taskState = TaskState.STOPPED;
	public static volatile Thread taskThread;
	@RequestMapping(value = "/progress2", method = RequestMethod.POST)
	public ResponseEntity<String> progress2() {
		if (taskState == TaskState.RUNNING) {
			return ResponseEntity.badRequest().body("任务已在运行中");
		}

		taskState = TaskState.RUNNING;
		taskThread = new Thread(() -> {
			try {
				for (int i = 1; i <= 100 && taskState != TaskState.STOPPED; i++) {
					// 检查暂停状态
					while (taskState == TaskState.PAUSED) {
						TimeUnit.MILLISECONDS.sleep(100); // 暂停时短暂休眠
					}

					// 模拟耗时点
					if (i == 55 || i == 77 || i == 92) {
						TimeUnit.SECONDS.sleep(3);
					} else {
						TimeUnit.MILLISECONDS.sleep(100);
					}

					while (taskState == TaskState.STOPPED) return;

					webSocket.sendMessage(String.valueOf(i));
				}
			} catch (InterruptedException | IOException e) {
				Thread.currentThread().interrupt();
			} finally {
				taskState = TaskState.STOPPED;
			}
		});
		// 启动任务线程
		taskThread.start();
		return ResponseEntity.ok("Progress started");
	}

	public enum TaskState {
		RUNNING,    // 运行中
		PAUSED,     // 已暂停
		STOPPED     // 已停止
	}
}
