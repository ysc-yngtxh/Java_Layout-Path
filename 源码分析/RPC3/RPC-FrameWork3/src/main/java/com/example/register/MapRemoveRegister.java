package com.example.register;

import com.example.common.URL;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-08 上午10:42
 * @apiNote TODO 注册中心
 */
public class MapRemoveRegister {

    // 模拟注册中心，存储服务提供者信息
    public static Map<String, List<URL>> map = new HashMap<>();

    // 注册服务
    public static void register(String interfaceName, URL url) {
        List<URL> list = map.get(interfaceName);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(url);

        map.put(interfaceName, list);
        saveFile();
    }

    // 获取注册的服务
    public static List<URL> get(String interfaceName) {
        map = getFile();
        return map.get(interfaceName);
    }


    // 模拟注册中心在调用服务后的缓存，文件存储路径
    public static String path = System.getProperty("user.dir") + "/RPC3/temp.txt";

    // 正常注册中心比如Nacos，为了能提高接口响应，会在访问过后在本地进行服务缓存。这里就是模拟的保存缓存信息
    @SneakyThrows
    private static void saveFile() {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(map);
    }

    // 正常注册中心比如Nacos，为了能提高接口响应，会在访问过后在本地进行服务缓存。这里就是模拟的获取缓存信息
    @SneakyThrows
    private static Map<String, List<URL>> getFile() {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectOutputStream = new ObjectInputStream(fileInputStream);
        return (Map<String, List<URL>>) objectOutputStream.readObject();
    }
}
