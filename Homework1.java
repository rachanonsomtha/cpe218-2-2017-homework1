/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework1.pkg1;

/**
 *
 * @author LuckyCat
 */
import homework1.pkg1.Homework1.Node;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.net.URL;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultTreeCellRenderer;

public class Homework1 {

    /**
     * @param args the command line arguments
     */
    public static String input;

    public static int inputLength;
    private static int size = 0;
    public static Node[] arr;
    public static char curChar;
    public static String equation = "";
    static Array var = new Array(999);

    public static void main(String[] args) {
        // TODO code application logic here

        // String input = args[0];
        String input = args[0];

        int inputLength = input.length();

        for (int i = 0; i < inputLength; i++) {
            char curChar = input.charAt(i);
            inOder(curChar);

        }

        inFix(var.top());
        //     System.out.print(equation);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TreeDemo.createAndShowGUI();
            }
        });
        //   System.out.print("=" + calculate(var.top()));
    }

    public static class Node {

        Node left;
        Node right;
        Node parent;
        char key;

        public Node(char data) {
            this.key = data;
        }

        public String toString() {
            return Character.toString(key);
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

    public static String inFix(Node node) {
        
    String ans = "";
        if (node == var.top()) {
            ans += inFix(node.left);
            ans += node.key;
            ans += inFix(node.right);
        }else if(isOperator(node.key)){
            ans += '(';
            ans += inFix(node.left);
            ans += node.key;
            ans += inFix(node.right);
            ans += ')';
        }else {
           ans += Character.getNumericValue(node.key);
        }
        return ans;
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

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
/**
 * This application that requires the following additional files:
 * TreeDemoHelp.html arnold.html bloch.html chan.html jls.html
 * swingtutorial.html tutorial.html tutorialcont.html vm.html
 */
class TreeDemo extends JPanel
        implements TreeSelectionListener {

    private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = false;
    Homework1.Node current = Homework1.var.top();
    
    
    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";

    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;

    public TreeDemo() {
        super(new GridLayout(1, 0));

        //Create the nodes.
        DefaultMutableTreeNode top
                = new DefaultMutableTreeNode(Homework1.var.top());
        createNodes(top);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }

        //Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);

        //Create the HTML viewing pane.
        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);
        initHelp();
        JScrollPane htmlView = new JScrollPane(htmlPane);

        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);

        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100);
        splitPane.setPreferredSize(new Dimension(500, 300));
        
        ImageIcon leafIcon = createImageIcon("middle.gif"); 
            if (leafIcon != null) { 
                DefaultTreeCellRenderer renderer =  
                new DefaultTreeCellRenderer(); 
                renderer.setOpenIcon(leafIcon); 
                renderer.setClosedIcon(leafIcon);
                tree.setCellRenderer(renderer); 
            }

        //Add the split pane to this panel.
        add(splitPane);
    }
    
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TreeDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Required by TreeSelectionListener interface.
     */
    public void valueChanged(TreeSelectionEvent e) {
//DefaultMutableTreeNode top = new DefaultMutableTreeNode(Homework1.var.top());
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        Node n = (Node) node.getUserObject();
        if(node.isLeaf())htmlPane.setText(Character.toString(n.key) + "=" + Homework1.calculate(n));
        
        htmlPane.setText(Homework1.inFix(n) + "=" + Homework1.calculate(n));
    }

    private class BookInfo {

        public String bookName;
        public URL bookURL;

        public BookInfo(String book, String filename) {
            bookName = book;
            bookURL = getClass().getResource(filename);
            if (bookURL == null) {
                System.err.println("Couldn't find file: "
                        + filename);
            }
        }

        public String toString() {
            return bookName;
        }
    }

    private void initHelp() {
        String s = "TreeDemoHelp.html";
        helpURL = getClass().getResource(s);
        if (helpURL == null) {
            System.err.println("Couldn't open help file: " + s);
        } else if (DEBUG) {
            System.out.println("Help URL is " + helpURL);
        }

        displayURL(helpURL);
    }

    private void displayURL(URL url) {
        try {
            if (url != null) {
                htmlPane.setPage(url);
            } else { //null url
                htmlPane.setText("File Not Found");
                if (DEBUG) {
                    System.out.println("Attempted to display a null URL.");
                }
            }
        } catch (IOException e) {
            System.err.println("Attempted to read a bad URL: " + url);
        }
    }

    private void createNodes(DefaultMutableTreeNode top) {

        Homework1.Node n = (Homework1.Node) top.getUserObject();

        if (n.left != null) {
            DefaultMutableTreeNode node1 = new DefaultMutableTreeNode(n.left);
            top.add(node1);
            createNodes(node1);
            DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(n.right);
            top.add(node2);
            createNodes(node2);
        }
    }

    //   top.add(category);
    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event dispatch thread.
     */
    public static void createAndShowGUI() {
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }

        //Create and set up the window.
        JFrame frame = new JFrame("TreeDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new TreeDemo());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}
