package com.example.demo.junit5test;

import com.example.demo.junit5test.model.DataWithEnum;
import com.example.demo.junit5test.model.EnumType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnumTests {
    @Test
    public void testFromString1(){
        EnumType enumType = EnumType.valueOf("EPAM");
        System.out.println(enumType);
        Assertions.assertEquals(enumType, EnumType.EPAM);
    }

    @Test
    public void testFromString2(){
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            EnumType enumType = EnumType.valueOf("EPAM-X");
            System.out.println(enumType);
        });
        Assertions.assertTrue(ex.getMessage().contains("EnumType.EPAM-X"));
        //Assertions.assertEquals(enumType, EnumType.EPAM);
    }

    @Test
    public void testDataWithEnum1() throws JsonProcessingException {
        DataWithEnum dataWithEnum = DataWithEnum.builder().data("foo").enumType(EnumType.EPAM).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String ss = objectMapper.writeValueAsString(dataWithEnum);
        System.out.println(ss);
    }
    //{"data":"foo","enumType":"EPAM"}
    @Test
    public void testDataWithEnum2() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        DataWithEnum dataWithEnum = objectMapper.readValue("{\"data\":\"foo\",\"enumType\":\"EPAM\"}", DataWithEnum.class);
        System.out.println(dataWithEnum);
        Assertions.assertEquals(dataWithEnum.getEnumType(), EnumType.EPAM);
        Assertions.assertEquals(dataWithEnum.getData(), "foo");
    }
}
