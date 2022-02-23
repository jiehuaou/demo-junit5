package com.example.demo.junit5test;

import com.example.demo.junit5test.model.Box;
import com.example.demo.junit5test.repo.HelloRepository;
import com.example.demo.junit5test.svc.HelloService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {

    @Mock
    private HelloRepository helloRepository;

    @InjectMocks
    private HelloService helloService;

    @Test
    public void testCreate(){

        Mockito.doNothing().when(helloRepository).create(Mockito.anyString());
        helloService.createData("sample");
        Mockito.verify(helloRepository, Mockito.times(1))
                .create("sample");
    }

    /**
     * Mockito.mock == @Mock
     */
    @Test
    public void testGetHeight(){

        Box box = Mockito.mock(Box.class);
        Mockito.when(box.getHeight()).thenReturn(100.0);
        // given
        Mockito.when(helloRepository.findBox(ArgumentMatchers.any(Long.class))).thenReturn(Mono.just(box));

        // when
        Mono<Double> result = helloService.getHeight(1L);

        // then
        Assertions.assertEquals(100.0, result.block());
        Mockito.verify(helloRepository, Mockito.times(1))
                .findBox(1L);
    }

    /**
     * Mockito thenAnswer(lambda) -> generate dynamic mock value
     */
    @Test
    public void testMockitiAnswer1(){
        // given
        HelloRepository repository = Mockito.mock(HelloRepository.class);
        Mockito.when(repository.findData(Mockito.anyLong())).thenAnswer(e->{
            Long arg = e.getArgument(0);
            return Mono.just(String.format("this is %d", arg));
        });

        Mono<String> data123 = repository.findData(123L);
        System.out.println(data123.block());
        StepVerifier.create(data123)
                .expectNext("this is 123")
                .verifyComplete();

        Mono<String> data996 = repository.findData(996L);
        System.out.println(data996.block());
        StepVerifier.create(data996)
                .expectNext("this is 996")
                .verifyComplete();
    }

    interface SampleInterface{
        String invoke(String link, List data);
    }

    /**
     * !!! it's required !!!
     * argument are either all raw values or all matchers.
     */
    @Test
    public void testArgumentMatchers(){
        SampleInterface sampleInterface = Mockito.mock(SampleInterface.class);

        // argument are all raw values
        Mockito.when(sampleInterface.invoke("123", new ArrayList<>())).thenReturn("hello");
        String result = sampleInterface.invoke("123", new ArrayList<>());
        Assertions.assertEquals("hello", result);
        Mockito.verify(sampleInterface, Mockito.times(1)).invoke("123", new ArrayList());

        // argument are all matchers.
        Mockito.when(sampleInterface.invoke(Mockito.eq("996"), Mockito.any(List.class))).thenReturn("world");
        String result2 = sampleInterface.invoke("996", new ArrayList<>());
        Assertions.assertEquals("world", result2);
        Mockito.verify(sampleInterface, Mockito.times(1)).invoke("996", new ArrayList<>());

        // 2 times for any-String and any-object
        Mockito.verify(sampleInterface, Mockito.times(2))
                .invoke(Mockito.anyString(), Mockito.any());
    }
}
