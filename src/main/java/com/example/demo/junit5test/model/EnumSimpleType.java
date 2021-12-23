package com.example.demo.junit5test.model;

/**
 * use valueOf() --> EnumSimpleType
 */
public enum EnumSimpleType {
    HELLO("HE"),
    WORLD("WR"),
    EPAM("EP");

    private final String value;

    EnumSimpleType(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }


    @Override
    public String toString() {
        return value;
    }


}
