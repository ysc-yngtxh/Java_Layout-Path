package A1_基础知识.语法;

import java.util.Scanner;

/*
 * 控制语句：
 *     * 选择结构
 *        - if, if...else
 *        - switch
 *     * 循环结构
 *        - for
 *        - while
 *        - do...while()
 *     * 控制循环的语句
 *        - break
 *        - continue
 */
public class If{
	public static void main(String[] args) {
		Scanner a = new Scanner(System.in);     // 接受用户键盘输入——————在DOS中输入命令指令，只需要输入一条语句就可以了
		System.out.print("请输入你的成绩分数：");  // ln表示换行，print后面没加ln，所以光标就在字符串后面
		double f = a.nextDouble();
		if(f < 0 || f > 100) {                  // 短路或
			System.out.println("你输入的成绩不合法");
		}else {
			if(f >= 60) {
				if(f >= 70) {
					if(f >= 80) {
						if(f >= 90){
							System.out.println("你输入的成绩突出");
						}else {
							System.out.println("你输入的成绩优秀");
						}
					}else {
						System.out.println("你输入的成绩良好");
					}
				}else {
					System.out.println("你输入的成绩及格");
				}
			}else {
				System.out.println("你输入的成绩不及格");
			}
		}

		if(f >= 90) {
			System.out.println('A');
		} else if(f >= 80) {
			System.out.println('B');
		} else if(f >= 70) {
			System.out.println('C');
		} else if(f >= 60) {
			System.out.println('D');
		}
		
		

		System.out.print("请输入性别（1表示男性，0表示女性）：");
		int r = a.nextInt();
		System.out.print("请输入天气状况（1表示晴天，0表示雨天）：");
		int i = a.nextInt();
		if(i == 1) {
			// 温度在晴天时才需要进行输入，所以将温度变量放入if语句中。这样在雨天时候就不需要进行输入温度
			System.out.print("请输入温度：");
			int e = a.nextInt();
			if(e > 30) {
				if(r == 1) {
					System.out.println("戴墨镜");
				} else if(r == 0) {
					System.out.println("擦防晒霜");
				}
			} else {
				System.err.println("天气十分舒适，祝你今天愉快！");
			}
		} else if(i == 0) {
			if(r == 1) {
				System.out.println("带一把大黑伞");
			} else if(r == 0) {
				System.out.println("带一把小花伞");
			}
		}
		
		
		
	}
		
}
