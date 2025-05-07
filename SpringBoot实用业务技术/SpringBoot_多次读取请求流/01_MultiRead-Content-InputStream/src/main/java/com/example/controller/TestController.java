package com.example.controller;

import com.example.vo.User;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author 游家纨绔
 * @dateTime 2023-04-29 15:46
 * @apiNote TODO 测试应用层
 */
@Controller
public class TestController {

    @RequestMapping(value = "/testGet", method = RequestMethod.GET)
    public String TestHttpRequiredGet(@RequestParam("userName") String userName,
                                      @RequestParam("passWord") String passWord,
                                      Model model) {
        model.addAttribute("userName", userName);
        model.addAttribute("passWord", passWord);
        return "test1";
    }


    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    public String TestHttpRequired(@RequestParam(value = "userName",required = false) String userName,
                                   @RequestParam(value = "passWord",required = false) String passWord,
                                   Model model,
                                   ServletRequest request) throws IOException {
        model.addAttribute("userName", userName);
        model.addAttribute("passWord", passWord);
        HttpServletRequest req = (HttpServletRequest) request;
        String str = new String(StreamUtils.copyToByteArray(req.getInputStream()));
        System.out.println("接口/test1参数userName="+req.getParameter("userName")+",参数流"+str);
        return "test1";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    public String TestHttpRequired2(@RequestParam("userName") String userName,
                                    @RequestParam("passWord") String passWord,
                                    @RequestPart MultipartFile file,
                                    Model model,
                                    ServletRequest request) throws IOException {
        model.addAttribute("passWord", passWord);
        model.addAttribute("userName", userName);
        model.addAttribute("file", new String(file.getBytes(), StandardCharsets.UTF_8));
        HttpServletRequest req = (HttpServletRequest) request;
        String str = new String(StreamUtils.copyToByteArray(request.getInputStream()));
        System.out.println("接口/test2参数userName="+req.getParameter("userName")+",参数流"+str);
        return "test2";
    }

    @RequestMapping(value = "/test3", method = RequestMethod.POST)
    public String TestHttpRequired3(@RequestBody User user, Model model, HttpServletRequest request) throws IOException {
        model.addAttribute("user", user);
        // 数据类型为 application/json 的请求无法通过request.getParameter()获取参数值
        String userName = request.getParameter("userName");
        // 再次获取请求参数流
        String str = new String(StreamUtils.copyToByteArray(request.getInputStream()));
        System.out.println("接口/test3参数userName="+userName+",参数流"+str);
        return "test3";
    }
}
