package com.example.demo.junit5test.conditional;



public class MyRepositoryOld implements MyRepository {
    final SubRepository subRepository;

    public MyRepositoryOld(SubRepository subRepository) {
        this.subRepository = subRepository;
    }

    @Override
    public String hello() {
        return "Old->" + subRepository.world();
    }
}
