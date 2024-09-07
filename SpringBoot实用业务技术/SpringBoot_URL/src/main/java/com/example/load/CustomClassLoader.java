package com.example.load;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CustomClassLoader extends ClassLoader {

    private String classPath;

    public CustomClassLoader(String classPath) {
        this.classPath = classPath;
    }

    // 重写loaderClass方法
    // 如果要想在JVM的不同类加载器中保留具有相同全限定名的类，那就要通过重写loadClass来实现，
    // 此时首先是通过用户自定义的类加载器来判断该类是否可加载，
    // 如果可以加载就由自定义的类加载器进行加载，如果不能够加载才交给父类加载器去加载。

    // loadClass：用于加载类的方法，它在类加载器层次结构中是负责委托给父类加载器加载类的。
    //            通常情况下，不需要重写loadClass方法，而是重写findClass方法。
    // findClass：这个方法会尝试加载指定名称的类，如果失败会调用父类加载器的loadClass方法。
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        }
        return defineClass(name, classData, 0, classData.length);
    }

    private byte[] loadClassData(String className) {
        String fileName = classPath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try (FileInputStream fis = new FileInputStream(fileName);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}