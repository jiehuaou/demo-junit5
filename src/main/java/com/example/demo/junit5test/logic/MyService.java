package com.example.demo.junit5test.logic;

import com.example.demo.junit5test.model.CatalogueItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

//import javax.validation.Valid;

@Service
@Validated
public class MyService {
    public String doSomething(@Valid CatalogueItem c){
        return "Valid";
    }
}
