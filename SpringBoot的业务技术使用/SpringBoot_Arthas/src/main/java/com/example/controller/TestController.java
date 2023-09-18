package com.example.controller;

import com.example.Car;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 * @dateTime 2023-09-18 08:32
 * @apiNote TODO
 */
@Controller
public class TestController {

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(@RequestBody Car car) {
        System.out.println(car.toString());
        return car.toString();
    }
}
