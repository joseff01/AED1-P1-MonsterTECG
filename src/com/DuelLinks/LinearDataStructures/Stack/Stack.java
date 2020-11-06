package com.DuelLinks.LinearDataStructures.Stack;

/**
 * Clase que permite usar la estructura lineal de datos "Stack"
 * Un stack solo puede ingresarse y sacarse valores al final de este.
 *
 * @Author Jose Retana
 */
public class Stack {
    private Object[] stackArray;

    private int top = -1;

    private int maxSize;

    /**
     * Constructor de Stack.
     * El valor max size delimita el largo máximo de la lista
     * La lista empieza vacía
     * El primer objeto que se le ingrese delimita el timpo de valores que va a contener
     * @param maxSize
     */
    public Stack(int maxSize) {
        this.maxSize =  maxSize;
        this.stackArray = new Object[maxSize];
    }

    /**
     * Constructor de Stack.
     * El valor max size delimita el largo máximo de la lista
     * La lista empieza con el valor indicado en value
     * El tipo de objeto de value va a delimitar qué tipos de objetos se pueden almacenar
     * @param maxSize
     * @param value
     */
    public Stack(int maxSize, Object value) {
        this.maxSize =  maxSize;
        this.stackArray = new Object[maxSize];
        this.stackArray[++top] = value;
    }

    /**
     * Verifica si un stack está vacío
     * @return boolean
     */
    public boolean isEmpty() {
        return (this.top < 0);
    }

    /**
     * Ingresa un valor al final del stack
     * @param value
     */
    public void push(Object value) {
        if (top < maxSize){
            this.stackArray[++top] = value;
        } else {
            System.out.println("It's full");
        }

    }

    /**
     * Elimina el ultimo valor del stack y lo devuelve
     * @return
     */
    public Object pop() {
        return this.stackArray[top--];
    }

    /**
     * Retorna el primer valor de la lista sin eliminarlo
     * @return
     */
    public Object peek() {
        return this.stackArray[top];
    }

}






