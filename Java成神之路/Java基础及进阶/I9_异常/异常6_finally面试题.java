package I9_异常;

public class 异常6_finally面试题 {

	public static void main(String[] args) {
		int result = m(100);
		System.out.println(result);

		int result2 = m(100);
		System.out.println(result2);
	}

	public static int m(int i) {
		try {
			return i; // 1、先将 i 的值（100）保存为返回值
		} finally {
			++i;      // 2、然后执行 finally 块，i 自增为 101
		}
	}

    /* m()方法执行过程：
     *     1、当执行 return i; 时，Java 会先将 i 的当前值（100）保存起来作为返回值。
     *     2、然后执行 finally 块，i 的值变为 101。
     *     3、但方法的返回值仍然是之前保存的 100，finally 中对 i 的修改不会影响返回值。
     */

	public static int n(int i) {
		try {
			return i;     // 1、先将 i 的值（100）保存为返回值
		} finally {
			return i+100; // 2、然后执行 finally 块，返回 i + 100（200）
		}
	}

	/* n()方法执行过程：
	 *     1、当执行 return i; 时，Java 会先将 i 的当前值（100）保存起来作为返回值。
	 *     2、然后执行 finally 块，finally 中的 return i + 100; 会覆盖之前的返回值。
	 */


	/** 总结
	 * finally 块一定会执行（除非 JVM 退出或线程中断）。
	 * 如果 finally 中没有 return：
	 *     try 中的 return 值会先被保存，然后执行 finally。
	 *     finally 中对变量的修改不会影响已保存的返回值。
	 * 如果 finally 中有 return：
	 *     finally 的 return 会覆盖 try 中的 return。
	 *     这是不推荐的做法，因为它会掩盖 try 块中的返回值和可能的异常。
	 */

}
