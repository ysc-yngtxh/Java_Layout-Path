package com.example.websocket;

import com.alibaba.fastjson2.JSONObject;
import com.example.controller.SystemController;
import com.example.service.SocketService;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.LongAdder;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个WebSocket服务器端。
 * 注解的值将被用于监听用户连接的终端访问URL地址，客户端可以通过这个URL来连接到WebSocket服务器端
 */
@Slf4j
@Component
@ServerEndpoint("/api/websocket/{sid}")
public class WebSocketServer {

	// 静态变量，用来记录当前在线连接数。使用 LongAdder 类型会更高效并且具有原子性。
	private static LongAdder onlineCount = new LongAdder();

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	@Getter
	private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	// 接收sid
	private String sid = "";

	/**
	 * 问题点：WebSocket 启动的时候优先于Spring容器，从而导致在 WebSocketServer 中调用业务Service会报空指针异常
	 *        即在引用 @Autowired 注解下的对象时会报空指针
	 * 方法一：新增一个 ApplicationContext 类型的属性，再提供一个静态的修改属性值的方法。
	 *        最后再在启动类上获取容器对象，将容器对象赋值给 WebSocketServer
	 *        从而 SocketService devService = context.getBean(SocketService.class); 获得引用
	 * <p>
	 * 方法二：提供一个工具类去实现 ApplicationContextAware 接口，并且加上一个懒加载注解 @Lazy,
	 *        重写其 setApplicationContext 方法，赋值给私有属性，而且提供一个静态的属性 get() 方法
	 *        从而 SocketService devService = ApplicationContextRegister.getApplicationContext().getBean(SocketService.class); 获得引用
	 */
	@Autowired
	private SocketService devService;
	private static ApplicationContext context;

	public static void setApplicationContext(ApplicationContext applicationContext) {
		context = applicationContext;
	}

	/**
	 * 连接成功回调的方法
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("sid") String sid) {
		this.session = session;
		this.sid = sid;
		onlineCount.add(1);     // 在线数原子性的加1
		webSocketSet.add(this); // 加入set中
		try {
			this.session.getBasicRemote().sendText("CONN_SUCCESS");
			log.info("有新窗口开始监听：{}，当前在线人数为：{}", sid, onlineCount.longValue());
		} catch (IOException e) {
			log.error("websocket IO Exception");
		}
	}

	/**
	 * 连接关闭成功回调的方法
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this); // 从set中删除
		onlineCount.add(-1);          // 在线数原子性的减1
		// 断开连接情况下，更新主板占用情况为释放
		log.info("释放的sid为：{}", sid);
		// 这里写你 释放的时候，要处理的业务
		log.info("有一连接关闭！当前在线人数为{}", onlineCount.longValue());

	}

	/**
	 * 收到客户端消息后回调的方法
	 */
	@OnMessage
	@SneakyThrows
	public void onMessage(String message, Session session) {
		log.info("收到来自窗口{}的信息：{}", sid, message);
		// 监听进度条任务是否取消的信号
		JSONObject jsonObject = JSONObject.parseObject(message);
		String signal = (String) jsonObject.get("action");
		if ("cancel".equals(signal)) {
			log.info("进度条任务取消");
			// 通过线程池取消任务
			SystemController.progressFuture.cancel(true);
		} else if ("pause".equals(signal)) {
			SystemController.taskState = SystemController.TaskState.PAUSED;
		} else if ("continue".equals(signal)) {
			SystemController.taskState = SystemController.TaskState.RUNNING;
		} else if ("stop".equals(signal)) {
			SystemController.taskState = SystemController.TaskState.STOPPED;
		}
		// 群发消息【此处定义逻辑为通知自己，可以修改为通知全体】
		for (WebSocketServer item : webSocketSet) {
			try {
				if (item.sid.equals(this.sid)) {
					item.sendMessage(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 错误时回调的方法
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		log.error("发生错误");
		error.printStackTrace();
	}

	/**
	 * 实现服务器主动推送
	 */
	public void sendMessage(String message) throws IOException {
		for (WebSocketServer webSocket : webSocketSet) {
			log.info("【websocket消息】广播消息, message={}", message);
			try {
				webSocket.session.getBasicRemote().sendText(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 群发自定义消息
	 */
	public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
		log.info("【推送消息】到窗口{}，推送内容：{}", sid, message);
		for (WebSocketServer serverItem : webSocketSet) {
			try {
				// 这里可以设定只推送给这个sid的，为null则全部推送
				if (sid == null) {
					// serverItem.sendMessage(message);
				} else if (serverItem.sid.equals(sid)) {
					serverItem.sendMessage(message);
				}
			} catch (IOException e) {
				continue;
			}
		}
	}

}
