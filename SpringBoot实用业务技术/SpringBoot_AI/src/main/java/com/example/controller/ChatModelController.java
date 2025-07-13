package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.DeepSeekChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-07-06 15:50:00
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")  // 允许跨域访问
public class ChatModelController {

	@Autowired
	private ChatModel chatModel;

	@GetMapping(value = "/ai03", produces = "text/html;charset=utf-8")
	public String generation03(@RequestParam(value = "message", defaultValue = "给我讲个笑话") String message) {
		ChatResponse response = chatModel.call(
				new Prompt(
						message, // new UserMessage(message)
						DeepSeekChatOptions.builder()
						                   .model("gpt-3.5-turbo")
						                   .temperature(0.4) // 让生成文字更有温度
						                   .build()
				)
		);
		// 等同于 .content
		return response.getResult().getOutput().getText();
	}


	// @GetMapping(value = "/ai05")
	// public String generation05(@RequestParam(value = "message", defaultValue = "画个龙") String message) {
	// 	ImageResponse response = DeepSeekImageModel.call(
	// 			new ImagePrompt(message, // 图片提示词
	// 			                DeepseekImageOptions.builder()
	// 			                                  .quality("hd") // 图片质量
	// 			                                  .withModel(OpenAiImageApi.DEFAULT_IMAGE_MODEL) // 图片模型
	// 			                                  .N(1) // 生成图片数量
	// 			                                  .height(1024)
	// 			                                  .width(1024).build())
	//
	// 	                                              );
	// 	return response.getResult().getOutput().getUrl();
	// }


}
