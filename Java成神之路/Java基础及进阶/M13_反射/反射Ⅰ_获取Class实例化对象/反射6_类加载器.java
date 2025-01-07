package M13_反射.反射Ⅰ_获取Class实例化对象;

/*
  关于JDK自带的类加载器：
       1、什么是类加载器？
             专门负责加载类的命令/工具。ClassLoader

       2、Java中有4种类加载器：
             Bootstrap ClassLoader（启动类加载器）：
                 负责加载存放在 JAVA_HOME/jre/lib/rt.jar 或者扩展模块路径（如JAVA_HOME/jre/lib/modules）中的核心类库。
                 Bootstrap ClassLoader 没有父类加载器，并且它是用C++编写的，是JVM的一部分。
                 它加载的类被认为是可信的，不需要进行签名验证。
             Extension ClassLoader（扩展类加载器）：
                 扩展类加载器通常会加载 JAVA_HOME/jre/lib/ext 目录中的类库，或者是由 java.ext.dirs 系统属性指定的路径中的类库。
                 它是由Java编写的，可以从 sun.misc.Launcher$ExtClassLoader 类中找到其实现。
                 扩展类加载器的父类加载器是 Bootstrap ClassLoader。
             System ClassLoader（应用类加载器/系统类加载器）：
                 负责加载用户类路径（ClassPath）所指定的类。
                 它可以从 sun.misc.Launcher$AppClassLoader 类中找到其实现。
                 System ClassLoader 的父类加载器是 Extension ClassLoader。
                 用户可以通过 ClassLoader.getSystemClassLoader() 方法获取到这个类加载器的实例。
             自定义类加载器。

          双亲（parent）委托机制
          指的是 Java 类加载器在加载一个类时，会首先将加载请求委托给它的父类加载器去处理，只有在父类加载器无法完成加载请求时，
          子类加载器才会尝试自己加载。这个机制的主要目的是避免类的重复加载和保证 Java 核心类库的正确性。
          所以加载顺序一般是:
          BootstrapClassLoader -> ExtensionClassLoader -> AppClassLoader -> 自定义类加载器（默认）

          一般在加载器的构造函数中可以指定父加载器：
             没指定父加载器则默认为AppClassLoader ，
             指定父加载器为null，父加载器则为BootstrapClassLoader。

       3、假设有这样一段代码
           String s = "abc";

           代码在开始执行之前，会将所需要类全部加载到JVM当中。
           通过类加载器加载，看到以上代码类加载器会找到 String.class 文件
           找到就加载，那么是怎么进行加载的呢？

               首先通过“启动类加载器”加载
                   注意：启动类记载器专门加载：C:\\Program Files\Java\jdk.8.0_101\jre\lib\rt.jar
                   rt.jar中都是JDK最核心的类库

               如果通过“启动类加载器”加载不到的时候。会通过“扩展类加载器”加载
                   注意：扩展加载器专门加载：C:\\Program Files\Java\jdk.8.0_101\jre\lib\ext\*.jar

               如果“扩展类加载器”没有加载到，那么会通过“应用加载器专门加载”
                   注意：应用类加载器专门加载：classpath中的类

       4、Java中为了保证类加载的安全，使用了双亲委派机制。
              优先从启动类加载器中加载，这个称为“父”
              “父”无法加载到，再从扩展类加载器中加载，这个称为“母”。
              双亲委派如果都加载不到，才会考虑从应用加载器中加载，直到加载到为止。

       5、双亲委派机制的优点
          避免类的重复加载：通过这种委派机制，可以避免同一个类被多个不同的加载器加载，从而避免了类的冲突。
          增强安全性：核心类库的类（如 java.lang.String）总是由引导类加载器加载，确保这些类的行为不会被应用程序级的类加载器所影响。
          保证类的一致性：相同的类由同一个类加载器加载，可以确保类的一致性，避免由于类定义不同而产生的问题。
 */
public class 反射6_类加载器 {
}
