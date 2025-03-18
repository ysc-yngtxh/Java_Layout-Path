package A1_基础知识.语法;

import java.util.Scanner;

/*
 * 关于switch语句：
 *     1、switch语句的语法结构：
 *
 *     2、switch语句的执行原理：
 *        switch后面小括号当中的“数据”和case后面的“数据”进行一一匹配，匹配成功的分支执行。按照自上而下的顺序依次匹配。
 *
 *     3、匹配成功的分支执行，分支当中最后有“break"语句的话，整个switch语句终止
 *
 *     4、匹配成功的分支执行，分支当中没有"break"；语句的话，直接进入下一个分支执行（不进行匹配），这种现象被称为case穿透现象【提供break；语句可以避免穿透】
 *
 *     5、所有分支都没有匹配成功，当有default语句的话，会执行default分支当中的程序
 *
 *     6、switch后面和case后面只能是int或者string类型的数据，不能是探测其他类型。
 *           当然 byte、short、char 也可以直接写到switch和case后面，因为它们可以进行自动类型转换
 *           byte，short,char可以自动转换成int类型
 */
public class Switch {
	public static void main(String[] args) {
		
		Scanner a = new Scanner(System.in);
		System.out.println("请输入数字：");
		int c = a.nextInt();
		switch(c) {
		    case 1:case 0:                     // case合并
		    	System.out.println("星期一");
		    	                               // 没有 break; 被称为case穿透
		    case 2:
		    	System.out.println("星期二");
		    	break;
		    case 3:
		    	System.out.println("星期三");
		    	break;
		    case 4:
		    	System.out.println("星期四");
		    	break;
		    case 5:
		    	System.out.println("星期五");
		    	break;
		    case 6:
		    	System.out.println("星期六");
		    	break;
		    case 7:
		    	System.out.println("星期七");
		    	break;
		    default :
		    	System.out.println("对不起，你输入的数字非法");
		}
		
		double d = 90.0;
		switch((int)d) {      // double类型加上了强制转换符，转成int类型
		    case 90:
		    	System.out.println("高级");
		}

		char s='A';
		switch(s) {           // ASCⅡ码  'A'=65     所以s不是int,string类型会转成int类型
		    case 'A':
		    	System.out.println("中级");
		    /*
		    case 65:
		    	System.out.println("中级");*/
		}
		
		System.out.println("欢迎使用简单计算机系统");
		System.out.print("请输入第一个数字：");
		int a1 = a.nextInt();
		System.out.print("请输入运算符：");
		String b1 = a.next();
		System.out.print("请输入第二个数字：");
		int c1 = a.nextInt();
		int result = 0;
		switch(b1) {
		    case "+":
		    	result = a1 + c1;
		        break;
		    case "-":
		    	result = a1 - c1;
		        break;
		    case "*":
		    	result = a1 * c1;
		        break;
		    case "/":
		    	result = a1 / c1;
		        break;
		    case "%":
		    	result = a1 % c1;
		        break;
		    default :
		    	System.out.println("你输入的运算符不合法");
		}
		System.out.println("运算结果：" + a1+b1+c1+ "=" + result);
	}
}
