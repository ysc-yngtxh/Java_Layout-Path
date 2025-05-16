package com.example.config;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-27 23:00:00
 * @apiNote 悲观锁 表集合
 */
@Data
@Component
@ConfigurationProperties(prefix = "lock-tables")
public class LockTables {

	private List<String> pessimistic;
}
