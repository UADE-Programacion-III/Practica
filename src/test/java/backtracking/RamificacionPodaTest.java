package backtracking;

import org.junit.Test;
import tda.MatrizTDA;
import tda.VectorTDA;
import tda.impl.Matriz;
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

    @Test
    public void asignacionRyPTest() {
        MatrizTDA<Integer> tareasPorEmpleado = new Matriz<>();
        tareasPorEmpleado.inicializarMatriz(4, 4);
        tareasPorEmpleado.setearValor(0, 0, 11);
        tareasPorEmpleado.setearValor(0, 1, 12);
        tareasPorEmpleado.setearValor(0, 2, 18);
        tareasPorEmpleado.setearValor(0, 3, 40);
        tareasPorEmpleado.setearValor(1, 0, 14);
        tareasPorEmpleado.setearValor(1, 1, 15);
        tareasPorEmpleado.setearValor(1, 2, 13);
        tareasPorEmpleado.setearValor(1, 3, 22);
        tareasPorEmpleado.setearValor(2, 0, 11);
        tareasPorEmpleado.setearValor(2, 1, 17);
        tareasPorEmpleado.setearValor(2, 2, 19);
        tareasPorEmpleado.setearValor(2, 3, 23);
        tareasPorEmpleado.setearValor(3, 0, 17);
        tareasPorEmpleado.setearValor(3, 1, 14);
        tareasPorEmpleado.setearValor(3, 2, 20);
        tareasPorEmpleado.setearValor(3, 3, 28);
        VectorTDA<Integer> resultado = RamificacionPoda.asignacionRyP(tareasPorEmpleado);
        int valor = 0;
        for (int i = 0; i < resultado.capacidadVector(); i++) {
            valor+= tareasPorEmpleado.obtenerValor(i, resultado.recuperarElemento(i));
        }
        assertEquals(61, valor);
    }
}
