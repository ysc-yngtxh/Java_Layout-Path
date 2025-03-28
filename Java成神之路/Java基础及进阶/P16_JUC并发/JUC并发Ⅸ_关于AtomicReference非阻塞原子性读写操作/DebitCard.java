package P16_JUC并发.JUC并发Ⅸ_关于AtomicReference非阻塞原子性读写操作;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-21 14:30
 * @apiNote TODO
 */
public class DebitCard {

    // 账户名
    private final String account;
    // 账户金额
    private final int amount;

    public DebitCard(String account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    public String getAccount() {
        return account;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "account='" + account + '\'' +
                ", amount=" + amount +
                '}';
    }
}
