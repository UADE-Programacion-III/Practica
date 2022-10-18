package backtracking;

import tda.ConjuntoTDA;
import tda.GrafoDirigidoTDA;
import tda.VectorTDA;
import tda.impl.Conjunto;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Backtracking {
    public static boolean reinas(VectorTDA<Integer> s, int n, int etapa) {
        boolean solucion = false;
        s.agregarElemento(etapa, 0);
        while (s.recuperarElemento(etapa) < n && !solucion) {
            s.agregarElemento(etapa, s.recuperarElemento(etapa) + 1);
            if (reinaValida(s, etapa)) {
                if (etapa == n - 1) {
                    solucion = true;
                } else {
                    solucion = reinas(s, n, etapa + 1);
                }

            }
        }
        return solucion;
    }

    private static boolean reinaValida(VectorTDA<Integer> s, int etapa) {
        for (int i = 0; i < etapa; i++) {
            if (Objects.equals(s.recuperarElemento(i), s.recuperarElemento(etapa)) || Math.abs(s.recuperarElemento(i) - s.recuperarElemento(etapa)) == Math.abs(i - etapa)) {
                return false;
            }
        }
        return true;
    }

    public static void sumaSubconjuntos(VectorTDA<Integer> v, int m, VectorTDA<Integer> solucionActual, int sumaActual, int etapa) {
        for (int i = 0; i <= 1; i++) {
            solucionActual.agregarElemento(etapa, i);
            sumaActual += v.recuperarElemento(etapa) * i;
            if (etapa == v.capacidadVector() - 1) {
                if (sumaActual == m) {
                    System.out.println(solucionActual);
                }
            } else {
                if (sumaActual <= m) {
                    sumaSubconjuntos(v, m, solucionActual, sumaActual, etapa + 1);
                }
            }
        }
    }

    public static void particionesIguales(VectorTDA<Integer> original, VectorTDA<Integer> solucion, int etapa) {
        for (int i = 0; i <= 1; i++) {
            solucion.agregarElemento(etapa, i);
            if (etapa == original.capacidadVector() - 1) {
                int suma1 = 0;
                int suma2 = 0;
                for (int j = 0; j < original.capacidadVector(); j++) {
                    if (solucion.recuperarElemento(j) == 0) {
                        suma1 += original.recuperarElemento(j);
                    } else {
                        suma2 += original.recuperarElemento(j);
                    }
                }
                if (suma1 == suma2) {
                    System.out.println(solucion);
                }
            } else {
                particionesIguales(original, solucion, etapa + 1);
            }
        }
    }

    public static <E> void dfs(GrafoDirigidoTDA<E> grafo, E vertice, ConjuntoTDA<E> visitados) {
        visitados.agregar(vertice);
        System.out.println(vertice);
        VectorTDA<E> vecinos = grafo.adyacentes(vertice).aVector();
        while (vecinos.capacidadVector() != 0) {
            E vecino = vecinos.recuperarElemento(0);
            vecinos.eliminarElemento(0);
            if (!visitados.pertenece(vecino)) {
                dfs(grafo, vecino, visitados);
            }
        }
    }

    public static <E> E bfs(GrafoDirigidoTDA<E> grafo, E inicio, E valor) {
        Queue<E> cola = new LinkedList<>();
        ConjuntoTDA<E> visitados = new Conjunto<>();
        VectorTDA<E> vecinos;
        visitados.inicializarConjunto();
        cola.add(inicio);
        while (!cola.isEmpty()) {
            E actual = cola.poll();
            if (actual.equals(valor)) {
                return actual;
            }
            vecinos = grafo.adyacentes(actual).aVector();
            while (vecinos.capacidadVector() != 0) {
                E vecino = vecinos.recuperarElemento(0);
                vecinos.eliminarElemento(0);
                if (!visitados.pertenece(vecino)) {
                    cola.add(vecino);
                }
            }
        }
        return null;
    }
}
