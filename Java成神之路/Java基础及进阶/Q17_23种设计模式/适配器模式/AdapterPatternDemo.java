package Q17_23种设计模式.适配器模式;

public class AdapterPatternDemo {
    public static void main(String[] args) {
        // 使用类适配器
        USASocket classAdapter = new EuropeToUSAAdapter();
        System.out.println("=== 类适配器 ===");
        classAdapter.flatPins();
        
        // 使用对象适配器
        EuropeanPlug plug = new EuropeanPlug();
        USASocket objectAdapter = new EuropeToUSAAdapter2(plug);
        System.out.println("\n=== 对象适配器 ===");
        objectAdapter.flatPins();
    }
}
