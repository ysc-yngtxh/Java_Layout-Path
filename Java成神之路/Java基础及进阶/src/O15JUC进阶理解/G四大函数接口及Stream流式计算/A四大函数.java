package O15JUC进阶理解.G四大函数接口及Stream流式计算;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class A四大函数 {
    /**
     * 四大函数接口都可以使用Lambda表达式来书写
     *      1、Function  函数型接口
     *          工具类：有一个输入的值，还有输出的值
     *
     *      2、Predicate  断定式接口
     *          工具类：有一个输入值，返回值是一个布尔类型
     *
     *      3、Consumer  消费者接口
     *          工具类：有一个输入值，没有返回值，里面是你想实现的代码
     *
     *      4、Supplier  供给者接口
     *          工具类：没有参数输入值，有返回值
     */
    public static void main(String[] args) {
        /*Function function = new Function<String,String>() {
            @Override
            public String apply(String str) {
                return str;
            }
        };*/
        Function function = (str)->{return str;};
        System.out.println(function.apply("游诗成"));

        /*Predicate predicate =new Predicate<String>() {
            @Override
            public boolean test(String str) {
                return false;
            }
        };*/
        Predicate predicate = (str)->{return str.toString().isEmpty();};
        System.out.println(predicate.test(""));

        /*Consumer consumer = new Consumer<String>() {
            @Override
            public void accept(String str) {
                System.out.println(str);
            }
        };*/
        Consumer consumer = (str)->{System.out.println(str);};
        consumer.accept("youshicheng");

        /*Supplier supplier = new Supplier() {
            @Override
            public Object get() {
                System.out.println("get()");
                return 2021;
            }
        };*/
        Supplier supplier = ()->{return 2021;};
        System.out.println(supplier.get());
    }
}
