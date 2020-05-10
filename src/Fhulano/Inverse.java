package Fhulano;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Inverse {
    public static void main(String argv[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("Dimension de la Matriz");
        int n = input.nextInt();
        double a[][] = new double[n][n];
        System.out.println("Elementos de matriz ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("Elemento [" + i + "] [" + j + "]");
                a[i][j] = input.nextDouble();
            }

        }

        double d[][] = Operaciones.invert(a);
        d = arreglar(d);
        System.out.println("The inverse is: ");

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (d[i][j] == -0.0 || d[i][j] == 0.0)
                    System.out.print("0\t");
                else
                    System.out.print(d[i][j] + "\t");
            }
            System.out.println();
        }
        input.close();
    }

    private static double[][] arreglar(double[][] d) {
        DecimalFormat df = new DecimalFormat("#.00");
        double aux[][] = d;
        for (int i = 0; i < d.length; ++i) {
            for (int j = 0; j < d.length; ++j) {
                // String aux2 = d[i][j] + " ";
                // String[] aux3 = aux2.split("\\.");
                // char aux1;
                // try {
                // aux1 = aux3[1].charAt(3);
                // } catch (Exception e) {
                // aux1 = '0';
                // }
                // System.out.println(aux3[1]);
                // if (aux1 == '9') {
                // if (d[i][j] > 0) {
                // aux[i][j] = Double.parseDouble(df.format((d[i][j] + 0.01)));
                // } else {
                // aux[i][j] = Double.parseDouble(df.format((d[i][j] - 0.01)));
                // }
                // } else
                aux[i][j] = Double.parseDouble(df.format((d[i][j])));
            }
        }
        return aux;
    }

}