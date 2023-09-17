package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 游家纨绔
 * @dateTime 2023-09-17 22:01
 * @apiNote TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private String name;
    private long price;
}
