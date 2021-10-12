package com.example.demo.junit5test;

import com.example.demo.junit5test.repo.HelloRepository;
import com.example.demo.junit5test.svc.HelloService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * service level test
 * SpringExtension + ContextConfiguration + Autowired
 */
@Log4j2
@ExtendWith({ SpringExtension.class })
@ContextConfiguration(classes = TestAppConfig.class)
public class MyServerTests2 {

    @Autowired
    private HelloRepository helloRepository;

    @MockBean
    private HelloService helloService ;

    @BeforeEach
    void setMockOutput() {
        // when(helloRepository.get()).thenReturn("Hello Mockito From Repository");
    }

    @DisplayName("Test helloService + helloRepository via SpringExtension")
    @Test
    void testGet() {
        //
        log.info("repo.get --> {}", helloRepository.get());
        log.info("svc.get --> {}", helloService.get());
        assertEquals("junit5", helloRepository.get());
        assertEquals("junit5", helloService.get());
    }

}
