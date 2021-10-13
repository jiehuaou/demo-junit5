package com.example.demo.junit5test;

import com.example.demo.junit5test.repo.HelloRepository;
import com.example.demo.junit5test.svc.HelloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * service level test
 * MockitoExtension + Mock (org.mockito) + InjectMocks (org.mockito)
 */

@ExtendWith({ MockitoExtension.class })
public class MyServerTests {

    @Mock
    private HelloRepository helloRepository;

    @InjectMocks // auto inject helloRepository
    private HelloService helloService;
    //private HelloService helloService = new HelloService();


    @BeforeEach
    void setMockOutput() {
        when(helloRepository.get()).thenReturn("Hello Mockito From Repository");
        //helloService = new HelloService(helloRepository);
    }

    @DisplayName("Test Mock helloService + helloRepository")
    @Test
    void testGet() {
        assertEquals("Hello Mockito From Repository", helloService.get());
    }

}
