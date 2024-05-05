package I9_异常.异常作业.第二题;

/*
  物资运输飞机，是武器，但是只能移动，不能攻击
 */
public class WuZiFeiJi extends Weapon implements Moveble{
    String s4 = "物资机";

    @Override
    public void move() {
        System.out.println("物资飞机起飞");
    }

    @Override
    public String toString() {
        return s4;
    }
}