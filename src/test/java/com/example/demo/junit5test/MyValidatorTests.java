package com.example.demo.junit5test;


import com.example.demo.junit5test.logic.MyService;
import com.example.demo.junit5test.logic.MyValidationAdvice;

import com.example.demo.junit5test.model.CatalogueItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * use ProxyFactory with MethodBeforeAdvice (MyValidationAdvice),
 * to trigger the validation
 */

public class MyValidatorTests {

    MyService testValidator;

    @BeforeEach
    public void initializeTest() {
        // No validation wrapper used.
        ProxyFactory factory = new ProxyFactory(new MyService());
        factory.addAdvice(new MyValidationAdvice());
        testValidator = (MyService) factory.getProxy();
    }


    @Test
    public void testInvalidId(){

        Exception ex1 = assertThrows(javax.validation.ConstraintViolationException.class, ()->{
            CatalogueItem c3 = new CatalogueItem(1L, "www", "abc", "aaa@163.com");
            testValidator.doSomething(c3);
        });
        Assertions.assertTrue(ex1.getMessage().contains("id must be >= 2L"));

    }

    @Test
    @DisplayName("test invalid mail format")
    public void testInvalidEmail(){

        Exception ex2 = assertThrows(javax.validation.ConstraintViolationException.class, ()->{
            CatalogueItem c3 = new CatalogueItem(2L, "www", "abc", "aaa-163.com");
            testValidator.doSomething(c3);
        });
        Assertions.assertTrue(ex2.getMessage().contains("please input email format"));

    }

    @Test
    public void testValid(){
        CatalogueItem c3 = new CatalogueItem(2L, "www", "abc", "aaa@163.com");
        String result = testValidator.doSomething(c3);
        assertTrue(result.equals("Valid"));
    }
}
