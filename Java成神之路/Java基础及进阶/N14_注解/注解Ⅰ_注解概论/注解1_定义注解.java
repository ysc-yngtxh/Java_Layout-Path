package N14_注解.注解Ⅰ_注解概论;

/*
 * 1、注解，或者叫做注释，英文单词是：Annotation
 *
 * 2、注解Annotation是一种引用数据类型，编译之后也是生成xxx.class文件
 *
 * 3、怎么自定义注解呢？语法格式？
 *      [修饰符列表] @interface 注解类型名 {}
 *
 * 4、注解怎么使用？用在什么地方？
 *       第一：注解使用时的语法格式是：
 *              @注解类型名
 *       第二：注解可以出现在类上、属性上、方法上、变量上等。。。
 *             注解还可以出现在注解类型上
 *
 * 5、Jdk内置了那些注解？
 *      Deprecated：用 @Deprecated 注释这样的程序元素表示已被弃用，不鼓励一个程序员使用，因为有更好的选择的存在。
 *      Override：表示一个方法声明的目的是覆盖父类方法声明。
 *      SuppressWarnings：指示在注释元素（和包含在注释元素中的所有程序元素中）应被抑制命名的编译器警告。
 *
 * 6、元注解
 *    什么是元注解
 *        用来标注 “注解类型” 的注解，称为 “元注解”【按住Ctrl点击下方程序中的@Override,可以看到元注解】
 *
 *    常见的元注解有哪些？
 *        @Target
 *        @Retention
 *        @Inherited
 *        @Documented
 *
 *    关于Target注解：
 *        这是一个元注解，用来标注“注解类型”的生效的位置
 *        @Target(ElementType.METHOD)  表示“被标注的注解”只能出现在方法上
 *        @Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
 *             表示该注解可以出现在：构造方法上，字段上，局部变量上，方法上，包上，参数上，类上
 *
 *    关于Retention注解：
 *        @Retention(RetentionPolicy.SOURCE)   表示该注解只被保留在Java源文件中。
 *        @Retention(RetentionPolicy.CLASS)    表示该注解被保存在class文件中
 *        @Retention(RetentionPolicy.RUNTIME)  表示该注解被保存在class文件中，并且可以被反射机制读取
 *
 *    关于Inherited注解：
 *        @Inherited   表示该注解可以被继承。
 *                     比如自己定义一个 @MyAnnotation 注解，然后在A类上添加该 @MyAnnotation 注解
 *                     B类去继承A类，但不在B类上添加 @MyAnnotation 注解，那么B类也可以使用 @MyAnnotation 注解。
 *
 *    关于Documented注解：
 *        @Documented   表示该注解可以被 javadoc 工具提取成文档。
 */
public class 注解1_定义注解 {

	@Override
	public String toString() {
		return "toString";
	}

}
