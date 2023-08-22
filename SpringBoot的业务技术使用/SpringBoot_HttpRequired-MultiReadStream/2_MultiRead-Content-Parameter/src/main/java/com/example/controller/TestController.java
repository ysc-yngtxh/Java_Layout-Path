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
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;

/**
 * @author youshicheng
 * @dateTime 2023-04-29 15:46
 * @apiNote TODO 测试应用层
 */
@Controller
public class TestController {
    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    public String TestHttpRequired(@RequestParam(value = "userName") String userName,
                                   @RequestParam(value = "passWord") String passWord,
                                   Model model,
                                   ServletRequest request) throws IOException {
        model.addAttribute("userName", userName);
        model.addAttribute("passWord", passWord);
        ContentCachingRequestWrapper req = (ContentCachingRequestWrapper) request;
        String user = new String(req.getContentAsByteArray());
        System.out.println("接口/test1参数"+user);
        return "test1";
    }
}
