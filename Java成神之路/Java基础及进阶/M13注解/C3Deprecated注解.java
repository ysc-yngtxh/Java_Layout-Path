package M13注解;

//@Deprecated   //表示这个类已过时
public class C3Deprecated注解 {
    public static void main(String[] args) {

    }

    @Deprecated
    public static void doOther(){
        System.out.println("do other...");
    }

    //Deprecated这个注解标注的元素已过时
    //这个注解主要是向其他程序员传达一个信息，告知已过时，有更好的解决方案存在
    @Deprecated
    public void dosome(){
        System.out.println("do some...");
    }
}

class G{
    public static void main(String[] args) {
        C3Deprecated注解 de = new C3Deprecated注解();
        de.dosome();

        C3Deprecated注解.doOther();
    }
}
