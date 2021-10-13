package com.example.demo.junit5test;

import com.example.demo.junit5test.repo.HelloRepository;
import com.example.demo.junit5test.svc.HelloService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * service level test
 * SpringExtension + ContextConfiguration + Autowired + MockBean (springframework)
 */

@Log4j2
@ExtendWith({ SpringExtension.class })
@ContextConfiguration(classes = TestAppConfig.class)
public class MyServerTests3 {

    @MockBean
    private HelloRepository helloRepository;

    @Autowired
    private HelloService helloService ;

    @BeforeEach
    void setMockOutput() {
        Mockito
                .when(helloRepository.get())
                .thenReturn("Mock Hello");
    }

    @DisplayName("Test helloService + helloRepository via SpringExtension")
    @Test
    void testGet() {
        //
        log.info("repo.get --> {}", helloRepository.get());
        log.info("svc.get --> {}", helloService.get());
        assertEquals("Mock Hello", helloRepository.get());
        assertEquals("Mock Hello", helloService.get());
    }

}
