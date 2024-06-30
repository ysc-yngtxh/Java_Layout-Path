package com.example.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-07 下午7:54
 * @apiNote TODO
 */
public class LocalRegister {

    public static Map<String, Class<?>> map = new HashMap<>();

    public static void register(String interfaceName, String version, Class<?> implClass) {
        // 将接口名和实现类进行注册
        map.put(interfaceName + version, implClass);
    }

    public static Class<?> get(String interfaceName, String version) {
        // 根据接口名获取对应的实现类
        return map.get(interfaceName + version);
    }
}
