package tda.impl;

import tda.MatrizTDA;

public class Matriz<E> implements MatrizTDA<E> {
    private E[][] matriz;
    private int i;
    private int j;

    @Override
    public void inicializarMatriz(int i, int j) {
        this.matriz = (E[][]) new Object[i][j];
        this.i = i;
        this.j = j;
    }

    @Override
    public int obtenerDimension() {
        return this.i;
    }

    @Override
    public E obtenerValor(int i, int j) {
        if (this.matriz.length > i && this.matriz[0].length > j) {
            return this.matriz[i][j];
        }
        return null;
    }

    @Override
    public void setearValor(int i, int j, E elemento) {
        this.matriz[i][j] = elemento;
    }
}
