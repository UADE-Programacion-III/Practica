package backtracking;

import tda.ConjuntoTDA;
import tda.VectorTDA;
import tda.impl.Par;
import tda.impl.Vector;

public class Juegos {
    public static final char JUGADOR = 'X';
    public static final char OPONENTE = 'O';

    public static int fosforos(int cantFosforos, int nivelJuego, ConjuntoTDA<Jugada<Integer>> jugadas) {
        Jugada<Integer> jugada = new Jugada<>();
        if (cantFosforos == 0) {
            return -1 * nivelJuego;
        } else {
            int hijos = 1;
            int proxNivelJuego;
            int valor;
            boolean podar = false;
            if (nivelJuego == 1) {
                proxNivelJuego = -1;
                valor = -1;
            } else {
                proxNivelJuego = 1;
                valor = 1;
            }
            while (hijos <= 3 && hijos <= cantFosforos && !podar) {
                cantFosforos -= hijos;
                jugada.setDato(hijos);
                jugada.setJugada(nivelJuego);
                jugadas.agregar(jugada);
                if (nivelJuego == 1) {
                    valor = Math.max(valor, fosforos(cantFosforos, proxNivelJuego, jugadas));
                } else {
                    valor = Math.min(valor, fosforos(cantFosforos, proxNivelJuego, jugadas));
                }
                if ((nivelJuego == 1 && valor == 1) || (nivelJuego == -1 && valor == -1)) {
                    podar = true;
                } else {
                    cantFosforos += hijos;
                    jugadas.sacar(jugada);
                }
                hijos++;
            }
            return valor;
        }
    }

    public static int tateti(char [][] tablero, char jugador) {
        System.out.println("Recorriendo nodo");
        int valor;
        if (esGanador(tablero, JUGADOR)) {
            return 1;
        }
        if (esGanador(tablero, OPONENTE)) {
            return -1;
        }
        VectorTDA<Par<Integer, Integer>> espaciosVacios = obtenerEspaciosVaciosEnTablero(tablero);
        if (espaciosVacios.capacidadVector() == 0) {
            return 0;
        }
        if (jugador == JUGADOR) {
            valor = Integer.MIN_VALUE;
        } else {
            valor = Integer.MAX_VALUE;
        }
        for (int i = 0; i < espaciosVacios.capacidadVector(); i++) {
            Par<Integer, Integer> coordenadas = espaciosVacios.recuperarElemento(i);
            tablero[coordenadas.getValor1()][coordenadas.getValor2()] = jugador;
            if (jugador == JUGADOR) {
                valor = Math.max(valor, tateti(tablero, OPONENTE));
            } else {
                valor = Math.min(valor, tateti(tablero, JUGADOR));
            }
            tablero[coordenadas.getValor1()][coordenadas.getValor2()] = 0;
        }
        return valor;
    }

