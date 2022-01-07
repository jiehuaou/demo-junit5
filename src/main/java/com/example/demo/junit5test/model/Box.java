package com.example.demo.junit5test.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)  // include super
@Getter
public class Box extends Rectangle {

    private double height;
}