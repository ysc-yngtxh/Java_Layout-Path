package com.example;

import com.baomidou.mybatisplus.core.toolkit.AES;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpandInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpandInterfaceApplication.class, args);
    }

    /*
    1. 什么是类的加载过程
        一个Java文件从编码完成到最终运行，一般会经历两个阶段：编译期、运行期。
        编译，即通过javac命令，将Java文件转化为二进制字节码文件，即.class文件；
        运行，则是将.class文件交给JVM执行。
        而类加载过程就是将.class文件中类的元信息加载进内存，创建Class对象并进行解析、初始化类变量等的过程
        JVM并不是一开始就会将所有的类加载到内存，而是用到某个类，才会去加载，只加载一次，后续会说到类的加载时机

    2. 类加载详解
            类加载分为三个部分：加载、连接、初始化

      1)、加载
            类的加载主要的职责为将.class文件的二进制字节流读入内存(JDK1.7及之前为JVM内存，JDK1.8及之后为本地内存)，
            并在堆内存中为之创建Class对象，作为.class进入内存后的数据的访问入口。在这里只是读入二进制字节流，
            后续的验证阶段就是要拿二进制字节流来验证.class文件，验证通过，才会将.class文件转为运行时数据结构

      2)、连接
            类的连接分为三个阶段：验证、准备、解析。
            验证：该阶段主要是为了保证加载进来的字节流符合JVM的规范，不会对JVM有安全性问题。其中有对元数据的验证，
                 例如检查类是否继承了被final修饰的类；还有对符号引用的验证，
                 例如校验符号引用是否可以通过全限定名找到，或者是检查符号引用的权限(private、public)是否符合语法规定等。
            准备：准备阶段的主要任务是为类的类变量开辟空间并赋默认值。
                  [1]、静态变量是基本类型（int、long、short、char、byte、boolean、float、double）的默认值为0
                  [2]、静态变量是引用类型的，默认值为null
                  [3]、静态常量默认值为声明时设定的值
                  例如：public static final int i = 3; 在准备阶段，i的值即为3
            解析：该阶段的主要职责为将Class在常量池中的符号引用转变为直接引用，此处针对的是静态方法及属性和私有方法与属性，
                 因为这类方法与私有方法不能被重写，静态属性在运行期也没有多态这一说，即在编译器可知，运行期不可变，
                 所以适合在该阶段解析，譬如类方法main替换为直接引用，为静态连接，区别于运行时的动态连接。
                 符号引用即字符串，说白了可以是一个字段名，或者一个方法名；直接引用即偏移量，说白了就是类的元信息位于内存的地址串，
                 例如，一个类的方法为test()，则符号引用即为test，这个方法存在于内存中的地址假设为0x123456，则这个地址则为直接引用。

      3)、初始化
            该阶段主要是为类的类变量初始化值的，初始化有两种方式：
            [1]、在声明类变量时，直接给变量赋值
            [2]、在静态初始化块为类变量赋值

    3. 类的加载时机(包括加载、连接、初始化)
        1、创建该类的实例
        2、调用该类的类方法
        3、访问类或接口的类变量，或为类变量赋值
        4、利用反射Class.forName(String name, boolean initialize,ClassLoader loader);
           当使用ClassLoader类的loadClass()方法来加载类时,该类只进行加载阶段，而不会经历初始化阶段，使用Class类的静态方法forName()，根据initialize来决定会不会初始化该类，不传该参数默认强制初始化
        5、初始化该类的子类
        6、运行main方法，main方法所在类会被加载
     */
}
