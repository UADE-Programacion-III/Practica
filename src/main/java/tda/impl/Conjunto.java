package tda.impl;

import tda.ConjuntoTDA;
import tda.VectorTDA;

import java.util.*;

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

    @Override
    public VectorTDA<E> aVector() {
        VectorTDA<E> vector = new Vector<>();
        vector.inicializarVector(this.capacidad());
        List<E> lista = new ArrayList<>(this.conjunto);
        for (int i = 0; i < lista.size(); i++) {
            vector.agregarElemento(i, lista.get(i));
        }
        return vector;
    }
}
