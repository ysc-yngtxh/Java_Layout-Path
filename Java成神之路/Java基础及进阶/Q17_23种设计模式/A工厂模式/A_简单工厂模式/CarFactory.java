package Q17_23种设计模式.A工厂模式.A_简单工厂模式;

/**
 * @Author 游家纨绔
 * @Description TODO 抽象工厂
 * @Date 2025-06-16 19:00:00
 */
public interface CarFactory {
	void run();
}



// 品牌-魏牌坦克
class Tank implements CarFactory {
	@Override
	public void run() {
		System.out.println("魏牌坦克在运行");
	}
}
// 品牌-特斯拉
class Tesla implements CarFactory {
	@Override
	public void run() {
		System.out.println("特斯拉在运行");
	}
}
// 品牌-小米
class Xiaomi implements CarFactory {
	@Override
	public void run() {
		System.out.println("小米在运行");
	}
}
