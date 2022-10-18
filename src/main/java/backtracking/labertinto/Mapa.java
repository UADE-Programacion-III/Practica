package backtracking.labertinto;

import java.util.*;

public class Mapa {
    private static final char ENTRADA = 'E';
    private static final char PARED = '#';
    private static final char SALIDA = 'S';
    private static final int[][] POSIBLES_MOVIMIENTOS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private final char[][] mapa;

    public Mapa(char[][] mapa) {
        this.mapa = mapa;
    }

    private int obtenerCantidadDePasos(Celda celda) {
        Queue<Nodo> cola = new LinkedList<>();
        Set<Nodo> visitados = new HashSet<>();
        List<Nodo> vecinos;
        Nodo actual = new Nodo(celda, 0);
        cola.add(actual);
        visitados.add(actual);
        while (!cola.isEmpty()) {
            Nodo primero = cola.poll();
            if (this.esSalida(primero.getCelda())) {
                return primero.getPasos();
            }
            vecinos = this.posiblesMovimientosDesdeCoordenada(primero);
            while (!vecinos.isEmpty()) {
                Nodo vecino = vecinos.remove(0);
                if (!visitados.contains(vecino)) {
                    cola.add(vecino);
                    visitados.add(vecino);
                }
            }
        }
        return -1;
    }

    private List<Nodo> posiblesMovimientosDesdeCoordenada(Nodo nodo) {
        List<Nodo> posiblesMovimientos = new ArrayList<>();
        int x = nodo.getPosicionX();
        int y = nodo.getPosicionY();
        int pasosHastaAhora = nodo.getPasos();
        for (int[] movimiento : POSIBLES_MOVIMIENTOS) {
            Celda siguienteCelda = new Celda(x + movimiento[0], y + movimiento[1]);
            if (!this.estaFueraDeMapa(siguienteCelda) && !this.esPared(siguienteCelda)) {
                posiblesMovimientos.add(new Nodo(siguienteCelda, pasosHastaAhora + 1));
            }
        }
        return posiblesMovimientos;
    }

    private boolean esPared(Celda celda) {
        return this.mapa[celda.getX()][celda.getY()] == PARED;
    }

    private boolean estaFueraDeMapa(Celda celda) {
        int x = celda.getX();
        int y = celda.getY();
        return x < 0 || y < 0 || x >= this.mapa.length || y >= this.mapa[x].length;
    }

    private boolean esSalida(Celda celda) {
        return this.mapa[celda.getX()][celda.getY()] == SALIDA;
    }

    public int escapar() {
        Celda entrada = buscarPosicionEntrada();
        return obtenerCantidadDePasos(entrada);
    }

    private Celda buscarPosicionEntrada() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == ENTRADA) {
                    return new Celda(i, j);
                }
            }
        }
        throw new EntradaFaltanteException("El mapa no tiene entrada");
    }

    public void imprimir() {
        for (char[] row : this.mapa) {
            for (char column : row) {
                System.out.printf("%s ", column);
            }
            System.out.println();
        }
    }
}
