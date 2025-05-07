package com.example.v1.factory;

import com.example.v1.annotation.Param;
import com.example.v1.annotation.Select;
import com.example.v1.entity.Student;
import com.example.v1.parser.GenericTokenParser;
import com.example.v1.parser.PlaceholderTokenHandler;
import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-05 00:00
 * @apiNote TODO Mapper工厂
 */
public class MapperProxyFactory1 {

    static {
        try {
            // JDBC注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/springdb?serverTimezone=UTC", "root", "131474");
    }

    public static <T> T getMapper(Class<T> mapperInterface) {
        // JDK的动态代理
        Object proxyInstance = Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(), new Class[]{mapperInterface}
                , (Object proxy, Method method, Object[] args) -> {

                    // JDBC获取连接
                    Connection conn = getConnection();

                    // 获取代理方法的@Select注解的Value值SQL语句
                    Select annotationSelect = method.getAnnotation(Select.class);
                    String sql = annotationSelect.value();

                    // 解析代理对象的方法参数，并将参数名与参数值包装成一个Map：{[name,xxx], [age,xxx]}
                    HashMap<String, Object> paramValueMapping = new HashMap<>();
                    Parameter[] parameters = method.getParameters();
                    for (int i = 0; i < parameters.length; i++) {
                        Param annotationParam = parameters[i].getAnnotation(Param.class);
                        String parameter = annotationParam.value();
                        // 添加 @Param 注解标注的参数对应值：myName、myAge...
                        paramValueMapping.put(parameter, args[i]);
                        // 添加通过反射获取的参数对应值：arg0、arg1...
                        paramValueMapping.put(parameters[i].getName(), args[i]);
                    }

                    // 解析Sql：将 #{} 解析为 ？占位符。这里解析过程参考了 MyBatis 提供的解析器，自己做了些许改动
                    PlaceholderTokenHandler tokenHandler = new PlaceholderTokenHandler();
                    GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", tokenHandler);
                    String parseSql = genericTokenParser.parse(sql);

                    // JDBC预编译Sql语句：将 #{} 替换为 ？，并使用参数值进行查询
                    PreparedStatement ps = conn.prepareStatement(parseSql);
                    ps.setString(1, paramValueMapping.get("name").toString());
                    ps.setInt(2, Integer.parseInt(paramValueMapping.get("age").toString()));

                    // JDBC中的Sql执行
                    ps.execute();
                    ResultSet resultSet = ps.getResultSet();

                    // JDBC解析结果集
                    List<Object> list = new ArrayList<>();
                    while (resultSet.next()) {
                        Student student = new Student();
                        student.setId(resultSet.getInt("id"));
                        student.setName(resultSet.getString("name"));
                        student.setEmail(resultSet.getString("email"));
                        student.setAge(resultSet.getString("age"));
                        list.add(student);
                    }

                    // JDBC关闭连接
                    conn.close();

                    return list;
                });
        return (T) proxyInstance;
    }
}
