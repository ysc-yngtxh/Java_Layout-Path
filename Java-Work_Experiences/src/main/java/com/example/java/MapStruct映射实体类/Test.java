package com.example.java.MapStruct映射实体类;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.example.java.vo.ModelView;
import com.example.java.vo.Models;
import com.example.java.vo.User;
import com.example.java.vo.Users;

import java.util.Date;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/11/30 17:48
 */
public class Test {
    public static final Log log = LogFactory.get(Test.class);

    public static void main(String[] args) {
        User user = User.builder().id(123L).name("小娘皮叶诗琪").email("google@163.com").date(new Date()).build();
        Users users = MapStructs.mapStr.toUsers(user);
        log.error(users.toString());

        User user1 = User.builder().id(123L).name("小娘皮叶诗琪").email("google@163.com").build();
        ModelView modelView = ModelView.builder().address("珞狮路狮城名居").build();
        Users users1 = MapStructs.mapStr.toUsers1(user1, modelView);
        log.error(users1.toString());

        User user2 = User.builder()
                .id(123L)
                .name("小娘皮叶诗琪")
                .email("google@163.com")
                .models(new Models(new ModelView("湖北武汉红花三区"), null))
                .build();
        Users users2 = MapStructs.mapStr.toUsers2(user2);
        log.error(users2.toString());
    }

}
