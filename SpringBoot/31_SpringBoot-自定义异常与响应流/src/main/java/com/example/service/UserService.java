package com.example.service;

import com.example.advice.UserEnum;
import com.example.advice.exception.CustomException;
import com.example.advice.exception.LyException;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 游家纨绔
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> queryUser1(Integer id) {
        User user = new User();
        user.setId(id);
        // 根据实体类查询，返回一个List类型
        List<User> users = userMapper.select(user);
        if(users.isEmpty()) {
            throw new LyException(UserEnum.NO_URL);
        }
        return users;
    }

    public User queryUser2(Integer id) {
        // selectByPrimaryKey  根据主键查询，返回实体类型
        User users = userMapper.selectByPrimaryKey(id);
        if(users == null) {
            throw new CustomException("不好意思，你查询的用户不存在！");
        }
        return users;
    }

    public List<User> queryUser3(User user) {
        // 过滤
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        // 默认排序
        example.setOrderByClause("age DESC");
        // 查询 selectByExample 能应付大部分的查询条件
        List<User> users = userMapper.selectByExample(example);
        if(users.isEmpty()) {
            throw new LyException(UserEnum.NO_URL);
        }
        return users;
    }

    public void saveUser1(User user) {
        int insert = userMapper.insert(user);
        if(insert != 1) {
            throw new LyException(UserEnum.NO_INSERT);
        }
    }

    public void saveUser2(User user) {
        user.setId(100);
        user.setUserName("游家纨绔");
        // insert是添加所有的字段，insertSelective是只对Selective的字段进行插入
        int i = userMapper.insertSelective(user);
        if(i != 1) {
            // throw new RuntimeException("不好意思，你添加的商品操作失败！");
            throw new LyException(UserEnum.NO_INSERT);
        }
    }
}
