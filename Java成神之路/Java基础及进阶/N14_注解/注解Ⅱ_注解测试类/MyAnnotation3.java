package N14_注解.注解Ⅱ_注解测试类;

public @interface MyAnnotation3 {
    /*
      注解当中的属性可以是哪一种类型？
        byte, short, int, long, float, double, boolean, char, String, Class,枚举类型Enum
        以及以上每一种的数组形式
     */
    int age();

    String[] email();

    Season3[] seasonArray();
}
