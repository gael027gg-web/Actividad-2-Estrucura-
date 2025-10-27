package actividad.pkg1.estructura;

public class Main {

    // ============================
    // Clase genérica Node (simple)
    // ============================
    static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public T getData() { return data; }
        public void setData(T data) { this.data = data; }

        public Node<T> getNext() { return next; }
        public void setNext(Node<T> next) { this.next = next; }
    }

    // ==========================================
    // Clase genérica NodeD para lista doble
    // ==========================================
    static class NodeD<T> {
        private T data;
        private NodeD<T> next;
        private NodeD<T> prev;

        public NodeD(T data) {
            this.data = data;
        }

        public T getData() { return data; }
        public NodeD<T> getNext() { return next; }
        public NodeD<T> getPrev() { return prev; }

        public void setNext(NodeD<T> next) { this.next = next; }
        public void setPrev(NodeD<T> prev) { this.prev = prev; }
    }

    // ============================
    // Lista simplemente enlazada
    // ============================
    static class LinkedList<T> {
        private Node<T> head;

        public void insertar(T data) {
            Node<T> nuevo = new Node<>(data);
            if (head == null) {
                head = nuevo;
            } else {
                Node<T> temp = head;
                while (temp.getNext() != null) temp = temp.getNext();
                temp.setNext(nuevo);
            }
        }

        public boolean eliminar(T data) {
            if (head == null) return false;
            if (head.getData().equals(data)) {
                head = head.getNext();
                return true;
            }
            Node<T> temp = head;
            while (temp.getNext() != null && !temp.getNext().getData().equals(data)) {
                temp = temp.getNext();
            }
            if (temp.getNext() == null) return false;
            temp.setNext(temp.getNext().getNext());
            return true;
        }

        public boolean buscar(T data) {
            Node<T> temp = head;
            while (temp != null) {
                if (temp.getData().equals(data)) return true;
                temp = temp.getNext();
            }
            return false;
        }

        public void mostrar() {
            if (head == null) {
                System.out.println("La lista está vacía.");
                return;
            }
            Node<T> temp = head;
            while (temp != null) {
                System.out.print(temp.getData() + " -> ");
                temp = temp.getNext();
            }
            System.out.println("NULL");
        }
    }

    // ============================
    // Lista doblemente enlazada
    // ============================
    static class DoublyLinkedList<T> {
        private NodeD<T> head;
        private NodeD<T> tail;

        public void insertar(T data) {
            NodeD<T> nuevo = new NodeD<>(data);
            if (head == null) {
                head = tail = nuevo;
            } else {
                tail.setNext(nuevo);
                nuevo.setPrev(tail);
                tail = nuevo;
            }
        }

        public void mostrar() {
            if (head == null) {
                System.out.println("La lista está vacía.");
                return;
            }
            NodeD<T> temp = head;
            while (temp != null) {
                System.out.print(temp.getData() + " <-> ");
                temp = temp.getNext();
            }
            System.out.println("NULL");
        }
    }

    // ============================
    // Clase Contacto (dato complejo)
    // ============================
    static class Contacto {
        private String nombre;
        private String direccion;
        private String telefono;

        public Contacto(String nombre, String direccion, String telefono) {
            this.nombre = nombre;
            this.direccion = direccion;
            this.telefono = telefono;
        }

        public String getNombre() { return nombre; }
        public String getDireccion() { return direccion; }
        public String getTelefono() { return telefono; }

        @Override
        public String toString() {
            return "[" + nombre + ", " + direccion + ", " + telefono + "]";
        }
    }

    // ============================
    // Clase DataTypeExamples
    // ============================
    static class DataTypeExamples {
        public static void ejemploPrimitivos() {
            LinkedList<Integer> listaInt = new LinkedList<>();
            listaInt.insertar(10);
            listaInt.insertar(20);
            listaInt.insertar(30);
            System.out.println("\nEjemplo de lista con enteros:");
            listaInt.mostrar();
        }

        public static void ejemploContactos() {
            LinkedList<Contacto> listaC = new LinkedList<>();
            listaC.insertar(new Contacto("Ana", "Monterrey", "8123456789"));
            listaC.insertar(new Contacto("Luis", "Guadalupe", "8112345678"));
            listaC.insertar(new Contacto("Karla", "Apodaca", "8187654321"));
            System.out.println("\nEjemplo de lista con contactos:");
            listaC.mostrar();
        }
    }

    // ============================
    // Main con menú interactivo
    // ============================
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.println("======================================");
        System.out.println(" Screenshot del alumno");
        System.out.println(" Nombre: Gael Giovanni Gaytán García");
        System.out.println(" Matrícula: 07099843");
        System.out.println("======================================");
        System.out.println("=== Sistema de Listas Enlazadas Genéricas ===");

        int tipoLista;
        System.out.println("\nSeleccione el tipo de lista a usar:");
        System.out.println("1. Lista Simple");
        System.out.println("2. Lista Doble");
        System.out.print("Opción: ");
        tipoLista = sc.nextInt();
        sc.nextLine();

        if (tipoLista == 1) {
            LinkedList<Object> lista = new LinkedList<>();
            menuListaSimple(lista, sc);
        } else if (tipoLista == 2) {
            DoublyLinkedList<Object> listaD = new DoublyLinkedList<>();
            menuListaDoble(listaD, sc);
        } else {
            System.out.println("Opción inválida. Fin del programa.");
        }
        sc.close();
    }

    // Menú lista simple
    public static void menuListaSimple(LinkedList<Object> lista, java.util.Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- Menú Lista Simple ---");
            System.out.println("1. Insertar elemento");
            System.out.println("2. Eliminar elemento");
            System.out.println("3. Buscar elemento");
            System.out.println("4. Mostrar lista");
            System.out.println("5. Ejemplos de tipos de datos");
            System.out.println("6. Agregar contacto");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese un dato: ");
                    Object dato = sc.nextLine();
                    lista.insertar(dato);
                    break;
                case 2:
                    System.out.print("Dato a eliminar: ");
                    Object elim = sc.nextLine();
                    if (lista.eliminar(elim))
                        System.out.println("Eliminado correctamente.");
                    else
                        System.out.println("No encontrado.");
                    break;
                case 3:
                    System.out.print("Dato a buscar: ");
                    Object bus = sc.nextLine();
                    if (lista.buscar(bus))
                        System.out.println("Dato encontrado.");
                    else
                        System.out.println("Dato no encontrado.");
                    break;
                case 4:
                    lista.mostrar();
                    break;
                case 5:
                    DataTypeExamples.ejemploPrimitivos();
                    DataTypeExamples.ejemploContactos();
                    break;
                case 6:
                    System.out.print("Nombre: ");
                    String n = sc.nextLine();
                    System.out.print("Dirección: ");
                    String d = sc.nextLine();
                    System.out.print("Teléfono: ");
                    String t = sc.nextLine();
                    Contacto c = new Contacto(n, d, t);
                    lista.insertar(c);
                    System.out.println("Contacto agregado.");
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 7);
    }

    // Menú lista doble
    public static void menuListaDoble(DoublyLinkedList<Object> lista, java.util.Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- Menú Lista Doble ---");
            System.out.println("1. Insertar elemento");
            System.out.println("2. Mostrar lista");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese un dato: ");
                    Object dato = sc.nextLine();
                    lista.insertar(dato);
                    break;
                case 2:
                    lista.mostrar();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 3);
    }
}
