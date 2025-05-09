package adt;

import java.util.Iterator;
import exceptions.ElementoNoEncontrado;
import exceptions.ElementoDuplicado;


public interface Diccionario<K, V> extends Iterable<K> {
    V insertar(K key, V value) throws ElementoDuplicado;
    V obtener(K key) throws ElementoNoEncontrado;
    V eliminar(K key) throws ElementoNoEncontrado;
    boolean contiene(K key);
    int talla();
    boolean esVacia();
    void borrar();

    @Override
    Iterator<K> iterator();
}
