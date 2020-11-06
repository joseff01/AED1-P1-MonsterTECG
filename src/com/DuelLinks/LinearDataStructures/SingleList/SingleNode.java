package com.DuelLinks.LinearDataStructures.SingleList;

/**
 * Clase nodo complementaria para que funcione la clase SingleList
 * Cada nodo solo contiene la referencia al siguiente nodo y el valor.
 * <T> Delimita el tipo de valor contenido en el nodo.
 * @param <T>
 * @author Jose Retana & Mariana Navarro
 */

public class SingleNode<T> {
    private T value;
    private SingleNode<T> next;

    /**
     * Crear un nodo vacío
     * @author Jose Retana & Mariana Navarro
     */
    public SingleNode() {
        this.next = null;
    }

    /**
     * Crear un nodo con un valor inicial
     * @param value
     * @author Jose Retana & Mariana Navarro
     */
    public SingleNode(T value) {
        this();
        this.value = value;
    }

    /**
     * Crear un nodo con un valor inicial y una referencia
     * @param value
     * @param next
     * @author Jose Retana & Mariana Navarro
     */
    public SingleNode(T value, SingleNode<T> next) {
        this(value);
        this.next = next;
    }

    /**
     * Retorna el valor contenido en el nodo.
     * @return value
     * @author Jose Retana & Mariana Navarro
     */
    public T getValue() {
        return value;
    }

    /**
     * Asigna el valor contenido en el nodo
     * @param value
     * @author Jose Retana & Mariana Navarro
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Retorna la referencia contenida en el nodo.
     * @return next
     * @author Jose Retana & Mariana Navarro
     */
    public SingleNode<T> getNext() {
        return next;
    }

    /**
     * Asigna la referencia al siguiente nodo.
     * @param next
     * @author Jose Retana & Mariana Navarro
     */
    public void setNext(SingleNode<T> next) {
        this.next = next;
    }

}

