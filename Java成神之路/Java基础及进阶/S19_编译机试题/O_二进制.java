package S19_编译机试题;

import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-29 13:10:00
 * @apiNote TODO 给定两位整数n和m，同时给定i和j，将m的二进制数位插入到n的二进制的第j到第i位，保证n的第j到第i位均为零，
 *               且m的二进制位数小于等于i-j+1，其中二进制的位数从0开始由低到高。
 *               1024 19 2 6
 */
public class O_二进制 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int m = scanner.nextInt();
		int n = scanner.nextInt();
		int i = scanner.nextInt();
		int j = scanner.nextInt();
		// Math.pow(i, j) 代表i的j次方：i^j。同样这也表示进行异或运算
		double res = m * Math.pow(i, j);
		System.out.println(n + res);

		m <<= j;
		System.out.println(m | n);
	}

}
