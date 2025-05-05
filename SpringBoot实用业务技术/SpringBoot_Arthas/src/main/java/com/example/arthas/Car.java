package com.example.arthas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 游家纨绔
 * @dateTime 2023-09-17 22:01
 * @apiNote TODO
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private String name;

    private long price;
}
