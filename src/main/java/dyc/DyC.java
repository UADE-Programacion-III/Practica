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

}
