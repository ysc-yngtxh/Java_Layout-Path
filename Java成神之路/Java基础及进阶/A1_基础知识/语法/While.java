package A1_基础知识.语法;

public class While{
	public static void main(String[] args) {

		int i = 10;
		// While循环体：先判断条件，条件为真才执行循环体【最少执行次数：可能0次】
		while (i >= 1) {
			System.out.println(i--);   // 10,9,8,7,6,5,4,3,2,1
		}
		System.out.println(i); // 0
		
		int a = 10;
		// do while循环体：先执行循环体，再判断条件是否继续【最少执行次数：至少一次】
		do {
			System.out.println(a); // 10 11 12 13 14 15 16 17 18 19
			a++;
		} while(a < 20);
	}
}
