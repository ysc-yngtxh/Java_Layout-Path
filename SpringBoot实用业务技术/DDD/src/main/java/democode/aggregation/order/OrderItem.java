package democode.aggregation.order;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Getter
public class OrderItem {
    private long orderId;
    private long itemId;
    private BigDecimal price;
}
