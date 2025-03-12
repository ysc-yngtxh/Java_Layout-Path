package E5_数组.数组Ⅰ_详解;

public class 数组4_main方法Args参数 {
    // 这个方法程序员负责写出来，JVM负责调用，JVM调用的时候一定会传一个String数组过来。
    public static void main(String[] args){
        System.out.println("JVM给传递过来的String数组参数，它这个数组的长度是？" + args.length);
        // 在不写入情况下结果为0
        // 通过测试得出：args不是null，JVM默认传递过来的这个数组对象的长度默认为0.

        /*
         * String[] args 数组是留给用户的，用户可以在控制台上输入参数，这个参数自动会被转换为 "String[] args"
         * 比如在DOS命令提示符窗口上，可以这样运行程序：
         *    javac 数组4_main方法Args参数.java ----> java 数组4_main方法Args参数 abc def xyz
         *
         *    那么这个时候JVM会自动将 "abc def xyz" 通过空格的方式进行分离，分离完成后，自动放到 "String[] args"数组里。
         * 把abc def xyz转换成字符串数组：{"abc", "def", "xyz"}
         */

        /*
         * 在IDEA上如何测试呢？
         * Run ----> Edit Configurations ----> Program arguments ----> abc def xyz
         */

        System.out.println("=======================================================================================");

        // 现在我们模拟一个系统，假设这个系统要使用，必须输入用户名和密码。
        if (args.length != 2) {
            System.out.println("使用该系统时请输入程序参数，参数中包括用户名和密码信息，例如：张三 123");
        }

         // 程序员执行到此处说明用户确实提供了用户名和密码
         // 接下来判断用户名和密码是否正确
         String username = args[0]; // 取出用户名
         String password = args[1]; // 取出密码

         // 假设用户名是admin，密码是123的时候表示登陆成功。其他一律失效。
         // 判断两个字符串是否相等，需要使用equals方法。
         // if(username.equals("admin") && password.equals("123")){
        //  equals 方法是对象实例的方法。相比于上面的写法，这种写法不会造成空指针异常
         if("admin".equals(username) && "123".equals(password)) {
             System.out.println("登陆成功，欢迎[" + username + "]回来");
             System.out.println("您可以继续使用该系统");
         } else {
             System.out.println("验证失败，用户名不存在或者密码错误！");
         }
    }
}
