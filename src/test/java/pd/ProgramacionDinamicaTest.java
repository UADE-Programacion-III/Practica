package pd;

import greedy.modelos.Objeto;
import org.junit.Assert;
import org.junit.Test;
import tda.VectorTDA;
import tda.impl.Vector;

import static junit.framework.TestCase.assertEquals;

public class ProgramacionDinamicaTest {

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
        Assert.assertEquals(2, ProgramacionDinamica.cambio(monedas, 6));
        Assert.assertEquals(2, ProgramacionDinamica.cambio(monedas, 150));
        Assert.assertEquals(0, ProgramacionDinamica.cambio(monedas, 0));
        monedas.inicializarVector(3);
        monedas.agregarElemento(0, 4);
        monedas.agregarElemento(1, 1);
        monedas.agregarElemento(2, 6);
        Assert.assertEquals(2, ProgramacionDinamica.cambio(monedas, 8));
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
        assertEquals(12, ProgramacionDinamica.mochila(objetos, peso));
    }
}
