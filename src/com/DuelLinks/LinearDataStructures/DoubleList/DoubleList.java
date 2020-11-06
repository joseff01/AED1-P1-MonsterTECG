package com.DuelLinks.LinearDataStructures.DoubleList;

import com.DuelLinks.LinearDataStructures.SingleList.SingleNode;

/**
 * Estructura de Datos Lineal DoubleList
 * Esta clase permite crear un DoubleList que almacene valores de la misma clase
 * Los nodos tienen referencia al valor siguiente y al anterior
 * @param <T>
 * @author Jose Retana & Mariana Navarro
 */

public class DoubleList<T> {
    private DoubleNode<T> first;

    /**
     * Constructor de Double List
     * Crea una lista Vacía
     */
    public DoubleList(){
        this.first =  null;
    }

    /**
     * Constructor de Double list
     * Crea una lista con el valor indicado
     * @param value
     */
    public DoubleList(T value){
        this.first =  new DoubleNode<T>(value);
    }

    /**
     * Verifica si la lista está vacía
     * @return boolean
     */
    public boolean isEmpty(){
        return (this.first == null);
    }

    /**
     * Devuelve el valor del largo de la lista actual
     * @return
     */
    public int getLength() {
        DoubleNode<T> ref = this.first;

        int length = 0;

        while(ref != null){
            length++;
            ref = ref.getNext();
        }
        return length;
    }

    /**
     * Se agrega un valor al inicio de la lista
     * @param value
     */
    public void addFirst(T value){
        if (this.isEmpty()) {
            this.first = new DoubleNode<T>(value);
        } else {
            DoubleNode<T> newNode = new DoubleNode<T>(value, null, this.first);
            this.first.setPrevious(newNode);
            this.first = newNode;
        }

    }
    /**
     * Se elimina el primer valor de la lista
     */
    public void deleteFirst() {
        this.first = this.first.getNext();
        this.first.setPrevious(null);
    }
    /**
     * Retorna el primer valor de la lista
     * @return T
     */
    public T getFirst() {
        return this.first.getValue();
    }
    /**
     * Argega un valor al final de la lista
     * @param value
     */
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
    /**
     * Se elimina el valor al final de la lista
     */
    public void deleteLast(){
        if (!this.isEmpty()) {
            DoubleNode<T> ref = this.first;
            while (ref.getNext() != null){
                ref = ref.getNext();
            }
            ref.getPrevious().setNext(null);
        }
    }
    /**
     * Retorna el último valor de la lista
     * @return T
     */
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
    /**
     * Se agrega un valor en la posición indicada de la lista
     * @param position
     * @param value
     */
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
    /**
     * Elimina un valor en un posición específica de la lista
     * @param position
     */
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


    /**
     * Devuelve un valor en una posición específica de la lista
     * @param position
     * @return
     */
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
    /**
     * Muestra en la consola una representación grafica de la lista
     * *WARNING* Los valores contenidos se deben poder desplegarse como strings
     * si no es posible, da error.
     */
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




