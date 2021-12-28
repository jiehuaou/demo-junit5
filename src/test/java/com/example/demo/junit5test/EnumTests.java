package com.example.demo.junit5test;

import com.example.demo.junit5test.model.EnumComplexType;
import com.example.demo.junit5test.model.EnumSimpleType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
public class EnumTests {

    @Getter
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class SimpleDataDTO {
        private String data;
        private EnumSimpleType simple;  // use valueOf()

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
    public void testFromString1(){
        EnumSimpleType enumType = EnumSimpleType.valueOf("EPAM");
        log.info(enumType);
        Assertions.assertEquals(enumType, EnumSimpleType.EPAM);
    }

    @Test
    public void testFromStringException(){
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, ()->{
            log.info("try convert");
            EnumSimpleType enumType = EnumSimpleType.valueOf("EPAM-X");

        });
        log.info(ex.getMessage());
        Assertions.assertTrue(ex.getMessage().contains("EPAM-X"));
        Assertions.assertTrue(ex.getMessage().contains("No enum constant"));
    }

    @Test
    public void testDataWithEnum2String() throws JsonProcessingException {
        SimpleDataDTO dataWithEnum = SimpleDataDTO.builder().data("foo").simple(EnumSimpleType.EPAM).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String ss = objectMapper.writeValueAsString(dataWithEnum);
        log.info(ss);
    }
    //{"data":"foo","enumType":"EPAM"}
    @Test
    public void testDataWithEnumFromString() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDataDTO dataWithEnum = objectMapper.readValue("{\"data\":\"foo\",\"simple\":\"EPAM\"}",
                SimpleDataDTO.class);
        System.out.println(dataWithEnum);
        Assertions.assertEquals(dataWithEnum.getSimple(), EnumSimpleType.EPAM);
        Assertions.assertEquals(dataWithEnum.getData(), "foo");
    }



    @Test
    public void testComplexData() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        DataDTO dataWithEnum = objectMapper.readValue("{\"data\":\"foo\",\"complex\":2}",
                DataDTO.class);
        System.out.println(dataWithEnum);
        Assertions.assertEquals(dataWithEnum.complex, EnumComplexType.WORLD);
        Assertions.assertEquals(dataWithEnum.data, "foo");
    }


}
