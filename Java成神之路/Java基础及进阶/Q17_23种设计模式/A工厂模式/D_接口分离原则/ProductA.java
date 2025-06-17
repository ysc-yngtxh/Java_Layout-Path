package Q17_23种设计模式.A工厂模式.D_接口分离原则;

/**
 * @Author 游家纨绔
 * @Description TODO 抽象产品族-燃油车
 * @Date 2025-06-16 19:00:00
 */
public interface ProductA {
	void use();
}



// 具体产品-燃油车(轿车)
class ProductFuel1 implements ProductA {
	@Override
	public void use() {
		System.out.println("Using Product A1");
	}
}
// 具体产品-燃油车(SUV)
class ProductFuel2 implements ProductA {
	@Override
	public void use() {
		System.out.println("Using Product A2");
	}
}
