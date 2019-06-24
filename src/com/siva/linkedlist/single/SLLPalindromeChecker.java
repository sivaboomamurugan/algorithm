package com.siva.linkedlist.single;

/**
 * Created by sivam on 11/30/15.
 */
public class SLLPalindromeChecker {

    public static void main(String[] args) {
        Node n1 = new Node(3);
        Node n2 = new Node(2);
        Node n3 = new Node(1);
        Node n4 = new Node(0);
        Node n5 = new Node(1);
        Node n6 = new Node(2);
        Node n7 = new Node(3);
        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
        n5.next=n6;
        n6.next=n7;
        SingleLinkedList singleLinkedList = new SingleLinkedList(n1);
        System.out.println(isPalindrome(n1,7).result);
    }

    private static Result isPalindrome(Node head, int size) {
        if(null == head || 0 == size)
            return new Result(true, null);
        if(1 == size)
            return new Result(true, head.next);
        if(2 == size)
            return new Result(head.data == head.next.data, head.next.next);
        Result result = isPalindrome(head.next, size-2);
        if(!result.result || null == result.next)
            return result;
        result.result = head.data == result.next.data;
        result.next = result.next.next;
        return result;
    }

    static class Result {
        boolean result;

        Node next;

        public Result(boolean result, Node next) {
            this.result = result;
            this.next = next;
        }
    }
}
