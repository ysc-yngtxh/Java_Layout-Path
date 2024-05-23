package com.example.config;

import com.example.conditional.ENCondition;
import com.example.conditional.ZHCondition;
import com.example.entity.Brand;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
// @ConditionalOnProperty: 可以作用于方法或者类上，在application.properties 或 application.yml 文件中
//                         将配置文件中的值和havingValue的值对比，如果一样则加载Bean。比如：
//                         custom.application.property.enable=true，havingValue="true"，加载Bean
//                         如果在配置文件中没有该配置项，是否加载Bean: matchIfMissing = true 表示加载，默认值是 false。
@ConditionalOnProperty(value="custom.application.property.enable", havingValue="true", matchIfMissing=true)

// prefix表示配置文件里节点前缀，name用来从application.properties中读取某个属性值，havingValue表示目标值。
// prefix + name为空，则返回false;
// prefix + name不为空，则将该值与havingValue指定的值进行比较，如果一样则返回true，否则返回false。
// 返回值为false，则该configuration不生效，为true则生效。
// @ConditionalOnProperty(prefix="custom.application.property", name="enable", havingValue="true")

// 当给定的在bean存在时,则实例化当前Bean，如果这个bean没有注册到ioc里，@ConditionalOnBean可以让当前Bean也不进行注册；
// 与之相反，有时候我们需要某个 Bean 不存在于应用上下文时才会加载，那么我们会用到 @ConditionalOnMissingBean 注解
@ConditionalOnBean(SpringConfig.class)

// 指定资源是否存在于 classpath 中，如果不存在，抛出异常；
@ConditionalOnResource(resources = "logback-spring.xml")
public class ConditionalConfig {

    // @Conditional给定的类，在一些需要条件满足才是实例化的类中，使用此@Conditional注解，
    // 比如项目中需要根据不同的场景使用不同的消息中间件，在消息队列的实例化bean上，
    // 使用@Conditional注解，根据配置文件的不同，来判断哪个bean需要加载到Spring容器中。
    @Bean
    @Conditional(ZHCondition.class) // 中文
    public Brand brandZH() {
        return new Brand(1L, "红楼梦", null, null, "中国古典书籍");
    }

    @Bean
    @Conditional(ENCondition.class) // 英文
    public Brand brandCN() {
        return new Brand(2L, "哈姆雷特", null, null, "外国经典名著");
    }

    // @ConditionalOnExpression：可以给定多个配置属性一起进行判断,只有当所有属性都为 true 的时候才注册 brandExpression，
    // 其中 :true 表示如果没有为该属性设置值，则为该属性设置默认值true, 其实这就是@Vaue注解的规范，一切 SpEL 都可以应用在这里.
    @Bean
    @ConditionalOnExpression("${custom.expression.enable1:true} and ${custom.expression.enable2:true}")
    // @ConditionalOnExpression("${custom.expression.enable2:true} or ${custom.expression.enable3:false}")
    public Brand brandExpression() {
        return new Brand(3L, "小游驾到", null, null, "小游语录");
    }
}