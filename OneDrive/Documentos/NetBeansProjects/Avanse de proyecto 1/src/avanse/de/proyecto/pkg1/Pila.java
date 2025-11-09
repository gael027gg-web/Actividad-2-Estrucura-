package avanse.de.proyecto.pkg1;

public class Pila<T> {

    private Nodo tope;

    private class Nodo {
        T dato;
        Nodo siguiente;
        Nodo(T dato) { this.dato = dato; }
    }

    public boolean estaVacia() { return tope == null; }

    public void push(T dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = tope;
        tope = nuevo;
        System.out.println("✅ Tarea agregada a la Pila");
    }

    public T pop() {
        if (estaVacia()) {
            System.out.println("⚠ La pila está vacía");
            return null;
        }
        T dato = tope.dato;
        tope = tope.siguiente;
        System.out.println("🗑 Tarea eliminada de la Pila");
        return dato;
    }

    public T peek() {
        if (estaVacia()) {
            System.out.println("⚠ La pila está vacía");
            return null;
        }
        return tope.dato;
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("⚠ No hay tareas urgentes en la Pila");
            return;
        }
        System.out.println("\n📌 Tareas en PILA (de cima a base):");
        Nodo actual = tope;
        while (actual != null) {
            System.out.println(" - " + actual.dato);
            actual = actual.siguiente;
        }
    }

    // devuelve arreglo de Tarea si T es Tarea; útil para consolidar (sin java.util)
    public T[] toArray(Class<T> clazz) {
        int n = 0;
        Nodo a = tope;
        while (a != null) { n++; a = a.siguiente; }
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) java.lang.reflect.Array.newInstance(clazz, n);
        int i = 0;
        a = tope;
        while (a != null) {
            arr[i++] = a.dato;
            a = a.siguiente;
        }
        return arr;
    }
}
