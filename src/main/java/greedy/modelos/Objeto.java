package greedy.modelos;

import java.util.Comparator;

public class Objeto {
    private int numero;
    private int peso;
    private int valor;

    public Objeto(int numero, int peso, int valor) {
        this.numero = numero;
        this.peso = peso;
        this.valor = valor;
    }

    public int getNumero() {
        return numero;
    }

    public int getPeso() {
        return peso;
    }

    public int getValor() {
        return valor;
    }

    public static class ComparadorValorPeso implements Comparator<Objeto> {
        @Override
        public int compare(Objeto o1, Objeto o2) {
            float relacionValorPeso1 = (float) o1.getValor() / (float) o1.getPeso();
            float relacionValorPeso2 = (float) o2.getValor() / (float) o2.getPeso();
            if (relacionValorPeso1 == relacionValorPeso2) {
                return 0;
            }
            return relacionValorPeso1 < relacionValorPeso2 ? 1 : -1;
        }
    }
}
