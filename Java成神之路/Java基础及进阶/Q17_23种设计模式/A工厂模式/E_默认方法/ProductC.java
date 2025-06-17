package Q17_23种设计模式.A工厂模式.E_默认方法;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-06-17 08:00:00
 */
public interface ProductC {
	void createWatch();
}



// 具体产品-纯电车(超跑)
class ProductElectricity1 implements ProductC {
	@Override
	public void createWatch() {
		System.out.println("Operating Product B1");
	}
}
// 具体产品-纯电车(轿车)
class ProductElectricity2 implements ProductC {
	@Override
	public void createWatch() {
		System.out.println("Operating Product B2");
	}
}
