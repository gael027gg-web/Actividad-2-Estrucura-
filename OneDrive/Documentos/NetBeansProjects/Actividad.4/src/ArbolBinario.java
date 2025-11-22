public class ArbolBinario<T extends Comparable<T>> {

    private Nodo<T> raiz;

    public ArbolBinario() {
        raiz = null;
    }

    // =============================
    // INSERTAR
    // =============================
    public void insertar(T dato) {
        raiz = insertarRec(raiz, dato);
    }

    private Nodo<T> insertarRec(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return new Nodo<T>(dato);
        }
        if (dato.compareTo(nodo.dato) < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, dato);
        } else {
            nodo.derecho = insertarRec(nodo.derecho, dato);
        }
        return nodo;
    }

    // =============================
    // BUSCAR
    // =============================
    public boolean buscar(T dato) {
        return buscarRec(raiz, dato);
    }

    private boolean buscarRec(Nodo<T> nodo, T dato) {
        if (nodo == null)
            return false;

        if (dato.compareTo(nodo.dato) == 0)
            return true;

        if (dato.compareTo(nodo.dato) < 0)
            return buscarRec(nodo.izquierdo, dato);

        return buscarRec(nodo.derecho, dato);
    }

    // =============================
    // ELIMINAR
    // =============================
    public void eliminar(T dato) {
        raiz = eliminarRec(raiz, dato);
    }

    private Nodo<T> eliminarRec(Nodo<T> nodo, T dato) {
        if (nodo == null)
            return null;

        if (dato.compareTo(nodo.dato) < 0)
            nodo.izquierdo = eliminarRec(nodo.izquierdo, dato);
        else if (dato.compareTo(nodo.dato) > 0)
            nodo.derecho = eliminarRec(nodo.derecho, dato);
        else {
            // Caso 1: sin hijos
            if (nodo.izquierdo == null && nodo.derecho == null)
                return null;

            // Caso 2: un hijo
            if (nodo.izquierdo == null)
                return nodo.derecho;
            if (nodo.derecho == null)
                return nodo.izquierdo;

            // Caso 3: dos hijos
            Nodo<T> temp = encontrarMin(nodo.derecho);
            nodo.dato = temp.dato;
            nodo.derecho = eliminarRec(nodo.derecho, temp.dato);
        }
        return nodo;
    }

    private Nodo<T> encontrarMin(Nodo<T> nodo) {
        while (nodo.izquierdo != null)
            nodo = nodo.izquierdo;

        return nodo;
    }

    // =============================
    // RECORRIDOS
    // =============================
    public void preOrden() {
        preOrdenRec(raiz);
        System.out.println();
    }

    private void preOrdenRec(Nodo<T> nodo) {
        if (nodo != null) {
            System.out.print(nodo.dato + " ");
            preOrdenRec(nodo.izquierdo);
            preOrdenRec(nodo.derecho);
        }
    }

    public void inOrden() {
        inOrdenRec(raiz);
        System.out.println();
    }

    private void inOrdenRec(Nodo<T> nodo) {
        if (nodo != null) {
            inOrdenRec(nodo.izquierdo);
            System.out.print(nodo.dato + " ");
            inOrdenRec(nodo.derecho);
        }
    }

    public void postOrden() {
        postOrdenRec(raiz);
        System.out.println();
    }

    private void postOrdenRec(Nodo<T> nodo) {
        if (nodo != null) {
            postOrdenRec(nodo.izquierdo);
            postOrdenRec(nodo.derecho);
            System.out.print(nodo.dato + " ");
        }
    }
}
