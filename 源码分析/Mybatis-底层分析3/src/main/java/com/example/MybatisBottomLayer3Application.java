package com.example;

import com.example.domain.User;
import com.example.io.Resources;
import com.example.mapper.UserMapper;
import com.example.session.SqlSession;
import com.example.session.SqlSessionFactory;
import com.example.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisBottomLayer3Application {

    // 引入了 Mybatis-BottomLayer2 项目依赖，所以可使用部分重复代码
    public static void main(String[] args) {
        // 1、读取mybatis配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        // 2、创建了SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        // 3、创建SqlSessionFactory对象
        SqlSessionFactory factory = factoryBuilder.build(in);
        // 4、获取SqlSession对象，从SqlSessionFactory中获取SqlSession
        SqlSession sqlSession = factory.openSession();

        // 获取包含了MapperProxy代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectOne(41);

        System.out.println("第一次查询: " + user);
        System.out.println();
        user = mapper.selectOne(1);
        System.out.println("第二次查询: " + user);
    }

}
