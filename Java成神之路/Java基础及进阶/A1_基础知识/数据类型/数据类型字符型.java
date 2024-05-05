package A1_基础知识.数据类型;

/*
关于Java语言当中的char类型：
        转义字符 \
        char类型因为只含有两个字节，而一个中文也占两个字节，所以char类型可以赋值一个中文字符，但不能赋值一串中文
        赋值一串中文是String类型
 */
public class 数据类型字符型 {
	public static void main(String[] args) {
		char c1 = 'n';
		// 普通的n字符
		System.out.println(c1);
		
		char c2 = '\n';
		// '\n'是字符不是字符串，表示的是换行符。'\'代表的是转义功能。
		System.out.println(c2);
		
		char c3='\t';     
		// '\t'表示的是制表符（Tab）
		// 强调：制表符和空格不同，他们的ASCⅡ码不一样，体现在键盘上的两个不同的“按键”。
		System.out.println(c3);
		
		// 要求在控制台上输出“反斜杠字符”
		char c4 = '\\';
		System.out.println(c4);
		// 第一个反斜杠具有转义功能，将第二个反斜杠转义为普通的反斜杠，所以第二个反斜杠不具有转义功能，就只是一个普通的‘\’字符
		
		char c5 = '\'';
		System.out.println(c5);
		// 输出的是普通的'
		
		System.out.println("“Hello World!”");
		System.out.println("\"Hello World!\"");
		// 两种写法都是将 Hello World! 输出时加上引号
		
		char c6 = '中';
		System.out.println(c6);
		// JDK中自带的native ascⅡ.exe命令，可以将文字转换成unicode编码形式
		// 在DOS命令行中输入native2ascii，回车，然后输入文字之后即可得到unicode编码。JDK9之后就没有了该命令
		
		char c7 = '\u4e2d';
		System.out.println(c7);
		// '中'对应的unicode编码是4e2d
		
		char c8 = '\u0000';
		System.out.println(c8);
		// char类型的默认值，输出什么都没有
		
		System.out.println("湖北加油!武汉加油!");
		// 输出的是”湖北加油！武汉加油！“，需要注意的是这是一个字符串，要用到""
	}
}