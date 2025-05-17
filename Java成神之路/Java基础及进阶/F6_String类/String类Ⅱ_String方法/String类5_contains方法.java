package F6_String类.String类Ⅱ_String方法;

public class String类5_contains方法 {

	public static void main(String[] args) {
		// (掌握)boolean contains(CharSequence s)
		// 判断前面的字符串中是否包含后面的字符串
		System.out.println("Helloworld.java".contains(".java"));          // true
		System.out.println("http://www.baidu.com".contains("https://"));  // false
	}

}
