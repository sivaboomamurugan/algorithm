package com.siva.trees;

/**
 * Created by sivam on 12/5/15.
 */
public class BinarySearchTreeCreator {

    public static void main(String[] args) {
        int[] sortedArray = new int[]{1,2,3,4,5,6,7,8,9,10};
        Node root = createBST(sortedArray,0,9);
        System.out.println("Tree created");
    }

    private static Node createBST(int[] sortedArray, int start, int end) {
        if(start > end)
            return null;
        int middle = (start+end)/2;
        Node node = new Node(sortedArray[middle]);
        node.left = createBST(sortedArray, start, middle-1);
        node.right = createBST(sortedArray, middle+1, end);
        return node;

    }

    static class Node {
        Node left, right;
        int value;

        public Node(int i) {
            this.value = i;
        }
    }
}
