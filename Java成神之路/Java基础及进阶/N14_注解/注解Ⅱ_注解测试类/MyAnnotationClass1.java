package N14_注解.注解Ⅱ_注解测试类;

public class MyAnnotationClass1 {

    /*
     *  报错原因：如果一个注解当中有属性，那么必须给属性赋值
     *  @MyAnnotation1
     *  public void doSome() {}
     */

    // @MyAnnotation1(属性名 = 属性值)。指定name属性的值就好了
    @MyAnnotation1(name = "游家纨绔", color = "yellow")  // 属性默认值可以不写，所以age可以不写
    public void doSome(){

    }
}
