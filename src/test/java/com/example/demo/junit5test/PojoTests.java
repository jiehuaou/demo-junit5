package com.example.demo.junit5test;

//import com.example.crud.demo;

import com.example.demo.junit5test.model.Box;
import com.example.demo.junit5test.model.CatalogueItem;
import com.example.demo.junit5test.model.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class PojoTests {
    @Test
    public void testCatalogueItem(){
        CatalogueItem c1 = CatalogueItem.of( "www", "abc", "aaa@163.com"); //CatalogueItem(1L, "www", "abc");
        CatalogueItem c2 = new CatalogueItem(null, "www", "abc", "aaa@163.com");
        CatalogueItem c3 = new CatalogueItem(1L, "www", "abc", "aaa@163.com");
        Assert.isTrue(c1.equals(c2), "c1 == c2");
        Assert.isTrue(!c2.equals(c3), "c2 != c3");
    }

    @Test
    public void testBox1(){
        Box b1 = new Box(10.0);
        Box b2 = new Box(10.0);
        Box b3 = new Box(11.0);
        String b4 = "10.0";
        String b5 = null;

        Rectangle r1 = new Box(10.0);
        Rectangle r2 = r1;
        Rectangle r3 = new Rectangle();

        b1.equals(b2);

        System.out.println("b1 == b2 => "+(b1 == b2));
        System.out.println("b1 == b3 => "+(b1 == b3));
        System.out.println("b1 == r1 => "+(b1 == r1));
        System.out.println("r1 == r2 => "+(r1 == r2));

        System.out.println("b1.equals(b2) => "+b1.equals(b2));
        System.out.println("b1.equals(b3) => "+b1.equals(b3));
        System.out.println("b1.equals(b4) => "+b1.equals(b4));
        System.out.println("b1.equals(b5) => "+b1.equals(b5));
        System.out.println("b1.equals(r1) => "+b1.equals(r1));
        System.out.println("b1.equals(r2) => "+b1.equals(r2));

        System.out.println("b1.equals(r1) => "+b1.equals(r3));
    }


}
