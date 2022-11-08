package backtracking.rey;

import tda.VectorTDA;

public class Resultado {
    private int mejorPeso;
    private VectorTDA<Celda> mejorRuta;

    public Resultado(int mejorPeso, VectorTDA<Celda> mejorRuta) {
        this.mejorPeso = mejorPeso;
        this.mejorRuta = mejorRuta;
    }

    public int getMejorPeso() {
        return mejorPeso;
    }

    public VectorTDA<Celda> getMejorRuta() {
        return mejorRuta;
    }

    @Override
    public String toString() {
        return "Resultado{" +
                "mejorPeso=" + mejorPeso +
                ", mejorRuta=" + mejorRuta +
                '}';
    }
}
