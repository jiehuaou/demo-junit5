package com.example.demo.junit5test;

import com.example.demo.junit5test.model.EnumComplexType;
import com.example.demo.junit5test.model.EnumSimpleType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnumTests {
    @Test
    public void testFromString1(){
        EnumSimpleType enumType = EnumSimpleType.valueOf("EPAM");
        System.out.println(enumType);
        Assertions.assertEquals(enumType, EnumSimpleType.EPAM);
    }

    @Test
    public void testFromStringEx(){
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            EnumSimpleType enumType = EnumSimpleType.valueOf("EPAM-X");
            System.out.println(enumType);
        });
        System.out.println(ex.getMessage());
        Assertions.assertTrue(ex.getMessage().contains("EnumType.EPAM-X"));
        Assertions.assertTrue(ex.getMessage().contains("No enum constant"));
    }

    @Test
    public void testDataWithEnum2String() throws JsonProcessingException {
        DataWithEnum dataWithEnum = DataWithEnum.builder().data("foo").enumType(EnumSimpleType.EPAM).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String ss = objectMapper.writeValueAsString(dataWithEnum);
        System.out.println(ss);
    }
    //{"data":"foo","enumType":"EPAM"}
    @Test
    public void testDataWithEnumFromString() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        DataWithEnum dataWithEnum = objectMapper.readValue("{\"data\":\"foo\",\"enumType\":\"EPAM\"}", DataWithEnum.class);
        System.out.println(dataWithEnum);
        Assertions.assertEquals(dataWithEnum.getEnumType(), EnumSimpleType.EPAM);
        Assertions.assertEquals(dataWithEnum.getData(), "foo");
    }

    public static class DataDTO{
        public String data;
        public EnumComplexType complex;  // use JsonCreator

        @Override
        public String toString() {
            return "DataDTO{" +
                    "data='" + data + '\'' +
                    ", complex=" + complex +
                    '}';
        }
    }

    @Test
    public void testComplexData() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        DataDTO dataWithEnum = objectMapper.readValue("{\"data\":\"foo\",\"complex\":2}", DataDTO.class);
        System.out.println(dataWithEnum);
    }

    @Getter
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class DataWithEnum {
        private String data;
        private EnumSimpleType enumType;  // use valueOf()

    }
}
