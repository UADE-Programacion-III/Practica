package tda;

import tda.impl.Arista;

public interface GrafoTDA<E> {
    ConjuntoTDA<E> adyacentes(E vertice);

    void agregarArista(E vertice1, E vertice2, int costo);

    void agregarVertice(E vertice);

    E elegir();

    void eliminarArista(E vertice1, E vertice2);

    void eliminarVertice(E vertice);

    boolean existeArista(E vertice1, E vertice2);

    void inicializarGrafo();

    int pesoArista(E vertice1, E vertice2);

    ConjuntoTDA<E> vertices();

    ConjuntoTDA<Arista<E>> aristas();

}