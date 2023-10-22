package I9_异常.异常作业.第二题;

/*
  战斗机，是武器，可以移动，可以射击
 */
public class Fighter extends Weapon implements Moveble,Shootable{
    String s = "战斗机";
    @Override
    public void move() {
        System.out.println("战斗机起飞");
    }

    @Override
    public void shoot() {
        System.out.println("战斗机开炮");
    }

    public String toString() {
        return s;
    }
}
