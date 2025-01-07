package M13_反射.反射Ⅱ_反射Field;

public class Student {

    // 6个Field，分别采用了不同的访问控制权限修饰符
    public String name;

    protected int age;
    boolean sex;
    private int no;
    private String level;
    private final String bounds = "11";

    public static final double MATH_PI = 3.1415926;

    public Student(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public String getBounds() {
        return bounds;
    }
}
