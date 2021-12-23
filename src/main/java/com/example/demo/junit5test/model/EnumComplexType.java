package com.example.demo.junit5test.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EnumComplexType {
    HELLO("HE", 1),
    WORLD("WR", 2),
    EPAM("EP", 3);
    private final String value;

    public Integer getIndex() {
        return index;
    }

    private final Integer index;

    EnumComplexType(String value, Integer index) {
        this.value = value;
        this.index = index;
    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString() {
        return String.format("COMPLEX[%s,%d]", value, index);
    }

    @JsonCreator
    public static EnumComplexType createEnum(int index) {
        if(index==1) return EnumComplexType.HELLO;
        if(index==2) return EnumComplexType.WORLD;
        if(index==3) return EnumComplexType.EPAM;
        throw new IllegalArgumentException("wrong index "+index);
    }
}
