package greedy;

import dyc.Ordenamiento;
import greedy.modelos.Objeto;
import tda.VectorTDA;
import tda.impl.Vector;

import java.util.Comparator;

public class Greedy {
    public static int cambio(VectorTDA<Integer> monedas, int vuelto) {
        int n = 0;
        int s = 0;
        int i = 0;
        Ordenamiento.mergeSort(monedas, 0, monedas.capacidadVector() - 1, Comparator.reverseOrder());
        while (s <= vuelto && i < monedas.capacidadVector()) {
            if (s + monedas.recuperarElemento(i) <= vuelto) {
                s += monedas.recuperarElemento(i);
                n++;
            } else {
                i++;
            }
        }
        if (i <= monedas.capacidadVector()) {
            return n;
        }
        return -1;
    }

    public static VectorTDA<Float> mochila(VectorTDA<Objeto> objetos, int peso) {
        VectorTDA<Float> resultado = new Vector<>();
        resultado.inicializarVector(objetos.capacidadVector());
        for (int i = 0; i < objetos.capacidadVector(); i++) {
            resultado.agregarElemento(i, 0f);
        }
        Ordenamiento.mergeSort(objetos, 0, objetos.capacidadVector() - 1, new Objeto.ComparadorValorPeso());
        int objeto = 0;
        int suma = 0;
        while (suma < peso && objeto <= objetos.capacidadVector()) {
            Objeto objetoActual = objetos.recuperarElemento(objeto);
            float fraccion = Math.min(1, (float) (peso - suma) / (float) objetoActual.getPeso());
            resultado.agregarElemento(objetoActual.getNumero(), fraccion);
            suma += fraccion * objetoActual.getPeso();
            objeto++;
        }
        return resultado;
    }
}
