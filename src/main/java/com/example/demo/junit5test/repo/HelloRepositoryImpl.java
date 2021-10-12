package com.example.demo.junit5test.repo;

import org.springframework.stereotype.Repository;

@Repository
public class HelloRepositoryImpl implements HelloRepository{
    @Override
    public String get() {
        return "junit5";
    }
}
