package Q17_23种设计模式.A工厂模式.A_简单工厂模式;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-06-16 19:00:00
 */
public class SimpleCarFactory {

	public CarFactory getCarInstance(String orderType) {
		switch (orderType) {
			case "特斯拉":
				return new Tesla();
			case "小米":
				return new Xiaomi();
			case "坦克":
				return new Tank();
			default:
				throw new IllegalArgumentException("Unknown type");
		}
	}

}
