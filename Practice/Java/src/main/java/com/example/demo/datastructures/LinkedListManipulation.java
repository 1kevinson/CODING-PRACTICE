package com.example.demo.datastructures;

import java.util.LinkedList;

public class LinkedListManipulation {

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();

        // Adding elements to the linked list
        linkedList.add("Apple");
        linkedList.add("Banana");
        linkedList.add("Orange");

        // Displaying the elements of the linked list
        System.out.println("Elements in the linked list:");
        for (String fruit : linkedList) {
            System.out.println(fruit);
        }

        // Adding an element at a specific index
        linkedList.add(1, "Grapes");

        // Removing an element from the linked list
        linkedList.removeFirst();

        // Displaying the modified linked list
        System.out.println("\nModified linked list:");
        for (String fruit : linkedList) {
            System.out.println(fruit);
        }
    }

}
