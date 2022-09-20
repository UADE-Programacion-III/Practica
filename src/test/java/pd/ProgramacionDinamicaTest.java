package pd;

import greedy.modelos.Objeto;
import org.junit.Assert;
import org.junit.Test;
import tda.GrafoDirigidoTDA;
import tda.VectorTDA;
import tda.impl.GrafoDirigido;
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

    @Test
    public void subsecuenciaMaximaTest() {
        VectorTDA<Character> x = new Vector<>();
        x.inicializarVector(4);
        x.agregarElemento(0, 'a');
        x.agregarElemento(1, 'b');
        x.agregarElemento(2, 'd');
        x.agregarElemento(3, 'a');
        VectorTDA<Character> y = new Vector<>();
        y.inicializarVector(7);
        y.agregarElemento(0, 'a');
        y.agregarElemento(1, 'b');
        y.agregarElemento(2, 'a');
        y.agregarElemento(3, 'c');
        y.agregarElemento(4, 'd');
        y.agregarElemento(5, 'e');
        y.agregarElemento(6, 'b');
        assertEquals(3, ProgramacionDinamica.longitudSubsecuenciaMaxima(x, y));
        x.inicializarVector(0);
        assertEquals(0, ProgramacionDinamica.longitudSubsecuenciaMaxima(x, y));
    }

    @Test
    public void subsecuenciaCrecienteMasLargaTest() {
        VectorTDA<Integer> x = new Vector<>();
        x.inicializarVector(7);
        x.agregarElemento(0, 100);
        x.agregarElemento(1, 22);
        x.agregarElemento(2, -2);
        x.agregarElemento(3, 33);
        x.agregarElemento(4, 21);
        x.agregarElemento(5, 50);
        x.agregarElemento(6, 41);
        assertEquals(3, ProgramacionDinamica.subsecuenciaCrecienteMasLarga(x));
    }

    @Test
    public void floydTest() {
        GrafoDirigidoTDA<Integer> grafo = new GrafoDirigido<>();
        grafo.inicializarGrafo();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarVertice(5);
        grafo.agregarVertice(6);
        grafo.agregarVertice(7);
        grafo.agregarVertice(8);
        grafo.agregarVertice(10);
        grafo.agregarArista(1, 2, 12);
        grafo.agregarArista(2, 1, 10);
        grafo.agregarArista(1, 3, 21);
        grafo.agregarArista(3, 4, 32);
        grafo.agregarArista(3, 5, 9);
        grafo.agregarArista(4, 6, 10);
        grafo.agregarArista(5, 6, 12);
        grafo.agregarArista(6, 5, 87);
        grafo.agregarArista(8, 10, 10);
        GrafoDirigidoTDA<Integer> grafoResultado = ProgramacionDinamica.floyd(grafo);
        Assert.assertEquals(12, grafoResultado.pesoArista(1, 2));
        Assert.assertEquals(21, grafoResultado.pesoArista(1, 3));
        Assert.assertEquals(53, grafoResultado.pesoArista(1, 4));
        Assert.assertEquals(30, grafoResultado.pesoArista(1, 5));
        Assert.assertEquals(42, grafoResultado.pesoArista(1, 6));
        Assert.assertEquals(0, grafoResultado.pesoArista(1, 7));
        Assert.assertEquals(0, grafoResultado.pesoArista(1, 8));
        Assert.assertEquals(0, grafoResultado.pesoArista(1, 10));
        Assert.assertEquals(10, grafoResultado.pesoArista(2, 1));
        Assert.assertEquals(31, grafoResultado.pesoArista(2, 3));
        Assert.assertEquals(63, grafoResultado.pesoArista(2, 4));
        Assert.assertEquals(40, grafoResultado.pesoArista(2, 5));
        Assert.assertEquals(52, grafoResultado.pesoArista(2, 6));
        Assert.assertEquals(32, grafoResultado.pesoArista(3, 4));
        Assert.assertEquals(9, grafoResultado.pesoArista(3, 5));
        Assert.assertEquals(21, grafoResultado.pesoArista(3, 6));
        Assert.assertEquals(97, grafoResultado.pesoArista(4, 5));
        Assert.assertEquals(10, grafoResultado.pesoArista(4, 6));
        Assert.assertEquals(12, grafoResultado.pesoArista(5, 6));
        Assert.assertEquals(87, grafoResultado.pesoArista(6, 5));
        Assert.assertEquals(10, grafoResultado.pesoArista(8, 10));
    }
}
