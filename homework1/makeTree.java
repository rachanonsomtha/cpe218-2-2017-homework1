/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework1;

import static homework1.Homework1.isOperator;

/**
 *
 * @author LuckyCat
 */
class makeTree {

    int n = 0;
    Node root;
    Node tail;
    String infix = "";

    public makeTree(Node root) {
        this.root = root;

    }

    public void drawEquation() {
        System.out.print(infix);
    }

    public void drawTree(Node current) {

        current = current.left;
        if (current != root.left) {
            infix += "(";
        }
        if (isOperator(current.key)) {
            drawTree(current);

        } else {
            infix += Character.toString(current.key);
        }
        current = current.parent;
        infix += Character.toString(current.key);
        current = current.right;
        if (isOperator(current.key)) {
            drawTree(current);
        } else {
            infix += Character.toString(current.key);
        }
        if (current != root.right) {
            infix += ")";
        }
    }
    
    

}
