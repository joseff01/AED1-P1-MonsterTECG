package com.DuelLinks.LinearDataStructures.SingleList;

/**
 * Estructura de Datos Lineal SingleList
 * Esta clase permite crear un SingleList que almacene valores de la misma clase
 * Los nodos solo tienen referencia al valor siguiente.
 * @param <T>
 * @author Jose Retana & Mariana Navarro
 */
public class SingleList<T> {
    private SingleNode<T> first;

    /**
     * Constructor de SingleList
     * Se crea una lista vacía
     */
    public SingleList() {
        this.first = null;
    }

    /**
     * Constructor de SingleList
     * Se crea una lista con un nodo que contiene a "value"
     * @param value
     */
    public SingleList(T value) {
        this.first = new SingleNode<T>(value, null);
    }

    /**
     * Verifica si la lista está vacía
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return (this.first == null);
    }

    /**
     * Retorna el largo actual de la lista
     * @return int
     */
    public int getLength() {
        SingleNode<T> ref = this.first;

        int length = 0;

        while (ref != null) {
            length++;
            ref = ref.getNext();
        }
        return length;
    }

    /**
     * Se agrega un valor al inicio de la lista
     * @param value
     */
    public void addFirst(T value) {
        this.first = new SingleNode<T>(value, this.first);
    }

    /**
     * Se elimina el primer valor de la lista
     */
    public void deleteFirst() {
        this.first = this.first.getNext();
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

    /**
     * Se elimina el valor al final de la lista
     */
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

    /**
     * Retorna el último valor de la lista
     * @return T
     */
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

    /**
     * Se agrega un valor en la posición indicada de la lista
     * @param position
     * @param value
     */
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

    /**
     * Elimina un valor en un posición específica de la lista
     * @param position
     */
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
        SingleNode<T> ref = this.first;
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






