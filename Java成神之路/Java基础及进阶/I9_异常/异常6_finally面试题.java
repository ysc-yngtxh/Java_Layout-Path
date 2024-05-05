package I9_异常;

public class 异常6_finally面试题 {
    public static void main(String[] args) {
        int result = m(100);
        System.out.println(result);
    }

    /*
     * Java语法规则
     *     1、方法体中的代码必须遵循自上而下的顺序依次逐行执行
     *     2、return语句一旦执行，整个方法必须结束
     */

    public static int m(int i) {
        try {
            return i;
        } finally {
            ++i;
        }
    }

    /*
      执行m()方法return的是100，所以m()返回值就是100.
      final表示一定执行，就算是return结束了都会去执行 ++i; 但是return结束后增 不影响已经返回的结果
     */
}
