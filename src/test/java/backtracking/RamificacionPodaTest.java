package backtracking;

import org.junit.Test;
import tda.VectorTDA;
import tda.impl.Vector;

import static junit.framework.TestCase.assertEquals;

public class RamificacionPodaTest {

    @Test
    public void mochilaRyPTest() {
        int objetos = 4;
        int capacidad = 15;
        VectorTDA<Integer> pesos = new Vector<>();
        VectorTDA<Integer> valores = new Vector<>();
        VectorTDA<Integer> mejorSolucion = new Vector<>();
        VectorTDA<Integer> actualSolucion = new Vector<>();
        pesos.inicializarVector(objetos);
        valores.inicializarVector(objetos);
        mejorSolucion.inicializarVector(objetos);
        actualSolucion.inicializarVector(objetos);
        pesos.agregarElemento(0, 2);
        pesos.agregarElemento(1, 4);
        pesos.agregarElemento(2, 6);
        pesos.agregarElemento(3, 9);
        valores.agregarElemento(0, 10);
        valores.agregarElemento(1, 10);
        valores.agregarElemento(2, 12);
        valores.agregarElemento(3, 18);
        VectorTDA<Integer> resultado = RamificacionPoda.mochilaRyP(valores, pesos, capacidad);
        int valor = 0;
        for (int i = 0; i < resultado.capacidadVector(); i++) {
            valor+= resultado.recuperarElemento(i) * valores.recuperarElemento(i);
        }
        assertEquals(38, valor);
    }
}
