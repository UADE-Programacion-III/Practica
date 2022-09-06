package tda.impl;

import java.util.Objects;

public class Vertice<E> {
    private E dato;

    public Vertice(E dato) {
        this.dato = dato;
    }

    public E getDato() {
        return dato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertice<?> vertice = (Vertice<?>) o;
        return Objects.equals(dato, vertice.dato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dato);
    }
}
