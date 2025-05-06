package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-27 23:00:00
 * @apiNote 全表操作更新/删除 表集合
 */
@Data
@Component
@ConfigurationProperties(prefix = "allow-tables")
public class AllowTables {

    /**
     * 启用
     */
    private boolean enable = false;

    /**
     * 允许全表删除的表
     */
    private List<String> delete;

    /**
     * 允许全表更新的表
     */
    private List<String> update;
}
