package backtracking;

import org.junit.Test;
import tda.VectorTDA;
import tda.impl.Vector;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class BacktrackingTest {
    @Test
    public void reinasTest() {
        int reinas = 4;
        VectorTDA<Integer> solucion = new Vector<>();
        solucion.inicializarVector(reinas);
        assertTrue(Backtracking.reinas(solucion, reinas, 0));
        System.out.println(solucion);
        reinas = 3;
        solucion.inicializarVector(reinas);
        assertFalse(Backtracking.reinas(solucion, reinas, 0));
    }

    @Test
    public void sumaSubconjuntosTest() {
        VectorTDA<Integer> v = new Vector<>();
        int elementos = 3;
        v.inicializarVector(elementos);
        v.agregarElemento(0, 13);
        v.agregarElemento(1, 20);
        v.agregarElemento(2, 7);
        int m = 20;
        VectorTDA<Integer> solucionActual = new Vector<>();
        solucionActual.inicializarVector(elementos);
        Backtracking.sumaSubconjuntos(v, m, solucionActual, 0, 0);
    }

    @Test
    public void particionesIgualesTest() {
        VectorTDA<Integer> v = new Vector<>();
        int elementos = 3;
        v.inicializarVector(elementos);
        v.agregarElemento(0, 13);
        v.agregarElemento(1, 20);
        v.agregarElemento(2, 7);
        VectorTDA<Integer> solucionActual = new Vector<>();
        solucionActual.inicializarVector(elementos);
        Backtracking.particionesIguales(v, solucionActual, 0);
        elementos = 5;
        v.inicializarVector(elementos);
        v.agregarElemento(0, 2);
        v.agregarElemento(1, 5);
        v.agregarElemento(2, 8);
        v.agregarElemento(3, 3);
        v.agregarElemento(4, 2);
        solucionActual.inicializarVector(elementos);
        Backtracking.particionesIguales(v, solucionActual, 0);
    }
}
