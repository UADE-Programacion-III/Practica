package backtracking;

import greedy.modelos.Objeto;
import tda.ConjuntoTDA;
import tda.MatrizTDA;
import tda.VectorTDA;
import tda.impl.Conjunto;
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

    private static NodoBB<Integer> asignacionCrearNodoRaiz(MatrizTDA<Integer> tareasPorEmpleado) {
        NodoBB<Integer> raiz = new NodoBB<>();
        VectorTDA<Integer> actualSolucion = new Vector<>();
        actualSolucion.inicializarVector(tareasPorEmpleado.obtenerDimension());
        for (int i = 0; i < tareasPorEmpleado.obtenerDimension(); i++) {
            actualSolucion.agregarElemento(i, -1);
        }
        raiz.setActualSolucion(actualSolucion);
        raiz.setEtapa(-1);
        raiz.setDato(0);
        raiz.setCotaInferior(0);
        raiz.setCotaSuperior(asignacionActualizarCotaSuperior(raiz, tareasPorEmpleado));
        return raiz;
    }

    private static int asignacionActualizarCotaSuperior(NodoBB<Integer> nodo, MatrizTDA<Integer> tareasPorEmpleado) {
        if (nodo.getEtapa() == tareasPorEmpleado.obtenerDimension()) {
            return nodo.getDato();
        } else {
            int valor = nodo.getDato();
            for (int i = nodo.getEtapa() + 1; i < tareasPorEmpleado.obtenerDimension(); i++) {
                valor += elegirMaximaTareaNoAsignada(nodo.getActualSolucion(), tareasPorEmpleado, i);
            }
            return valor;
        }
    }

    private static int elegirMaximaTareaNoAsignada(VectorTDA<Integer> actualSolucion, MatrizTDA<Integer> tareasPorEmpleado, int empleado) {
        int maximo = Integer.MIN_VALUE;
        for (int i = 0; i< tareasPorEmpleado.obtenerDimension(); i++) {
            if (!actualSolucion.contieneElemento(tareasPorEmpleado.obtenerValor(empleado, i))) {
                maximo = Math.max(maximo, tareasPorEmpleado.obtenerValor(empleado, i));
            }
        }
        return maximo;
    }

    private static int actualizarCota(NodoBB<Integer> nodo, int cota) {
        return Math.min(nodo.getCotaSuperior(), cota);
    }

    private static boolean podar(NodoBB<Integer> nodo, int cota) {
        return nodo.getCotaInferior() > cota;
    }

    private static ConjuntoTDA<NodoBB<Integer>> generarHijos(NodoBB<Integer> nodo, MatrizTDA<Integer> tareasPorEmpleado) {
        ConjuntoTDA<NodoBB<Integer>> hijos = new Conjunto<>();
        hijos.inicializarConjunto();
        for (int i = 0; i < tareasPorEmpleado.obtenerDimension(); i++) {
            if (!tareaUsada(nodo, i)) {
                NodoBB<Integer> hijo = new NodoBB<>();
                VectorTDA<Integer> solucionParcial = new Vector<>();
                solucionParcial.inicializarVector(tareasPorEmpleado.obtenerDimension());
                copiarVector(nodo.getActualSolucion(), solucionParcial, tareasPorEmpleado.obtenerDimension());
                hijo.setActualSolucion(solucionParcial);
                solucionParcial.agregarElemento(nodo.getEtapa() + 1, i);
                hijo.setDato(nodo.getDato() + tareasPorEmpleado.obtenerValor(nodo.getEtapa() + 1, i));
                hijo.setCotaInferior(hijo.getDato());
                hijo.setCotaSuperior(asignacionActualizarCotaSuperior(hijo, tareasPorEmpleado));
                hijo.setEtapa(nodo.getEtapa() + 1);
                hijos.agregar(hijo);
            }
        }
        return hijos;
    }

    private static boolean tareaUsada(NodoBB<Integer> nodo, int i) {
        return nodo.getActualSolucion().contieneElemento(i);
    }

    private static boolean esSolucion(NodoBB<Integer> nodo) {
        return nodo.getEtapa() + 1 == nodo.getActualSolucion().capacidadVector();
    }

    private static boolean esMejorSolucion(int mejorSolucion, NodoBB<Integer> nodo) {
        return nodo.getDato() < mejorSolucion;
    }

    public static VectorTDA<Integer> asignacionRyP(MatrizTDA<Integer> tareasPorEmpleado) {
        int cota = Integer.MAX_VALUE;
        NodoBB<Integer> nAux;
        NodoBB<Integer> raiz;
        raiz = asignacionCrearNodoRaiz(tareasPorEmpleado);
        Queue<NodoBB<Integer>> LNV = new PriorityQueue<>(Comparator.comparingInt(NodoBB::getCotaInferior));
        cota = actualizarCota(raiz, cota);
        LNV.add(raiz);
        NodoBB<Integer> mejorSolucion = raiz;
        while (!LNV.isEmpty()) {
            nAux = LNV.poll();
            if (!podar(nAux, cota)) {
                VectorTDA<NodoBB<Integer>> hijos = generarHijos(nAux, tareasPorEmpleado).aVector();
                for (int i = 0; i < hijos.capacidadVector(); i++) {
                    NodoBB<Integer> hijo = hijos.recuperarElemento(i);
                    if (!podar(hijo, cota)) {
                        if (esSolucion(hijo)) {
                            if (esMejorSolucion(mejorSolucion.getDato(), hijo) || mejorSolucion.getEtapa() == -1) {
                                mejorSolucion = hijo;
                                cota = actualizarCota(hijo, cota);
                            }
                        } else {
                            cota = actualizarCota(hijo, cota);
                            LNV.add(hijo);
                        }
                    }
                }
            }
        }
        return mejorSolucion.getActualSolucion();
    }
}
