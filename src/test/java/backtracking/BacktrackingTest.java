package backtracking;

import backtracking.labertinto.EntradaFaltanteException;
import backtracking.labertinto.Mapa;
import org.junit.Test;
import tda.ConjuntoTDA;
import tda.GrafoDirigidoTDA;
import tda.VectorTDA;
import tda.impl.Conjunto;
import tda.impl.GrafoDirigido;
import tda.impl.Vector;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertThrows;
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

    @Test
    public void dfsTest() {
        GrafoDirigidoTDA<Character> grafo = new GrafoDirigido<>();
        grafo.inicializarGrafo();
        grafo.agregarVertice('A');
        grafo.agregarVertice('B');
        grafo.agregarVertice('C');
        grafo.agregarVertice('D');
        grafo.agregarVertice('E');
        grafo.agregarVertice('F');
        grafo.agregarArista('A', 'B', 1);
        grafo.agregarArista('A', 'D', 1);
        grafo.agregarArista('A', 'E', 1);
        grafo.agregarArista('A', 'F', 1);
        grafo.agregarArista('B', 'C', 1);
        grafo.agregarArista('B', 'D', 1);
        grafo.agregarArista('C', 'D', 1);
        grafo.agregarArista('E', 'D', 1);
        grafo.agregarArista('E', 'F', 1);
        grafo.agregarArista('F', 'D', 1);
        ConjuntoTDA<Character> visitados = new Conjunto<>();
        visitados.inicializarConjunto();
        Backtracking.dfs(grafo, 'A', visitados);
    }

    @Test
    public void bfsTest() {
        GrafoDirigidoTDA<Integer> grafo = new GrafoDirigido<>();
        grafo.inicializarGrafo();
        grafo.agregarVertice(0);
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarVertice(5);
        grafo.agregarVertice(6);
        grafo.agregarVertice(7);
        grafo.agregarVertice(8);
        grafo.agregarArista(0, 1, 1);
        grafo.agregarArista(0, 2, 1);
        grafo.agregarArista(0, 3, 1);
        grafo.agregarArista(1, 0, 1);
        grafo.agregarArista(1, 2, 1);
        grafo.agregarArista(1, 5, 1);
        grafo.agregarArista(2, 3, 1);
        grafo.agregarArista(2, 5, 1);
        grafo.agregarArista(2, 6, 1);
        grafo.agregarArista(3, 0, 1);
        grafo.agregarArista(3, 4, 1);
        grafo.agregarArista(3, 7, 1);
        grafo.agregarArista(4, 6, 1);
        grafo.agregarArista(4, 7, 1);
        grafo.agregarArista(5, 6, 1);
        grafo.agregarArista(6, 3, 1);
        grafo.agregarArista(6, 8, 1);
        grafo.agregarArista(7, 0, 1);
        grafo.agregarArista(8, 9, 1);
        Integer nodo = Backtracking.bfs(grafo, 0, 8);
        assertNotNull(nodo);
        assertEquals(8, (int) nodo);
    }

    @Test
    public void laberintoTest() {
        Mapa mapa  = new Mapa(new char[][]{{'.', '.', '.'}, {'.', '.', '.'}, {'.', '.', 'S'}});
        assertThrows(EntradaFaltanteException.class, mapa::escapar);
        Mapa mapa1 = new Mapa(new char[][]{{'E', '.', '.'}, {'.', '.', '.'}, {'.', '.', 'S'}});
        assertEquals(4, mapa1.escapar());
        Mapa mapa2 = new Mapa(new char[][]{{'E', '#', '.'}, {'.', '#', '.'}, {'.', '#', 'S'}});
        assertEquals(-1, mapa2.escapar());
        Mapa mapa3 = new Mapa(new char[][]{{'E', '.', 'S'}, {'.', '.', '.'}, {'.', '.', 'S'}});
        assertEquals(2, mapa3.escapar());
        Mapa mapa4 = new Mapa(new char[][]{{'E', '#', '.','.'}, {'.', '.', '.','.'},{'#', '.', '#','.'},{'.', '#', '#','S'} });
        assertEquals(6, mapa4.escapar());
    }

}
