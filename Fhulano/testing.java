package Fhulano;

import java.text.DecimalFormat;
import Fhulano.Operaciones;

public class testing {
    public static void main(String args[]) {
        double SRC = 0.0;
        double varianza;
        double[][] matC;

        // double[][] x = new double[3][3];
        double[][] x = { { 1.0, 10.0, 3.0, 1.0 }, { 1.0, 5.0, 1.5, 1.0 }, { 1.0, 10.0, 1.0, 1.0 },
                { 1.0, 20.0, 2.0, 1.0 }, { 1.0, 15.0, 4.0, 1.0 }, { 1.0, 10.0, 2.0, 1.0 }, { 1.0, 6.0, 0.0, 0.0 },
                { 1.0, 25.0, 1.0, 1.0 }, { 1.0, 30.0, 2.0, 1.0 }, { 1.0, 10.0, 1.0, 1.0 }, { 1.0, 10.0, 1.0, 1.0 },
                { 1.0, 20.0, 1.0, 1.0 }, { 1.0, 20.0, 0.5, 1.0 }, { 1.0, 20.0, 2.0, 1.0 }, { 1.0, 30.0, 0.5, 1.0 },
                { 1.0, 20.0, 1.0, 0.0 }, { 1.0, 10.0, 1.0, 1.0 }, };

        // double[][] y = new double[3][3];
        double[][] y = { { 72.0 }, { 63.0 }, { 65.0 }, { 68.0 }, { 78.0 }, { 64.0 }, { 56.0 }, { 59.0 }, { 60.0 },
                { 85.0 }, { 67.0 }, { 61.0 }, { 65.0 }, { 79.0 }, { 61.0 }, { 52.0 }, { 65.0 } };

        // double[][] d = x;
        double[][] d = Operaciones.multiplicacionNxM(Operaciones.transpuesta(x), x);
        d = Operaciones.invert(d);
        double[][] aux = Operaciones.multiplicacionNxM(Operaciones.transpuesta(x), y);
        d = Operaciones.multiplicacionNxM(d, aux);
        // d = redondear(d);
        double[] betas = new double[d.length];
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                betas[i] = d[i][j];
                // System.out.print(d[i][j] + "\t");
            }
            // System.out.println();
        }

        // System.out.println(Arrays.deepToString(betas));
        // for (int i = 0; i < betas.length; i++) {
        // System.out.println("B" + i + ": " + betas[i]);
        // }

        // Y aproximadas
        double[][] ya = new double[y.length][1];
        for (int i = 0; i < ya.length; i++) {
            ya[i][0] = betas[0] + (x[i][1] * betas[1]) + (x[i][2] * betas[2]) + (x[i][3] * betas[3]);
        }

        // for (int i = 0; i < ya.length; i++) {
        // for (int j = 0; j < ya[0].length; j++) {
        // System.out.println("y " + i + ": \t" + ya[i][j]);
        // }
        // if (ya[0].length == 0)
        // System.out.println();
        // }

        /// Y menos Y aproximadas al cuadrado _ SRC
        d = Operaciones.restarA_B(y, ya);
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                d[i][j] = d[i][j] * d[i][j];
            }
        }

        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                SRC += d[i][j];
            }
        }
        varianza = SRC / (y.length - 3);
        // System.out.println("Varianza: \t" + varianza);

        // MATRIZ DE VARIANZAS
        // C inversa de la multiplicacion de X transpuesta con X
        matC = Operaciones.multiplicacionNxM(Operaciones.transpuesta(x), x);
        matC = Operaciones.invert(matC);
        // matC = redondearMat(matC);
        for (int i = 0; i < matC.length; i++) {
            for (int j = 0; j < matC[0].length; j++) {
                matC[i][j] *= varianza;
                // System.out.print(redondearNum(matC[i][j]) + "\t");
            }
            // System.out.println();
        }
        // Vector de VARIANZAS Bi
        double[] varBeta = new double[matC.length];
        for (int i = 0; i < matC.length; i++) {
            varBeta[i] = varianza * matC[i][i];
            // System.out.print(redondearNum(varBeta[i]) + "\t");
        }
        double[][] limites = new double[varBeta.length][2];

        // t de tablas con 95% de confianza con 16 GL
        double ttablas = 2.1199;

        for (int i = 0; i < limites.length; i++) {
            for (int j = 0; j < limites[0].length; j++) {
                if (j % 2 == 0) {
                    limites[i][j] = varBeta[i] - ttablas * Math.sqrt(varianza) * Math.sqrt(matC[i][i]);
                } else {
                    limites[i][j] = varBeta[i] + ttablas * Math.sqrt(varianza) * Math.sqrt(matC[i][i]);
                }
                // System.out.print(limites[i][j]+ "\t");
            }
            // System.out.println();
        }

        // Prueba de significacia individual
        double[] t = new double[varBeta.length];
        for (int i = 0; i < t.length; i++) {
            t[i] = varBeta[i] / (Math.sqrt(varianza) * Math.sqrt(matC[i][i]));
            System.out.println(redondearNum(t[i]) + "\t");
        }

        // H0: Baproxi = Bi0
        // H1: Baproxi != Bi0
        String[] pruebas = new String[varBeta.length];
        for (int i = 0; i < pruebas.length; i++) {
            if (t[i] > ttablas)
                pruebas[i] = "Se rechaza H0";
            else
                pruebas[i] = "Se rechaza H1";
            System.out.println(pruebas[i] + ":\t"+"t aprox "+ redondearNum(t[i]) + " t tabla "+ ttablas);
        }
    }

    private static double[][] redondearMat(double[][] d) {
        DecimalFormat df = new DecimalFormat("#.0000");
        double aux[][] = d;
        for (int i = 0; i < d.length; ++i) {
            for (int j = 0; j < d[0].length; ++j) {
                aux[i][j] = Double.parseDouble(df.format((d[i][j])));
            }
        }
        return aux;
    }

    private static double redondearNum(double d) {
        double aux;
        DecimalFormat df = new DecimalFormat("#.0000");
        aux = Double.parseDouble(df.format((d)));
        return aux;
    }

}