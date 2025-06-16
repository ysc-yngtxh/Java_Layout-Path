package Q17_23种设计模式.A工厂模式.B_工厂方法模式;

/**
 * @Author 游家纨绔
 * @Description TODO 抽象产品-汽车
 * @Date 2025-06-16 19:00:00
 */
public interface Product {
	/**
	 * 制造汽车品牌
	 */
	void makeCarStyle();
	/**
	 * 制造汽车颜色
	 */
	void makeCarColor();
}



// 具体产品-坦克300
class TankStyle implements Product {
	@Override
	public void makeCarStyle() {
		System.out.println("魏牌坦克300在运行");
	}

	@Override
	public void makeCarColor() {
		System.out.println("黑武士");
	}
}
// 具体产品-特斯拉 model Y
class TeslaStyle implements Product {
	@Override
	public void makeCarStyle() {
		System.out.println("特斯拉 model Y 在运行");
	}

	@Override
	public void makeCarColor() {
		System.out.println("烈焰红");
	}
}
// 具体产品-小米 su 7
class XiaomiStyle implements Product {
	@Override
	public void makeCarStyle() {
		System.out.println("小米 su 7 在运行");
	}

	@Override
	public void makeCarColor() {
		System.out.println("挖孔流星蓝");
	}
}
