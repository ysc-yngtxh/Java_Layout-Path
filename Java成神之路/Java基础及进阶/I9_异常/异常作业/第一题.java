package I9_异常.异常作业;

import java.util.Scanner;

/* 编写程序模拟用户注册：
 *     1、程序开始执行时，提示用户输入"用户名"和“密码”信息。
 *     2、输入信息之后，后台Java程序模拟用户注册
 *     3、注册时用户名要求长度在[6-14]之间，小于或者大于都表示异常
 *
 * 注意：
 *     完成注册的方法放到一个单独的类中
 *     异常类自定义即可
 *
 *     class UserService{
 *        public void register(String username, String password){
 *            // 这个方法中完成注册！
 *        }
 *     }
 *
 *     编写main方法，在main方法中接收用户输入的信息，在main方法中调用UserService的register方法完成注册
 */
public class 第一题 {

	public static void main(String[] args) {
		UserService u = new UserService();
		Scanner s = new Scanner(System.in);
		System.out.println("请输入用户名和密码");
		while (true) {
			try {
				String username = s.next();
				String password = s.next();
				u.register(username, password);
				return;
			} catch (Mytate e) {
				System.out.println(e.getMessage());
			}
		}
	}

}

class UserService {

	public void register(String username, String password) throws Mytate {
		// 引用等于null的这个判断最好放在所有条件的最前面，避免空指针异常
		if (username == null || username.length() < 6 || username.length() > 14) {
			throw new Mytate("您的用户名长度不符合规定，请在[6-14]长度之间选择用户名");
		}
		// 程序能执行到此处，说明用户名合法
		System.out.println("注册成功");
	}

}

// 自定义异常
class Mytate extends Exception {

	public Mytate() {}

	public Mytate(String message) {
		super(message);
	}

}
