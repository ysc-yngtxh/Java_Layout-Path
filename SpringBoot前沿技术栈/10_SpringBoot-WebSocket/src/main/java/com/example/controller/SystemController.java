package com.example.controller;

import com.example.websocket.WebSocketServer;
import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/api/socket")
public class SystemController {

    @Resource
    private WebSocketServer webSocket;

    // 推送数据接口
    @ResponseBody
    @RequestMapping("/push/{cid}")
    public Map<String, Object> pushToWeb(@PathVariable String cid
                                       , @PathParam("message") String message) {
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

    @RequestMapping("/toJinDuTiao")
    public String toJinDuTiao(){
        return "jinDuTiao";
    }

    @RequestMapping("/jinDuTiao")
    public void test() throws InterruptedException, IOException {
        String msg = "";
        int a = 0;
        for (int i = 0; i <= 100; i++) {
            msg = String.valueOf(a);
            TimeUnit.SECONDS.sleep(1);
            webSocket.sendMessage(msg);
            a = a+1;
        }
        webSocket.onClose();
    }
}
