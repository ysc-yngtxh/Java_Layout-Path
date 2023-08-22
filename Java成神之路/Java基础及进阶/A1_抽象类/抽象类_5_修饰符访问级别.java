package A1_抽象类;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2023/1/12 17:36
 */
public class 抽象类_5_修饰符访问级别 {
    /**
       修饰符	   当前类  包  子类  其他包
       public	    √	  √	   √	 √
       protected	√	  √	   √	 ×
       no modifier	√	  √	   ×	 ×
       private	    √	  ×	   ×	 ×
    */
    public static void main(String[] args) {
        // TODO 同一个包下（不能访问到被private修饰的属性）
        PageInternal pageInternal = new PageInternal();
        pageInternal.name = "叶诗琪";
        pageInternal.age = 21;
        pageInternal.sex = false;
    }
}

class PageInternal{
    public String name;
    protected int age;
    Boolean sex;
    private String address;
}

class son extends PageInternal{

}
