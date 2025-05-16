package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 20:50
 * @apiNote TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsgModel {

    private String orderSn;

    private Integer userId;

    private String desc;
}
