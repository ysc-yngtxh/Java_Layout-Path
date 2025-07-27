package Q17_23种设计模式.策略模式;

public interface PaymentStrategy {
    void pay(double amount);
}

// 支付宝支付
class AlipayStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("使用支付宝支付: " + amount + "元");
        // 实际的支付宝支付逻辑...
    }
}
// 微信支付
class WechatPayStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("使用微信支付: " + amount + "元");
        // 实际的微信支付逻辑...
    }
}
// 银行卡支付
class BankCardStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("使用银行卡支付: " + amount + "元");
        // 实际的银行卡支付逻辑...
    }
}
