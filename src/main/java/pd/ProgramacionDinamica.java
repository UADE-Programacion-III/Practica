package pd;

import dyc.Ordenamiento;
import greedy.modelos.Objeto;
import tda.ConjuntoTDA;
import tda.GrafoDirigidoTDA;
import tda.MatrizTDA;
import tda.VectorTDA;
import tda.impl.GrafoDirigido;
import tda.impl.Matriz;
import tda.impl.Vector;

import java.util.Comparator;

public class ProgramacionDinamica {
    public static int cambio(VectorTDA<Integer> monedas, int valor) {
        Ordenamiento.mergeSort(monedas, 0, monedas.capacidadVector() - 1, Comparator.naturalOrder());
        int cantidadMonedas = monedas.capacidadVector();
        MatrizTDA<Integer> matriz = new Matriz<>();
        matriz.inicializarMatriz(cantidadMonedas, valor + 1);
        for (int i = 0; i < cantidadMonedas; i++) {
            Integer monedaActual = monedas.recuperarElemento(i);
            matriz.setearValor(i, 0, 0);
            for (int j = 1; j <= valor; j++) {
                if (i == 0) {
                    if (monedaActual > j) {
                        matriz.setearValor(i, j, Integer.MAX_VALUE);
                    } else {
                        matriz.setearValor(i, j, 1 + matriz.obtenerValor(i, j - monedaActual));
                    }
                } else {
                    if (monedaActual > j) {
                        matriz.setearValor(i, j, matriz.obtenerValor(i - 1, j));
                    } else {
                        matriz.setearValor(i, j, Math.min(matriz.obtenerValor(i - 1, j), 1 + matriz.obtenerValor(i, j - monedaActual)));
                    }
                }
            }
        }
        return matriz.obtenerValor(cantidadMonedas - 1, valor);
    }

    public static int mochila(VectorTDA<Objeto> objetos, int peso) {
        MatrizTDA<Integer> matriz = new Matriz<>();
        matriz.inicializarMatriz(objetos.capacidadVector(), peso);
        for (int i = 0; i < objetos.capacidadVector(); i++) {
            Objeto objetoActual = objetos.recuperarElemento(i);
            for (int j = 0; j < peso; j++) {
                if (i == 0 && objetoActual.getPeso() > j) {
                    matriz.setearValor(i, j, 0);
                } else {
                    if (i == 0) {
                        matriz.setearValor(i, j, objetoActual.getValor());
                    } else {
                        if (objetoActual.getPeso() > j) {
                            matriz.setearValor(i, j, matriz.obtenerValor(i - 1, j));
                        } else {
                            matriz.setearValor(i, j, Math.max(matriz.obtenerValor(i - 1, j), matriz.obtenerValor(i - 1, j - objetoActual.getPeso()) + objetoActual.getValor()));
                        }
                    }
                }
            }
        }
        return matriz.obtenerValor(objetos.capacidadVector() - 1, peso - 1);
    }

    public static int longitudSubsecuenciaMaxima(VectorTDA<Character> x, VectorTDA<Character> y) {
        int longitudX = Math.max(1, x.capacidadVector());
        int longitudY = Math.max(1, y.capacidadVector());
        MatrizTDA<Integer> matriz = new Matriz<>();
        matriz.inicializarMatriz(longitudX, longitudY);
        for (int i = 0; i < longitudX; i++) {
            for (int j = 0; j < longitudY; j++) {
                if (i == 0 || j == 0) {
                    matriz.setearValor(i, j, 0);
                }
            }
        }
        for (int i = 1; i < x.capacidadVector(); i++) {
            for (int j = 1; j < y.capacidadVector(); j++) {
                if (x.recuperarElemento(i - 1).equals(y.recuperarElemento(j - 1))) {
                    matriz.setearValor(i, j, matriz.obtenerValor(i - 1, j - 1) + 1);
                } else {
                    matriz.setearValor(i, j, Math.max(matriz.obtenerValor(i - 1, j), matriz.obtenerValor(i, j - 1)));
                }
            }
        }
        return matriz.obtenerValor(longitudX - 1, longitudY - 1);
    }

    public static int subsecuenciaCrecienteMasLarga(VectorTDA<Integer> x) {
        int longitud = 1;
        VectorTDA<Integer> resultado = new Vector<>();
        resultado.inicializarVector(x.capacidadVector());
        for (int i = 0; i < x.capacidadVector(); i++) {
            resultado.agregarElemento(i, 1);
        }
        for (int i = 0; i < x.capacidadVector(); i++) {
            for (int j = 0; j < i; j++) {
                if (x.recuperarElemento(i) > x.recuperarElemento(j)) {
                    resultado.agregarElemento(i, Math.max(resultado.recuperarElemento(i), resultado.recuperarElemento(j) + 1));
                }
            }
            int longitudParcial = resultado.recuperarElemento(i);
            if (longitud < longitudParcial) {
                longitud = longitudParcial;
            }
        }
        return longitud;
    }

    public static <E> GrafoDirigidoTDA<E> floyd(GrafoDirigidoTDA<E> grafo) {
        ConjuntoTDA<E> conjuntoI, conjuntoJ, conjuntoK;
        E i, j, k;
        GrafoDirigidoTDA<E> r = new GrafoDirigido<>();
        r.inicializarGrafo();
        conjuntoK = grafo.vertices();
        while (!conjuntoK.conjuntoVacio()) {
            k = conjuntoK.elegir();
            conjuntoK.sacar(k);
            r.agregarVertice(k);
        }
        conjuntoK = grafo.vertices();
        while (!conjuntoK.conjuntoVacio()) {
            k = conjuntoK.elegir();
            conjuntoK.sacar(k);
            conjuntoI = grafo.adyacentes(k);
            while (!conjuntoI.conjuntoVacio()) {
                i = conjuntoI.elegir();
                conjuntoI.sacar(i);
                r.agregarArista(k, i, grafo.pesoArista(k, i));
            }
        }
        conjuntoK = grafo.vertices();
        while (!conjuntoK.conjuntoVacio()) {
            k = conjuntoK.elegir();
            conjuntoK.sacar(k);
            conjuntoI = grafo.vertices();
            conjuntoI.sacar(k);
            while (!conjuntoI.conjuntoVacio()) {
                i = conjuntoI.elegir();
                conjuntoI.sacar(i);
                if (r.existeArista(i, k)) {
                    conjuntoJ = r.adyacentes(k);
                    conjuntoJ.sacar(i);
                    while (!conjuntoJ.conjuntoVacio()) {
                        j = conjuntoJ.elegir();
                        conjuntoJ.sacar(j);
                        if (r.existeArista(i, j)) {
                            if (r.pesoArista(i, k) + r.pesoArista(k, j) < r.pesoArista(i, j)) {
                                r.agregarArista(i, j, r.pesoArista(i, k) + r.pesoArista(k, j));
                            }
                        } else {
                            r.agregarArista(i, j, r.pesoArista(i, k) + r.pesoArista(k, j));
                        }
                    }
                }
            }
        }
        return r;
    }
}
