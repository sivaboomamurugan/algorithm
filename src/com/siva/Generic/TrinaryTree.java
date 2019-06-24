package com.siva.Generic;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by sivam on 3/15/17.
 */
public class TrinaryTree {

    public static void main(String[] args) {
        Node root = new Node(5);
        root.insert(root, new Node(4));
        root.insert(root, new Node(9));
        root.insert(root, new Node(5));
        root.insert(root, new Node(7));
        root.insert(root, new Node(2));
        root.insert(root, new Node(2));
        root.insert(root, new Node(7));
        printNodesInLevel(root);
        root.deleteNode(root,4);
        System.out.println();
        printNodesInLevel(root);
    }


    private static void printNodesInLevel(Node root) {
        Queue<Node> nodes = new ArrayBlockingQueue<Node>(100);
        nodes.add(root);
        while(!nodes.isEmpty()) {
            Node currentNode = nodes.poll();
            System.out.print(currentNode.data);
            if(currentNode.left != null)
                nodes.offer(currentNode.left);
            if(currentNode.center != null)
                nodes.offer(currentNode.center);
            if(currentNode.right != null)
                nodes.offer(currentNode.right);
        }
    }

    static class Node implements Comparable<Node>{
        Node left, right, center;
        int data;

        public Node(int i) {
            this.data = i;
        }
        public Node insert(Node root, Node newNode) {
            if (root == null) {
                root = newNode;
            } else {
                if(root.data == newNode.data) {
                    root.center = insert(root.center, newNode);
                } else if (root.data > newNode.data) {
                    root.left = insert(root.left, newNode);
                } else{
                    root.right = insert(root.right, newNode);
                }
            }
            return root;
        }

        public static Node deleteNode(Node root, int value) {
            if (root == null)
                return null;
            if (root.data > value) {
                root.left = deleteNode(root.left, value);
            } else if (root.data < value) {
                root.right = deleteNode(root.right, value);
            } else {
                if(root.center != null) {
                    root.center = deleteNode(root.center, value);
                }
                if (root.left != null && root.right != null) {
                    Node temp = root;
                    Node minNodeForRight = minimumElement(temp.right);
                    root.data = minNodeForRight.data;
                    root.center = minNodeForRight.center;
                    deleteNode(root.right, minNodeForRight.data);

                }
                else if (root.left != null) {
                    root = root.left;
                }
                else if (root.right != null) {
                    root = root.right;
                }
                else
                    root = null;
            }
            return root;
        }

        private static Node minimumElement(Node root) {
            if (root.left == null)
                return root;
            else {
                return minimumElement(root.left);
            }
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.data,o.data);
        }
    }
}
