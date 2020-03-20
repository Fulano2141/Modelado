package Fhulano;

import java.text.DecimalFormat;

public class testing {
    public static void main(String args[]) {
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

        d = Operaciones.restarA_B(y, ya);
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                d[i][j] = d[i][j] * d[i][j];
            }
        }
        double SRC = 0;
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                SRC += d[i][j];
            }
        }
        SRC = SRC / (y.length - 3);
        /// SRC/(n-k)
        System.out.println("Varianza: \t" + SRC);
        d = Operaciones.multiplicacionNxM(Operaciones.transpuesta(x), x);
        d = Operaciones.invert(d);
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                d[i][j] *= SRC;
            }
        }
        // MATRIZ DE VARIANZAS
        // d = redondear(d);
        // for (int i = 0; i < d.length; i++) {
        // for (int j = 0; j < d[0].length; j++) {
        // System.out.print(d[i][j] + "\t");
        // }
        // System.out.println();
        // }
        double[] varBeta = new double[d.length];
        for (int i = 0; i < d.length; i++) {
            varBeta[i] = d[i][i] * SRC;
        }
        for (int i = 0; i < d.length; i++) {
            System.out.println(varBeta[i]);
        }
    }

    private static double[][] redondear(double[][] d) {
        DecimalFormat df = new DecimalFormat("#.0000");
        double aux[][] = d;
        for (int i = 0; i < d.length; ++i) {
            for (int j = 0; j < d[0].length; ++j) {
                aux[i][j] = Double.parseDouble(df.format((d[i][j])));
            }
        }
        return aux;
    }

}