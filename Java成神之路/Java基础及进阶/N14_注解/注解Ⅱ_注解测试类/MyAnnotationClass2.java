package N14_注解.注解Ⅱ_注解测试类;

public class MyAnnotationClass2 {

    // @MyAnnotation1(属性名 = 属性值)。
    @MyAnnotation2("23")     // 只有当注解文件中只有一个属性值且属性值为value的时候，属性名可以省略不写
    public void doOther() {}
}
