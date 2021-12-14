package com.example.demo.junit5test.repo;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class HelloRepositoryImpl implements HelloRepository{
    @Override
    public String get() {
        return "junit5";
    }

    @Override
    public String getData(Long id) {
        return String.format("data{id:%d}", id);
    }

    /**
     * can finddata except id=0
     */

    @Override
    public Mono<String> findData(Long id) {
        if(id.equals(0L)){
            return Mono.empty();
        }
        return Mono.just(String.format("data{id:%d}", id));
    }

    @Override
    public void create(String data) {
        System.out.println("data created ==> " + data);
    }
}
