package com.example.demo.junit5test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * scan the component from service and repository package
 */
@Configuration
@ComponentScan({"com.example.demo.junit5test.svc"})
@ComponentScan({"com.example.demo.junit5test.repo"})
public class TestAppConfig {
}
