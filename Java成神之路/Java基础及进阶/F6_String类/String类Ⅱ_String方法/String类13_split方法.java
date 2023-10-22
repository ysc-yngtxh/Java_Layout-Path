package F6_String类.String类Ⅱ_String方法;

public class String类13_split方法 {
    public static void main(String[] args) {

        // (掌握) String[] split(String regex)
        // 拆分字符串
        String[] ymd ="1997-04-29".split("-");
        for (int i = 0; i < ymd.length; i++) {
            System.out.println(ymd[i]);
        }
        // 1997
        // 04
        // 29

        String[] ysc ="name=zhangsan & password=123 & age=20".split("&");
        for (int i = 0; i < ysc.length; i++) {
            System.out.println(ysc[i]);
        }
        // name=zhangsan
        // password=123
        // age=20
    }
}
