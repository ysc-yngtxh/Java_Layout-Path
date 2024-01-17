package com.example.controller;

import com.example.event.PublishEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-17 12:03
 * @apiNote TODO
 */
@Controller
public class EventController {
    @Autowired
    private PublishEvent publishEvent;

    @RequestMapping("/pub")
    public void pub() {
        for (int i = 0; i < 5; i++) {
            publishEvent.publish("你若为我繁华，你好呀：" + (i + 1));
        }
    }

}
