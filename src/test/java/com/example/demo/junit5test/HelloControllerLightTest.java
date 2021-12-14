package com.example.demo.junit5test;


import com.example.demo.junit5test.controller.HelloController;
import com.example.demo.junit5test.svc.HelloService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * only load web layer, but you need to inject the dependency bean.
 */
@WebMvcTest(controllers = {HelloController.class})
public class HelloControllerLightTest {
    @Autowired
    private MockMvc mockMvc;  // need spring-boot-starter-web

    @MockBean
    private HelloService helloService;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        // when
        Mockito.when(helloService.greet("333")).thenReturn("Hello, 333");
        // then
        this.mockMvc.perform(get("/hello/333"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, 333")));
    }
}
