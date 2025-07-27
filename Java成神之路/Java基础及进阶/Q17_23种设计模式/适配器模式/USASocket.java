package Q17_23种设计模式.适配器模式;

// 目标接口 - 美国标准插座
public interface USASocket {
    void flatPins();
}


// 对象适配器 - 通过组合实现
class EuropeToUSAAdapter2 implements USASocket {
    private EuropeanPlug europeanPlug;

    public EuropeToUSAAdapter2(EuropeanPlug europeanPlug) {
        this.europeanPlug = europeanPlug;
    }

    @Override
    public void flatPins() {
        System.out.println("适配器将两扁脚转换为两圆脚");
        europeanPlug.roundPins();
    }
}
