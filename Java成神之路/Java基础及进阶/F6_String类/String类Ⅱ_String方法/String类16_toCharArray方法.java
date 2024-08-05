package F6_String类.String类Ⅱ_String方法;

public class String类16_toCharArray方法 {
    public static void main(String[] args) {
        // (掌握) char[] toCharArray()
        // 将字符串转换成char数组
        char[] chars ="我是中国人".toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
        }
    }
}
