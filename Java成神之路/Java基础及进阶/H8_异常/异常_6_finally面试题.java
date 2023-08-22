package H8_异常;

public class 异常_6_finally面试题 {
    public static void main(String[] args) {
        int  result = m();
        System.out.println(result);
    }

    /*
     * Java语法规则
     *     1、方法体中的代码必须遵循自上而下的顺序依次逐行执行
     *     2、return语句一旦执行，整个方法必须结束
     */

    public static int m() {
        int i = 100;
        try{
            return i;
        } finally {
            ++i;
        }
    }

    /*
      反编译之后的结果
      public static int m(){
          int i = 100;
          int j = i;
          i++;
          return j;
      }
   */

}
