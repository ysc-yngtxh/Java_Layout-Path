package Q17_23种设计模式.适配器模式;

// 被适配者 - 欧洲标准插头
public class EuropeanPlug {
    public void roundPins() {
        System.out.println("使用两圆脚插头供电");
    }
}


// 类适配器 - 通过继承实现
class EuropeToUSAAdapter extends EuropeanPlug implements USASocket {
    @Override
    public void flatPins() {
        System.out.println("适配器将两扁脚转换为两圆脚");
        super.roundPins();
    }
}
