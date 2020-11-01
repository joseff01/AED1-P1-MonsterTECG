package com.DuelLinks.LinearDataStructures.Stack;

public class Stack {
    private Object[] stackArray;

    private int top = -1;

    private int maxSize;

    public Stack(int maxSize) {
        this.maxSize =  maxSize;
        this.stackArray = new Object[maxSize];
    }

    public Stack(int maxSize, Object value) {
        this.maxSize =  maxSize;
        this.stackArray = new Object[maxSize];
        this.stackArray[++top] = value;
    }

    public boolean isEmpty() {
        return (this.top < 0);
    }

    public void push(Object value) {
        if (top < maxSize){
            this.stackArray[++top] = value;
        } else {
            System.out.println("It's full");
        }

    }

    public Object pop() {
        return this.stackArray[top--];
    }

    public Object peek() {
        return this.stackArray[top];
    }

}






