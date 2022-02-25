package com.example.demo.junit5test.conditional;


public class MyRepositoryNew implements MyRepository {
    final SubRepository subRepository;

    public MyRepositoryNew(SubRepository subRepository) {
        this.subRepository = subRepository;
    }

    @Override
    public String hello() {
        return "New->" + subRepository.world();
    }
}
