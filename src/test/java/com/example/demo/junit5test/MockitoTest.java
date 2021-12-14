package com.example.demo.junit5test;

import com.example.demo.junit5test.repo.HelloRepository;
import com.example.demo.junit5test.svc.HelloService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {

    @Mock
    private HelloRepository helloRepository;

    @InjectMocks
    private HelloService helloService;

    @Test
    public void testCreate(){
        Mockito.doNothing().when(helloRepository).create(Mockito.anyString());
        helloService.createData("hello");
        Mockito.verify(helloRepository, Mockito.times(1))
                .create("hello");
    }
}
