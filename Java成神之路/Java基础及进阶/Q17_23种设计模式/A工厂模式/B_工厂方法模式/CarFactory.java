package Q17_23种设计模式.A工厂模式.B_工厂方法模式;

/**
 * @Author 游家纨绔
 * @Description TODO 抽象工厂
 * @Date 2025-06-16 19:00:00
 */
public interface CarFactory {
	Product makeCarStyle();
}



// 品牌-魏牌坦克
class Tank implements CarFactory {
	@Override
	public Product makeCarStyle() {
		return new TankStyle();
	}
}
// 品牌-特斯拉
class Tesla implements CarFactory {
	@Override
	public Product makeCarStyle() {
		return new TeslaStyle();
	}
}
// 品牌-小米
class Xiaomi implements CarFactory {
	@Override
	public Product makeCarStyle() {
		return new XiaomiStyle();
	}
}
