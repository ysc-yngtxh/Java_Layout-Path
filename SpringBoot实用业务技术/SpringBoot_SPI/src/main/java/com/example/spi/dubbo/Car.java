package com.example.spi.dubbo;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-19 08:41
 * @apiNote TODO
 */
@SPI  // TODO 注意⚠️：只有使用 Dubbo SPI 时才需要该@SPI注解
public interface Car {
    void sayHello();
}
