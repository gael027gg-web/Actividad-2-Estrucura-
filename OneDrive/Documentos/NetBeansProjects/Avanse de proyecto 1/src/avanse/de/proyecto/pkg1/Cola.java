package avanse.de.proyecto.pkg1;

public class Cola<T> {

    private Nodo frente, fin;

    private class Nodo {
        T dato;
        Nodo sig;
        Nodo(T dato) { this.dato = dato; }
    }

    public boolean estaVacia() { return frente == null; }

    public void enqueue(T dato) {
        Nodo nuevo = new Nodo(dato);
        if (fin != null) fin.sig = nuevo;
        fin = nuevo;
        if (frente == null) frente = nuevo;
        System.out.println("✅ Tarea agregada a la Cola");
    }

    public T dequeue() {
        if (estaVacia()) {
            System.out.println("⚠ La cola está vacía");
            return null;
        }
        T dato = frente.dato;
        frente = frente.sig;
        if (frente == null) fin = null;
        System.out.println("🗑 Tarea eliminada de la Cola");
        return dato;
    }

    public T front() {
        if (estaVacia()) {
            System.out.println("⚠ La cola está vacía");
            return null;
        }
        return frente.dato;
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("⚠ No hay tareas programadas en la Cola");
            return;
        }
        System.out.println("\n📌 Tareas en COLA (frente -> fin):");
        Nodo actual = frente;
        while (actual != null) {
            System.out.println(" - " + actual.dato);
            actual = actual.sig;
        }
    }

    public T[] toArray(Class<T> clazz) {
        int n = 0;
        Nodo a = frente;
        while (a != null) { n++; a = a.sig; }
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) java.lang.reflect.Array.newInstance(clazz, n);
        int i = 0;
        a = frente;
        while (a != null) {
            arr[i++] = a.dato;
            a = a.sig;
        }
        return arr;
    }
}
