package com.siva.trees;

/**
 * Created by sivam on 12/6/15.
 */
public class PrintPathToASum {

    public static void main(String[] args) {
        int[] sortedArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Node root = createBST(sortedArray, 0, 9);
        findsum(root, 7);
        System.out.println("Tree created");
    }

    private static void findsum(Node root, int sum) {
        int depth = findDepth(root);
        int[] path = new int[depth];
        findPath(root, path, sum, 0);
    }

    private static void findPath(Node root, int[] path, int sum, int level) {
        if (null == root)
            return;
        path[level] = root.value;
        int temp = 0;
        for (int index = level; index >= 0; index--) {
            temp += path[index];
            if (sum == temp)
                printPath(path, index, level);
        }
        findPath(root.left, path, sum, level + 1);
        findPath(root.right, path, sum, level + 1);
    }

    private static void printPath(int[] path, int start, int end) {
        for (int index = start; index <= end; index++)
            System.out.print(path[index] + " ");
        System.out.println();
    }

    private static int findDepth(Node root) {
        if (null == root)
            return 0;
        return 1 + Math.max(findDepth(root.left), findDepth(root.right));
    }

    private static Node createBST(int[] sortedArray, int start, int end) {
        if (start > end)
            return null;
        int middle = (start + end) / 2;
        Node node = new Node(sortedArray[middle]);
        node.left = createBST(sortedArray, start, middle - 1);
        node.right = createBST(sortedArray, middle + 1, end);
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
