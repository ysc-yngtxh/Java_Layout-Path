package Q17_23种设计模式.A工厂模式.D_接口分离原则;

/**
 * @Author 游家纨绔
 * @Description TODO 抽象产品族-新能源
 * @Date 2025-06-16 19:00:00
 */
public interface ProductB {
	void operate();
}



// 具体产品-新能源(超跑)
class ProductNewEnergy1 implements ProductB {
	@Override
	public void operate() {
		System.out.println("Operating Product B1");
	}
}
// 具体产品-新能源(轿车)
class ProductNewEnergy2 implements ProductB {
	@Override
	public void operate() {
		System.out.println("Operating Product B2");
	}
}
