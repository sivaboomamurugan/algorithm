package com.siva.linkedlist.single;

/**
 * Created by sivam on 11/29/15.
 */
public class SubtractLLValues {

    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        Node head = null;
        for(int index =6; index > 0; index--) {
            if(head == null) {
                head = new Node();
                linkedList.head = head;
            } else {
                head.next = new Node();
                head = head.next;
            }
            head.data = index;
            head.data = index;


        }
        Node headPtr = linkedList.head;
        Node tailPtr = linkedList.head;

        //subtract(linkedList.head,null);
        subtract(linkedList.head,6);
        print(linkedList.head);

    }

    private static void print(Node head) {
        while(head != null) {
            System.out.print(head.data + " -> ");
            head=head.next;
        }

    }

    private static void subtract(Node head, Node lastPlusOne) {
        if(head == lastPlusOne || head.next == lastPlusOne)
            return;
        Node last = head;
        while(last.next != lastPlusOne)
            last = last.next;
        head.data = head.data - last.data;
        subtract(head.next, last);
    }

    private static Node subtract(Node head, int size) {
        if(null == head || size == 0)
            return head;
        if(size == 1)
            return head;
        if(size == 2) {
            head.data = head.data - head.next.data;
            return head.next.next;
        }
        Node node = subtract(head.next, size-2);
        head.data = head.data - node.data;
        return node.next;

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
