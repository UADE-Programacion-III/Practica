package pd;

import dyc.Ordenamiento;
import greedy.modelos.Objeto;
import tda.MatrizTDA;
import tda.VectorTDA;
import tda.impl.Matriz;

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
}
