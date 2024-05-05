package A1_基础知识.语法;

public class While{
	public static void main(String[] args) {
		int i = 10;
		while (i >= 1) {               // While循环体
			System.out.println(i--);   // 10,9,8,7,6,5,4,3,2,1
		}
		System.out.println(i); // 0
		
		int a = 10;
		do {                       // do while循环结构体
			System.out.println(a); // 10 11 12 13 14 15 16 17 18 19
			a++;
		} while(a < 20);
	}
}