package F6_String类.String类Ⅱ_String方法;

class  Customer {
    // 重写toString()方法
    // @Override
    // public String toString() {
    //     return "我是湖北武汉人，我要年薪百万！";
    // }
}
public class String类19_valueOf方法 {
    public static void main(String[] args) {

        // (掌握) String中只有一个方法是静态的，不需要new对象
        // 这个方法叫做valueOf
        // 作用：将“非字符串”转换成“字符串”
        System.out.println(String.valueOf(true));   // true不是布尔类型了，而是一个字符串
        System.out.println(String.valueOf(3.14));   // 3.14是一个字符串

        // 这个静态的valueOf()方法，参数是一个对象的时候，会自动调用该对象的toString()方法
        String s = String.valueOf(new Customer());
        System.out.println(s);
        // 为什么输出一个引用会自动调用toString()方法
        // 因为println()方法在输出任何数据的时候都是先转换字符串输出，就会调用valueOf()方法，而valueOf()方法又会调用object的toString()方法

        /*
           如果Customer类中没有重写toString()方法
           String s = String.valueOf(new Customer());
           System.out.println(s);
           输出：F6_String类.String类Ⅱ_String方法.Customer@77556fd
         */
    }
}
