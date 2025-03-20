package P16_JUC并发.JUC并发Ⅶ_四大函数接口及Stream流式计算;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SuppressWarnings({"rawtypes", "unchecked"})
public class A四大函数 {
    /**
     * 四大函数接口都可以使用Lambda表达式来书写
     *      1、Function<T, R>  函数型接口
     *          工具类：接收一个T类型参数,返回一个R类型结果
     * <p>
     *      2、Predicate<T>  断言式接口
     *          工具类：接收一个T类型参数,返回一个Boolean类型结果
     * <p>
     *      3、Consumer<T>  消费型接口
     *          工具类：接收一个T类型参数，不返回值
     * <p>
     *      4、Supplier<T>  供给型接口
     *          工具类：不接受参数，返回一个T类型的结果
     */
    /**
     * Java8中对于接收两个参数的场景提供了相关的函数式接口。如下：
     *      1、BiFunction<T, U, R>
     *          工具类：接收一个T类型和一个U类型参数，返回一个R类型结果
     *
     *      2、BiConsumer<T, U>
     *          工具类：接收一个T类型和一个U类型参数,不返回值
     *
     *      3、BiPredicate<T, U>
     *          工具类：接收一个T类型和一个U类型参数返回一个Boolean类型结果
     */

    public static void main(String[] args) {
        /*
         * Function = new Function<String, String>() {
         *     @Override
         *     public String apply(String str) {
         *         return str;
         *     }
         * };
         */
        Function function = (str) -> {return str;};
        System.out.println( function.apply("游诗成") );

        /*
         * Predicate = new Predicate<String>() {
         *     @Override
         *     public boolean Test(String str) {
         *         return false;
         *     }
         * };
         */
        Predicate predicate = (str) -> {return str.toString().isEmpty();};
        System.out.println( predicate.test("") );

        /*
         * Consumer = new Consumer<String>() {
         *     @Override
         *     public void accept(String str) {
         *         System.out.println(str);
         *     }
         * };
         */
        Consumer consumer = (str) -> {System.out.println(str);};
        consumer.accept( "YouShiCheng" );

        /*
         * Supplier supplier = new Supplier() {
         *     @Override
         *     public Object get() {
         *         System.out.println("get()");
         *         return 2021;
         *     }
         * };
         */
        Supplier supplier = () -> {return 2021;};
        System.out.println( supplier.get() );
    }
}
