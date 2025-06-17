package Q17_23种设计模式.A工厂模式.E_默认方法;

import Q17_23种设计模式.A工厂模式.D_接口分离原则.ProductB;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-06-17 08:00:00
 */
public interface ProductD {
	void operate();
}



// 具体产品-混合动力车(超跑)
class ProductHybrid1 implements Q17_23种设计模式.A工厂模式.D_接口分离原则.ProductB {
	@Override
	public void operate() {
		System.out.println("Operating Product B1");
	}
}
// 具体产品-混合动力车(轿车)
class ProductHybrid2 implements ProductB {
	@Override
	public void operate() {
		System.out.println("Operating Product B2");
	}
}
