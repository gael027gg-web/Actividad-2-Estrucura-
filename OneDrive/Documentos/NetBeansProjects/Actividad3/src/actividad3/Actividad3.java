package actividad3;

import java.io.*;

public class Actividad3 {

    // Mostrar datos del alumno
    public static void mostrarDatos() {
        System.out.println("======================================");
        System.out.println(" Nombre: Gael Giovanni Gaytán García");
        System.out.println(" Matrícula: 07099843");
        System.out.println("======================================");
    }

    // Sudoku 9x9
    static int[][] sudoku = new int[9][9];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion = 0;

        while (opcion != 4) {

            mostrarDatos();

            System.out.println("========= MENÚ PRINCIPAL =========");
            System.out.println("1. Serie de Fibonacci (Recursiva)");
            System.out.println("2. Subset Sum (Recursivo)");
            System.out.println("3. Resolver Sudoku (Archivo .sdk)");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");

            opcion = Integer.parseInt(br.readLine());

            switch (opcion) {
                case 1:
                    Fibonacci.ejecutar();
                    break;

                case 2:
                    SubsetSum.ejecutar();
                    break;

                case 3:
                    ejecutarSudoku();
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    return;

                default:
                    System.out.println("Opción inválida.\n");
            }

            System.out.println("\nPresiona ENTER para continuar...");
            br.readLine();
        }
    }

    // =========================
    //    EJECUTAR SUDOKU
    // =========================
    public static void ejecutarSudoku() {

        mostrarDatos();
        System.out.println("Resolver Sudoku desde archivo local\n");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nombreArchivo = "";

        try {
            System.out.print("Escribe el nombre del archivo .sdk (ejemplo: puzzle_7099843.sdk): ");
            nombreArchivo = br.readLine();
        } catch (IOException e) {
            System.out.println("Error al leer entrada.");
            return;
        }

        // Ruta automática al archivo dentro del proyecto
        File archivo = new File("src/actividad3/" + nombreArchivo);

        if (!archivo.exists()) {
            System.out.println("ERROR: No se encontró el archivo en:");
            System.out.println(archivo.getAbsolutePath());
            return;
        }

        try {
            leerSudoku(archivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        System.out.println("\n=== SUDOKU ORIGINAL ===");
        imprimirSudoku();

        if (resolverSudoku()) {
            System.out.println("\n=== SUDOKU RESUELTO ===");
            imprimirSudoku();
        } else {
            System.out.println("No se pudo resolver el Sudoku.");
        }
    }

    // Leer archivo SDK
    public static void leerSudoku(File archivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        int fila = 0;

        while ((linea = br.readLine()) != null && fila < 9) {
            for (int col = 0; col < 9; col++) {
                sudoku[fila][col] = Character.getNumericValue(linea.charAt(col));
            }
            fila++;
        }

        br.close();
    }

    // Imprimir tablero
    public static void imprimirSudoku() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) System.out.println("-------------------------");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) System.out.print("| ");
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------------------");
    }

    // Resolver Sudoku (backtracking)
    public static boolean resolverSudoku() {
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {

                if (sudoku[fila][col] == 0) { // espacio vacío

                    for (int num = 1; num <= 9; num++) {

                        if (esValido(fila, col, num)) {
                            sudoku[fila][col] = num;

                            if (resolverSudoku()) return true;

                            sudoku[fila][col] = 0;
                        }
                    }

                    return false;
                }
            }
        }
        return true;
    }

    // Validación
    public static boolean esValido(int fila, int col, int num) {

        for (int i = 0; i < 9; i++) {
            if (sudoku[fila][i] == num) return false;
            if (sudoku[i][col] == num) return false;
        }

        int subFila = fila - fila % 3;
        int subCol = col - col % 3;

        for (int i = subFila; i < subFila + 3; i++) {
            for (int j = subCol; j < subCol + 3; j++) {
                if (sudoku[i][j] == num) return false;
            }
        }

        return true;
    }
}
