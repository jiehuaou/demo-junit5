package com.example.demo.junit5test.conditional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestMockRepoNew {
    @Mock
    SubRepository subRepository;

    @InjectMocks
    MyRepositoryNew myRepositoryNew;

    @Test
    public void testSuccess(){
        Mockito.when(subRepository.world()).thenReturn("abc");

        String text = myRepositoryNew.hello();

        Assertions.assertEquals("New->abc", text);

    }
}
