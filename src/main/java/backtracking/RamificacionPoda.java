package backtracking;

import greedy.modelos.Objeto;
import tda.VectorTDA;
import tda.impl.Vector;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class RamificacionPoda {
    private static void mochilaNodoRaiz(NodoBB<Objeto> raiz,
                                        VectorTDA<Integer> valores, VectorTDA<Integer> pesos, int capacidad) {
        VectorTDA<Integer> actualSolucion = new Vector<>();
        actualSolucion.inicializarVector(valores.capacidadVector());
        for (int i = 0; i < valores.capacidadVector(); i++) {
            actualSolucion.agregarElemento(i, 0);
        }
        raiz.setActualSolucion(actualSolucion);
        raiz.setEtapa(-1);
        Objeto objeto = new Objeto(0, 0, 0);
        raiz.setDato(objeto);
        raiz.setCotaInferior(0);
        raiz.setCotaSuperior(mochilaCotaSuperior(valores, pesos, raiz, capacidad));
    }

    private static int mochilaCotaSuperior(VectorTDA<Integer> valores, VectorTDA<Integer> pesos, NodoBB<Objeto> n, int capacidad) {
        if (n.getEtapa() + 1 == valores.capacidadVector()) {
            return n.getDato().getValor();
        } else {
            return n.getDato().getValor() + (capacidad - n.getDato().getPeso()) *
                    valores.recuperarElemento(n.getEtapa() + 1) / pesos.
                    recuperarElemento(n.getEtapa() + 1);
        }
    }

    private static int mochilaGenerarHijos(NodoBB<Objeto> n, VectorTDA<Integer> valores, VectorTDA<Integer> pesos, int capacidad, VectorTDA<NodoBB<Objeto>> hijos) {
        int cantHijos = 0;
        if (n.getEtapa() + 1 < valores.capacidadVector()) {
            cantHijos = 1;
            NodoBB<Objeto> h1 = new NodoBB<>();
            VectorTDA<Integer> actualSolucion = new Vector<>();
            h1.setActualSolucion(actualSolucion);
            actualSolucion.inicializarVector(valores.capacidadVector());
            copiarVector(n.getActualSolucion(), actualSolucion, n.getActualSolucion().capacidadVector());
            actualSolucion.agregarElemento(n.getEtapa() + 1, 0);
            Objeto objeto = new Objeto(n.getEtapa() + 1, n.getDato().getPeso(), n.getDato().getValor());
            h1.setDato(objeto);
            h1.setEtapa(n.getEtapa() + 1);
            h1.setCotaInferior(n.getDato().getValor());
            h1.setCotaSuperior(mochilaCotaSuperior(valores, pesos, h1, capacidad));
            hijos.agregarElemento(0, h1);
            if (n.getDato().getPeso() + pesos.recuperarElemento(n.getEtapa() + 1) <= capacidad) {
                cantHijos = 2;
                NodoBB<Objeto> h2 = new NodoBB<>();
                VectorTDA<Integer> actualSolucion2 = new Vector<>();
                actualSolucion2.inicializarVector(valores.capacidadVector());
                h2.setActualSolucion(actualSolucion2);
                copiarVector(n.getActualSolucion(), actualSolucion2, n.getActualSolucion().capacidadVector());
                actualSolucion2.agregarElemento(n.getEtapa() + 1, 1);
                Objeto objeto2 = new Objeto(n.getEtapa() + 1, n.getDato().getPeso() + pesos.recuperarElemento(n.getEtapa() + 1), n.getDato().getValor() + valores.recuperarElemento(n.getEtapa() + 1));
                h2.setDato(objeto2);
                h2.setEtapa(n.getEtapa() + 1);
                h2.setCotaInferior(n.getDato().getValor());
                h2.setCotaSuperior(mochilaCotaSuperior(valores, pesos, h2, capacidad));
                hijos.agregarElemento(1, h2);
            }
        }
        return cantHijos;
    }

    private static void copiarVector(VectorTDA<Integer> origen, VectorTDA<Integer> destino, int capacidadVector) {
        for (int i=0; i< capacidadVector; i++) {
            destino.agregarElemento(i, origen.recuperarElemento(i));
        }
    }

    private static int mochilaValorPoda(NodoBB<Objeto> n, int cota) {
        return Math.max(n.getCotaInferior(), cota);
    }

    private static boolean mochilaEsSolucion(NodoBB<Objeto> n) {
        return (n.getEtapa() + 1 == n.getActualSolucion().capacidadVector());
    }

    public static VectorTDA<Integer> mochilaRyP(VectorTDA<Integer> valores, VectorTDA<Integer> pesos, int capacidad) {
        int cota = 0;
        NodoBB<Objeto> nAux;
        NodoBB<Objeto> raiz = new NodoBB<>();
        mochilaNodoRaiz(raiz, valores, pesos, capacidad);
        NodoBB<Objeto> mejorSolucion = raiz;
        Queue<NodoBB<Objeto>> LNV = new PriorityQueue<>(Comparator.comparingInt(NodoBB::getCotaSuperior));
        cota = mochilaValorPoda(raiz, cota);
        VectorTDA<NodoBB<Objeto>> hijos = new Vector<>();
        hijos.inicializarVector(2);
        LNV.add(raiz);
        int cantHijos;
        while (!LNV.isEmpty()) {
            nAux = LNV.poll();
            if (nAux.getCotaSuperior() >= cota) {
                cantHijos = mochilaGenerarHijos(nAux, valores, pesos, capacidad, hijos);
                for (int i = 0; i < cantHijos; i++) {
                    if (hijos.recuperarElemento(i).getCotaSuperior() >= cota) {
                        if (mochilaEsSolucion(hijos.recuperarElemento(i))) {
                            if (hijos.recuperarElemento(i).getDato().getValor() >= cota) {
                                mejorSolucion = hijos.recuperarElemento(i);
                                cota = hijos.recuperarElemento(i).getDato().getValor();
                            }
                        } else {
                            cota = mochilaValorPoda(hijos.recuperarElemento(i), cota);
                            LNV.add(hijos.recuperarElemento(i));
                        }
                    }
                }
            }
        }
        return mejorSolucion.getActualSolucion();
    }
}
