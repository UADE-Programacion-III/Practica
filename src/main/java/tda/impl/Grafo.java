package tda.impl;

import tda.ConjuntoTDA;
import tda.GrafoTDA;

import java.util.*;

public class Grafo<E> implements GrafoTDA<E> {
    private Map<Vertice<E>, List<Arista<E>>> grafo;

    @Override
    public ConjuntoTDA<E> adyacentes(E vertice) {
        Vertice<E> v = new Vertice<>(vertice);
        ConjuntoTDA<E> adyacentes = new Conjunto<>();
        adyacentes.inicializarConjunto();
        List<Arista<E>> aristas = this.grafo.get(v);
        for (Arista<E> arista : aristas) {
            adyacentes.agregar(arista.getDesde());
            adyacentes.agregar(arista.getHacia());
        }
        adyacentes.sacar(vertice);
        return adyacentes;
    }


    @Override
    public void agregarArista(E vertice1, E vertice2, int costo) {
        Vertice<E> v1 = new Vertice<>(vertice1);
        Vertice<E> v2 = new Vertice<>(vertice2);
        List<Arista<E>> aristas1 = this.grafo.get(v1);
        List<Arista<E>> aristas2 = this.grafo.get(v2);
        if (aristas1 == null) {
            this.agregarVertice(vertice1);
            aristas1 = this.grafo.get(v1);
        }
        if (aristas2 == null) {
            this.agregarVertice(vertice2);
            aristas2 = this.grafo.get(v2);
        }
        aristas1.add(new Arista<>(vertice1, vertice2, costo));
        aristas2.add(new Arista<>(vertice2, vertice1, costo));
    }

    @Override
    public void agregarVertice(E vertice) {
        Vertice<E> v = new Vertice<>(vertice);
        this.grafo.put(v, new ArrayList<>());
    }

    @Override
    public E elegir() {
        Optional<Vertice<E>> vertice = this.grafo.keySet().stream().skip(new Random().nextInt(this.grafo.size())).findFirst();
        return vertice.map(Vertice::getDato).orElse(null);
    }

    @Override
    public void eliminarArista(E vertice1, E vertice2) {
        Vertice<E> v1 = new Vertice<>(vertice1);
        List<Arista<E>> aristas1 = this.grafo.get(v1);
        if (aristas1 != null) {
            Optional<Arista<E>> arista = aristas1.stream().filter(a -> a.getDesde().equals(vertice1) && a.getHacia().equals(vertice2)).findFirst();
            arista.ifPresent(aristas1::remove);
        }
    }

    @Override
    public void eliminarVertice(E vertice) {
        Vertice<E> v = new Vertice<>(vertice);
        this.grafo.remove(v);
    }

    @Override
    public boolean existeArista(E vertice1, E vertice2) {
        Vertice<E> v = new Vertice<>(vertice1);
        List<Arista<E>> aristas1 = this.grafo.get(v);
        if (aristas1 != null) {
            return aristas1.stream().anyMatch(a -> a.getDesde().equals(vertice1) && a.getHacia().equals(vertice2));
        }
        return false;
    }

    @Override
    public void inicializarGrafo() {
        this.grafo = new HashMap<>();
    }

    @Override
    public int pesoArista(E vertice1, E vertice2) {
        Vertice<E> v = new Vertice<>(vertice1);
        List<Arista<E>> aristas1 = this.grafo.get(v);
        if (aristas1 != null) {
            Optional<Arista<E>> arista = aristas1.stream().filter(a -> a.getDesde().equals(vertice1) && a.getHacia().equals(vertice2)).findFirst();
            if (arista.isPresent()) {
                return arista.get().getPeso();
            }
        }
        return 0;
    }

    @Override
    public ConjuntoTDA<E> vertices() {
        ConjuntoTDA<E> vertices = new Conjunto<>();
        vertices.inicializarConjunto();
        for (Vertice<E> vertice : this.grafo.keySet()) {
            vertices.agregar(vertice.getDato());
        }
        return vertices;
    }

    @Override
    public ConjuntoTDA<Arista<E>> aristas() {
        ConjuntoTDA<Arista<E>> aristas = new Conjunto<>();
        aristas.inicializarConjunto();
        for (Vertice<E> vertice : this.grafo.keySet()) {
            List<Arista<E>> aristasDeVertice = this.grafo.get(vertice);
            for (Arista<E> a : aristasDeVertice) {
                aristas.agregar(a);
            }
        }
        return aristas;
    }
}
