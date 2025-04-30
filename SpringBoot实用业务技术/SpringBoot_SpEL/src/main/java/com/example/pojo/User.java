package com.example.pojo;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-07 22:24
 * @apiNote TODO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private String country;

    private String[] account;

    private Integer[] money;

    private String email;

    private List<String> address;

    private List<Integer> phone;

    private List<UserInfo> userInfos;

    private List<UserInfo> userInfoList;
}
