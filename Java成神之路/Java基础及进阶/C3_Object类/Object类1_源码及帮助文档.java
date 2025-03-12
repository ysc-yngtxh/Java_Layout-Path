package C3_Object类;

/*
 * Object类当中有哪些常用的方法？
 * 我们去哪里找这些方法呢？
 *     第一种方法：去源代码当中（但是这种方式比较麻烦，源代码也比较难）
 *     第二种方法：去查阅Java的类库的帮助文档
 *
 * 目前为止我们只需要知道这几个方法即可：
 *      protected Object clone()    // 负责对象克隆
 *      int hashCode()              // 获取对象哈希值的一个方法
 *      boolean equals(Object obj)  // 判断两个对象是否相等
 *      String toString()           // 将对象转换成字符串形式
 *      protected void finalize()   // 垃圾回收器负责调用的方法
 *
 * 1、toString()方法
 *    以后所有类的toString()方法需要重写的。
 *    重写规则，越简单越明了就好·
 *
 *    System.out.println(引用); 这里会自动调用“引用”的toString（）方法。
 *
 *    String类是SUN写的，toString方法已经重写了
 *
 * 2、equals()方法
 *    以后所有类的equals方法也需重写，因为Object中的equals方法比较
 *    的是两个对象的内存地址，我们应该比较内容，所以需要重写
 *
 *    重写规则：主要看是什么和什么相等时表示两个对象相等。
 *
 *    基本数据类型比较实用：==
 *    对象和对象比较：调用equals方法
 *
 *    String类是SUN编写的，所以String类的equals方法重写了。
 *    以后判断两个字符串是否相等，最好不要使用==，要调用字符串对象的equals方法。
 *
 * 3、finalize()方法
 * */
public class Object类1_源码及帮助文档 {}
