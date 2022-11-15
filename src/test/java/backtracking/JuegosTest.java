package backtracking;

import org.junit.Test;
import tda.ConjuntoTDA;
import tda.VectorTDA;
import tda.impl.Conjunto;
import tda.impl.Vector;

import static junit.framework.TestCase.assertEquals;

public class JuegosTest {

    @Test
    public void fosforosTest() {
        ConjuntoTDA<Jugada<Integer>> jugadas = new Conjunto<>();
        jugadas.inicializarConjunto();
        assertEquals(-1, Juegos.fosforos(7, 0, jugadas));
    }

    @Test
    public void tatetiTest() {
        char [][] tablero = new char[][]{{'X', 0, 'X'}, {0, 'O', 0}, {'O', 0, 'O'}};
        assertEquals(1, Juegos.tateti(tablero, Juegos.JUGADOR));
    }

    @Test
    public void monedasTest() {
        VectorTDA<Integer> pilas = new Vector<>();
        pilas.inicializarVector(1);
        pilas.agregarElemento(0, 6);
        assertEquals(1, Juegos.monedas(pilas, 1));
    }

    @Test
    public void tatetiAlfaBetaTest() {
        char [][] tablero = new char[][]{{'X', 0, 'X'}, {0, 'O', 0}, {'O', 0, 'O'}};
        assertEquals(1, Juegos.tatetiAlfaBeta(tablero, Juegos.JUGADOR, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
}
