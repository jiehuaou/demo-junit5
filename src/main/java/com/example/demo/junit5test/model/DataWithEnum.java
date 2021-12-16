package com.example.demo.junit5test.model;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DataWithEnum {
    private String data;
    private EnumType enumType;

}
