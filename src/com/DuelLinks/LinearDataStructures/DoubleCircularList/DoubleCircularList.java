package com.DuelLinks.LinearDataStructures.DoubleCircularList;

import com.DuelLinks.LinearDataStructures.DoubleList.DoubleNode;
import com.DuelLinks.LinearDataStructures.SingleList.SingleNode;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Estructura de Datos Lineal Double Circular List
 * Esta clase permite crear un DoubleList que almacene valores de la misma clase
 * Los nodos tienen referencia al valor siguiente y al anterior
 * El ultimo valor tiene como referencia al siguiete nodo al nodo inicial
 * El nodo previo al nodo inicial es el final
 * @param <T>
 * @author Jose Retana & Mariana Navarro
 */
public class DoubleCircularList<T> {

    private DoubleNode<T> first;

    /**
     * Constructor de Double List
     * Crea una lista Vacía
     */
    public DoubleCircularList(){
        this.first =  null;
    }

    /**
     * Constructor de Double list
     * Crea una lista con nodo indicado
     * @param firstNode
     */
    public DoubleCircularList(DoubleNode<T> firstNode) {
        this.first = firstNode;
    }

    /**
     * Crea una lista con el valor indicado
     * @param value
     */
    public DoubleCircularList(T value){
        this.first =  new DoubleNode<T>(value);
        this.first.setPrevious(this.first);
        this.first.setNext(this.first);
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

    /**
     * Se agrega un valor al inicio de la lista
     * @param value
     */

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
            this.first = newNode;
        }

    }
    /**
     * Se elimina el primer valor de la lista
     */
    public void deleteFirst() {
        if (!this.isEmpty()) {
            if (this.getLength() == 1) {
                this.first = null;
            }
            DoubleNode<T> last = this.first.getPrevious();
            last.setNext(this.first.getNext());
            DoubleNode<T> next = this.first.getNext();
            next.setPrevious(last);
            this.first = next;
        }
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
            DoubleNode<T> last = this.first.getPrevious();
            DoubleNode<T> newNode = new DoubleNode<T>(value,last,this.first);
            last.setNext(newNode);
            this.first.setPrevious(newNode);


        }
    }
    /**
     * Se elimina el valor al final de la lista
     */
    public void deleteLast(){
        if (!this.isEmpty()) {
            DoubleNode<T> last = this.first.getPrevious();
            DoubleNode<T> lastPrevious = last.getPrevious();
            lastPrevious.setNext(this.first);
            this.first.setPrevious(lastPrevious);
        }
    }
    /**
     * Retorna el último valor de la lista
     * @return T
     */
    public T getLast() {
        return this.first.getPrevious().getValue();
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
     * Dado un valor, Se elimina la primera intancia de esta que se presente.
     * @param value
     * @return
     */
    public boolean deleteValue(T value){
        if (!isEmpty()){
            if (this.first.getValue().equals(value)){
                this.deleteFirst();
                return true;
            } else if (this.first.getPrevious().getValue().equals(value)){
                this.deleteLast();
                return true;
            }
            DoubleNode<T> ref = this.first.getNext();
            while(!ref.getValue().equals(value)){
                if (ref == this.first){
                    return false;
                }
                ref = ref.getNext();
            }
            DoubleNode<T> prev = ref.getPrevious();
            DoubleNode<T> next = ref.getNext();
            prev.setNext(next);
            next.setPrevious(prev);
            return true;

        }
        return false;

    }

    /**
     * Dado un valor, returna true si la lista sí contiene por lo menos uno de los elementos dados.
     * @param value
     * @return
     */
    public boolean isValue(T value){
        if (!isEmpty()){
            if (this.first.getValue().equals(value)){
                return true;
            } else if (this.first.getPrevious().getValue().equals(value)){
                return true;
            }
            DoubleNode<T> ref = this.first.getNext();
            while(!ref.getValue().equals(value)){
                if (ref == this.first){
                    return false;
                }
                ref = ref.getNext();
            }
            return true;

        }
        return false;

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
            while (ref.getNext() != this.first) {
                System.out.print(ref.getValue() + ",");
                ref = ref.getNext();
            }
            System.out.print(ref.getValue() + "]");
            System.out.println();
        }


    }

}




