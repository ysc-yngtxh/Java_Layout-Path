package com.example.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * (Consumer)表实体类
 *
 * @author 游家纨绔
 * @since 2023-08-28 22:28:21
 */
@Getter
public class Consumer extends Model<Consumer> {

    @Serial
    private static final long serialVersionUID = -5855679260765705905L;

    private Integer id;

    private String username;

    private String password;

    private String alias;

    private Integer age;

    private String sex;
    
    private String phone;

    private String address;

    private Integer deleteFlag;


    /**
     * 获取主键值
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}

