/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework1;

/**
 *
 * @author LuckyCat
 */
public class Node {

    Node head;
    Node left;
    Node right;
    Node parent;
    char key;

    public Node(char data) {
        this.key = data;
    }
}
