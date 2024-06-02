package com.example.mapper;

import com.example.annotation.Entity;
import com.example.annotation.Select;
import com.example.domain.User;

@Entity(User.class)
public interface UserMapper {

    @Select("select * from t_user where id = ?")
    public User selectOne(Object id);
}
