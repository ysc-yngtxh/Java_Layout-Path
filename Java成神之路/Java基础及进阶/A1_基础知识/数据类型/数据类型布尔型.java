package A1_基础知识.数据类型;

/*
 *  在Java语言当中boolean类型只有两个值：true、false，没有其他值。
 *  不像C语言当中，0和1可以表示假和真
 *
 *   布尔类型在实际开发当中非常重要，经常使用在逻辑运算和条件控制语句当中。
 */
public class 数据类型布尔型 {
	public static void main(String[] args) {
		boolean S = false;
		if(S) {
			System.out.println("恭喜你，登陆成功！");
		}else {
			System.out.println("对不起，用户名不存在或者密码错误！");	
		};
	}
}
/*关于基本数据类型之间的互相转换：转换规则
      1、八种基本数据类型当中除布尔类型之外剩下的7种类型之间都可以互相转换
      2、小容量像大容量转换，称为自动类型转换，容量从小到大排序：
         byte < short  < int  < long  < float  < double
         char
      3、大容量转换成小容量，叫做强制类型转换符，需要加强类型转换符，程序才能编译通过，但是在运型阶段可能会损失精度，所以谨慎使用。
      4、当整数型字面值没有超出byte、short、char的取值范围，可以直接赋值给byte、short、char类型的变量。
      5、byte、short、char混合运算的时候，各自先转换成int类型再做运算 。
      6、多种数据类型混合运算，先转换成容量最大的那种类型再做运算。      
*/
