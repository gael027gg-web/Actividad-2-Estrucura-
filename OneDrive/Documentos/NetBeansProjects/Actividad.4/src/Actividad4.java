import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Actividad4 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArbolBinario<Integer> arbol = new ArbolBinario<>();

        mostrarDatos(); // Mostrar tus datos al inicio

        int opcion = 0;

        do {
            System.out.println("\n====== SISTEMA DE EMPLEADOS (Árbol Binario) ======");
            System.out.println("1. Insertar empleado (ID)");
            System.out.println("2. Eliminar empleado (ID)");
            System.out.println("3. Buscar empleado (ID)");
            System.out.println("4. Mostrar recorridos");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(br.readLine());
            } catch (Exception e) {
                System.out.println("⚠ Opción inválida. Presiona R para regresar.");
                br.readLine();
                continue;
            }

            switch (opcion) {

                case 1:
                    System.out.print("Ingresa ID del empleado: ");
                    try {
                        int id = Integer.parseInt(br.readLine());
                        arbol.insertar(id);
                        System.out.println("✔ Empleado insertado.");
                    } catch (Exception e) {
                        System.out.println("⚠ Error. Presiona R para regresar.");
                        br.readLine();
                    }
                    break;

                case 2:
                    System.out.print("Ingresa ID a eliminar: ");
                    try {
                        int id = Integer.parseInt(br.readLine());
                        arbol.eliminar(id);
                        System.out.println("✔ Empleado eliminado (si existía).");
                    } catch (Exception e) {
                        System.out.println("⚠ Error. Presiona R para regresar.");
                        br.readLine();
                    }
                    break;

                case 3:
                    System.out.print("Ingresa ID a buscar: ");
                    try {
                        int id = Integer.parseInt(br.readLine());
                        boolean encontrado = arbol.buscar(id);
                        if (encontrado)
                            System.out.println("✔ Empleado encontrado.");
                        else
                            System.out.println("✖ No existe ese empleado.");
                    } catch (Exception e) {
                        System.out.println("⚠ Error. Presiona R para regresar.");
                        br.readLine();
                    }
                    break;

                case 4:
                    System.out.println("PreOrden:");
                    arbol.preOrden();

                    System.out.println("InOrden:");
                    arbol.inOrden();

                    System.out.println("PostOrden:");
                    arbol.postOrden();
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("⚠ Opción inválida.");
                    break;
            }

        } while (opcion != 5);
    }

    // ======================================
    // Mostrar datos del alumno
    // ======================================
    public static void mostrarDatos() {
        System.out.println("======================================");
        System.out.println(" Nombre: Gael Giovanni Gaytán García");
        System.out.println(" Matrícula: 07099843");
        System.out.println("======================================");
    }
}
