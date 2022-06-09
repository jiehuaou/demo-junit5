package com.example.demo.junit5test;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class OptionalTest {
    @Test
    void testMapElse1(){
        Optional<String> data = Optional.of("hello");
        String out = data.map(s->s+"-001").orElse("else");
        Assertions.assertEquals("hello-001", out);
    }

    @Test
    void testMapElse2(){
        Optional<String> data = Optional.empty();
        String out = data.map(String::toUpperCase).orElse("else");
        Assertions.assertEquals("else", out);
    }

    @Test
    void testAnyOneNull() {
        String s1 = "1";
        String s2 = null;
        String s3 = "3";
        String test = Optional.ofNullable(s1).map(s -> s2).map(s -> s3)
                .map(s -> "all_non_null")
                .orElse("any_one_null");
        Assertions.assertEquals("any_one_null", test);
    }

    @Test
    void testAllNotNull() {
        String s1 = "1";
        String s2 = "2";
        String s3 = "3";
        String test = Optional.ofNullable(s1).map(s -> s2).map(s -> s3)
                .map(s -> s1+s2+s3)
                .orElse("any_one_null");

        Assertions.assertEquals("123", test);
    }

    @Test
    @DisplayName("get non-null property from hierarchy 1")
    void testMapChain1(){
        Optional<ComplexData> data = Optional.ofNullable(new ComplexData("abc",
                Arrays.asList("a1","a2")));
        String out = data.map(e -> e.items).map(e -> e.get(0)).orElse("else");
        Assertions.assertEquals("a1", out);
    }

    @Test
    @DisplayName("get non-null property from hierarchy when there is null")
    void testMapChain2(){
        ComplexData complexData = new ComplexData("abc", null);
        String out = Optional.ofNullable(complexData)
                .map(e -> e.items)
                .map(e -> e.get(0))
                .orElse("else");
        Assertions.assertEquals("else", out);
    }

    @Test
    void testFilterElse(){
        Optional<String> data = Optional.of("Hello");
        String out = data
                .filter(s->s.equalsIgnoreCase("hello-1"))
                .orElse("else");
        Assertions.assertEquals("else", out);
    }

    Optional<String> getOptional(String text){
        if("empty".equalsIgnoreCase(text)){
            return Optional.empty();
        }
        return Optional.of(text);
    }

    static class ComplexDataWithoutEqual {
        private final String name;
        private final List<String> items;

        ComplexDataWithoutEqual(String name, List<String> items) {
            this.name = name;
            this.items = new ArrayList<>(items);
        }
    }


    static class ComplexData{
        private final String name;
        private final List<String> items;

        ComplexData(String name, List<String> items) {
            this.name = name;
            this.items = items;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ComplexData that = (ComplexData) o;

            if (!name.equals(that.name)) return false;
            return items.equals(that.items);
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + items.hashCode();
            return result;
        }
    }

    static List<String> genItems(){
        return Arrays.asList("1", "2");
    }

    @Test
    void testOptionalEqual(){

        // test equal, simple object
        Optional<String> actual = getOptional("hello");
        Assertions.assertEquals(Optional.of("hello"), actual);

        // test equal, object with equal & hashCode
        Optional<ComplexData> data1 = Optional.of(new ComplexData("a", genItems()));
        Optional<ComplexData> data2 = Optional.of(new ComplexData("a", genItems()));
        Assertions.assertEquals(data1, data2);

        // not equal,  object without equal & hashCode
        Optional<ComplexDataWithoutEqual> dataw1 = Optional.of(new ComplexDataWithoutEqual("a", genItems()));
        Optional<ComplexDataWithoutEqual> dataw2 = Optional.of(new ComplexDataWithoutEqual("a", genItems()));
        Assertions.assertNotEquals(dataw1, dataw2);

        // test not equal
        Optional<String> empty = getOptional("empty");
        Assertions.assertTrue(empty.isEmpty());
        Assertions.assertNotEquals(Optional.of("hello"), empty);

        // not equal
        Assertions.assertNotEquals(Optional.of("hello"), Optional.of("else"));


    }

    @Test
    void testOptionalConsume(){
        Optional.of("hello")
                .ifPresent(e-> System.out.println("found " + e));

        Optional.empty()
                .ifPresentOrElse(e->{}, ()->System.out.println("found nothing here"));
    }

    @Test
    @DisplayName("stream with optional to avoid null point exception")
    void testStreamWithOptional() {
        ObjectWithList testObject1 = new ObjectWithList(Arrays.asList("abc", "www"));
        ObjectWithList testObject2 = new ObjectWithList(null);

        List<ObjectWithList> list = Arrays.asList(testObject1, testObject2);

        List<String> list2 = list.stream()
                .map(e -> Optional.ofNullable(e.getData()).orElseGet(Collections::emptyList))
                .flatMap(e -> e.stream())
                .map(s -> s + "000")
                .collect(Collectors.toList())
        ;

        System.out.println(list2);

    }

    public static class ObjectWithList{
        private final List<String> data ;

        public ObjectWithList(List<String> data) {
            this.data = data;
        }


        public List<String> getData() {
            return data;
        }


    }

}
