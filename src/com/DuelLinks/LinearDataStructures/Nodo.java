package com.DuelLinks.LinearDataStructures;

public class Nodo{
    private int valor;
    private Nodo next;

    public Nodo(int valor) {
        this.valor = valor;
        this.next = null;
    }

    public int getValor() {
        return this.valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Nodo getNext() {
        return this.next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }
}
}
