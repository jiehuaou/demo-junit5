package com.example.demo.junit5test;

import lombok.extern.log4j.Log4j2;

import java.util.function.Function;

/**
 * Lambda benefit :
 *
 * 1. make code simple
 * 2. invoke direct lambda without new class creation
 * 3. make lazy call conditionally
 */
@Log4j2
public class LambdaTests {
    //private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(Lambda.class);
    public static void main(String[] args) {

        makeLazyCall(1);  // lambda not call
        makeLazyCall(2);  // lambda was call
    }

    static void makeLazyCall(int value){
        int i = Math.floorMod(value,2);
        if(i==0){
            /*
            Function<String, String> lazy = (e)->{
                return "hello " + "world";
            };*/
            log.info(()->{
                // this.getClass() not work here
                System.out.println("concat string [hello world] in lambda, where there is no 'This' pointer " );
                return "hello " + "world";
            });
        }else{
            log.info("Lambda was not invoked");
        }
        log.info("lazy call end value={}" , value);
    }
}
