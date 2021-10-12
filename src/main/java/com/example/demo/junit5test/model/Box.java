package com.example.demo.junit5test.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)  // include super
public class Box extends Rectangle {

    private double height;
}