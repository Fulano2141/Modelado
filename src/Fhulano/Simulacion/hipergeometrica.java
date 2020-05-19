package Fhulano.Simulacion;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class hipergeometrica {
//    input n , m , p

    public static void main(String[] args) {
        double p = 0.7;
        double n = 100;
        int N = 500;
        double[][] matrix = new double[11][3];
        double fx = 0.0;
        for (int i = 0; i < 11; i++) {
            double px = (combinatoria(N * p, i) * combinatoria(N * (1 - p), n - i)) / combinatoria(N, n);
            fx += px;
            matrix[i][0] = i;
            matrix[i][1] = px;
            matrix[i][2] = fx;
        }
        System.out.println("La matriz es: ");
        showMatrix(matrix);

        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
        separadoresPersonalizados.setDecimalSeparator('.');

        DecimalFormat formato1 = new DecimalFormat("#.00", separadoresPersonalizados);
        Random random = new Random();
        List<Integer> lista = new ArrayList<>();
        for (int j = 0; j < 2500; j++) {
            int positionSeleccionable = 0;
            double numRandom01 = random.nextDouble();
            double resultOfSeleccionable = Math.max(numRandom01, matrix[0][1]) - Math.min(numRandom01, matrix[0][1]);
            for (int i = 0; i < matrix.length; i++) {
                if ((Math.max(numRandom01, matrix[i][1]) - Math.min(numRandom01, matrix[i][1])) < resultOfSeleccionable) {
                    positionSeleccionable = i;
                    resultOfSeleccionable = matrix[i][1];
                }
/*                 if(formato1.format(matrix[i][1]).equals(formato1.format(numRandom01))){
                    lista.add(i);
                }*/
            }
            lista.add(positionSeleccionable);
        }
        System.out.println(mostrarvector(lista));
    }

    public static String mostrarvector(List<Integer> lista) {
        StringBuilder cad = new StringBuilder();
        for (int i = 0; i < lista.size(); i++) {
            if (i % 30 == 0) {
                cad.append(lista.get(i)).append("\n");
            } else
                cad.append(lista.get(i)).append("   ");
        }
        return cad.toString();
    }

    public static double factorial(double n) {

        if (n <= 1.0) return 1;

        else return (n * factorial(n - 1));

    }

//Calculo de C=   n!/r!(n-r)!

    public static double combinatoria(double n, double r) {

        return (factorial(n) / (factorial(r) * factorial(n - r)));

    }

    public static void showMatrix(double[][] m) {

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
