package Q17_23种设计模式.责任链模式;

public class PurchaseRequest {
    private final String purpose; // 用途
    private final double amount;  // 金额
    
    public PurchaseRequest(String purpose, double amount) {
        this.purpose = purpose;
        this.amount = amount;
    }
    
    public String getPurpose() {
        return purpose;
    }
    
    public double getAmount() {
        return amount;
    }
}
