/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actividad3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubsetSum {

    public static void ejecutar() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Cantidad de elementos: ");
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        System.out.println("Ingresa los números:");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        System.out.print("Ingresa la suma objetivo: ");
        int objetivo = Integer.parseInt(br.readLine());

        boolean existe = subsetSum(arr, objetivo, 0);

        System.out.println("¿Existe un subconjunto que sume " + objetivo + "? " + existe + "\n");
    }

    public static boolean subsetSum(int[] arr, int objetivo, int index) {
        if (objetivo == 0) return true;
        if (index == arr.length) return false;

        if (subsetSum(arr, objetivo - arr[index], index + 1)) return true;

        return subsetSum(arr, objetivo, index + 1);
    }
}

