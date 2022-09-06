package tda.impl;

public class Arista<E> implements Comparable<Arista<E>> {
    private E desde;
    private E hacia;
    private int peso;

    public Arista(E desde, E hacia, int peso) {
        this.desde = desde;
        this.hacia = hacia;
        this.peso = peso;
    }

    public E getDesde() {
        return desde;
    }

    public E getHacia() {
        return hacia;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public int compareTo(Arista<E> o) {
        return Integer.compare(o.getPeso(), this.peso);
    }
}
