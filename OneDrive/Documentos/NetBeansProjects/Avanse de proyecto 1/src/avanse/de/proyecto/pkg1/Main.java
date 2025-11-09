package avanse.de.proyecto.pkg1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // estructuras
    private static final Pila<Tarea> pila = new Pila<>();
    private static final Cola<Tarea> cola = new Cola<>();
    private static final Lista<Tarea> lista = new Lista<>();

    public static void main(String[] args) throws IOException {
        System.out.println("======================================");
        System.out.println(" Nombre: Gael Giovanni Gaytán García");
        System.out.println(" Matrícula: 07099843");
        System.out.println("======================================");

        int opcion;
        do {
            System.out.println("\n===== SISTEMA DE TAREAS =====");
            System.out.println("1. Gestionar PILA (Urgentes)");
            System.out.println("2. Gestionar COLA (Programadas)");
            System.out.println("3. Gestionar LISTA (Departamentos)");
            System.out.println("4. Agregar tarea (elige módulo manualmente)");
            System.out.println("5. Ver todas las tareas (ordenadas: urgencia->depto->fecha)");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = leerEnteroSeguridad();

            switch (opcion) {
                case 1 -> menuPila();
                case 2 -> menuCola();
                case 3 -> menuLista();
                case 4 -> agregarTareaManual();
                case 5 -> mostrarTodasOrdenadas();
                case 0 -> System.out.println("✅ Saliendo...");
                default -> System.out.println("❌ Opción inválida");
            }
        } while (opcion != 0);
    }

    // ------ Lectura segura y validaciones ------
    private static String readLineTrim() {
        try { return br.readLine().trim(); } catch (IOException e) { return ""; }
    }

    private static int leerEnteroSeguridad() {
        try { return Integer.parseInt(readLineTrim()); } catch (Exception e) { return -1; }
    }

    private static LocalDate leerFecha() {
        LocalDate fecha = null;
        do {
            System.out.print("Fecha límite (dd/MM/yyyy): ");
            String s = readLineTrim();
            try {
                fecha = LocalDate.parse(s, FORMATO);
            } catch (DateTimeParseException ex) {
                System.out.println("❌ Formato inválido. Usa dd/MM/yyyy.");
            }
        } while (fecha == null);
        return fecha;
    }

    private static int leerUrgencia() {
        int urg = -1;
        do {
            System.out.print("Urgencia (1=BAJA,2=MEDIA,3=ALTA): ");
            try {
                urg = Integer.parseInt(readLineTrim());
                if (urg < 1 || urg > 3) { System.out.println("❌ Ingresa 1, 2 o 3."); urg = -1; }
            } catch (NumberFormatException e) { System.out.println("❌ Número inválido."); urg = -1; }
        } while (urg == -1);
        return urg;
    }

    private static String leerDepartamento() {
        String dep = "";
        do {
            System.out.print("Departamento (Sistemas, Ventas, Compras, Diseño): ");
            dep = readLineTrim();
            if (dep.isEmpty()) { System.out.println("❌ No puede estar vacío."); dep = ""; continue; }
            String low = dep.toLowerCase();
            if (!(low.equals("sistemas") || low.equals("ventas") || low.equals("compras") || low.equals("diseño") || low.equals("diseno"))) {
                System.out.println("❌ Departamento no reconocido. Usa: Sistemas, Ventas, Compras, Diseño.");
                dep = "";
            } else {
                if (dep.equalsIgnoreCase("diseno")) dep = "Diseño";
            }
        } while (dep.isEmpty());
        return dep;
    }

    private static Tarea crearTareaInteractiva() {
        try {
            System.out.print("Nombre de la tarea: ");
            String nombre = readLineTrim();
            String departamento = leerDepartamento();
            int urg = leerUrgencia();
            LocalDate fecha = leerFecha();
            return new Tarea(nombre, departamento, urg, fecha);
        } catch (Exception e) {
            System.out.println("❌ Error al crear la tarea.");
            return null;
        }
    }

    // ------ Menús por estructura ------
    private static void menuPila() {
        System.out.println("\n--- PILA (Urgentes) ---");
        System.out.println("1. Agregar tarea (push)");
        System.out.println("2. Eliminar tarea (pop)");
        System.out.println("3. Ver tope (peek)");
        System.out.println("4. Mostrar pila");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
        int op = leerEnteroSeguridad();
        switch (op) {
            case 1 -> {
                Tarea t = crearTareaInteractiva();
                if (t != null) pila.push(t);
            }
            case 2 -> {
                Tarea rem = pila.pop();
                if (rem != null) System.out.println("Eliminada: " + rem);
            }
            case 3 -> {
                Tarea top = pila.peek();
                if (top != null) System.out.println("Tope: " + top);
            }
            case 4 -> pila.mostrar();
            case 0 -> {}
            default -> System.out.println("❌ Opción inválida");
        }
    }

    private static void menuCola() {
        System.out.println("\n--- COLA (Programadas) ---");
        System.out.println("1. Agregar tarea (enqueue)");
        System.out.println("2. Eliminar tarea (dequeue)");
        System.out.println("3. Ver frente (front)");
        System.out.println("4. Mostrar cola");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
        int op = leerEnteroSeguridad();
        switch (op) {
            case 1 -> {
                Tarea t = crearTareaInteractiva();
                if (t != null) cola.enqueue(t);
            }
            case 2 -> {
                Tarea rem = cola.dequeue();
                if (rem != null) System.out.println("Eliminada: " + rem);
            }
            case 3 -> {
                Tarea f = cola.front();
                if (f != null) System.out.println("Frente: " + f);
            }
            case 4 -> cola.mostrar();
            case 0 -> {}
            default -> System.out.println("❌ Opción inválida");
        }
    }

    private static void menuLista() {
        System.out.println("\n--- LISTA (Departamentos) ---");
        System.out.println("1. Agregar tarea (insertar)");
        System.out.println("2. Eliminar por nombre (delete)");
        System.out.println("3. Buscar por nombre (find)");
        System.out.println("4. Mostrar lista");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
        int op = leerEnteroSeguridad();
        switch (op) {
            case 1 -> {
                Tarea t = crearTareaInteractiva();
                if (t != null) lista.insertar(t);
            }
            case 2 -> {
                System.out.print("Nombre de la tarea a eliminar: ");
                String nombre = readLineTrim();
                lista.delete(nombre);
            }
            case 3 -> {
                System.out.print("Nombre de la tarea a buscar: ");
                String nombre = readLineTrim();
                Tarea f = lista.find(nombre);
                if (f != null) System.out.println("Encontrada: " + f);
                else System.out.println("❌ No encontrada");
            }
            case 4 -> lista.mostrar();
            case 0 -> {}
            default -> System.out.println("❌ Opción inválida");
        }
    }

    // ------ Agregar tarea manualmente a un módulo (usuario decide) ------
    private static void agregarTareaManual() {
        System.out.println("\nA qué módulo quieres agregar la tarea?");
        System.out.println("1. PILA (Urgentes)");
        System.out.println("2. COLA (Programadas)");
        System.out.println("3. LISTA (Departamentos)");
        System.out.print("Elige módulo: ");
        int m = leerEnteroSeguridad();
        Tarea t = crearTareaInteractiva();
        if (t == null) { System.out.println("❌ Tarea no creada."); return; }
        switch (m) {
            case 1 -> pila.push(t);
            case 2 -> cola.enqueue(t);
            case 3 -> lista.insertar(t);
            default -> System.out.println("❌ Módulo inválido. Cancela.");
        }
    }

    // ------ Consolidar y ordenar (sin java.util) ------
    private static void mostrarTodasOrdenadas() {
        // obtener arrays de cada estructura
        Tarea[] a = pila.toArray(Tarea.class);   // cima->base
        Tarea[] b = cola.toArray(Tarea.class);   // frente->fin
        Tarea[] c = lista.toArray(Tarea.class);  // inicio->...

        int total = (a != null ? a.length : 0) + (b != null ? b.length : 0) + (c != null ? c.length : 0);
        if (total == 0) {
            System.out.println("No hay tareas registradas.");
            return;
        }

        // combinar manualmente
        Tarea[] todas = (Tarea[]) java.lang.reflect.Array.newInstance(Tarea.class, total);
        int idx = 0;
        if (a != null) for (int i = 0; i < a.length; i++) todas[idx++] = a[i];
        if (b != null) for (int i = 0; i < b.length; i++) todas[idx++] = b[i];
        if (c != null) for (int i = 0; i < c.length; i++) todas[idx++] = c[i];

        // Ordenamiento sencillo: burbuja estable con la prioridad solicitada
        // Comparador: urgencia(desc), departamento(alfabético), fecha(asc)
        for (int i = 0; i < total - 1; i++) {
            for (int j = 0; j < total - 1 - i; j++) {
                if (debeIntercambiar(todas[j], todas[j+1])) {
                    Tarea tmp = todas[j];
                    todas[j] = todas[j+1];
                    todas[j+1] = tmp;
                }
            }
        }

        System.out.println("\n===== TODAS LAS TAREAS (ordenadas) =====");
        for (int i = 0; i < total; i++) System.out.println(" - " + todas[i]);
    }

    // retorna true si a debe ir despues de b (por lo tanto intercambiamos)
    private static boolean debeIntercambiar(Tarea a, Tarea b) {
        if (a == null || b == null) return false;
        // urgencia: mayor prioridad para numero mayor (3 primero)
        if (a.getUrgencia() < b.getUrgencia()) return true;
        if (a.getUrgencia() > b.getUrgencia()) return false;
        // mismo nivel de urgencia -> comparar departamento (case-insensitive)
        String da = a.getDepartamento().toLowerCase();
        String db = b.getDepartamento().toLowerCase();
        int cmp = da.compareTo(db);
        if (cmp > 0) return true;
        if (cmp < 0) return false;
        // mismo departamento -> fecha (más próxima primero)
        LocalDate fa = a.getFechaLimite();
        LocalDate fb = b.getFechaLimite();
        if (fa == null && fb == null) return false;
        if (fa == null) return true;  // consideramos null como "lejana", poner después
        if (fb == null) return false;
        return fa.isAfter(fb); // si a fecha es después de b, intercambiar (b antes)
    }
}
