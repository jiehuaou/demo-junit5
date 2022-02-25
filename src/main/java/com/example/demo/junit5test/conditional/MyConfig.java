package com.example.demo.junit5test.conditional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyConfig {

    @Value("${choose.profile:xyz}")
    public String profile;

    @Bean(name = "my-repository")
    @ConditionalOnProperty(name = "new.config.use", havingValue = "true")
    public MyRepository getRepository1(SubRepository subRepository){
        // ...
        return new MyRepositoryNew(subRepository);
    }

    @Bean(name = "my-repository")
    @ConditionalOnProperty(name = "new.config.use", havingValue = "false")
    public MyRepository getRepository2(SubRepository subRepository){
        // ...
        return new MyRepositoryOld(subRepository);
    }
}
