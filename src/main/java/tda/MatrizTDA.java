package tda;

public interface MatrizTDA<E> {
    void inicializarMatriz(int i, int j);

    int obtenerDimension();

    E obtenerValor(int i, int j);

    void setearValor(int i, int j, E elemento);
}
