package com.siva.stack;

import java.util.ArrayList;

/**
 * Created by sivam on 12/4/15.
 */
public class PopAtSpecificPosition {

    ArrayList<Stack> stacks = new ArrayList<>();
    int capacity;

    public static void main(String[] args) {
        PopAtSpecificPosition popAtSpecificPosition = new PopAtSpecificPosition(3);
        for(int index = 0; index < 10; index++)
            popAtSpecificPosition.push(index);
        for(int index = 0; index < 3; index++)
            popAtSpecificPosition.pop();
        popAtSpecificPosition.popAt(1);
        System.out.println("x");
    }

    PopAtSpecificPosition(int capacity) {
        this.capacity = capacity;
    }

    public Stack getLastStack() {
        if(stacks.size() == 0 )return null;
        return stacks.get(stacks.size()-1);
    }

    public void push(int value) {
        Stack lastStack = getLastStack();
        if(null != lastStack && !lastStack.isFull())
            lastStack.push(value);
        else {
            Stack stack = new Stack(capacity);
            stack.push(value);
            stacks.add(stack);
        }
    }

    public void pop() {
        Stack lastStack = getLastStack();
        lastStack.pop();
        if (lastStack.isEmpty())
            stacks.remove(lastStack);
    }

    public void popAt(int stackNum) {
        leftShift(stackNum,true);
    }

    private int leftShift(int stackNum, boolean removeAtTop) {
        Stack  stack = stacks.get(stackNum);
        int removedItem;
        if(removeAtTop)
            removedItem = stack.pop();
        else removedItem = stack.removeBottom();
        if(stack.isEmpty())
            stacks.remove(stack);
        else if(stacks.size() > stackNum+1) {
            int vlaue = leftShift(stackNum+1,false);
            stack.push(vlaue);
        }

        return removedItem;
    }

    class Node {
        int value;
        Node above;
        Node below;

        public Node(int value) {
            this.value = value;
        }
    }

    class Stack {
        int capacity;
        public Node top, bottom;
        public int size =0;

        Stack(int capacity) {
            this.capacity = capacity;
        }

        public boolean isFull() {
            return size == capacity;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void push(int value) {
            Node node = new Node(value);
            if(isEmpty())
                bottom = node;
            size++;
            join(node, top);
            top = node;
        }

        public int pop() {
            size--;
            int value = top.value;
            if(!isEmpty())
                top.below.above = null;
            top = top.below;
            return value;
        }

        public int removeBottom() {
            size--;
            int value = bottom.value;
            if(!isEmpty())
                bottom.above.below=null;
            bottom = bottom.above;
            return value;
        }

        private void join(Node node, Node top) {
            if(node != null)
                node.below = top;
            if(top != null)
                top.above = node;
        }
    }
}
