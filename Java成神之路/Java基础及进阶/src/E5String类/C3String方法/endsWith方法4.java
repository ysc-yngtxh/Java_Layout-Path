package E5String类.C3String方法;

public class endsWith方法4 {
    public static void main(String[] args) {

        //(掌握)boolean endsWith(String suffix)
        //判断当前字符串是否以某个子字符串结尾
        System.out.println("test.txt".endsWith("txt"));
        System.out.println("test.txt".endsWith("java"));

        //(掌握)boolean startsWith(String prefix)
        //判断某个字符串是否以某个子字符串开始
        System.out.println("test.txt".endsWith("test"));
        System.out.println("test.txt".endsWith("java"));
    }
}
