package com.example.demo.datastructures;

import java.util.ArrayList;
import java.util.List;

public class ArrayListManipulation {

    static List<String> fruits = new ArrayList<>();

    public static void main(String[] args) {
        adding();
        editing();
        deleting();
        System.out.println(fruits);
    }

    static void adding() {
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
    }

    static void editing() {
        fruits.set(2, "Pamplemousse");
    }

    static void deleting() {
        fruits.remove("Banana");
        fruits.remove(1);
    }

}
