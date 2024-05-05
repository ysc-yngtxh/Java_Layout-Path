package C3_Object类;
/*
 * 关于object类中的equals方法
 * 1、equals方法的源代码：
 *       public boolean equals(Object obj){
 *          return (this == obj);
 *       }
 *    以上这个方法是Object类的默认实现。
 *
 * 2、SUN公司设计equals方法的目的是什么？
 *    以后编程的过程当中，都要通过equals方法来判断两个对象是否相等
 *    equals方法是判断两个对象是否相等的。
 *
 * 3、判断两个Java对象是否相等，不能使用“==”。因为“==”比较的是两个对象的内存地址。
 */
class Time {  // 其实每个类都继承了Object, 即extends Object
    int year,month,day;
    public Time(){

    }
    public Time(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    // 重写要求：返回值类型一样，形参列表一样，
    public boolean equals(Object obj){
        if(obj == null  || !(obj instanceof Time)){ // 当obj为空值null时候，程序执行到所在的方法体时候会出现空指针异常
            return false;                           // ! 表示布尔值取反
        }
        if(this == obj){          // 这个表示的是this和obj保存的内存地址相同，没必要比较了，直接返回true
            return true;          // this表示的是main()方法中传入的参数，而obj表示的是equals中传入的形参
        }
        Time t = (Time)obj;       // 上面已经做判断了obj是不是Time类型，所以程序到了这一步说明obj是Time类型。
		/*
		  if(this.year == t.year && this.month == t.month && this.day == t.day){
		  	return true;
		  }
		  return false;
		*/
        return this.year == t.year && this.month == t.month && this.day == t.day;  // 与上诉注释代码效果一样
    }
}  // 比较基本数据类型用"=="，比较引用数据类型用equals


public class Object类3_Equals {
    public static void main(String[] args) {
        // 判断两个基本数据类型的数据是否相等直接使用“==”就行。
        int a = 12;
        int b = 12;
        System.out.println(a == b);

        // 判断两个Java对象是否相等，可以直接使用“==”吗？
        Time t1 = new Time(1997, 3, 22);
        Time t2 = new Time(1997, 3, 22);
        // 这里的t1和t2都是对象，对象其实就是内存地址x23456，所以t1,t2内存地址不相等，结果为false
        // 所以，有的时候为了比较两对象内容是否相等 ，就用到了equals重写类
        System.out.println(t1 == t2);

        // Time类对象t1引用方法equals，将Time类对象t2作为形参obj与t1进行内容比较，结果为true
        boolean g = t1.equals(t2);
        System.out.println(g);

        Time t3 = new Time(1997, 4, 29);
        // t1内容不等于t3，所以结果为false
        System.out.println( t1.equals(t3) );
    }

}