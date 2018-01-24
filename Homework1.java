/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

/**
 *
 * @author Rachanon
 */
public class Homework1 {

    /**
     * @param args the command line arguments
     */
    public static String input;
    public static int inputLength;
    public static Node[] arr;
    public static char curChar;
    public static String equation = "";
    static Array var = new Array(999);
    // static Array calculate = new Array(999);

    private static int size = 0;

    public static void main(String[] args) {
        // TODO code application logic here

        String input = args[0];
        int inputLength = input.length();

        for (int i = 0; i < inputLength; i++) {
            char curChar = input.charAt(i);
            inOder(curChar);

        }

        inFix(var.top());
        System.out.print(equation);
        System.out.print("=" + calculate(var.top()));
    }

    public static class Node {

        Node left;
        Node right;
        Node parent;
        char key;

        public Node(char data) {
            this.key = data;
        }
    }

    public static boolean isOperator(char op) {
        switch (op) {
            case '+':
                return true;
            case '-':
                return true;
            case '*':
                return true;
            case '/':
                return true;

            default:
                return false;

        }
    }

    public static int calculate(Node node) {

        if (isOperator(node.key)) {
            switch (node.key) {
                case '+':
                    return calculate(node.left) + calculate(node.right);
                case '-':
                    return calculate(node.left) - calculate(node.right);
                case '*':
                    return calculate(node.left) * calculate(node.right);
                case '/':
                    return calculate(node.left) / calculate(node.right);
            }
        } else {
            return Character.getNumericValue(node.key);
        }
        return 0;

    }

    public static void inOder(char Char) {

        if (!isOperator(Char)) {
            var.pushBack(new Node(Char));

            //  calculate.pushBack(new Node(curChar));
        } else if (isOperator(Char)) {
            Node current = new Node(Char);
            current.right = var.popBack();
            current.right.parent = current;
            current.left = var.popBack();
            current.left.parent = current;
            var.pushBack(current);

        }

    }

    public static void inFix(Node node) {

        Node root = var.top();

        node = node.left;
        if (node != root.left) {
            equation += "(";
        }
        if (isOperator(node.key)) {
            inFix(node);
        } else {
            equation += node.key;

        }
        node = node.parent;
        equation += node.key;
        node = node.right;
        if (isOperator(node.key)) {
            inFix(node);
        } else {
            equation += node.key;

        }
        if (node != root.right) {
            equation += ")";
        }

    }

    public static class Array {

        public Array(int cap) {

            arr = new Node[cap];
        }

        public void pushBack(Node data) {

            arr[size] = data;
            size++;

        }

        public Node top() {

            return arr[size - 1];

        }

        public Node popBack() {
            // FIXED THIS
            Node x = arr[size - 1];
            arr[size - 1] = null;
            size--;

            return x;

        }
    }

}
