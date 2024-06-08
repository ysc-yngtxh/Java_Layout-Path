package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-08 上午10:47
 * @apiNote TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class URL implements Serializable {
    @Serial
    private static final long serialVersionUID = 3951293658407109323L;
    private String hostName;
    private Integer port;
}
