package Q17_23种设计模式.A工厂模式.C_抽象工厂模式;

/**
 * @Author 游家纨绔
 * @Description TODO 抽象工厂
 * @Date 2025-06-16 19:00:00
 */
interface CarFactory {
	ProductA createProductA();
	ProductB createProductB();
}



// 品牌-魏牌坦克
class TankFactory implements CarFactory {
	@Override
	public ProductA createProductA() {
		return new ProductFuel1();
	}

	@Override
	public ProductB createProductB() {
		return new ProductNewEnergy1();
	}
}
// 品牌-特斯拉
class TeslaFactory implements CarFactory {
	@Override
	public ProductA createProductA() {
		return new ProductFuel2();
	}

	@Override
	public ProductB createProductB() {
		return new ProductNewEnergy2();
	}
}
// 品牌-小米
class XiaomiFactory implements CarFactory {
	@Override
	public ProductA createProductA() {
		return new ProductFuel2();
	}

	@Override
	public ProductB createProductB() {
		return new ProductNewEnergy2();
	}
}
