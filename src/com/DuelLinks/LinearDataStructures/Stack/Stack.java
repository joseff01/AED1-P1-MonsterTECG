package com.DuelLinks.LinearDataStructures.Stack;

public class Stack<T> {
    private SingleNode<T> first;

    public Stack() {
        this.first = null;
    }

    public Stack(T value) {
        this.first = new SingleNode<T>(value, null);
    }

    public boolean isEmpty() {
        return (this.first == null);
    }

    public int getLength() {
        SingleNode<T> ref = this.first;

        int length = 0;

        while (ref != null) {
            length++;
            ref = ref.getNext();
        }
        return length;
    }


    public void addFirst(T value) {
        this.first = new SingleNode<T>(value, this.first);
    }

    public void deleteFirst() {
        this.first = this.first.getNext();
    }

    public T getFirst() {
        return this.first.getValue();
    }

    public void addLast(T value) {
        if (this.isEmpty()) {
            this.first = new SingleNode<T>(value);
        } else {
            SingleNode<T> ref = this.first;
            while (ref.getNext() != null) {
                ref = ref.getNext();
            }
            ref.setNext(new SingleNode<T>(value));
        }
    }

    public void deleteLast() {
        if (!this.isEmpty()) {
            SingleNode<T> ref = this.first;
            SingleNode<T> prev = null;
            while (ref.getNext() != null) {
                prev = ref;
                ref = ref.getNext();
            }
            prev.setNext(null);
        }
    }

    public T getLast() {
        if (!this.isEmpty()) {
            SingleNode<T> ref = this.first;
            while (ref.getNext() != null) {
                ref = ref.getNext();
            }
            return ref.getValue();
        }
        return null;
    }

    public void addAt(int position, T value) {
        int length = this.getLength();
        if (length < position) {

            System.out.println("Index out of range");

        } else if (position == 0) {

            this.addFirst(value);

        } else if (length == position) {

            this.addLast(value);

        } else {

            int i = 0;
            SingleNode<T> ref = this.first;
            SingleNode<T> prev = null;
            while (i != position) {
                i++;
                prev = ref;
                ref = ref.getNext();
            }
            SingleNode<T> newSingleNode = new SingleNode<T>(value, ref);
            prev.setNext(newSingleNode);
        }

    }

    public void deleteAt(int position) {
        int length = this.getLength();
        if (length < position) {

            System.out.println("Index out of range");

        } else if (position == 0) {

            this.deleteFirst();

        } else if (length == position) {

            this.deleteLast();

        } else {

            int i = 0;
            SingleNode<T> ref = this.first;
            SingleNode<T> prev = null;
            while (i != position) {
                i++;
                prev = ref;
                ref = ref.getNext();
            }

            prev.setNext(ref.getNext());

        }

    }


    public T getValueAt(int position) {
        int length = this.getLength();
        if (length < position) {

            System.out.println("Index out of range");
            return null;

        } else if (position == 0) {

            return this.getFirst();

        } else if (length == position) {

            return this.getLast();
        }

        int i = 0;
        SingleNode<T> ref = this.first;
        while (i != position) {
            i++;
            ref = ref.getNext();
        }
        return ref.getValue();



    }

    public void print(){
        if (this.isEmpty()){
            System.out.println("[]");
            return;
        }else if(this.getLength() == 1){
            System.out.println("[" + this.first.getValue() + "]");
        }else {
            System.out.print("[" + this.first.getValue() + ",");
            SingleNode<T> ref = this.first.getNext();
            while (ref.getNext() != null) {
                System.out.print(ref.getValue() + ",");
                ref = ref.getNext();
            }
            System.out.print(ref.getValue() + "]");
            System.out.println();
        }


    }

}






