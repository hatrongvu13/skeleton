package com.jax.security;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import java.util.Collections;

@EnableMongoAuditing
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    DozerBeanMapper mapper() {
        DozerBeanMapper beanMapper = new DozerBeanMapper();
        beanMapper.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));
        return beanMapper;
    }
}
