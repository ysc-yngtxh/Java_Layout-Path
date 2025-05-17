package A1_基础知识.初识Java;

/*
 * 关于Java语言当中的方法：
 *   1、方法怎么定义，语法结构：
 *         [修饰符列表] 返回值类型 方法名 (形式参数列表) {
 *                 方法体;
 *         }
 *   2、返回值类型：
 *         返回值是一个具体存在的数据，数据都是有类型的，此处需要指定的是返回值的具体类型。
 *         Java中规定，当一个方法执行结束之后不返回任何数据的话，返回值类型位置必须编写：void关键字
 *         返回值类型可以是：byte, short, int, long, float, double, boolean, char, String, void...
 *         返回值类型若不是void，表示这个方法执行结束之后必须返回一个具体的数值。
 *             当方法执行结束的时候没有返回任何数据的话编译器报错
 *
 *             方法：
 *                // public表示公开的
 *                // static表示静态的
 *                // void表示方法执行结束之后不返回任何数据
 *                // main是方法名：主方法
 *                // (String[] args)：可变参数列表，其中String[]是一种引用数据类型，args是一个局部变量的变量名
 *                // 所以只有args这个局部变量的变量名是随意的
 *
 *            *** // break是终止当前语句循环
 *                // return是终止类循环
 *   3、在JVM（虚拟机）内存划分上有这样三块主要的内存空间（当然除了这三块之外还有其他的内存空间）：
 *         方法区内存【方法代码片段】
 *         堆内存（）
 *         栈内存【栈内存中分配方法运行的的所属空间】
 *             - 栈内存遵循先进后出，后进先出的原则
 *             - 方法调用的瞬间，会给该方法分配内存空间，会在栈中发生压栈动作，方法执行结束之后，给该方法分配的内存空间全部释放，此时发生弹栈动作
 *             - 局部变量在“方法体”中声明，局部变量运行阶段内存在栈中分配
 *         常见的数据结构：
 *             - 数组
 *             - 队列
 *             - 栈
 *             - 链表
 *             - 二叉树
 *             - 哈希表/散列表
 */
public class 方法 {

	public static void sumInt(int a, int b) {         // (int a,int b)形参列表
		int c = a + b;
		System.out.println(a + "+" + b + "=" + c);    // 方法只有在调用的时候才会执行
	}

	public static void main(String[] args) {
		方法.sumInt(10, 23);      // 方法调用的时候实参和形参要求个数对应相同，数据类型相同
		方法.sumInt(111, 666);    // (111, 666)实参列表
		sumInt(888, 555);        // 方法.可以省略，但sumInt()必须要在类中找到
		A.M();                   // 这里调用的是外界A类的函数，所以不能省类名.
		System.out.println();

		System.out.println("100以内的所有素数");
		int count = 0;                    // count表示的是控制台一行的输出值个数；
		for (int i = 2; i <= 100; i++) {  // 素数只能被1和自身整除，所以最小的素数是2
			boolean sushu = true;         // 定义一个Boolean类型的sushu变量为真
			for (int j = 2; j < i; j++) {
				if (i%j == 0) {
					sushu = false;      // 当 i%j == 0 成立时，即表明i为非素数
					break;              // break表示结束该语句循环
				}
			}
			if (sushu) {
				System.out.print(i + " ");
				count++;                // 每输出一个i,即控制台输出个数加1
				if (count == 8) {
					System.out.println();
					count = 0;          // 每8个循环一行
				}
			}
		}
	}

}

class A {

	public static void M() {
		System.out.println("Hello World!");
	}

}
