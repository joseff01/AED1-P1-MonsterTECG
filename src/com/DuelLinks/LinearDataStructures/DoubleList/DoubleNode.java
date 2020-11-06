package com.DuelLinks.LinearDataStructures.DoubleList;
/**
 * Clase nodo complementaria para que funcione la clase DoubleList y DoubleCircularList
 * Cada nodo contiene la referencia al valor previo y al sigueinte, aparete del valor almacenado
 * <T> Delimita el tipo de valor contenido en el nodo.
 * @param <T>
 * @author Jose Retana & Mariana Navarro
 */
public class DoubleNode<T> {
    private T value;
    private DoubleNode<T> next;
    private DoubleNode<T> prev;

    /**
     * Crear un nodo vacío
     * @author Jose Retana & Mariana Navarro
     */
    public DoubleNode() {
        this.next = null;
    }
    /**
     * Crear un con un valor
     * @author Jose Retana & Mariana Navarro
     */
    public DoubleNode(T value) {
        this();
        this.value = value;
    }
    /**
     * Crear un nodo con un valor inicial, su referencia previa y la siguiente
     * @param value
     * @author Jose Retana & Mariana Navarro
     *
     * @param value
     * @param prev
     * @param next
     */
    public DoubleNode(T value, DoubleNode<T> prev, DoubleNode<T> next) {
        this(value);
        this.prev = prev;
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
    public DoubleNode<T> getNext() {
        return next;
    }
    /**
     * Asigna la referencia al siguiente nodo.
     * @param next
     * @author Jose Retana & Mariana Navarro
     */
    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

    /**
     * Retorna el valor de la referencia previa
     * @return prev
     */
    public DoubleNode<T> getPrevious() {
        return prev;
    }

    /**
     * Asigna la referencia al nodo previo.
     * @param prev
     */
    public void setPrevious(DoubleNode<T> prev) {
        this.prev = prev;
    }

}

