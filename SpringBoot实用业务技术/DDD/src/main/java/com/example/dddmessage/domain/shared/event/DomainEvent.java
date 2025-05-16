package com.example.dddmessage.domain.shared.event;

import java.util.Date;

/**
 * 领域事件可能有唯一标识（比如付款的序列化）
 * 也可能唯一性通过其它的方面的条件来标识。
 *
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public interface DomainEvent {

	/**
	 * 返回事件的唯一ID
	 *
	 * @return 事件的唯一ID
	 */
	String id();

	/**
	 * 返回事件的产生的时间
	 *
	 * @return 事件的产生的时间
	 */
	Date timestamp();

	/**
	 * 判断领域事件是否一致
	 *
	 * @param other 另外一个领域事件
	 * @return <code>true</code> 如果当前比较的两个领域事件一致返回true
	 */
	boolean sameEventAs(DomainEvent other);
}
