package com.example.demo.junit5test.model;

public enum EnumType {
    HELLO("HELLO"),
    WORLD("WORLD"),
    EPAM("EPAM");

    private final String value;

    EnumType(String value) {
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
