package com.example;

import com.example.condition.JettyCondition;
import com.example.condition.TomcatCondition;
import com.example.webServer.JettyWebServer;
import com.example.webServer.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-01 22:40
 * @apiNote TODO
 */
@Configuration
public class WebServerAutoConfiguration {

    @Bean
    @Conditional(TomcatCondition.class)
    public TomcatWebServer tomcatWebServer(){
        return new TomcatWebServer();
    }

    @Bean
    @Conditional(JettyCondition.class)
    public JettyWebServer jettyWebServer(){
        return new JettyWebServer();
    }
}
