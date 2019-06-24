package com.siva.stack;

/**
 * Created by sivam on 12/3/15.
 */
public class DynamicStack {

    static int numberOfStack = 3;
    static int defaultSize = 4;
    static int totalSize = defaultSize * numberOfStack;
    static Stack[] stacks = {new Stack(0,defaultSize), new Stack(defaultSize,defaultSize), new Stack(defaultSize*2, defaultSize)};
    static int[] buffer = new int[totalSize];

    public static void main(String[] args) throws Exception {
        push(2,5);
        push(2,5);
        push(2,5);
        push(2,5);
        push(2,5);
        push(0,10);
        push(0,10);
        push(0,10);
        push(1,8);
        push(1,8);
        int v = pop(0);

    }

    private static int pop(int staclnum) {
        return 0;
    }

    private static void push(int stacknum, int value) throws Exception {
        Stack stack = stacks[stacknum];
        if(stack.size >=stack.capacity) {
            if(numnerOfElements() >= totalSize)
                throw new Exception("out Of Space");
            else
                expand(stacknum);
        }
        stack.size++;
        stack.pointer = nextElement(stack.pointer);
        buffer[stack.pointer] = value;
    }

    private static int nextElement(int pointer) {
        if(pointer + 1 == totalSize)
            return 0;
        return pointer + 1;
    }

    private static void expand(int stacknum) {
        shift((stacknum+1)%numberOfStack);
        stacks[stacknum].capacity++;
    }

    private static void shift(int stacknum) {
        Stack stack = stacks[stacknum];
        if(stack.size >= stack.capacity) {
            int nextStack = (stacknum +1 )% numberOfStack;
            shift(nextStack);
            stack.capacity++;
        }

        for(int index=(stack.start + stack.capacity-1)%totalSize; stack.iswithinStack(index, totalSize); index=previousElement(index)) {
            buffer[index] = buffer[previousElement(index)];
        }
        buffer[stack.start]=0;
        stack.start=nextElement(stack.start);
        stack.pointer=nextElement(stack.pointer);
        stack.capacity--;
    }

    private static int previousElement(int index) {
        if(index == 0)
            return totalSize-1;
        return index-1;
    }

    private static int numnerOfElements() {

        return stacks[0].size + stacks[1].size+stacks[2].size;
    }

    static class Stack {
        int start, capacity, pointer, size;

        Stack(int start, int capacity) {
            this.start = start;
            this.size = 0;
            this.pointer = start-1;
            this.capacity = capacity;
        }

        public boolean iswithinStack(int index, int totalSize) {
            if(start <= index && index < start + capacity)
                return true;
            else if (start +capacity > totalSize && index < (start + capacity)%totalSize)
                return true;
            return false;
        }
    }
}
