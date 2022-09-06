package tda.impl;

import tda.ConjuntoTDA;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Conjunto<E> implements ConjuntoTDA<E> {
    private Set<E> conjunto;

    @Override
    public void agregar(E elemento) {
        this.conjunto.add(elemento);
    }

    @Override
    public boolean conjuntoVacio() {
        return this.conjunto.isEmpty();
    }

    @Override
    public E elegir() {
        return this.conjunto.stream().skip(new Random().nextInt(this.conjunto.size())).findFirst().orElse(null);
    }

    @Override
    public void inicializarConjunto() {
        this.conjunto = new HashSet<>();
    }

    @Override
    public boolean pertenece(E elemento) {
        return this.conjunto.contains(elemento);
    }

    @Override
    public void sacar(E elemento) {
        this.conjunto.remove(elemento);
    }

    @Override
    public int capacidad() {
        return this.conjunto.size();
    }
}
