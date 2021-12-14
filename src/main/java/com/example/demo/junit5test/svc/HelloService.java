package com.example.demo.junit5test.svc;

import com.example.demo.junit5test.repo.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String greet(String id) {
        return "Hello, " + id;
    }
}
