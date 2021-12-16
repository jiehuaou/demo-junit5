package com.example.demo.junit5test;

import com.example.demo.junit5test.controller.MyController;
import com.example.demo.junit5test.repo.HelloRepository;
import com.example.demo.junit5test.svc.HelloService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith({ SpringExtension.class })
@WebFluxTest(controllers = MyController.class)
@Import({ HelloService.class})
public class WebFluxControllerTest {
//    @MockBean
//    private HelloRepository helloRepository;

    @MockBean
    private HelloService helloService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testGetData() {
        Mockito.when(helloService.findData(123L)).thenReturn(Mono.just("hello:123"));

        webTestClient.get()
                .uri("/data/123")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class)
                .consumeWith(System.out::println)
                .isEqualTo("hello:123");

        Mockito.verify(helloService, Mockito.times(1)).findData(123L);
    }
}
