package com.example.demo.junit5test.conditional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
public class TestMockRepoOld {
    @Mock
    SubRepository subRepository;

    @InjectMocks
    MyRepositoryOld myRepositoryOld;

    @Test
    public void testSuccess(){
        Mockito.when(subRepository.world()).thenReturn("abc");

        String text = myRepositoryOld.hello();

        Assertions.assertEquals("Old->abc", text);

    }
}
