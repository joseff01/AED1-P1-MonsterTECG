package com.DuelLinks.LinearDataStructures.DoubleList;

public class DoubleNode<T> {
    private T value;
    private DoubleNode<T> next;
    private DoubleNode<T> prev;

    public DoubleNode() {
        this.next = null;
    }

    public DoubleNode(T value) {
        this();
        this.value = value;
    }

    public DoubleNode(T value, DoubleNode<T> prev, DoubleNode<T> next) {
        this(value);
        this.prev = prev;
        this.next = next;
    }

    public DoubleNode(T value, DoubleNode<T> next){
        this(value);
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public DoubleNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

    public DoubleNode<T> getPrevious() {
        return prev;
    }

    public void setPrevious(DoubleNode<T> prev) {
        this.prev = prev;
    }

}

