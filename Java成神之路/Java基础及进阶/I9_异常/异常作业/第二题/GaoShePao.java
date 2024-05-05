package I9_异常.异常作业.第二题;

/*
  高射炮是武器，但是不能移动，只能攻击
 */
public class GaoShePao extends Weapon implements Shootable{
    String s3 = "高射炮";

    @Override
    public void shoot() {
        System.out.println("高射炮开炮！！！");
    }

    @Override
    public String toString() {
        return s3;
    }
}