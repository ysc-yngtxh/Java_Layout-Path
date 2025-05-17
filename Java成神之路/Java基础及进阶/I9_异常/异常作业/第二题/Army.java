package I9_异常.异常作业.第二题;

/*
 * 军队
 */
public class Army {

	/*
	  武器数组
	 */
	private Weapon[] w;

	public Army(int count) {
		// count 武器数量
		// 动态初始化数组中每一个元素默认值都是null
		// 武器数组是有了，但是武器数组中没有放武器
		this.w = new Weapon[count];  // 动态数组
	}

	// 将武器加入数组
	public void addWeapon(Weapon weapon) throws AddWeaponException {
		for (int i = 0; i < w.length; i++) {
			if (null == w[i]) {
				w[i] = weapon;
				System.out.println(weapon + ":武器添加成功");
				return;
			}
		}
		// 程序如果执行到此处说明，武器没有添加成功
		throw new AddWeaponException("武器数量已达上限");
	}

	// 所有可攻击的武器攻击
	public void attackAll() {
		// 遍历数组
		for (int i = 0; i < w.length; i++) {
			if (w[i] instanceof Shootable) {
				// 调用子类方法
				Shootable shootable = (Shootable) w[i];
				shootable.shoot();
			}
		}
	}

	// 所有可移动的武器移动
	public void moveAll() {
		// 遍历数组
		for (int i = 0; i < w.length; i++) {
			if (w[i] instanceof Moveble) {
				Moveble moveble = (Moveble) w[i];
				moveble.move();
			}
		}
	}
}
