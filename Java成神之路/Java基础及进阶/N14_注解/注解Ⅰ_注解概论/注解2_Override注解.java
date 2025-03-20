package N14_注解.注解Ⅰ_注解概论;

/*
 * 关于Jdk lang包下的Override注解
 *    源代码：
 *    public @interface Override{}
 *
 *    标识编译器做参考的
 *    编译器看到方法性注解，给上有这个注解的时候，编译器会自动检查该方法是否重写了父类的方法。如果没有重写，报错。
 *
 *    这个注解只有在编译阶段起作用，与运行期无关
 *
 *    @Override 这个注解只能注解方法
 *    @Override 这个注解是给编译器参考的，和运行阶段没有关系
 *    凡是Java中的方法带有这个注解的，编译器都会进行编译检查，如果这个方法不是重写父类的方法，编译器报错
 */
public class 注解2_Override注解 {
    @Override
    public String toString() {
        return "toString";
    }
}
