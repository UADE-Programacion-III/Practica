package dyc;

import tda.VectorTDA;
import tda.impl.Vector;

public class DyC {
    public static boolean busquedaBinaria(VectorTDA<Integer> s, int inicio, int fin, int x) {
        if (fin - inicio == 0) {
            return false;
        }
        if (fin - inicio == 1) {
            return s.recuperarElemento(inicio) == x;
        } else {
            int medio = (fin + inicio) / 2;
            if (s.recuperarElemento(medio) == x) {
                return true;
            } else {
                if (x < medio) {
                    return busquedaBinaria(s, inicio, medio, x);
                } else {
                    return busquedaBinaria(s, medio + 1, fin, x);
                }
            }
        }
    }

    public static boolean palindromo(VectorTDA<Character> secuencia, int inicio, int fin) {
        if (inicio >= fin) {
            return true;
        } else {
            if (!secuencia.recuperarElemento(inicio).equals(secuencia.recuperarElemento(fin))) {
                return false;
            } else {
                return palindromo(secuencia, inicio + 1, fin - 1);
            }
        }
    }

    public static int fibonacci(int n) {
        if (n < 2) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static int elementoMayoritario(VectorTDA<Integer> v) {
        VectorTDA<Integer> copia = new Vector<>();
        copia.inicializarVector(v.capacidadVector());
        for (int i = 0; i < v.capacidadVector(); i++) {
            copia.agregarElemento(i, v.recuperarElemento(i));
        }
        int x = buscarCandidato(copia, 0, copia.capacidadVector() - 1);
        if (x != -1) {
            int suma = 0;
            for (int i = 0; i < v.capacidadVector(); i++) {
                if (v.recuperarElemento(i) == x) {
                    suma++;
                }
            }
            if (suma > v.capacidadVector() / 2) {
                return x;
            } else {
                return -1;
            }
        }
        return -1;
    }

    private static int buscarCandidato(VectorTDA<Integer> v, int inicio, int fin) {
        if (fin < inicio) {
            return -1;
        } else {
            if (inicio == fin) {
                return v.recuperarElemento(inicio);
            } else {
                int j = inicio;
                if ((fin - inicio + 1) % 2 == 0) {
                    for (int i = inicio + 1; i <= fin; i += 2) {
                        if (v.recuperarElemento(i - 1).equals(v.recuperarElemento(i))) {
                            v.agregarElemento(j, v.recuperarElemento(i));
                            j++;
                        }
                    }
                    return buscarCandidato(v, inicio, j - 1);
                } else {
                    for (int i = inicio + 1; i < fin; i += 2) {
                        if (v.recuperarElemento(i - 1).equals(v.recuperarElemento(i))) {
                            v.agregarElemento(j, v.recuperarElemento(i));
                            j++;
                        }
                        i += 2;
                    }
                    int candidato = buscarCandidato(v, inicio, j - 1);
                    if (candidato != -1) {
                        return candidato;
                    } else {
                        return v.recuperarElemento(fin);
                    }
                }
            }
        }
    }
}
