package solutions;

import adt.Diccionario;
import ds.TablaHash;
import exceptions.ElementoDuplicado;
import exceptions.ElementoNoEncontrado;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main2 {

    // Clase para representar una posición (línea, columna)
    static class Pos {
        int linea;
        int columna;

        Pos(int linea, int columna) {
            this.linea = linea;
            this.columna = columna;
        }

        @Override
        public String toString() {
            return "(" + linea + ":" + columna + ")";
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("===== PRUEBA CON DiccionarioImpl (BÁSICO) =====");
            // Creamos un diccionario básico (implementación simple, por ejemplo, con tabla hash interna)
            Diccionario<String, String> dic = new TablaHash<>(10);

            // Insertar pares clave-valor
            dic.insertar("p1", "valor1");
            dic.insertar("p2", "valor2");
            dic.insertar("p3", "valor3");
            // Actualizar una clave existente
            String oldVal = dic.insertar("p2", "valor2_actualizado");
            System.out.println("Actualización de 'p2': valor anterior = " + oldVal);

            // Obtener valores
            System.out.println("Obtener 'p1': " + dic.obtener("p1"));
            System.out.println("Obtener 'p2': " + dic.obtener("p2"));

            // Comprobar existencia
            System.out.println("Contiene 'p3'? " + dic.contiene("p3"));
            System.out.println("Contiene 'p4'? " + dic.contiene("p4"));

            // Mostrar talla (número de elementos)
            System.out.println("Talla del diccionario: " + dic.talla());

            // Iterar sobre las claves
            System.out.println("Iteración (clave -> valor):");
            for (String key : dic) {
                System.out.println(key + " -> " + dic.obtener(key));
            }

            // Eliminar una clave
            String eliminado = dic.eliminar("p1");
            System.out.println("Eliminado 'p1', valor = " + eliminado);
            System.out.println("Talla tras eliminación: " + dic.talla());

            // Borrar todo el diccionario
            dic.borrar();
            System.out.println("Después de borrar, es vacío? " + dic.esVacia());


            System.out.println("\n===== PRUEBA CON TablaHash (OCURRENCIAS DE PALABRAS) =====");
            // Usaremos una TablaHash para contar ocurrencias de palabras.
            // Clave: palabra (String)
            // Valor: Lista de Posiciones (List<Pos>)
            Diccionario<String, List<Pos>> tablaHash = new TablaHash<>(10);

            // Simulamos un texto dividido en líneas:
            String[] lineas = {
                    "p1 p2 p3",
                    "p2 p3 p1",
                    "p3 p2",
                    "p1 p3"
            };

            // Recorremos cada línea y cada palabra, guardando su posición (línea, columna)
            for (int i = 0; i < lineas.length; i++) {
                String[] palabras = lineas[i].split("\\s+");
                for (int j = 0; j < palabras.length; j++) {
                    String palabra = palabras[j];
                    if (!tablaHash.contiene(palabra)) {
                        List<Pos> listaPos = new ArrayList<>();
                        listaPos.add(new Pos(i + 1, j + 1)); // Se usa 1-indexado
                        tablaHash.insertar(palabra, listaPos);
                    } else {
                        List<Pos> listaPos = tablaHash.obtener(palabra);
                        listaPos.add(new Pos(i + 1, j + 1));
                    }
                }
            }

            // Mostrar para cada palabra la lista de ocurrencias (línea:columna)
            System.out.println("Ocurrencias de cada palabra:");
            Iterator<String> it = tablaHash.iterator();
            while (it.hasNext()) {
                String palabra = it.next();
                List<Pos> lista = tablaHash.obtener(palabra);
                System.out.print(palabra + " -> ");
                for (Pos pos : lista) {
                    System.out.print(pos + " ");
                }
                System.out.println();
            }

        } catch (ElementoDuplicado | ElementoNoEncontrado e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
