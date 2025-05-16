package democode.aggregation.order;

import lombok.Getter;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Getter
public class Agreement {

	private int agreementId;
	private int buyerId;
	private int sellerId;
	private Status status;

	public void refunding() {
		// 更新退货退款协议状态
	}

	// 退货/退款协议状态
	public interface Status {

		String REFUNDING = "Refunding"; // 申请退款中
		String REFUND_REJECTED = "RefundRejected";  // 拒绝退款中
	}
}
