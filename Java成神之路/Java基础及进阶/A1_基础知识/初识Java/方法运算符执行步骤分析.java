package A1_基础知识.初识Java;

public class 方法运算符执行步骤分析 {

	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		int retValue = sumInt(a, b);
		method(a);
		System.out.println("retValue = " + retValue);        // 10
		System.out.println("a = " + a);                      // 10
	}

	public static int sumInt(int i, int j) {
		int result = i + j;
		int num = 3;
		int retValue = divide(result, num);
		return retValue;
	}

	public static int divide(int x, int y) {
		int z = x / y;
		return z;
	}

	public static void method(int a) {
		a++;
		System.out.println("a = " + a);                     // 11
	}

}
/* 此程序的运行过程
 *        1、method()方法后进，所以先出，也就是 a = 11;
 *        2、sumInt()、divide()方法没有输出项，所以直接去看main方法
 *        3、执行到int retValue=sumInt(a, b)，所以调用到sumInt()方法
 *        4、执行到int retValue=divide(30, 3)，所以调用divide()方法
 *        5、divide返回值 z = 30/3 = 10;
 *        6、所以返回值 z = 10 赋给sumInt()方法，int retValue = divide = 10;
 *        7、sumInt返回值 retValue = 10
 *        8、所以返回值又赋给main方法，int retValue=sumInt=10，所以输出10
 *        9、再看main方法中的a，因为a是值传递，而int类型的传参不会在调用的方法中改变原始值，所以a的值不会改变，输出10
 *        10、DOS窗口输出顺序为11、10、10
 */
