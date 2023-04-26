package E5String类.C3String方法;

public class replace方法10 {
    public static void main(String[] args) {

        //(掌握) String replace(CharSequence target,CharSequence replacement)
        //String的父接口：CharSequence

        System.out.println("http://www.baidu.com".replace("http://","https://"));
        //https://www.baidu.com

        System.out.println("name=zhangsan & password=123 & age=20".replace("=",":"));
        //name:zhangsan & password:123 & age:20
    }
}
