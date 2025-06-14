package A1_基础知识.初识Java;

/* 关于Java编程中运算符
 *      一、算术运算符：++出现变量后，先做赋值运算，再进行自加1操作。
 *           int m = 20;                      int  m = 20;                  int e = 20;                              int s=20;
 *           int n = m++;                     int n = ++m;                  System.out.println(e++); // 20           System.out.println(++s);//21
 *           System.out.println(n); // 20     System.out.println(n); // 21  System.out.println(e);   // 21             System.out.println(s);//21
 *      二、关系运算符：【关系运算符的运算结果一定是布尔类型】
 *                 <         小于
 *                 >         大于
 *                =<        小于等于
 *                >=        大于等于
 *                ==         等于
 *                !=        不等于
 *              例如：  System.out.println(a > b); // ture
 *      三、逻辑运算符：
 *               &  逻辑与【并且】  （两边的算子都是true真，结果才是true真）
 *               |  逻辑或【或者】  （两边的算子只要有一个是true，结果就是true）
 *               !  逻辑非         （取反，！false就是true，！true就是false，这是个单目运算符）
 *               ^  逻辑异或       （两边的算子只要不一样，结果就是true）
 *               && 短路与         （第一个表达式执行结果是false，会发生短路与）
 *               || 短路或         （第一个表达式执行结果是true，会发生短路或）
 *
 *          1、逻辑运算要求两边的算子都是布尔类型，并且逻辑运算符最终的运算结果也是一个布尔类型。
 *          2、短路与和逻辑与（短路或与逻辑或）最终的运算结果是相同的，只不过短路与（短路或）存在短路现象
 *      四、赋值类运算符：
 *               基本的赋值类运算符：=
 *               扩展的赋值类运算符：
 *                 +=   加等于【i+=5等同于i=i+5,可以翻译为追加】
 *                 -=   减等于
 *                 *=   乘等于
 *                 /=   除等于
 *       五 、字符串连接符
 *                +运算符在Java语言当中有两个作用：
 *                                          加法运算
 *                                          字符串的连接运算
 *       六、三元运算符/三目运算符/条件运算符
 *                语法规则：
 *                       布尔表达式 ? 表达式1 : 表达式2
 */
public class 方法运算符 {

	public static void main(String[] args) {
		int x = 10;
		int y = 8;

		System.out.println(5 > 3 & 5 > 2);    // true 逻辑与

		System.out.println(5 > 3 | 5 > 2);    // true 逻辑或

		System.out.println(!true);            // false 逻辑非

		System.out.println(5 > 3 ^ 5 > 2);    // false 逻辑异或

		System.out.println(x < y && ++x < y); // false 短路与
		System.out.println(x); // 10   x<y结果是false，整个表达式结果已经确定是false，所以后面++a表达式没有再执行，这种现象被称为短路现象

		System.out.println(x > y || ++x < y); // true  短路或。
		System.out.println(x); // 10   x>y结果是true，整个表达式结果已经确定是true，所以后面++a表达式没有再执行，这种现象被称为短路现象

		x += 5; // 等同于 x = x+5
		System.out.println(x);
		x -= 5; // 等同于 x = x-5
		System.out.println(x);
		x *= 5; // 等同于 x = x*5;
		System.out.println(x);
		x /= 5; // 等同于 x = x/5
		System.out.println(x);


		byte b = 5;
		b = (byte) (b + 5); // b是byte类型，5是int类型，编译器在执行语法是不通过，所以要在前面加一个强制转换符byte
		System.out.println(b);
		b += 5;             // 等同于b=byte(b+5)
		System.out.println(b);
		/*b = b + (byte) 5;这种写法编译器不兼容不合法
		System.out.println(b);*/
		b += 113;
		System.out.println(b); // -128   本来的结果是128，但是超出了byte类型的取值范围，所以会损失精度。


		int a1 = 10;
		int b1 = 20;
		System.out.println(a1 + "+" + b1 + "=" + (a1 + b1));     // 控制台输出10+20=30  是一个是“动态输出”
		int a2 = 50;
		int b2 = 90;
		System.out.println(a2 + " + " + b2 + " = " + (a2 + b2)); // 50+90=140  变量要连接字符串必须要加上“+”


		boolean sex = true;
		char c = sex ? '男' : '女';
		System.out.println(c);                 // 男
		System.out.println(sex ? '男' : '女');  // 男
		System.out.println(sex ? '男' : "女");  // 男     在println括号中字符或者字符号都可以运行
		System.out.println(a1 > b1 ? a1 : b1); // 20
		String s = sex ? "男" : "女";
		System.out.println(sex ? "猛男的" : "淑女的"); // 猛男的
	}

}
