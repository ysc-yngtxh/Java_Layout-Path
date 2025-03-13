package H8_Java对日期的处理;

/*
  采用枚举的方式改造程序
      1、枚举是一种引用数据类型
      2、枚举类型怎么定义，语法是：
            enum 枚举类型名 {
                 枚举值1，枚举值2
            }
      3、结果只有两种情况的，建议使用布尔类型
         结果超过两种类型并且还是可以一枚一枚列举出来的，建议使用枚举类型。
             例如：颜色，四季，星期等都可以使用枚举类型
 */
public class Java7_枚举类型 {
    public static void main(String[] args) {
        Result R = divide(10, 2);
        System.out.println(R == Result.SUCCESS ? "计算成功" : "计算失败");

        System.out.println("=========================================================================================");

        // switch也能进行枚举，但好像低版本是不可以的
        switch(Season.SUMMER) {
            case SPRING:
                System.out.println("春天");
                break;
            case SUMMER:
                System.out.println("夏天");
                break;
            case AUTUMN:
                System.out.println("秋天");
                break;
            case WINTER:
                System.out.println("冬天");
                break;
        }
    }

    public static Result divide(int a, int b) {
        try {
            int c = a/b;
            return Result.SUCCESS;
        } catch (Exception e) {
            return Result.FAIL;
        }
    }
}

enum Result {
    // SUCCESS,FAIL是枚举Result类型中的值
    // 枚举中的每一个值，可以看作是“常量”
    SUCCESS,
    FAIL;
}

// 四季枚举类型
enum Season {
    SPRING,
    SUMMER,
    AUTUMN,
    WINTER
}

// 颜色枚举类型
enum Color {
    RED,
    BLUE,
    YELLO,
    BLACK
}
