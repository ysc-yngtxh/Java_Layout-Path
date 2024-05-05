package democode.transfer.version1;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@AllArgsConstructor
public class AccountJournal {
    private String type;
    private BigDecimal oldBalance;
    private BigDecimal newBalance;
}