package Q17_23种设计模式.A工厂模式.D_接口分离原则;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-06-17 08:00:00
 */
public interface CarFactory2 {
	ProductC createWatch();
	ProductD createTracker();
}



// 品牌-长城哈弗
class HavalFactory implements CarFactory, CarFactory2 {
	@Override
	public ProductA createProductA() {
		return new ProductFuel1();
	}

	@Override
	public ProductB createProductB() {
		return new ProductNewEnergy1();
	}

	@Override
	public ProductC createWatch() {
		return null;
	}

	@Override
	public ProductD createTracker() {
		return null;
	}
}
