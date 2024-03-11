package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
