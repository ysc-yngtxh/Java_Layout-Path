package com.example.mybatis3.factory;

import com.example.mybatis3.annotation.Param;
import com.example.mybatis3.annotation.Select;
import com.example.mybatis3.convert.IntegerTypeHandler;
import com.example.mybatis3.convert.StringTypeHandler;
import com.example.mybatis3.convert.TypeHandler;
import com.example.mybatis3.parser.GenericTokenParser;
import com.example.mybatis3.parser.ParameterMapping;
import com.example.mybatis3.parser.ParameterMappingTokenHandler;
import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-05 00:00
 * @apiNote TODO Mapper工厂
 */
public class MapperProxyFactory3 {

    private static Map<Class<?>, TypeHandler> typeHandlerMap = new HashMap<>();

    static {
        typeHandlerMap.put(String.class, new StringTypeHandler());
        typeHandlerMap.put(Integer.class, new IntegerTypeHandler());

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

    public static <T> T getMapper(Class<T> mapperInterface){
        // JDK的动态代理
        Object proxyInstance = Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader()
                , new Class[]{mapperInterface}
                , (Object proxy, Method method, Object[] args) -> {
                    // JDBC获取连接
                    Connection conn = getConnection();

                    // 获取代理方法的@Select注解的Value值SQL语句
                    Select annotationSelect = method.getAnnotation(Select.class);
                    String sql = annotationSelect.value();

                    // 解析参数，并将参数名与参数值包装成一个Map：{[name,xxx], [age,xxx]}
                    HashMap<String, Object> paramValueMapping = new HashMap<>();
                    Parameter[] parameters = method.getParameters();
                    for (int i = 0; i < parameters.length; i++) {
                        Param annotationParam = parameters[i].getAnnotation(Param.class);
                        String parameter = annotationParam.value();
                        // 添加参数对应值：name、age...
                        paramValueMapping.put(parameter, args[i]);
                        // 添加通过反射获取的参数对应值：arg0、arg1...
                        paramValueMapping.put(parameters[i].getName(), args[i]);
                    }

                    // 解析Sql：将 #{} 解析为 ？占位符。这里的解析过程参考了 MyBatis 提供的解析器，自己做了些许改动
                    ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
                    GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", tokenHandler);
                    String parseSql = genericTokenParser.parse(sql);
                    // 获取所有的占位符的参数名
                    List<ParameterMapping> parameterKeyMappings = tokenHandler.getParameterMappings();

                    // JDBC预编译Sql语句
                    PreparedStatement ps = conn.prepareStatement(parseSql);
                    for (int i = 0; i < parameterKeyMappings.size(); i++) {
                        // 遍历得到占位符的参数名
                        String property = parameterKeyMappings.get(i).getProperty();
                        // 通过占位符的参数名得到对应的参数值
                        Object value = paramValueMapping.get(property);
                        // 通过反射得到参数值的数据类型
                        Class<?> aClass = value.getClass();
                        // 这里使用了策略模式，根据参数值的类型来动态选择参数注入到Sql时的数据状态以及使用的方法
                        typeHandlerMap.get(aClass).setParameter(ps, i+1, value);
                    }

                    // JDBC中的SQl执行
                    ps.execute();
                    ResultSet resultSet = ps.getResultSet();

                    // 定义当前方法的返回值类型
                    Object result = null;

                    // JDBC处理结果集
                    Class resultType = null;
                    Type genericReturnType = method.getGenericReturnType();
                    if (genericReturnType instanceof Class) {
                        // 不是泛型
                        resultType = (Class) genericReturnType;
                    } else if (genericReturnType instanceof ParameterizedType) {
                        // 是泛型
                        Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
                        resultType = (Class) actualTypeArguments[0];
                    }

                    ResultSetMetaData metaData = resultSet.getMetaData();
                    List<String> columnList = new ArrayList<>();
                    // 获取Sql语句中返回的查询字段名，并将所有的字段包装到List集合中
                    for (int i = 0; i < metaData.getColumnCount(); i++) {
                        columnList.add(metaData.getColumnName(i + 1));
                    }

                    // 获取查询数据对应的实体类的setter方法，并包装到Map中：[name, setName]
                    Map<String, Method> setterMethodMapping = new HashMap<>();
                    for (Method declaredMethod : resultType.getDeclaredMethods()) {
                        if (declaredMethod.getName().startsWith("set")) {
                            String propertyName = declaredMethod.getName().substring(3);
                            propertyName = propertyName.substring(0, 1).toLowerCase(Locale.ROOT) + propertyName.substring(1);
                            setterMethodMapping.put(propertyName, declaredMethod);
                        }
                    }

                    // 将从数据库中的查询数据包装到对象实例，通过setter方法将数据封装
                    // TODO 这里在调用setter方法时，进行准确的数据类型替换，而非resultSet.getObject(column)
                    List<Object> list = new ArrayList<>();
                    while (resultSet.next()) {
                        Object newInstance = resultType.getDeclaredConstructor().newInstance();
                        for (int i = 0; i < columnList.size(); i++) {
                            String column = columnList.get(i);
                            Method setterMethod = setterMethodMapping.get(column);
                            // 获取setter方法中的参数类型：setter方法只有一个参数
                            Class clazz = setterMethod.getParameterTypes()[0];
                            // 通过setter方法的参数类型，动态选择类型处理器
                            TypeHandler typeHandler = typeHandlerMap.get(clazz);
                            setterMethod.invoke(newInstance, typeHandler.getResult(resultSet, column));
                        }
                        list.add(newInstance);
                    }

                    // 判断返回的数据类型是否为List，还是实例对象
                    if (method.getReturnType().equals(List.class)) {
                        result = list;
                    } else {
                        result = list.get(0);
                    }

                    // JDBC关闭连接
                    conn.close();
                    return result;
                });
        return (T) proxyInstance;
    }
}
