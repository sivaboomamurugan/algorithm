package com.siva.trees;

/**
 * Created by sivam on 12/5/15.
 */
public class BSTChecker {

    private static Integer lastPrinted = null;

    public static void main(String[] args) {
        int[] sortedArray = new int[]{1,2,3,4,5,6,7,8,9,10};
        Node root = createBST(sortedArray,0,9);
        System.out.println(isBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE));
        System.out.println(isBSTViaValChecker(root));
        System.out.println("Tree created");
    }

    private static boolean isBSTViaValChecker(Node root) {
        if(null == root)
            return true;
        if(!isBSTViaValChecker(root.left))
            return false;
        if(null != lastPrinted && root.value <= lastPrinted)
           return false;
        lastPrinted = root.value;
        if(!isBSTViaValChecker(root.right))
            return false;
        return true;
    }

    private static boolean isBST(Node root, int maxValue, int minValue) {
        if(null == root)
            return true;
        if(!(root.value < maxValue && root.value >= minValue) )
            return false;
        if(!isBST(root.left, root.value, minValue))
            return false;
        if(!isBST(root.right, maxValue, root.value))
            return false;
        return true;
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
