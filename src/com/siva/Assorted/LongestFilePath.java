package com.siva.Assorted;

/*Suppose we abstract our file system by a string in the following manner:

        The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

        dir
            subdir1
            subdir2
                file.ext
        The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

        The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

        dir
            subdir1
                file1.ext
                subsubdir1
            subdir2
                subsubdir2
        f           ile2.ext
        The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

        We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

        Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.

        Note:
        The name of a file contains at least a . and an extension.
        The name of a directory or sub-directory will not contain a ..
        Time complexity required: O(n) where n is the size of the input string.

        Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LongestFilePath {

    public static void main(String... arg) {
        String input = "dir\\n\\tsubdir1\\n\\tsubdir2\\n\\t\\tfile.ext";
        //String input = "dir\\n\\tsubdir1\\n\\t\\tfile1.ext\\n\\t\\tsubsubdir1\\n\\tsubdir2\\n\\t\\tsubsubdir2\\n\\t\\t\\tfile2.ext";
        System.out.println(lengthLongestPath(input));
        groupAnagrams(new String[] {"eat","tea","tan","ate","nat","bat"});
    }

    private static int lengthLongestPath(String input) {
        if(null == input || input.length() == 0 || !input.contains("."))
            return 0;
        StringBuilder currentObjName = new StringBuilder();
        Tree tree = new Tree();
        tree.root = new Node("");
        Node currRoot = tree.root;
        char[] inputAsChar = input.toCharArray();
        for(int index=0; index < inputAsChar.length; index++) {
            char currChar = inputAsChar[index];
            if(currChar == '\\' && isCurrentDirFileDone(inputAsChar, index+1)) {
                currRoot = parseChildElement(currentObjName, currRoot);
                index++;
            } else if(currChar == '\\' && isSubDir(inputAsChar, index+1)) {
                currRoot = navigateToCorrectDir(tree, currRoot);
                index++;
            } else {
                currentObjName.append(currChar);
            }
        }
        currRoot = parseChildElement(currentObjName, currRoot);

        return getMaxLength(tree.root)-1;
    }

    private static Node navigateToCorrectDir(Tree tree, Node currRoot) {
        if(null == currRoot) {
            currRoot = tree.root;
        } else {
            ArrayList<Node> childs = currRoot.childNodes;
            if(null != childs && childs.size() > 0) {
                currRoot = childs.get(childs.size()-1);
            }
        }
        return currRoot;
    }

    private static Node parseChildElement(StringBuilder currentObjName, Node currRoot) {
        Node currNode = null;
        if(currRoot.name.equalsIgnoreCase("")) {
            currNode = currRoot;
            currNode.name = currentObjName.toString();
        }
        else {
            currNode = new Node(currentObjName.toString());
            currRoot.childNodes.add(currNode);
        }
        currNode.childNodes = new ArrayList<>();
        currentObjName.delete(0, currentObjName.length());
        currRoot = null;
        return currRoot;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        List<List<String>> groupedAnagram = new ArrayList<>();
        for(String str : strs) {
            char[] c = str.toCharArray();
            Arrays.sort(c);
            String key = String.valueOf(c);
            List<String> list = null;
            if(map.containsKey(key)) {
                list = map.get(key);
            } else {
                list = new ArrayList<String>();
            }
            list.add(str);
            map.put(key, list);
        }
        for(String key : map.keySet()) {
            groupedAnagram.add(map.get(key));
        }

        return groupedAnagram;
    }

    private static int getMaxLength(Node root) {
        int curMax = 0;
        int length = root.name.length() + 1;
        for(Node child : root.childNodes) {
            int childLength = getMaxLength(child) ;
            if(childLength > curMax)
                curMax = childLength;
        }
        return length + curMax;
    }

    private static boolean isSubDir(char[] inputAsChar, int nextIndex) {
        if(nextIndex < inputAsChar.length && inputAsChar[nextIndex] == 't')
            return true;
        return false;
    }

    private static boolean isCurrentDirFileDone(char[] inputAsChar, int nextIndex) {
        if(nextIndex < inputAsChar.length && inputAsChar[nextIndex] == 'n')
            return true;
        return false;
    }

    static class Node {
        ArrayList<Node> childNodes;
        String name;
        public Node(String obj) {
            this.name = obj;
        }
    }

    static class Tree {
        Node root;
    }
}
