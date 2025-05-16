package N14_注解.注解Ⅱ_注解测试类;

public class MyAnnotationClass3 {

    // 当数组中只有一个元素的时候可以省略大括号
    @MyAnnotation3(age = 23, email = {"游家纨绔", "好想", "曹家千金"}, seasonArray = Season3.WINTER)
    public void doSome() {}
}
