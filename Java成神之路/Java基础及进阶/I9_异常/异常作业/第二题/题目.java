package I9_异常.异常作业.第二题;

/* 开放性题目，随意发挥
 *     写一个类Army,代表一支军队，这个类有一个属性Weapon数组w(用来存储该军队所拥有的所有的武器)
 *     该类还提供一个构造方法，在构造方法里通过传一个int类型的参数来限定该类所能拥有的最大武器数量
 *     并用这一大小来初始化数组w。
 *
 *     该类还提供一个方法addWeapon(Weapon wa),表示把参数wa所代表的武器加入到数组w中
 *     在这个类中还定义两个方法attackAll()让w数组中的所有武器攻击；
 *     以及moveAll()让w数组中的所有可移动的武器移动
 *
 *     写一个主方法去测试以上程序
 *
 *     提示：
 *          Weapon是一个父类，应该有很多子武器
 *          这些子武器应该有一些是可移动的，有一些是可攻击的
 */
public class 题目 {

	public static void main(String[] args) {
		// 构建一个军队
		Army army = new Army(4);
		// 创建武器对象
		Fighter fighter = new Fighter();
		Tank tank = new Tank();
		WuZiFeiJi wuZiFeiJi = new WuZiFeiJi();
		GaoShePao gaoShePao = new GaoShePao();
		Fighter fighter1 = new Fighter();
		try {
			army.addWeapon(fighter);
			army.addWeapon(tank);
			army.addWeapon(wuZiFeiJi);
			army.addWeapon(gaoShePao);
			army.addWeapon(fighter1);
		} catch (AddWeaponException e) {
			System.out.println(e.getMessage());
		}
		// 所以能攻击的进行攻击
		army.attackAll();
		// 所有能移动的进行移动
		army.moveAll();
	}

}
