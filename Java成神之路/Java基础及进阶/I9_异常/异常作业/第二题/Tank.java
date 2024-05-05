package I9_异常.异常作业.第二题;

/*
  坦克是一个武器，可移动，可攻击
 */
public class Tank extends Weapon implements Moveble {
    String s2 = "坦克";

    @Override
    public void move() {
        System.out.println("坦克移动");
    }


    public void shoot() {
        System.out.println("坦克开炮");
    }

    @Override
    public String toString() {
        return s2;
    }
}