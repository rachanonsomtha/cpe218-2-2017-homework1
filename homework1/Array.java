/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework1;

import java.util.Arrays;

/**
 *
 * @author LuckyCat
 */
public class Array {

    private Node[] arr;

    private int capacity;
    private int size = 0; // Last element can be indexed at size-1

    public Array(int cap) {

        capacity = cap;
        arr = new Node[cap];
    }

    public void pushBack(Node data) {
        // FIXED THIS
        arr[size] = data;
        size++;

    }

    public Node top() {

        return arr[size - 1];

    }

    public  Node popBack() {
        // FIXED THIS
        Node x = arr[size - 1];
        arr[size - 1] = null;
        size--;

        return x;

    }

    public Node get(int i) {
        // FIXED THIS

        return arr[i];

    }

    public void set(int i, Node value) {
        // FIXED THIS
        arr[i] = value;

    }

    public void remove(int i) {
        // FIXED THIS        
        for (int q = i + 1; q < size - 1; q++) {
            arr[i] = arr[q];
            i++;
        }
    }

    public boolean isEmpty() {
        // FIXED THIS
        return size == 0;
    }

    public int getSize() {
        // FIXED THIS

        return size;
    }

    public void printStructure() {
        // FIXED THIS
        System.out.print("Size = " + size + ", Cap = " + capacity + ", arr = [" + arr[0]);
        for (int i = 1; i < size; i++) {
            System.out.print(" ," + arr[i]);
        }
        System.out.println(" ]");

    }
}
