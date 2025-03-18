package A1_基础知识.数据类型;

/*
 * 关于字面值：
 *     - 10、1000
 *     - 3.14
 *     - "abc"
 *     - 'a'
 *     - true、false
 *   * 字面值就是数据
 *   * 数据在现实世界当中是分门别类的，所以数据在计算机编程语言当中也是有类型
 *     - 10、1000           属于整数型字面值
 *     - 3.14               属于浮点型字面值
 *     - true、false        属于布尔型字面值
 *     - "abc"、"中国人"     属于字符串字面值
 *     - 'A'、'人'          属于字符型字面值
 *   * 注意：
 *         Java语言当中多有的字符串型字面值必须使用双引号括起来，双引号是英文型的
 *         Java语言当中多有的字符型字面值必须使用单引号括起来，单引号是英文型的
 */
public class 字面值 {
	/*
	 * 局部变量：在方法体当中的变量叫做局部变量
	 * 成员变量：在方法体外【类体之外】声明的变量叫做成员变量
	 * 在不同的作用域当中，变量名是可以相同的
	 * 在相同的作用域当中，变量名不可以重名
	 * JAVA遵循 “就近原则”
	 */
	static int k = 90; // 成员变量
	public static void main(String[] args) {
		System.out.println("abc");
		System.out.println(1000);
		System.out.println('A');
		System.out.println(3.14);
		System.out.println(true);
		int i = 1; // 局部变量
		System.out.println(i);
		/*通常访问一个变量包括两种访问方式
		 * 第一种：读取变量中保存的具体数据    get/获取
		 * 第二种：修改变量中保存的具体数据    set/设置
		 * */
		System.out.println(k);
		/*
		 什么是变量的作用域：
		    -变量的作用域，其实描述的就是变量的有效范围。在这个范围之内是可以被访问的，只要出了这个范围该变量就无法访问了
		    -变量的作用域只要记住一句话：出了大括号就不认识了
		    
		 */
		int j; // 作用于是main方法，成员变量
		for(j = 0; j < 10; j++) {
			
		}
		System.out.println(j);  // 访问的是main方法中的变量
		/*
		 four(int j; j = 0; j < 10; j++){   // j变量的作用域是整个for循环，for循环结束之后，j变量的内存就释放了
		 }
		 System.out.println(j);
		 这种写法是错误的，得不到j的值
		 */
	}
}
