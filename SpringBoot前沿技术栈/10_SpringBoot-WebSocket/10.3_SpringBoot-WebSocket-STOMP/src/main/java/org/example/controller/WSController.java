package org.example.controller;

import org.example.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-07-14 14:40:00
 */
@Controller
public class WSController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	/*
	 * @MessageMapping 注解是用来处理客户端发送的消息的，有点类似 @RequestMapping。
	 *     当客户端发送一个消息到指定的路径时，Spring会将这个消息路由到对应的处理方法。
	 * @SendTo 注解是用来指定消息处理方法的返回值应该发送到哪个路径。
	 *     当处理方法返回一个值时，这个值会被发送到所有订阅了指定路径的客户端。
	 * @SendToUser 注解，则返回的消息会发送给用户端一对一通信的，而不是所有订阅者。
	 *
	 * 在当前例子中，@MessageMapping("/hello") 表示当客户端发送一个消息到 /app/hello 路径时，
	 * 这个方法会被调用，并且返回的消息会被发送到所有订阅了 /topic/hello 路径的客户端。
	 *
	 * 注意：这里的路径是相对于 WebSocket 的应用前缀（在 WebSocketConfig 中配置的前缀）而言的。
	 *      如在 WebSocketConfig 类中配置了 config.setApplicationDestinationPrefixes("/app")，
	 *      那么客户端发送到 /app/hello 的消息会被路由到这个方法。
	 */
	@MessageMapping("/hello")
	@SendTo("/topic/hello")
	public Message hello(Message message) {
		System.out.println("接收消息：" + message);
		return new Message("服务端接收到你发的：" + message, "服务端");
	}

	// @MessageMapping 和 @SendTo 注解一般是应答时响应的，
	// 如果需要服务端主动发送消息可以通过 simpMessagingTemplate类 的 convertAndSend() 方法。
	// 注意 convertAndSendToUser(token, "/msg", msg) 配置的 config.setUserDestinationPrefix("/user/")，
	// 这里客户端订阅的是 /user/{token}/msg，千万不要搞错。
	@GetMapping("/sendMsgByUser")
	public @ResponseBody Object sendMsgByUser(String token, String msg) {
		simpMessagingTemplate.convertAndSendToUser(token, "/msg", msg);
		return "success";
	}

	@GetMapping("/sendMsgByAll")
	public @ResponseBody Object sendMsgByAll(String msg) {
		// 这里客户端订阅的是 /user/topic
		simpMessagingTemplate.convertAndSend("/topic", msg);
		return "success";
	}

	@GetMapping("/test")
	public String test() {
		return "test-stomp.html";
	}

	// Session 共享的问题
	// 上面反复提到一个问题就是，服务端如果要主动发送消息给客户端一定要用到 session。而 session 这个东西是不跨 jvm 的。
	// 如果有多台服务器，在 http 请求的情况下，我们可以通过把 session 放入缓存中间件中来共享解决这个问题，通过 spring session 几条配置就解决了。
	// 但是 web socket  不可以。他的 session 是不能序列化的，当然这样设计的目的不是为了为难你，而是出于对 http 与 web socket 请求的差异导致的。
	//
	// 目前网上找到的最简单方案就是通过 redis 订阅广播的形式，主要代码跟第二种方式差不多，你要在本地放个 map 保存请求的 session。
	// 也就是说每台服务器都会保存与他连接的 session 于本地。
	// 然后发消息的地方要修改，并不是现在这样直接发送，而通过 redis 的订阅机制。
	// 服务器要发消息的时候，你通过 redis 广播这条消息，所有订阅的服务端都会收到这个消息，然后本地尝试发送。
	// 最后肯定只有有这个对应用户 session 的那台才能发送出去。
}
