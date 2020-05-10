package Fhulano.Experiencia;

import Fhulano.Simulacion.exponencial;
import Fhulano.Simulacion.uniforme;

public class Ejer10 {
    public static void main(String[] args) {
        uniforme A = new uniforme(45, 121, 1000);

        double[] tipoB = {100, 124, 50, 123, 80, 75, 55, 60, 70};
        double promedio = promedio(tipoB);
        System.out.println("Prom " + promedio);
        exponencial B = new exponencial(promedio, 1000);
//        B.graficar();

        double promUni = promedio(A.getData(), 0);
        double promExp = promedio(B.getData(), 0);
        System.out.println("Tipo A: " + promUni + "\nTipo B: " + promExp);
    }

    private static double promedio(double[][] data, int col) {
        double sum = 0.0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i][col];
            if (col == 1) {
                System.out.println(data[i][1] + "");
            }
        }
        return sum / data.length;
    }

    public static double promedio(double[] tipoB) {
        double sum = 0.0;
        for (int i = 0; i < tipoB.length; i++) {
            sum += tipoB[i];
        }
        return sum / tipoB.length;
    }

}
