package solutions;
import adt.Lista;
import ds.ListaImpl;
import exceptions.IndiceError;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Lista<Integer> lista = new ListaImpl<>();
        try {
            // Insertando elementos
            lista.insertar(0, 10); // [10]
            lista.insertar(1, 20); // [10, 20]
            lista.insertar(2, 30); // [10, 20, 30]
            lista.insertar(3, 40); // [10, 20, 30, 40]
            System.out.println("Talla actual: " + lista.talla()); // 4

            // Obtener elemento en la posición 2 (debe ser 30)
            System.out.println("Elemento en pos 2: " + lista.obtener(2));

            // Buscar el elemento 20
            int pos20 = lista.buscar(20);
            System.out.println("El 20 está en la posición: " + pos20);

            // Eliminar el elemento en la posición 1 (el 20)
            lista.delete(1); // Ahora la lista es [10, 30, 40]
            System.out.println("Talla después de eliminar pos 1: " + lista.talla()); // 3

            // Mostrar todos los elementos mediante acceso directo
            System.out.println("Recorriendo la lista con for:");
            for (int i = 0; i < lista.talla(); i++) {
                System.out.println("Elemento en pos " + i + ": " + lista.obtener(i));
            }

            // Recorrer la lista usando el iterador
            System.out.println("Recorriendo la lista con el iterador:");
            Iterator<Integer> it = lista.iterator();
            while(it.hasNext()){
                System.out.println("Elemento: " + it.next());
            }

            // Intentamos eliminar elementos de más para provocar excepción
            lista.delete(0);
            lista.delete(0);
            lista.delete(0);
            lista.delete(0);

        } catch (IndiceError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
