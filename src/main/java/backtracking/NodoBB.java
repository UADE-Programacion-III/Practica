package backtracking;

import tda.VectorTDA;

public class NodoBB<E> {
    private VectorTDA<Integer> actualSolucion ;
    private E dato;
    private int cotaInferior;
    private int cotaSuperior;
    private int etapa;

    public NodoBB() {

    }

    public VectorTDA<Integer> getActualSolucion() {
        return actualSolucion;
    }

    public void setActualSolucion(VectorTDA<Integer> actualSolucion) {
        this.actualSolucion = actualSolucion;
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public int getCotaInferior() {
        return cotaInferior;
    }

    public void setCotaInferior(int cotaInferior) {
        this.cotaInferior = cotaInferior;
    }

    public int getCotaSuperior() {
        return cotaSuperior;
    }

    public void setCotaSuperior(int cotaSuperior) {
        this.cotaSuperior = cotaSuperior;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }
}
