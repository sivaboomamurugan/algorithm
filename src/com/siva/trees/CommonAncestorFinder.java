package com.siva.trees;

/**
 * Created by sivam on 12/6/15.
 */
public class CommonAncestorFinder {

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
        Node node = commonAncestor(root, n4,n9);
        if(node != null)
            System.out.println(node.value);
        else
            System.out.println("Node Not found");
    }

    private static Node commonAncestor(Node root, Node node1, Node node2) {
        Result result = commonAncestorHelper(root, node1, node2);
        if(result.isAncestor)
            return result.node;
        return null;
    }

    private static Result commonAncestorHelper(Node root, Node node1, Node node2) {
        if(null == root)
            return new Result(null,false);
        if(root == node1 && root == node2)
            return new Result(root, true);

        Result leftResult = commonAncestorHelper(root.left, node1, node2);
        if(leftResult.isAncestor)
            return leftResult;

        Result rightResult = commonAncestorHelper(root.right, node1, node2);
        if(rightResult.isAncestor)
            return rightResult;

        if(rightResult.node != null && leftResult.node != null) {
            return new Result(root,true);
        } else if(root == node1 || root == node2) {
            boolean isAncestor = leftResult.node != null || rightResult.node != null ? true : false;
            return new Result(root,isAncestor);
        } else
            return new Result(leftResult.node != null ? leftResult.node : rightResult.node, false);

    }

    static class Result {
        Node node;
        boolean isAncestor;

        Result(Node node, boolean isAncestor) {
            this.isAncestor = isAncestor;
            this.node = node;
        }
    }

    static class Node {
        Node left, right;
        int value;

        public Node(int i) {
            this.value = i;
        }
    }
}
