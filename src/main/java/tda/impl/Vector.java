package tda.impl;

import tda.VectorTDA;

import java.util.ArrayList;
import java.util.List;

public class Vector<E> implements VectorTDA<E> {
    private List<E> vector;

    @Override
    public void agregarElemento(int posicion, E elemento) {
        if (this.vector.size() > posicion && this.vector.get(posicion) != null) {
            this.vector.set(posicion, elemento);
        } else {
            this.vector.add(posicion, elemento);
        }
    }

    @Override
    public int capacidadVector() {
        return this.vector.size();
    }

    @Override
    public void eliminarElemento(int posicion) {
        this.vector.remove(posicion);
    }

    @Override
    public void inicializarVector(int n) {
        this.vector = new ArrayList<>(n);
    }

    @Override
    public E recuperarElemento(int posicion) {
        return this.vector.get(posicion);
    }

    @Override
    public boolean contieneElemento(E elemento) {
        return this.vector.contains(elemento);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "vector=" + vector +
                '}';
    }
}
