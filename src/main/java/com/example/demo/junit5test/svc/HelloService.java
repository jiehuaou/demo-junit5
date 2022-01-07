package com.example.demo.junit5test.svc;

import com.example.demo.junit5test.model.Box;
import com.example.demo.junit5test.repo.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class HelloService {

//    @Autowired
    HelloRepository helloRepository;

    public HelloService(HelloRepository helloRepository){
        this.helloRepository = helloRepository;
    }

    public String get() {
        return helloRepository.get();
    }

    public String getData(Long id) {
        return helloRepository.getData(id);
    }

    public void createData(String data) {
        helloRepository.create(data);
    }

    public Mono<String> findData(Long id){
        return helloRepository.findData(id);
    }

    public String greet(String id) {
        return "Hello, " + id;
    }

    public Mono<Double> getHeight(Long id) {
        Mono<Box> box = helloRepository.findBox(id);
        return box.map(Box::getHeight);
    }
}
