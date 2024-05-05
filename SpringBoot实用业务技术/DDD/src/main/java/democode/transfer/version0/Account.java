package democode.transfer.version0;

import lombok.Getter;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Getter
public class Account {

    private long id;

    private BigDecimal availableBalance;

    private AccountJournal journal;

    public Account(long id, BigDecimal availableBalance){
        this.id = id;
        this.availableBalance = availableBalance;
    }

    // 收入
    public void income(BigDecimal amount){
        Assert.isTrue(amount.compareTo(BigDecimal.ZERO) > 0, "amount必须大于0");
        BigDecimal oldBalance = this.availableBalance;
        this.availableBalance = this.availableBalance.add(amount);
        this.journal = new AccountJournal("收入", oldBalance, this.availableBalance);
    }

    // 支出
    public void transferTo(Account toAccount, BigDecimal amount){
        Assert.isTrue(availableBalance.compareTo(amount) >= 0, "availableBalance必须大于等于转账金额");
        BigDecimal oldBalance = this.availableBalance;
        this.availableBalance = this.availableBalance.subtract(amount);
        toAccount.income(amount);
        this.journal = new AccountJournal("支出", oldBalance, this.availableBalance);
    }
}
