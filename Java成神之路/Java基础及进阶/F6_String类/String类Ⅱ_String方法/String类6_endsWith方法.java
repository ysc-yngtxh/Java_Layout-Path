package F6_String类.String类Ⅱ_String方法;

public class String类6_endsWith方法 {
    public static void main(String[] args) {

        // (掌握)boolean endsWith(String suffix)
        // 判断当前字符串是否以某个子字符串结尾
        System.out.println("Test.txt".endsWith("txt"));
        System.out.println("Test.txt".endsWith("java"));

        // (掌握)boolean startsWith(String prefix)
        // 判断某个字符串是否以某个子字符串开始
        System.out.println("Test.txt".startsWith("Test"));
        System.out.println("Test.txt-java%csvakjava".startsWith("java"));
    }
}
