package F6_String类.String类Ⅱ_String方法;

public class String类4_compareTo方法 {
    public static void main(String[] args) {

        // (了解)int compareTo(String anotherString)
        // 就是进行字符串比较
        int result = "abc".compareTo("abc");
        System.out.println(result);                   // 输出结果为0，即字符串是相等的

        int result2 = "abcd".compareTo("abce");
        System.out.println(result2);                  // 输出结果为-1，即d-e=-1

        int result3 = "abce".compareTo("abcd");
        System.out.println(result3);                  // 输出结果为1，即e-d=1

        System.out.println("xyz".compareTo("yxz"));   // 输出结果为-1，x-y=-1
        // 拿着字符串第一个字母和后面字符串的第一个字母比较。分出胜负就不用再比后面的。
    }
}
