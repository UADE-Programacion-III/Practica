package backtracking.labertinto;

import java.util.Objects;

public class Nodo {
    private Celda celda;
    private int pasos;

    public Nodo(Celda celda, int pasos) {
        this.celda = celda;
        this.pasos = pasos;
    }

    public Celda getCelda() {
        return celda;
    }

    public void setCelda(Celda celda) {
        this.celda = celda;
    }

    public int getPasos() {
        return pasos;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }

    public int getPosicionY() {
        return this.celda.getY();
    }

    public int getPosicionX() {
        return this.celda.getX();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nodo nodo = (Nodo) o;
        return celda.equals(nodo.celda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(celda);
    }
}