    private static VectorTDA<Par<Integer, Integer>> obtenerEspaciosVaciosEnTablero(char[][] tablero) {
        VectorTDA<Par<Integer, Integer>> espaciosVacios = new Vector<>();
        espaciosVacios.inicializarVector(9);
        int cantidadEspaciosVacios = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == 0) {
                    espaciosVacios.agregarElemento(cantidadEspaciosVacios, new Par<>(i, j));
                    cantidadEspaciosVacios++;
                }
            }
        }
        return espaciosVacios;
    }

    private static boolean esGanador(char[][] tablero, char jugador) {
        if (((tablero[0][0] == tablero[1][1]) &&(tablero[1][1] == tablero[2][2])  && (tablero[2][2]  == jugador)) || (tablero[0][2] == tablero[1][1]) && (tablero[1][1] == tablero[2][0]) && (tablero[2][0]  == jugador) ) {
            return true;
        }
        if (((tablero[0][0] == tablero[1][0]) && (tablero[1][0] == tablero[2][0]) && (tablero[2][0] == jugador)) || ((tablero[0][1] == tablero[1][1]) && (tablero[1][1] == tablero[2][1]) && (tablero[2][1] == jugador)) || ((tablero[0][2] == tablero[1][2]) && (tablero[1][2] == tablero[2][2]) && (tablero[2][2] == jugador))) {
            return true;
        }
        return ((tablero[0][0] == tablero[0][1]) && (tablero[0][1] == tablero[0][2]) && (tablero[0][2] == jugador)) || ((tablero[1][0] == tablero[1][1]) && (tablero[1][1] == tablero[1][2]) && (tablero[1][2] == jugador)) || ((tablero[2][0] == tablero[2][1]) && (tablero[2][1] == tablero[2][2]) && (tablero[2][2] == jugador));
    }

    public static int monedas(VectorTDA<Integer> pilas, int jugador) {
        int valor = jugador;
        for (int i = 0; i < pilas.capacidadVector(); i++) {
            if (pilas.recuperarElemento(i) > 2) {
                VectorTDA<VectorTDA<Integer>> posiblesDivisiones = dividir(pilas, i);
                for (int j = 0; j < posiblesDivisiones.capacidadVector(); j++) {
                    if (jugador == 1) {
                        valor = Math.max(valor, monedas(posiblesDivisiones.recuperarElemento(j), -1));
                    } else {
                        valor = Math.min(valor, monedas(posiblesDivisiones.recuperarElemento(j), 1));
                    }
                }
            } else {
                valor = jugador * -1;
            }
        }
        return valor;
    }

    private static VectorTDA<VectorTDA<Integer>> dividir(VectorTDA<Integer> pilas, Integer posicionActual) {
        VectorTDA<VectorTDA<Integer>> divisiones = new Vector<>();
        divisiones.inicializarVector(pilas.capacidadVector());
        int monedas = pilas.recuperarElemento(posicionActual);
        int mitad = monedas / 2;
        for (int i = 1; i <= mitad; i++) {
            VectorTDA<Integer> division = new Vector<>();
            division.inicializarVector(posicionActual + 1);
            int j;
            for (j = 0; j < posicionActual; j++) {
                division.agregarElemento(j, pilas.recuperarElemento(j));
            }
            division.agregarElemento(j++, i);
            division.agregarElemento(j, monedas - i);
            divisiones.agregarElemento(i - 1, division);
        }
        return divisiones;
    }

    public static int tatetiAlfaBeta(char [][] tablero, char jugador, int alfa, int beta) {
        System.out.println("Recorriendo nodo");
        if (esGanador(tablero, JUGADOR)) {
            return 1;
        }
        if (esGanador(tablero, OPONENTE)) {
            return -1;
        }
        VectorTDA<Par<Integer, Integer>> espaciosVacios = obtenerEspaciosVaciosEnTablero(tablero);
        if (espaciosVacios.capacidadVector() == 0) {
            return 0;
        }
        if (jugador == JUGADOR) {
            int valor = Integer.MIN_VALUE;
            for (int i = 0; i < espaciosVacios.capacidadVector(); i++) {
                Par<Integer, Integer> coordenadas = espaciosVacios.recuperarElemento(i);
                tablero[coordenadas.getValor1()][coordenadas.getValor2()] = jugador;
                valor = Math.max(valor, tatetiAlfaBeta(tablero, OPONENTE, alfa, beta));
                alfa = Math.max(alfa, valor);
                if (alfa >= beta) {
                    System.out.printf("Poda: alfa %d, beta: %d\n", alfa, beta);
                    return alfa;
                }
                tablero[coordenadas.getValor1()][coordenadas.getValor2()] = 0;
            }
            return alfa;
        } else {
            int valor = Integer.MAX_VALUE;
            for (int i = 0; i < espaciosVacios.capacidadVector(); i++) {
                Par<Integer, Integer> coordenadas = espaciosVacios.recuperarElemento(i);
                tablero[coordenadas.getValor1()][coordenadas.getValor2()] = jugador;
                valor = Math.min(valor, tatetiAlfaBeta(tablero, JUGADOR, alfa, beta));
                beta = Math.min(valor, beta);
                if (alfa >= beta) {
                    System.out.printf("Poda: alfa %d, beta: %d\n", alfa, beta);
                    return beta;
                }
                tablero[coordenadas.getValor1()][coordenadas.getValor2()] = 0;
            }
            return beta;
        }
    }

}
