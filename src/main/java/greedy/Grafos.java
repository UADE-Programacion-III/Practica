package greedy;

import dyc.Ordenamiento;
import tda.*;
import tda.impl.*;

import java.util.Comparator;

public class Grafos {
    public static <E> GrafoDirigidoTDA<E> dijkstra(GrafoDirigidoTDA<E> grafo, E origen, E valorInicial) {
        E vertice, verticeAuxiliar, mejorVertice;
        int mejorDistancia;
        GrafoDirigidoTDA<E> distanciasMinimas = new GrafoDirigido<>();
        distanciasMinimas.inicializarGrafo();
        distanciasMinimas.agregarVertice(origen);
        ConjuntoTDA<E> vertices = grafo.vertices();
        vertices.sacar(origen);
        while (!vertices.conjuntoVacio()) {
            vertice = vertices.elegir();
            vertices.sacar(vertice);
            distanciasMinimas.agregarVertice(vertice);
            if (grafo.existeArista(origen, vertice)) {
                distanciasMinimas.agregarArista(origen, vertice, grafo.pesoArista(origen, vertice));
            }
        }
        ConjuntoTDA<E> pendientes = grafo.vertices();
        pendientes.sacar(origen);
        ConjuntoTDA<E> auxiliarPendientes = new Conjunto<>();
        auxiliarPendientes.inicializarConjunto();
        while (!pendientes.conjuntoVacio()) {
            mejorDistancia = 0;
            mejorVertice = valorInicial;
            while (!pendientes.conjuntoVacio()) {
                verticeAuxiliar = pendientes.elegir();
                pendientes.sacar(verticeAuxiliar);
                auxiliarPendientes.agregar(verticeAuxiliar);
                if ((distanciasMinimas.existeArista(origen, verticeAuxiliar) && (mejorDistancia == 0 || (
                        mejorDistancia > distanciasMinimas.pesoArista(origen, verticeAuxiliar))))) {
                    mejorDistancia = distanciasMinimas.pesoArista(origen, verticeAuxiliar);
                    mejorVertice = verticeAuxiliar;
                }
            }
            vertice = mejorVertice;
            if (vertice != valorInicial) {
                auxiliarPendientes.sacar(vertice);
                while (!auxiliarPendientes.conjuntoVacio()) {
                    verticeAuxiliar = auxiliarPendientes.elegir();
                    auxiliarPendientes.sacar(verticeAuxiliar);
                    pendientes.agregar(verticeAuxiliar);
                    if (grafo.existeArista(vertice, verticeAuxiliar)) {
                        if (!distanciasMinimas.existeArista(origen, verticeAuxiliar)) {
                            distanciasMinimas.agregarArista(origen, verticeAuxiliar, distanciasMinimas.pesoArista(origen, vertice) + grafo.pesoArista(vertice, verticeAuxiliar));
                        } else {
                            if (distanciasMinimas.pesoArista(origen, verticeAuxiliar) > distanciasMinimas.pesoArista(origen, vertice) + grafo.pesoArista(vertice, verticeAuxiliar)) {
                                distanciasMinimas.agregarArista(origen, verticeAuxiliar, distanciasMinimas.pesoArista(origen, vertice) + grafo.pesoArista(vertice, verticeAuxiliar));
                            }
                        }
                    }
                }
            }
        }
        return distanciasMinimas;
    }

