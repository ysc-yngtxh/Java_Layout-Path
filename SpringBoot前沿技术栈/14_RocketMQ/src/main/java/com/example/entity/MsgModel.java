package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 20:47
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
