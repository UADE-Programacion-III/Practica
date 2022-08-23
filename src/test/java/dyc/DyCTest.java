package dyc;

import org.junit.Test;
import tda.VectorTDA;
import tda.impl.Vector;

import static org.junit.Assert.*;

public class DyCTest {
    @Test
    public void busquedaBinariaTest() {
        VectorTDA<Integer> s = new Vector<>();
        int elementos = 10;
        s.inicializarVector(elementos);
        for (int i = 0; i < elementos; i++) {
            s.agregarElemento(i, i);
        }
        int elementoABuscar = 5;
        assertTrue(DyC.busquedaBinaria(s, 0, elementos - 1, elementoABuscar));
        elementoABuscar = 20;
        assertFalse(DyC.busquedaBinaria(s, 0, elementos - 1, elementoABuscar));
        elementos = 9;
        s.inicializarVector(elementos);
        for (int i = 0; i < elementos; i++) {
            s.agregarElemento(i, i);
        }
        elementoABuscar = 5;
        assertTrue(DyC.busquedaBinaria(s, 0, elementos - 1, elementoABuscar));
        elementoABuscar = 20;
        assertFalse(DyC.busquedaBinaria(s, 0, elementos - 1, elementoABuscar));
    }

    @Test
    public void busquedaBinariaVectorVacioTest() {
        VectorTDA<Integer> s = new Vector<>();
        int elementos = 0;
        s.inicializarVector(elementos);
        int elementoABuscar = 10;
        assertFalse(DyC.busquedaBinaria(s, 0, elementos, elementoABuscar));
    }

    @Test
    public void busquedaBinariaVectorConDuplicadosTest() {
        VectorTDA<Integer> s = new Vector<>();
        int elementos = 10;
        s.inicializarVector(elementos);
        for (int i = 0; i < elementos; i++) {
            s.agregarElemento(i, i / 2);
        }
        int elementoABuscar = 2;
        assertTrue(DyC.busquedaBinaria(s, 0, elementos - 1, elementoABuscar));
        elementoABuscar = 20;
        assertFalse(DyC.busquedaBinaria(s, 0, elementos - 1, elementoABuscar));
    }

    @Test
    public void palindromoTest() {
        String palabra = "salas";
        VectorTDA<Character> s = new Vector<>();
        int elementos = palabra.length();
        s.inicializarVector(elementos);
        for (int i = 0; i < elementos; i++) {
            s.agregarElemento(i, palabra.charAt(i));
        }
        assertTrue(DyC.palindromo(s, 0, elementos - 1));
        palabra = "hola";
        elementos = palabra.length();
        s.inicializarVector(elementos);
        for (int i = 0; i < elementos; i++) {
            s.agregarElemento(i, palabra.charAt(i));
        }
        assertFalse(DyC.palindromo(s, 0, elementos - 1));
        palabra = "h";
        elementos = palabra.length();
        s.inicializarVector(elementos);
        for (int i = 0; i < elementos; i++) {
            s.agregarElemento(i, palabra.charAt(i));
        }
        assertTrue(DyC.palindromo(s, 0, elementos - 1));
        palabra = "";
        elementos = palabra.length();
        s.inicializarVector(elementos);
        for (int i = 0; i < elementos; i++) {
            s.agregarElemento(i, palabra.charAt(i));
        }
        assertTrue(DyC.palindromo(s, 0, elementos - 1));
    }

    @Test
    public void fibonacciTest() {
        int n = 1;
        assertEquals(1, DyC.fibonacci(n));
        n = 5;
        assertEquals(8, DyC.fibonacci(n));
    }

    @Test
    public void elementoMayoritarioTest() {
        VectorTDA<Integer> s = new Vector<>();
        int elementos = 10;
        s.inicializarVector(elementos);
        s.agregarElemento(0, 1);
        s.agregarElemento(1, 1);
        s.agregarElemento(2, 3);
        s.agregarElemento(3, 3);
        s.agregarElemento(4, 1);
        s.agregarElemento(5, 3);
        s.agregarElemento(6, 1);
        s.agregarElemento(7, 4);
        s.agregarElemento(8, 1);
        s.agregarElemento(9, 1);
        assertEquals(1, DyC.elementoMayoritario(s));
        s.inicializarVector(elementos);
        for (int i = 0; i < elementos; i++) {
            s.agregarElemento(i, i);
        }
        assertEquals(-1, DyC.elementoMayoritario(s));
    }

    @Test
    public void sumaMaximaTest() {
        VectorTDA<Integer> s = new Vector<>();
        int elementos = 6;
        s.inicializarVector(elementos);
        s.agregarElemento(0, 4);
        s.agregarElemento(1, -1);
        s.agregarElemento(2, 8);
        s.agregarElemento(3, 2);
        s.agregarElemento(4, 5);
        s.agregarElemento(5, -9);
        assertEquals(18, DyC.sumaMaxima(s, 0, elementos - 1));
        elementos = 9;
        s.inicializarVector(elementos);
        s.agregarElemento(0, 904);
        s.agregarElemento(1, 40);
        s.agregarElemento(2, 523);
        s.agregarElemento(3, 12);
        s.agregarElemento(4, -335);
        s.agregarElemento(5, -385);
        s.agregarElemento(6, -124);
        s.agregarElemento(7, 481);
        s.agregarElemento(8, -31);
        assertEquals(1479, DyC.sumaMaxima(s, 0, elementos - 1));
    }

    @Test
    public void puntoNegativoEnFuncionTest() {
        assertEquals(10, DyC.puntoNegativoEnFuncion(x -> -4*x + 40));
        assertEquals(0, DyC.puntoNegativoEnFuncion(x -> -x*x));
        assertEquals(-1, DyC.puntoNegativoEnFuncion(x -> x + 1));
        assertEquals(5, DyC.puntoNegativoEnFuncion(x -> -x*x + 20));
    }
}
