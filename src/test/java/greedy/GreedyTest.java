package greedy;

import dyc.Ordenamiento;
import greedy.modelos.Objeto;
import org.junit.Test;
import tda.VectorTDA;
import tda.impl.Vector;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class GreedyTest {
    @Test
    public void cambioTest() {
        VectorTDA<Integer> monedas = new Vector<>();
        monedas.inicializarVector(6);
        monedas.agregarElemento(0, 25);
        monedas.agregarElemento(1, 5);
        monedas.agregarElemento(2, 1);
        monedas.agregarElemento(3, 10);
        monedas.agregarElemento(4, 50);
        monedas.agregarElemento(5, 100);
        assertEquals(2, Greedy.cambio(monedas, 6));
        assertEquals(2, Greedy.cambio(monedas, 150));
        assertEquals(0, Greedy.cambio(monedas, 0));
    }

    @Test
    public void mochilaTest() {
        int peso = 10;
        VectorTDA<Objeto> objetos = new Vector<>();
        objetos.inicializarVector(4);
        objetos.agregarElemento(0, new Objeto(0, 3, 4));
        objetos.agregarElemento(1, new Objeto(1, 5, 7));
        objetos.agregarElemento(2, new Objeto(2, 4, 2));
        objetos.agregarElemento(3, new Objeto(3, 4, 5));
        VectorTDA<Float> fraccion = new Vector<>();
        fraccion.inicializarVector(4);
        fraccion.agregarElemento(0, 1f);
        fraccion.agregarElemento(1, 1f);
        fraccion.agregarElemento(2, 0f);
        fraccion.agregarElemento(3, 0.5f);
        VectorTDA<Float> fraccionResultado = Greedy.mochila(objetos, peso);
        Ordenamiento.mergeSort(objetos, 0, objetos.capacidadVector() - 1, Comparator.comparingInt(Objeto::getNumero));
        for (int i = 0; i < objetos.capacidadVector(); i++) {
            assertEquals(fraccion.recuperarElemento(i), fraccionResultado.recuperarElemento(i));
        }
        float valor = 0;
        int pesoTotal = 0;
        for (int i = 0; i < fraccionResultado.capacidadVector(); i++) {
            valor += fraccionResultado.recuperarElemento(i) * objetos.recuperarElemento(i).getValor();
            pesoTotal += fraccionResultado.recuperarElemento(i) * objetos.recuperarElemento(i).getPeso();
        }
        assertEquals(13.5, valor, 0.01);
        assertEquals(peso, pesoTotal);
    }
}
