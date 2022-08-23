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

    public static int sumaMaxima(VectorTDA<Integer> vector, int indiceIzquierdo, int indiceDerecho) {
        if (indiceIzquierdo > indiceDerecho) {
            return Integer.MIN_VALUE;
        }
        if (indiceIzquierdo == indiceDerecho) {
            return vector.recuperarElemento(indiceIzquierdo);
        }
        int medio = (indiceDerecho + indiceIzquierdo) / 2;
        return Math.max(sumaMaxima(vector, indiceIzquierdo, medio - 1), Math.max(sumaMaxima(vector, medio + 1, indiceDerecho), maximaSumaCruzada(vector, indiceIzquierdo, medio, indiceDerecho)));
    }

    private static int maximaSumaCruzada(VectorTDA<Integer> vector, int indiceIzquierdo, int medio, int indiceDerecho) {
        int sumaParcial = 0;
        int sumaMaximaIzquierda = Integer.MIN_VALUE;
        for (int i = indiceIzquierdo; i <= medio; i++) {
            sumaParcial+= vector.recuperarElemento(i);
            if (sumaParcial > sumaMaximaIzquierda) {
                sumaMaximaIzquierda = sumaParcial;
            }
        }
        sumaParcial = 0;
        int sumaMaximaDerecha = Integer.MIN_VALUE;
        for (int i = medio; i <= indiceDerecho; i++) {
            sumaParcial+= vector.recuperarElemento(i);
            if (sumaParcial > sumaMaximaDerecha) {
                sumaMaximaDerecha = sumaParcial;
            }
        }
        return Math.max(sumaMaximaIzquierda + sumaMaximaDerecha - vector.recuperarElemento(medio), Math.max(sumaMaximaIzquierda, sumaMaximaDerecha));
    }

    public static int puntoNegativoEnFuncion(Funcion funcion) {
        if (funcion.evaluar(0) <= 0) {
            return 0;
        }
        int i = 1;
        while (funcion.evaluar(i) > 0) {
            i*=2;
        }
        return busquedaBinariaFuncion(funcion, i/2, i);
    }

    private static int busquedaBinariaFuncion(Funcion funcion, int limiteInferior, int limiteSuperior) {
        if (limiteSuperior >= limiteInferior) {
            int mitad = (limiteInferior + limiteSuperior) / 2;
            if (funcion.evaluar(mitad) <= 0 && funcion.evaluar(mitad - 1) > 0) {
                return mitad;
            }
            if (funcion.evaluar(mitad) < 0) {
                return busquedaBinariaFuncion(funcion, limiteInferior, mitad - 1);
            } else {
                return busquedaBinariaFuncion(funcion, mitad + 1, limiteSuperior);
            }
        }
        return -1;
    }


    public interface Funcion {
        int evaluar(int x);
    }
}
