package Q17_23种设计模式.模版方法模式;

/**
 * @Author 游家纨绔
 * @Date 2025-06-16 17:00:00
 */
public class TemplateMethodPattern {
	public static void main(String[] args) {
		AbstractClass tm = new ConcreteClass();
		tm.TemplateMethod();
	}
}

// 抽象类
abstract class AbstractClass {
	// 模板方法
	public void TemplateMethod() {
		SpecificMethod();
		abstractMethod1();
		abstractMethod2();
	}

	// 具体方法
	public void SpecificMethod() {
		System.out.println("抽象类中的具体方法被调用...");
	}

	// 抽象方法1
	public abstract void abstractMethod1();

	// 抽象方法2
	public abstract void abstractMethod2();
}

// 具体子类
class ConcreteClass extends AbstractClass {
	public void abstractMethod1() {
		System.out.println("抽象方法1的实现被调用...");
	}

	public void abstractMethod2() {
		System.out.println("抽象方法2的实现被调用...");
	}
}
