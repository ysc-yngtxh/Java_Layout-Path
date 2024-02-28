package I9_异常;

/*
  java中我们可以自己去定义异常
      第一步：编写一个类继承Exception或者RuntimeException
      第二部：提供两个构造方法，一个是无参的，一个是有参的

  throw和throws的区别：
       1 throw是语句抛出一个异常。
         语法：throw (异常对象);
       2 throws是方法可能抛出异常的声明。(用在声明方法时，表示该方法可能要抛出异常)
 */
public class 异常7_异常在实际开发中的使用 {
    public static void main(String[] args) {
        MyException e = new MyException("用户名不能为空！");

        String s = e.getMessage();
        System.out.println(s);

        e.printStackTrace();
    }
}

class MyException extends Exception {
    public MyException() {
    }
    public MyException(String message) {
        super(message);
    }
}
// ******异常在实际开发中的使用具体可参考数组作业第一题，关于压栈弹栈失败，可以当作异常来处理