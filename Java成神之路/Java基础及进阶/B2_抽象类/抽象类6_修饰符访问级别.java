package B2_抽象类;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2023/1/12 17:36
 */
class PageInternal{
    public String name;
    protected int age;
    Boolean sex;
    final double price = 56.72;
    private String address;
}
class son extends PageInternal{}

public class 抽象类6_修饰符访问级别 {
    /**
        修饰符	     当前类   同一个包中的类    子类(不同包)  所有类
        public        √	          √	            √	       √
        protected     √	          √	            √	       ×
        缺省           √	          √             ×	       ×        [缺省的同包子类可以访问]
        private       √	          ×	            ×	       ×
     */
    public static void main(String[] args) {
        // TODO 同一个包下（不能访问到被private修饰的属性）
        PageInternal pageInternal = new PageInternal();
        pageInternal.name = "曹家千金";
        pageInternal.age = 21;
        pageInternal.sex = false;

        son son = new son();
        System.out.println(son.name);
        System.out.println(son.age);
        System.out.println(son.price);
        System.out.println(son.sex);
    }
}
