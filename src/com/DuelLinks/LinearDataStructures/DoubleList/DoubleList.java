package com.DuelLinks.LinearDataStructures.DoubleList;

import com.DuelLinks.LinearDataStructures.SingleList.SingleNode;

public class DoubleList<T> {
    private DoubleNode<T> first;

    public DoubleList(){
        this.first =  null;
    }

    public DoubleList(T value){
        this.first =  new DoubleNode<T>(value);
    }

    public boolean isEmpty(){
        return (this.first == null);
    }

    public int getLength() {
        DoubleNode<T> ref = this.first;

        int length = 0;

        while(ref != null){
            length++;
            ref = ref.getNext();
        }
        return length;
    }


    public void addFirst(T value){
        if (this.isEmpty()) {
            this.first = new DoubleNode<T>(value);
        } else {
            DoubleNode<T> newNode = new DoubleNode<T>(value, null, this.first);
            this.first.setPrevious(newNode);
            this.first = newNode;
        }

    }

    public void deleteFirst() {
        this.first = this.first.getNext();
        this.first.setPrevious(null);
    }

    public T getFirst() {
        return this.first.getValue();
    }

    public void addLast(T value){
        if (this.isEmpty()) {
            this.addFirst(value);
        } else {
            DoubleNode<T> ref = this.first;
            while (ref.getNext() != null){
                ref = ref.getNext();
            }
            DoubleNode<T> newNode = new DoubleNode<T>(value, ref,null);
            ref.setNext(newNode);
        }
    }

    public void deleteLast(){
        if (!this.isEmpty()) {
            DoubleNode<T> ref = this.first;
            while (ref.getNext() != null){
                ref = ref.getNext();
            }
            ref.getPrevious().setNext(null);
        }
    }

    public T getLast() {
        if (!this.isEmpty()) {
            DoubleNode<T> ref = this.first;
            while (ref.getNext() != null) {
                ref = ref.getNext();
            }
            return ref.getValue();
        }
        return null;
    }

    public void addAt(int position, T value) {
        int length = this.getLength();
        if (length < position ) {

            System.out.println("Index out of range");

        }else if (position == 0){

            this.addFirst(value);

        }else if (length == position) {

            this.addLast(value);

        } else {

            int i = 0;
            DoubleNode<T> ref = this.first;
            while(i != position){
                i++;
                ref = ref.getNext();
            }
            DoubleNode<T> prev = ref.getPrevious();
            DoubleNode<T> newDoubleNode = new DoubleNode<T>(value,prev,ref);
            prev.setNext(newDoubleNode);
            ref.setPrevious(newDoubleNode);
        }

    }

    public void deleteAt(int position){
        int length = this.getLength();
        if (length < position) {

            System.out.println("Index out of range");

        } else if (position == 0){

            this.deleteFirst();

        }else if (length == position) {

            this.deleteLast();

        } else {

            int i = 0;
            DoubleNode<T> ref = this.first;
            while(i != position){
                i++;
                ref = ref.getNext();
            }
            DoubleNode<T> prev = ref.getPrevious();
            DoubleNode<T> next = ref.getNext();
            prev.setNext(next);
            next.setPrevious(prev);

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
        DoubleNode<T> ref = this.first;
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
            DoubleNode<T> ref = this.first.getNext();
            while (ref.getNext() != null) {
                System.out.print(ref.getValue() + ",");
                ref = ref.getNext();
            }
            System.out.print(ref.getValue() + "]");
            System.out.println();
        }


    }

}




