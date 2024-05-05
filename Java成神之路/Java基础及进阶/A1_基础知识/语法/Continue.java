package A1_基础知识.语法;

/*
continue语句：
       1、continue：表示继续/go on/下一个，同时也是一个关键字，加一个分号构成一个单独的完整的Java语句
       3、break 和 continue 的区别：
             break表示循环结束，不执行了
             continue表示当前语句结束，直接进入下一次循环执行
 */
public class Continue{
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			if(i == 5) {
				continue;
			}
			// 1 2 3 4 6 7 8 9  结果打印中没有5。Continue当前语句循环结束，直接进入下一次循环“继续”执行。
			System.out.println("i="+i);
		}
		System.out.println("Hello World!");
	}
}