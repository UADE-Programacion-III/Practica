package tda;

public interface VectorTDA<E> {
    void agregarElemento(int posicion, E elemento);

    int capacidadVector();

    void eliminarElemento(int posicion);

    void inicializarVector(int n);

    E recuperarElemento(int posicion);

    boolean contieneElemento(E elemento);

    VectorTDA<E> copiar();
}
