package com.example.load;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义类加载器
 *
 * @author 游家纨绔
 * @date 2024/02/22 17:54
 **/
public class MyClassLoader extends URLClassLoader {

    // URLClassLoader 是一种特殊的类加载器，可以从指定的 URL 中加载类和资源。
    // 它的主要作用是动态加载外部的 JAR 包或者类文件，从而实现动态扩展应用程序的功能。
    // 为了便于管理动态加载的jar包，自定义类加载器继承URLClassloader。


    // 定义一个map保存已加载的类信息。key为这个类的ClassName，value为这个类的类信息。
    private Map<String, Class<?>> loadedClasses = new ConcurrentHashMap<>();

    public Map<String, Class<?>> getLoadedClasses() {
        return loadedClasses;
    }

    public MyClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    // 重写findClass方法，当类加载器加载类时，会调用该方法
    @Override
    protected Class<?> findClass(String name) {
        // 从已加载的类集合中获取指定名称的类
        Class<?> clazz = loadedClasses.get(name);
        if (clazz != null) {
            return clazz;
        }
        try {
            // 调用父类的findClass方法加载指定名称的类
            clazz = super.findClass(name);
            // 将加载的类添加到已加载的类集合中
            loadedClasses.put(name, clazz);
            return clazz;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 定义了类加载器的卸载方法：将已加载的类的集合中移除该类。
    public void unload() {
        try {
            for (Map.Entry<String, Class<?>> entry : loadedClasses.entrySet()) {
                // 从已加载的类集合中移除该类
                String className = entry.getKey();
                loadedClasses.remove(className);
                try {
                    // 由于此类可能使用系统资源或调用线程，为了避免资源未回收引起的内存溢出，通过反射调用这个类中的destroy方法，回收资源。
                    Class<?> clazz = entry.getValue();
                    Method destory = clazz.getDeclaredMethod("destory");
                    destory.invoke(clazz);
                } catch (Exception e) {
                    // 表明该类没有destory方法
                    System.out.println("该类没有destory方法");
                }
            }
            // 从其父类加载器的加载器层次结构中移除该类加载器
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}