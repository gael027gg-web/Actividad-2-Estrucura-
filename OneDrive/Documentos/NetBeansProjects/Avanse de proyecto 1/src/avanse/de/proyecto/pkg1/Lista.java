package avanse.de.proyecto.pkg1;

public class Lista<T> {

    private Nodo inicio;

    private class Nodo {
        T dato;
        Nodo sig;
        Nodo(T dato) { this.dato = dato; }
    }

    public boolean estaVacia() { return inicio == null; }

    // insertar al inicio
    public void insertar(T dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.sig = inicio;
        inicio = nuevo;
        System.out.println("✅ Tarea agregada a la Lista");
    }

    // busca por nombre (asumiendo T == Tarea)
    public T find(String nombre) {
        Nodo actual = inicio;
        while (actual != null) {
            if (actual.dato instanceof Tarea) {
                Tarea t = (Tarea) actual.dato;
                if (t.getNombre().equalsIgnoreCase(nombre)) return actual.dato;
            }
            actual = actual.sig;
        }
        return null;
    }

    // elimina la primera ocurrencia por nombre (asumiendo T == Tarea)
    public boolean delete(String nombre) {
        Nodo actual = inicio, prev = null;
        while (actual != null) {
            if (actual.dato instanceof Tarea) {
                Tarea t = (Tarea) actual.dato;
                if (t.getNombre().equalsIgnoreCase(nombre)) {
                    if (prev == null) inicio = actual.sig;
                    else prev.sig = actual.sig;
                    System.out.println("🗑 Tarea eliminada de la Lista");
                    return true;
                }
            }
            prev = actual;
            actual = actual.sig;
        }
        System.out.println("⚠ No se encontró la tarea en la Lista");
        return false;
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("⚠ No hay tareas en la Lista");
            return;
        }
        System.out.println("\n📌 Tareas en LISTA (inicio -> ...):");
        Nodo actual = inicio;
        while (actual != null) {
            System.out.println(" - " + actual.dato);
            actual = actual.sig;
        }
    }

    public T[] toArray(Class<T> clazz) {
        int n = 0;
        Nodo a = inicio;
        while (a != null) { n++; a = a.sig; }
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) java.lang.reflect.Array.newInstance(clazz, n);
        int i = 0;
        a = inicio;
        while (a != null) {
            arr[i++] = a.dato;
            a = a.sig;
        }
        return arr;
    }
}
