package com.example.spi.dubbo;

import org.apache.dubbo.common.extension.ExtensionScope;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-19 08:00
 * @apiNote TODO
 */
// 注意⚠️：1、只有使用 Dubbo SPI 时才需要该@SPI注解
//        2、需要显式指定属性 scope（FRAMEWORK, APPLICATION, MODULE, SELF）
//           FRAMEWORK：全JVM唯一实例，所有应用/模块共用。
//           APPLICATION：同一个Dubbo应用内共享实例，不同应用隔离。
//           MODULE：同一个模块（如Spring Context）内共享，跨模块隔离。
//           SELF：每次调用都生成新实例，彻底隔离。
@SPI(scope = ExtensionScope.FRAMEWORK)
public interface Car {

    void sayHello();
}
