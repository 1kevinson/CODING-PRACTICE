package com.example.demo.datastructures;

public class ArrayManipulation {

    public static void main(String[] args) {
        // declares an array of integers
        int[] numbers;

        // allocates memory for 10 integers
        numbers = new int[10];

        // initialize first element
        numbers[0] = 100;
        // initialize second element
        numbers[1] = 200;
        // and so forth
        numbers[2] = 300;

        System.out.println("Element at index 0: " + numbers[0]);
        System.out.println("Element at index 1: " + numbers[1]);
        System.out.println("Element at index 2: " + numbers[2]);
    }
}
