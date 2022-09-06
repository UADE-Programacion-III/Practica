package tda.impl;

public class Par<E, F> {
    private E valor1;
    private F valor2;

    public Par(E valor1, F valor2) {
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    public E getValor1() {
        return valor1;
    }

    public F getValor2() {
        return valor2;
    }

    public void setValor2(F valor2) {
        this.valor2 = valor2;
    }
}
