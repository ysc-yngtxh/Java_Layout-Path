package com.example.component;

import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-10 18:00
 * @apiNote TODO
 */
@Component("mySpELBean")
public class MySpELBean {

    public boolean hasAuthorization(String str) {
        return Pattern.matches("^\\d+$", str);
    }
}
