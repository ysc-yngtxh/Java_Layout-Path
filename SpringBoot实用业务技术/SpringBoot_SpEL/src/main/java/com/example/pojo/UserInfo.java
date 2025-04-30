package com.example.pojo;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-10 09:09
 * @apiNote TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private Long id;

    private String sex;

    private Integer age;

    private Float height;

    private LocalDateTime birthday;
}
