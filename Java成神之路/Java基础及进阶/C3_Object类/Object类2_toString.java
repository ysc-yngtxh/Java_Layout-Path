package C3_Object类;
/*
 * 1、源代码上toString()方法的默认实现是：
 *     类名@对象的内存地址转换成十六进制的形式
 * 2、源代码上toString()方法的目的是什么？
 *    toString()方法的作用是什么？
 *       toString()方法的设计目的是：通过调用这个方法可以将一个“Java对象”转换成“字符串表示形式”
 * 3、其实SUN公司开发Java语言的时候，建议所有的子类都去重写toString()方法。
 *    toString()方法应该是一个简洁的，详实的，易阅读的。
 */
class MyTime{
    int year,month,day;
    public MyTime(){

    }
    public void MyTime1(){};
    public MyTime(int year,int month,int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    // 如果不去重写这个toString()方法,输出结果是一个以十六进制的字符串。
    public String toString(){
        return this.year + "/" + this.month + "/" + this.day;
    }
}


public class Object类2_toString {
    public static void main(String[] args) {
        MyTime t1 = new MyTime(1997,4,29);
		/*
		  String s1 = t1.toString();
		  System.out.println(s1);
		 */
        System.out.println(t1.toString());
    }
}
