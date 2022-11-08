package backtracking;

public class Jugada<E> {
    private E dato;
    private int jugada;

    public Jugada() {
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public int getJugada() {
        return jugada;
    }

    public void setJugada(int jugada) {
        this.jugada = jugada;
    }
}
