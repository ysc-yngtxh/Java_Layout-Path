package Q17_23种设计模式.策略模式;

public class StrategyPatternDemo {
    public static void main(String[] args) {
        // 创建支付上下文
        PaymentContext context = new PaymentContext(new AlipayStrategy());
        
        // 使用支付宝支付
        context.executePayment(100.0);
        
        // 切换到微信支付
        context.setStrategy(new WechatPayStrategy());
        context.executePayment(200.0);
        
        // 切换到银行卡支付
        context.setStrategy(new BankCardStrategy());
        context.executePayment(300.0);
    }
}
