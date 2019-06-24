package com.siva.trees;

/**
 * Created by sivam on 12/5/15.
 */
public class CheckIfTreeIsBalanaced {

    public static void main(String[] args) {
        Node root = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        Node n10 = new Node(9);
        Node n11 = new Node(9);
        Node n12 = new Node(9);

        root.left = n2;
        root.right = n3;

        n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        n4.left = n8;
        n4.right = n9;
        //n9.left = n10;
        n10.left = n11;

        isBalanced(root);
    }

    private static void isBalanced(Node root) {
        if(checkHeight(root) == -1)
            System.out.println("Not Balanced");
        else
            System.out.println("Balanced");
    }

    private static int checkHeight(Node root) {
        if(root == null)
            return 0;

        //left Height
        int leftHeight = checkHeight(root.left);
        if(leftHeight == -1)
            return -1;

        int rightHeight = checkHeight(root.right);
        if(rightHeight == -1)
            return -1;

        int diffHeight = leftHeight - rightHeight ;
        if(Math.abs(diffHeight) > 1)
            return -1;
        return Math.max(rightHeight,leftHeight)+1;
    }

    static class Node {
        Node left, right;
        int value;

        public Node(int i) {
            this.value = i;
        }
    }
}
