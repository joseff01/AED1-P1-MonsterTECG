package com.DuelLinks.LinearDataStructures.DoubleCircularList;

import com.DuelLinks.LinearDataStructures.DoubleList.DoubleNode;

public class DoubleCircularList<T> {

    private DoubleNode<T> first;

    public DoubleCircularList(){
        this.first =  null;
    }

    public DoubleCircularList(T value){
        this.first =  new DoubleNode<T>(value);
        this.first.setPrevious(this.first);
        this.first.setNext(this.first);
    }

    public boolean isEmpty(){
        return (this.first == null);
    }

    public int getLength() {
        if (this.isEmpty()){
            return 0;
        }
        DoubleNode<T> ref = this.first;

        int length = 1;

        while(ref.getNext() != this.first){
            length++;
            ref = ref.getNext();
        }
        return length;
    }


    public void addFirst(T value){
        if (this.isEmpty()) {
            this.first =  new DoubleNode<T>(value);
            this.first.setPrevious(this.first);
            this.first.setNext(this.first);
        } else {
            DoubleNode<T> newNode = new DoubleNode<T>(value, this.first.getPrevious(), this.first);
            DoubleNode<T> last = this.first.getPrevious();
            last.setNext(newNode);
            this.first.setPrevious(newNode);
        }

    }

    public void deleteFirst() {
        if (!this.isEmpty()) {
            if (this.getLength() == 1) {
                this.first = null;
            }
            DoubleNode<T> last = this.first.getPrevious();
            last.setNext(this.first.getNext());
            DoubleNode<T> next = this.first.getNext();
            next.setPrevious(this.first.getPrevious());
        }
    }

    public T getFirst() {
        return this.first.getValue();
    }

    public void addLast(T value){
        if (this.isEmpty()) {
            this.addFirst(value);
        } else {
            DoubleNode<T> last = this.first.getPrevious();
            DoubleNode<T> newNode = new DoubleNode<T>(value,last,this.first);
            last.setNext(newNode);
            this.first.setPrevious(newNode);
            this.first = newNode;

        }
    }

    public void deleteLast(){
        if (!this.isEmpty()) {
            DoubleNode<T> last = this.first.getPrevious();
            DoubleNode<T> lastPrevious = last.getPrevious();
            lastPrevious.setNext(this.first);
            this.first.setPrevious(lastPrevious);
        }
    }

    public T getLast() {
        return this.first.getPrevious().getValue();
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

    public void eliminateAt(int position){
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

}




