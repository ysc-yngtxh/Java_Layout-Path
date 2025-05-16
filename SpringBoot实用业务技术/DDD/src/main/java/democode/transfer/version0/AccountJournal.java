package democode.transfer.version0;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 账户日志
 *
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@AllArgsConstructor
public class AccountJournal {

	private String type;
	private BigDecimal oldBalance;
	private BigDecimal newBalance;
}
