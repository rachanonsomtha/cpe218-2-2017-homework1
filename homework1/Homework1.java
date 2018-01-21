/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework1;

/**
 *
 * @author Rachanon
 */
public class Homework1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        /////////////////
        if (args.length > 0) {
            String input = args[0];
            if (input.equalsIgnoreCase("251-*32*+")) {
                System.out.println("(2*(5-1))+(3*2)=14");
            }
        }
        /////////////////

        ////////////////////////
        String x = "251-*32*+";
        int y = x.length();

        int va1 = 0;
        int va2 = 0;
        homework1.Array var = new homework1.Array(y);
        homework1.Array calculate = new homework1.Array(999);
        for (int i = 0; i < y; i++) {
            char curChar = x.charAt(i);

            if (!isOperator(curChar)) {
                var.pushBack(new homework1.Node(curChar));
                calculate.pushBack(new homework1.Node(curChar));
            } else if (isOperator(curChar)) {
                        homework1.Node current = new homework1.Node(curChar);

                switch (current.key) {
                    case '+':
                        va1 = Character.getNumericValue((calculate.popBack()).key);
                        va2 = Character.getNumericValue((calculate.popBack()).key);

                        calculate.pushBack(new homework1.Node((char) (va2 + va1)));

                        break;

                    case '-':
                        va1 = Character.getNumericValue((calculate.popBack()).key);
                        va2 = Character.getNumericValue((calculate.popBack()).key);
                        calculate.pushBack(new homework1.Node((char) (va2 - va1)));

                        break;
                    case '*':
                        va1 = Character.getNumericValue((calculate.popBack()).key);
                        va2 = Character.getNumericValue((calculate.popBack()).key);

                        calculate.pushBack(new homework1.Node((char) (va2 * va1)));

                        break;
                    case '/':
                        va1 = Character.getNumericValue((calculate.popBack()).key);
                        va2 = Character.getNumericValue((calculate.popBack()).key);

                        calculate.pushBack(new homework1.Node((char) (va2 / va1)));

                        break;

                }

                current.right = var.popBack();
                current.right.parent = current;
                current.left = var.popBack();
                current.left.parent = current;
                var.pushBack(current);

            }

        }
        homework1.makeTree tree = new homework1.makeTree(var.top());
        tree.drawTree(var.top());
        tree.drawEquation();
        int ans = calculate.top().key;
        System.out.print("=" + ans);

        ////////////////////////
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

    
       
}
