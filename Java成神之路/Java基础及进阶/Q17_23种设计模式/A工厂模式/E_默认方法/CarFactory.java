package Q17_23种设计模式.A工厂模式.E_默认方法;

/**
 * @Author 游家纨绔
 * @Description TODO 抽象工厂
 * @Date 2025-06-16 19:00:00
 */
interface CarFactory {
	ProductA createProductA();
	ProductB createProductB();

	// 新产品默认实现（可选）
	default ProductC createWatch() {
		throw new UnsupportedOperationException("Watch not supported");
	}
	default ProductD createSpeaker() {
		throw new UnsupportedOperationException("Watch not supported");
	}
}



// 品牌-长城哈弗
class HavalFactory implements CarFactory {
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
		return CarFactory.super.createWatch();
	}

	@Override
	public ProductD createSpeaker() {
		return CarFactory.super.createSpeaker();
	}
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
