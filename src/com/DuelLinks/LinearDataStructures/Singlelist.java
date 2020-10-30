package com.DuelLinks.LinearDataStructures;

public class Singlelist {
    private Nodo first = null;

    public Singlelist() {
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public void changeStart(int element) {
        if (this.isEmpty()) {
            this.first = new Nodo(element);
        } else {
            Nodo puntero = this.first;
            puntero.setValor(element);
        }

    }

    public void eraseStart() {
        this.first = this.first.getNext();
    }

    public void changeAsIWant(int pos, int element) {
        if (this.largoLista() < pos) {
            System.out.println("Index out of range");
        } else {
            int cont = 0;

            Nodo temp;
            for(temp = this.first; cont != pos; temp = temp.getNext()) {
                ++cont;
            }

            temp.setValor(element);
        }

    }

    public void newRightWhereIWant(int pos, int element) {
        if (this.isEmpty()) {
            this.first = new Nodo(element);
        } else if (this.largoLista() < pos) {
            System.out.println("system out of range");
        } else {
            int cont = 0;
            Nodo temp = this.first;
            Nodo cola = this.first;

            while(cont != pos) {
                ++cont;
                temp = temp.getNext();
                cola.setNext(temp);
            }

            Nodo nuevo = new Nodo(element);
            nuevo.setNext(temp.getNext());
            temp.setNext(nuevo);
            this.first = cola;
        }

    }

    public void newxWhereIWant(int pos, int element) {
        if (this.isEmpty()) {
            this.first = new Nodo(element);
        } else if (this.largoLista() < pos) {
            System.out.println("system out of range");
        } else {
            int cont = 0;
            Nodo temp = this.first;

            Nodo cola;
            for(cola = this.first; cont != pos; ++cont) {
                cola.setNext(temp);
                temp = temp.getNext();
            }

            Nodo nuevo = new Nodo(element);
            nuevo.setNext(temp);
            this.first = cola;
        }

    }

    public int largoLista() {
        int cont = 0;

        for(Nodo temp = this.first; temp != null; temp = temp.getNext()) {
            ++cont;
        }

        return cont;
    }

    public void addLast(int element) {
        if (this.isEmpty()) {
            this.first = new Nodo(element);
        } else {
            Nodo puntero;
            for(puntero = this.first; puntero.getNext() != null; puntero = puntero.getNext()) {
            }

            puntero.setNext(new Nodo(element));
        }

    }

    public void newStart(int element) {
        if (this.isEmpty()) {
            this.first = new Nodo(element);
        } else {
            Nodo nuevaCabeza = new Nodo(element);
            nuevaCabeza.setNext(this.first);
            this.first = nuevaCabeza;
        }

    }

    public void printList() {
        for(Nodo puntero2 = this.first; puntero2 != null; puntero2 = puntero2.getNext()) {
            System.out.println(puntero2.getValor());
        }

    }
}

}
