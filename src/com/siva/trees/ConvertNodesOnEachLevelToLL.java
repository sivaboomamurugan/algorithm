package com.siva.trees;

import java.util.*;

/**
 * Created by sivam on 12/5/15.
 */
public class ConvertNodesOnEachLevelToLL {

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

        ArrayList<LinkedList<Node>> level = convertBTToLL(root);
        printNodesInLevel(root);
        System.out.println("Finished");

    }

    private static void printNodesInLevel(Node root) {
        Queue<Node> nodes = new LinkedList<>();
        Node nullNode = new NullNode(-1);
        nodes.add(root);

        while(!nodes.isEmpty()) {
            Node currentNode = nodes.poll();
            if(null == currentNode) {
                System.out.println();
                continue;
            }
            System.out.print(currentNode.value);
            if(currentNode.left != null)
                nodes.offer(currentNode.left);
            if(currentNode.right != null)
                nodes.offer(currentNode.right);
        }
    }

    private static ArrayList<LinkedList<Node>> convertBTToLL(Node root) {
        if(null == root)
            return null;
        ArrayList<LinkedList<Node>> levels = new ArrayList<>();
        LinkedList<Node> currentLevel = new LinkedList<>();
        currentLevel.add(root);
        System.out.println(root.value);
        while (!currentLevel.isEmpty()) {
            levels.add(currentLevel);
            LinkedList<Node> parent = currentLevel;
            currentLevel = new LinkedList<>();
            for(Node node : parent) {
                if(node.left != null) {
                    currentLevel.add(node.left);
                    System.out.print(node.left.value);
                }

                if(node.right != null) {
                    currentLevel.add(node.right);
                    System.out.print(node.right.value);
                }
            }
            System.out.println();
        }
        return levels;
    }

    static class Node implements Comparable<Node>{
        Node left, right;
        int value;

        public Node(int i) {
            this.value = i;
        }



        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value,o.value);
        }
    }

    static class NullNode extends Node {

        public NullNode(int i) {
            super(i);
        }
    }
}
