package backtracking.rey;

import java.util.Objects;

public class Celda {
    private int x;
    private int y;
    private int peso;

    public Celda(int x, int y, int peso) {
        this.x = x;
        this.y = y;
        this.peso = peso;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Celda celda = (Celda) o;
        return x == celda.x && y == celda.y && peso == celda.peso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, peso);
    }

    @Override
    public String toString() {
        return "Celda{" +
                "x=" + x +
                ", y=" + y +
                ", peso=" + peso +
                '}';
    }
}
