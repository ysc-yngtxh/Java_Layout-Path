package org.example;

import com.example.pojo.Order;
import org.apache.ibatis.session.SqlSession;
import com.example.mapper.OrderMapper;
import com.example.mapper.RoleMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.Role;
import com.example.pojo.User;
import com.example.utils.MyBatisUtils;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /** 一对一查询模型
     *     用户表和订单表的关系为，一个用户有多个订单（一对多），一个订单只从属于一个用户（一对一）
     *     一对一查询的需求：查询一个订单，与此同时查询出该订单所属的用户
     */
    @Test
    public void oneToOne() {
        // 获取SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        // 执行SQL语句，使用SqlSession类的方法
        Order order = mapper.OrderWithUser(1);
        System.out.println(order.toString());
        // 关闭
        sqlSession.close();
    }

    /** 一对多查询模型
     *     用户表和订单表的关系为，一个用户有多个订单，一个订单只从属于一个用户
     *     一对多查询的需求：查询一个用户，与此同时查询出该用户具有的订单
     */
    @Test
    public void oneToMany() {
        // 获取SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 执行SQL语句，使用SqlSession类的方法
        // 查询用户id=41的用户以及拥有的订单
        User user = mapper.UserWithOrders(41);
        System.out.println(user.toString());
        // 关闭
        sqlSession.close();
    }

    /** 多对多查询的模型
     *     用户表和角色表的关系为，一个用户有多个角色，一个角色被多个用户使用
     *     多对多查询的需求：查询用户同时查询出该用户的所有角色
     *     在mybatis中多对多实现，跟一对多步骤是一样，区别就在于sql语句
     */
    @Test
    public void manyToMany() {
        // 获取SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        // 执行SQL语句，使用SqlSession类的方法
        // 查询角色id=1的角色以及对应的用户
        Role role = mapper.RoleWithUsers(1);
        System.out.println(role.toString());
        // 关闭
        sqlSession.close();
    }

    /* 总结：
     *     1. 一对一配置：使用<resultMap> + <association>做配置
     *                  association：
     *                  property：关联的实体类属性名
     *                  javaType：关联的实体类型（别名）
     *     2. 一对多配置：使用<resultMap> + <collection>做配置
     *                  collection：
     *                  property：关联的集合属性名
     *                  ofType：关联的集合元素类型（别名）
     *     3. 多对多的配置跟一对多很相似，难度在于SQL语句的编写。
     */
}
