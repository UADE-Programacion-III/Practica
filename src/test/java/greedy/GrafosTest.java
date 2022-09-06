package greedy;

import org.junit.Test;
import tda.GrafoDirigidoTDA;
import tda.GrafoTDA;
import tda.impl.Grafo;
import tda.impl.GrafoDirigido;

import static org.junit.Assert.assertEquals;

public class GrafosTest {

    @Test
    public void dijkstraTest() {
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
        GrafoDirigidoTDA<Integer> grafoResultado = Grafos.dijkstra(grafo, 1, 0);
        assertEquals(12, grafoResultado.pesoArista(1, 2));
        assertEquals(21, grafoResultado.pesoArista(1, 3));
        assertEquals(53, grafoResultado.pesoArista(1, 4));
        assertEquals(30, grafoResultado.pesoArista(1, 5));
        assertEquals(42, grafoResultado.pesoArista(1, 6));
        assertEquals(0, grafoResultado.pesoArista(1, 7));
        assertEquals(0, grafoResultado.pesoArista(1, 8));
        assertEquals(0, grafoResultado.pesoArista(1, 10));
    }

    @Test
    public void primTest() {
        GrafoTDA<Integer> grafo = new Grafo<>();
        grafo.inicializarGrafo();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarVertice(5);
        grafo.agregarArista(1, 2, 15);
        grafo.agregarArista(5, 2, 8);
        grafo.agregarArista(5, 1, 10);
        grafo.agregarArista(3, 1, 9);
        grafo.agregarArista(4, 1, 7);
        grafo.agregarArista(4, 5, 3);
        grafo.agregarArista(4, 3, 8);
        GrafoTDA<Integer> resultado = Grafos.prim(grafo, 0);
        assertEquals(8, resultado.pesoArista(3, 4));
        assertEquals(8, resultado.pesoArista(2, 5));
        assertEquals(3, resultado.pesoArista(4, 5));
        assertEquals(7, resultado.pesoArista(1, 4));
        assertEquals(0, resultado.pesoArista(1, 2));
        assertEquals(0, resultado.pesoArista(1, 3));
        assertEquals(0, resultado.pesoArista(1, 5));
        assertEquals(0, resultado.pesoArista(2, 3));
        assertEquals(0, resultado.pesoArista(2, 4));
        assertEquals(0, resultado.pesoArista(3, 5));
    }

    @Test
    public void kruskalTest() {
        GrafoTDA<Integer> grafo = new Grafo<>();
        grafo.inicializarGrafo();
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarVertice(5);
        grafo.agregarArista(1, 2, 15);
        grafo.agregarArista(5, 2, 8);
        grafo.agregarArista(5, 1, 10);
        grafo.agregarArista(3, 1, 9);
        grafo.agregarArista(4, 1, 7);
        grafo.agregarArista(4, 5, 3);
        grafo.agregarArista(4, 3, 8);
        GrafoTDA<Integer> resultado = Grafos.kruskal(grafo, 0);
        assertEquals(8, resultado.pesoArista(3, 4));
        assertEquals(8, resultado.pesoArista(2, 5));
        assertEquals(3, resultado.pesoArista(4, 5));
        assertEquals(7, resultado.pesoArista(1, 4));
        assertEquals(0, resultado.pesoArista(1, 2));
        assertEquals(0, resultado.pesoArista(1, 3));
        assertEquals(0, resultado.pesoArista(1, 5));
        assertEquals(0, resultado.pesoArista(2, 3));
        assertEquals(0, resultado.pesoArista(2, 4));
        assertEquals(0, resultado.pesoArista(3, 5));
    }
}