    public static <E> GrafoTDA<E> prim(GrafoTDA<E> grafo, E valorInicial) {
        E vertice, verticeAuxiliar, mejorVertice;
        int mejorDistancia;
        GrafoTDA<E> resultado = new Grafo<>();
        resultado.inicializarGrafo();
        ConjuntoTDA<E> vertices = grafo.vertices();
        vertice = vertices.elegir();
        vertices.sacar(vertice);
        resultado.agregarVertice(vertice);
        while (!vertices.conjuntoVacio()) {
            verticeAuxiliar = vertices.elegir();
            vertices.sacar(verticeAuxiliar);
            resultado.agregarVertice(verticeAuxiliar);
            if (grafo.existeArista(verticeAuxiliar, vertice)) {
                resultado.agregarArista(verticeAuxiliar, vertice, grafo.pesoArista(verticeAuxiliar, vertice));
            }
        }
        ConjuntoTDA<E> pendientes = grafo.vertices();
        pendientes.sacar(vertice);
        ConjuntoTDA<E> pendientesAuxiliar = new Conjunto<>();
        pendientesAuxiliar.inicializarConjunto();
        while (!pendientes.conjuntoVacio()) {
            mejorDistancia = 0;
            mejorVertice = valorInicial;
            while (!pendientes.conjuntoVacio()) {
                verticeAuxiliar = pendientes.elegir();
                pendientes.sacar(verticeAuxiliar);
                pendientesAuxiliar.agregar(verticeAuxiliar);
                if ((!resultado.adyacentes(verticeAuxiliar).conjuntoVacio()) && (mejorDistancia == 0 ||
                        (mejorDistancia > resultado.pesoArista(verticeAuxiliar, resultado.adyacentes(verticeAuxiliar).elegir())))) {
                    mejorDistancia = resultado.pesoArista(verticeAuxiliar, resultado.adyacentes(verticeAuxiliar).elegir());
                    mejorVertice = verticeAuxiliar;
                }
            }
            vertice = mejorVertice;
            pendientesAuxiliar.sacar(vertice);
            while (!pendientesAuxiliar.conjuntoVacio()) {
                verticeAuxiliar = pendientesAuxiliar.elegir();
                pendientesAuxiliar.sacar(verticeAuxiliar);
                pendientes.agregar(verticeAuxiliar);
                if (grafo.existeArista(verticeAuxiliar, vertice)) {
                    if (resultado.adyacentes(verticeAuxiliar).conjuntoVacio()) {
                        resultado.agregarArista(verticeAuxiliar, vertice, grafo.pesoArista(verticeAuxiliar, vertice));
                    } else {
                        if (resultado.pesoArista(verticeAuxiliar, resultado.adyacentes(verticeAuxiliar).elegir()) > grafo.pesoArista(verticeAuxiliar, vertice)) {
                            resultado.eliminarArista(verticeAuxiliar, resultado.adyacentes(verticeAuxiliar).elegir());
                            resultado.agregarArista(verticeAuxiliar, vertice, grafo.pesoArista(verticeAuxiliar, vertice));
                        }
                    }
                }
            }
        }
        return resultado;
    }

    public static <E> GrafoTDA<E> kruskal(GrafoTDA<E> grafo, E valorInicial) {
        E vertice, c1 = valorInicial, c2 = valorInicial;
        int cantidad = 0, cantidadPendientes;
        Par<E, E> parAuxiliar;
        GrafoTDA<E> resultado = new Grafo<>();
        resultado.inicializarGrafo();
        ConjuntoTDA<E> vertices = grafo.vertices();
        while (!vertices.conjuntoVacio()) {
            vertice = vertices.elegir();
            vertices.sacar(vertice);
            resultado.agregarVertice(vertice);
            cantidad++;
        }
        ConjuntoTDA<Arista<E>> conjuntoAristas = grafo.aristas();
        VectorTDA<Arista<E>> aristas = new Vector<>();
        aristas.inicializarVector(conjuntoAristas.capacidad());
        int cantidadAristas = 0;
        while (!conjuntoAristas.conjuntoVacio()) {
            Arista<E> arista = conjuntoAristas.elegir();
            aristas.agregarElemento(cantidadAristas++, arista);
            conjuntoAristas.sacar(arista);
        }
        Ordenamiento.mergeSort(aristas, 0, aristas.capacidadVector() - 1, Comparator.comparing(Arista::getPeso));
        VectorTDA<Par<E, E>> conjuntos = new Vector<>();
        conjuntos.inicializarVector(cantidad);
        vertices = grafo.vertices();
        int i = 0;
        while (!vertices.conjuntoVacio()) {
            vertice = vertices.elegir();
            vertices.sacar(vertice);
            parAuxiliar = new Par<>(vertice, vertice);
            conjuntos.agregarElemento(i, parAuxiliar);
            i++;
        }
        cantidadPendientes = cantidad;
        Arista<E> aristaAuxiliar;
        while (cantidadPendientes > 1) {
            aristaAuxiliar = aristas.recuperarElemento(0);
            for (i = 0; i < cantidad; i++) {
                if (conjuntos.recuperarElemento(i).getValor1().equals(aristaAuxiliar.getDesde())) {
                    c1 = conjuntos.recuperarElemento(i).getValor2();
                }
                if (conjuntos.recuperarElemento(i).getValor1().equals(aristaAuxiliar.getHacia())) {
                    c2 = conjuntos.recuperarElemento(i).getValor2();
                }
            }
            if (c1 != c2) {
                for (i = 0; i < cantidad; i++) {
                    if (conjuntos.recuperarElemento(i).getValor2().equals(c2)) {
                        conjuntos.recuperarElemento(i).setValor2(c1);
                    }
                }
                resultado.agregarArista(aristaAuxiliar.getDesde(), aristaAuxiliar.getHacia(), aristaAuxiliar.getPeso());
                cantidadPendientes--;
            }
            aristas.eliminarElemento(0);
        }
        return resultado;
    }
}
