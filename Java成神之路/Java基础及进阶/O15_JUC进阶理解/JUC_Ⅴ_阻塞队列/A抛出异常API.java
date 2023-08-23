package O15_JUC进阶理解.JUC_Ⅴ_阻塞队列;

import java.util.concurrent.ArrayBlockingQueue;

public class A抛出异常API {
    /**
     * 什么时候使用队列？多线程并发处理，线程池！
     * 四组API
     *    1、抛出异常
     *    2、不会抛异常
     *    3、阻塞等待
     *    4、超时等待
     */
    public static void main(String[] args) {
        test();
    }

    /**
     * 队列溢出或者队列空取会抛出异常,且异常没有返回值
     */
    public static void test(){
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3); // 队列卡槽为3个
        // 往队列中添加元素
        System.out.println(queue.add("a")); // true
        System.out.println(queue.add("b")); // true
        System.out.println(queue.add("c")); // true
        //System.out.println(queue.add("d")); // 队列已满，再添加就会抛异常IllegalStateException: Queue full

        System.out.println("===========================");

        // 从队列中取出元素
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        //System.out.println(queue.remove()); // 队列已空，再取就会抛异常NoSuchElementException
    }


}
