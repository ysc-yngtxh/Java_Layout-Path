package com.example.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.core.DatabaseClient;

@Configuration
@EnableR2dbcRepositories
public class R2dbcConfig {
    
    @Bean
    @Primary
    @ConfigurationProperties("spring.r2dbc")
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                ConnectionFactoryOptions.parse("r2dbc:postgresql://localhost:5432/mydb")
                        .mutate()
                        .option(ConnectionFactoryOptions.USER, "root")
                        .option(ConnectionFactoryOptions.PASSWORD, "131474")
                        .build()
        );
    }
    
    @Bean
    @Primary
    public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient.builder()
                             .connectionFactory(connectionFactory)
                             .build();
    }
}
