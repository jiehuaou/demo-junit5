package com.example.demo.junit5test;


import com.example.demo.junit5test.logic.MyValidator;
import com.example.demo.junit5test.logic.ValidationAdvice;
import com.example.demo.junit5test.model.CatalogueItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
//@Import(MyConfig.class)
//@ExtendWith(MockitoExtension.class)
public class MyValidatorTests {

    MyValidator testValidator;

    @BeforeEach
    public void initializeTest() {
        // No validation wrapper used.
        ProxyFactory factory = new ProxyFactory(new MyValidator());
        factory.addAdvice(new ValidationAdvice());
        testValidator = (MyValidator) factory.getProxy();
    }


    @Test
    public void testInvalidId(){

        Exception ex1 = assertThrows(javax.validation.ConstraintViolationException.class, ()->{
            CatalogueItem c3 = new CatalogueItem(1L, "www", "abc", "aaa@163.com");
            testValidator.validate(c3);
        });
        Assertions.assertTrue(ex1.getMessage().contains("id must be >= 2L"));

    }

    @Test
    @DisplayName("test invalid mail format")
    public void testInvalidEmail(){

        Exception ex2 = assertThrows(javax.validation.ConstraintViolationException.class, ()->{
            CatalogueItem c3 = new CatalogueItem(2L, "www", "abc", "aaa-163.com");
            testValidator.validate(c3);
        });
        Assertions.assertTrue(ex2.getMessage().contains("please input email format"));

    }

    @Test
    public void testValid(){
        CatalogueItem c3 = new CatalogueItem(2L, "www", "abc", "aaa@163.com");
        String result = testValidator.validate(c3);
        assertTrue(result.equals("Valid"));
    }
}
