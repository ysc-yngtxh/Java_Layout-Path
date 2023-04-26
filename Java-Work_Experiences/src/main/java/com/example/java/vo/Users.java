package com.example.java.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/11/30 17:43
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private String idVO;
    private String nameVO;
    private String emailVO;
    private String addressVO;
    private String dateVO;
}
