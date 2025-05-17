package F6_String类.String类Ⅱ_String方法;

public class String类12_replace方法 {

	public static void main(String[] args) {
		// (掌握) String replace(CharSequence target,CharSequence replacement) 字符串替换
		// String的父接口：CharSequence

		System.out.println("http://www.baidu.com".replace("http://", "https://"));
		// https://www.baidu.com

		System.out.println("name=zhangsan & password=123 & age=20".replace("=", ":"));
		// name:zhangsan & password:123 & age:20

		System.out.println("name=zhangsan & password=zhangsan & alias=zhangsan".replaceAll("zhangsan", "lisi"));
		// name=lisi & password=lisi & alias=lisi
	}

}
