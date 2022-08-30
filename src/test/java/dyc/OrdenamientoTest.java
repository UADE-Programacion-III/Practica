package dyc;

import org.junit.Test;
import tda.VectorTDA;
import tda.impl.Vector;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class OrdenamientoTest {
    @Test
    public void mergeSortVectorDesordenadoTest() {
        VectorTDA<Integer> s = new Vector<>();
        int elementos = 10;
        s.inicializarVector(elementos);
        for (int i = 0; i < elementos; i++) {
            s.agregarElemento(i, elementos - i);
        }
        // Antes de ordenar: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
        Ordenamiento.mergeSort(s, 0, elementos - 1, Comparator.naturalOrder());
        // Después de ordenar: [1, 2, 3, 4, 5, 6, 7, 8 ,9, 10]
        assertEquals(elementos, s.capacidadVector());
        for (int i = 0; i < elementos; i++) {
            assertEquals(i + 1, s.recuperarElemento(i).intValue());
        }
        // Número impar de elementos
        elementos = 9;
        s.inicializarVector(elementos);
        for (int i = 0; i < elementos; i++) {
            s.agregarElemento(i, elementos - i);
        }
        // Antes de ordenar: [9, 8, 7, 6, 5, 4, 3, 2, 1]
        Ordenamiento.mergeSort(s, 0, elementos - 1,  Comparator.naturalOrder());
        // Después de ordenar: [1, 2, 3, 4, 5, 6, 7, 8 ,9]
        assertEquals(elementos, s.capacidadVector());
        for (int i = 0; i < elementos; i++) {
            assertEquals(i + 1, s.recuperarElemento(i).intValue());
        }
    }

    @Test
    public void mergeSortVectorOrdenadoTest() {
        VectorTDA<Integer> s = new Vector<>();
        int elementos = 10;
        s.inicializarVector(elementos);
        for (int i = 0; i < elementos; i++) {
            s.agregarElemento(i, i + 1);
        }
        // Antes de ordenar: [1, 2, 3, 4, 5, 6, 7, 8 ,9, 10]
        Ordenamiento.mergeSort(s, 0, elementos - 1,  Comparator.naturalOrder());
        // Después de ordenar: [1, 2, 3, 4, 5, 6, 7, 8 ,9, 10]
        assertEquals(elementos, s.capacidadVector());
        for (int i = 0; i < elementos; i++) {
            assertEquals(i + 1, s.recuperarElemento(i).intValue());
        }
    }

    @Test
    public void mergeSortVectorVacioTest() {
        VectorTDA<Integer> s = new Vector<>();
        int elementos = 0;
        s.inicializarVector(elementos);
        Ordenamiento.mergeSort(s, 0, elementos,  Comparator.naturalOrder());
        assertEquals(elementos, s.capacidadVector());
    }

    @Test
    public void mergeSortVectorConDuplicadosTest() {
        VectorTDA<Integer> s = new Vector<>();
        int elementos = 10;
        s.inicializarVector(elementos);
        for (int i = 0; i < elementos; i++) {
            s.agregarElemento(i, (elementos - i) / 2);
        }
        // Antes de ordenar: [5, 4, 4, 3, 3, 2, 2, 1, 1, 0]
        Ordenamiento.mergeSort(s, 0, elementos - 1,  Comparator.naturalOrder());
        // Después de ordenar: [0, 1, 1, 2, 2, 3, 3, 4, 4, 5]
        assertEquals(elementos, s.capacidadVector());
        for (int i = 0; i < elementos; i++) {
            assertEquals((elementos - i) / 2, s.recuperarElemento(elementos - i - 1).intValue());
        }
    }

    @Test
    public void quickSortVectorDesordenadoTest() {
        VectorTDA<Integer> s = new Vector<>();
        int elementos = 10;
        s.inicializarVector(elementos);
        for (int i = 0; i < elementos; i++) {
            s.agregarElemento(i, elementos - i);
        }
        // Antes de ordenar: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
        Ordenamiento.quickSort(s, 0, elementos - 1);
        // Después de ordenar: [1, 2, 3, 4, 5, 6, 7, 8 ,9, 10]
        assertEquals(elementos, s.capacidadVector());
        for (int i = 0; i < elementos; i++) {
            assertEquals(i + 1, s.recuperarElemento(i).intValue());
        }
        // Número impar de elementos
        elementos = 9;
        s.inicializarVector(elementos);
        for (int i = 0; i < elementos; i++) {
            s.agregarElemento(i, elementos - i);
        }
        // Antes de ordenar: [9, 8, 7, 6, 5, 4, 3, 2, 1]
        Ordenamiento.quickSort(s, 0, elementos - 1);
        // Después de ordenar: [1, 2, 3, 4, 5, 6, 7, 8 ,9]
        assertEquals(elementos, s.capacidadVector());
        for (int i = 0; i < elementos; i++) {
            assertEquals(i + 1, s.recuperarElemento(i).intValue());
        }
    }

    @Test
    public void quickSortVectorOrdenadoTest() {
        VectorTDA<Integer> s = new Vector<>();
        int elementos = 10;
        s.inicializarVector(elementos);
        for (int i = 0; i < elementos; i++) {
            s.agregarElemento(i, i + 1);
        }
        // Antes de ordenar: [1, 2, 3, 4, 5, 6, 7, 8 ,9, 10]
        Ordenamiento.quickSort(s, 0, elementos - 1);
        // Después de ordenar: [1, 2, 3, 4, 5, 6, 7, 8 ,9, 10]
        assertEquals(elementos, s.capacidadVector());
        for (int i = 0; i < elementos; i++) {
            assertEquals(i + 1, s.recuperarElemento(i).intValue());
        }
    }

    @Test
    public void quickSortVectorVacioTest() {
        VectorTDA<Integer> s = new Vector<>();
        int elementos = 0;
        s.inicializarVector(elementos);
        Ordenamiento.quickSort(s, 0, elementos);
        assertEquals(elementos, s.capacidadVector());
    }

    @Test
    public void quickSortVectorConDuplicadosTest() {
        VectorTDA<Integer> s = new Vector<>();
        int elementos = 10;
        s.inicializarVector(elementos);
        for (int i = 0; i < elementos; i++) {
            s.agregarElemento(i, (elementos - i) / 2);
        }
        // Antes de ordenar: [5, 4, 4, 3, 3, 2, 2, 1, 1, 0]
        Ordenamiento.quickSort(s, 0, elementos - 1);
        // Después de ordenar: [0, 1, 1, 2, 2, 3, 3, 4, 4, 5]
        assertEquals(elementos, s.capacidadVector());
        for (int i = 0; i < elementos; i++) {
            assertEquals((elementos - i) / 2, s.recuperarElemento(elementos - i - 1).intValue());
        }
    }

}
