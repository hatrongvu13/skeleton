package com.jax.datasource.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
public class DbConfig {

    @Configuration
    @Order(1)
    @RequiredArgsConstructor
    @EnableTransactionManagement
    @EnableJpaRepositories(
            basePackages = {"com.jax.datasource"})
    public class MasterDbConfig{

    }
}
