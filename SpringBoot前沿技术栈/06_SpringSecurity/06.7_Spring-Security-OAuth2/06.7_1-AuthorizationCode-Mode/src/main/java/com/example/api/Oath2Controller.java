package com.example.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2024-11-03 09:01
 * @apiNote TODO
 */
@RestController
public class Oath2Controller {

    @RequestMapping("/oauth2/callback")
    public String Oath2AuthorizationCallback() {
        return "Callback Success!!!";
    }
}
