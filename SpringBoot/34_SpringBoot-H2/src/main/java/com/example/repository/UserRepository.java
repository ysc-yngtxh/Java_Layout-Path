package com.example.repository;

import com.example.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
   
    // 根据用户名查询用户
    User findByUserName(String userName);

    // 根据邮箱查询用户
    User findByEmail(String email);
}
