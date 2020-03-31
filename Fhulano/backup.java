package Fhulano;

import java.text.DecimalFormat;
// import Fhulano.Operaciones;

public class backup {
    // double[][] x = { { 1.0, 10.0, 3.0, 1.0 }, { 1.0, 5.0, 1.5, 1.0 }, { 1.0,
    // 10.0, 1.0, 1.0 }, { 1.0, 20.0, 2.0, 1.0 },
    // { 1.0, 15.0, 4.0, 1.0 }, { 1.0, 10.0, 2.0, 1.0 }, { 1.0, 6.0, 0.0, 0.0 }, {
    // 1.0, 25.0, 1.0, 1.0 },
    // { 1.0, 30.0, 2.0, 1.0 }, { 1.0, 10.0, 1.0, 1.0 }, { 1.0, 10.0, 1.0, 1.0 }, {
    // 1.0, 20.0, 1.0, 1.0 },
    // { 1.0, 20.0, 0.5, 1.0 }, { 1.0, 20.0, 2.0, 1.0 }, { 1.0, 30.0, 0.5, 1.0 }, {
    // 1.0, 20.0, 1.0, 0.0 },
    // { 1.0, 10.0, 1.0, 1.0 }, };

    // double[][] y = { { 72.0 }, { 63.0 }, { 65.0 }, { 68.0 }, { 78.0 }, { 64.0 },
    // { 56.0 }, { 59.0 }, { 60.0 }, { 85.0 },
    // { 67.0 }, { 61.0 }, { 65.0 }, { 79.0 }, { 61.0 }, { 52.0 }, { 65.0 } };

    public static void main(String args[]) {
        double[][] x = { { 22.0, 27.0, 20.0 }, { 24.0, 22.0, 27.0 }, { 28.0, 24.0, 22.0 }, { 33.0, 28.0, 24.0 },
                { 40.0, 33.0, 28.0 }, { 42.0, 40.0, 33.0 }, { 48.0, 42.0, 40.0 }, { 52.0, 48.0, 42.0 },
                { 50.0, 52.0, 48.0 }, { 47.0, 50.0, 52.0 }, { 58.0, 47.0, 50.0 }, { 67.0, 58.0, 47.0 },
                { 77.0, 67.0, 58.0 }, { 73.0, 77.0, 67.0 }, { 72.0, 73.0, 77.0 }, { 80.0, 72.0, 73.0 },
                { 83.0, 80.0, 72.0 }, { 80.0, 83.0, 80.0 }, };
        double[][] y = { { 24.0 }, { 28.0 }, { 33.0 }, { 40.0 }, { 42.0 }, { 48.0 }, { 52.0 }, { 50.0 }, { 47.0 },
                { 58.0 }, { 67.0 }, { 77.0 }, { 73.0 }, { 72.0 }, { 80.0 }, { 83.0 }, { 80.0 }, { 90.0 }, };

        double SRC = 0.0, STC = 0.0, SEC;
        double varianza;
        double[][] matC;
        int n = x.length;
        int k = 3;
        boolean imprimir = true;
        // t de tablas con 95% de confianza con 16 GL
        double ttablas = 2.1098;
        double ftablas = 2.1098;
        double[] betas = HallarBetas(x, y, imprimir);

        // Y aproximadas
        double[][] ya = YAproximadas(betas, x, k, imprimir);

        /// ((Y) - (Yaproximadas)) al cuadrado = SRC
        double[][] d = ObtenerSRCMat(y, ya);

        SRC = SumatoriaSRCMat(d, imprimir);
        STC = HallarSTC(y);
        SEC = STC - SRC;
        varianza = SRC / (n - k);
        // System.out.println("Varianza: \t" + varianza);

        // C inversa de la multiplicacion de X transpuesta con X
        matC = ObtenerMatC(x);
        // MATRIZ DE VARIANZAS
        double[][] matVar = ObtenerMatdevarianzas(matC, varianza, imprimir);
        // Vector de VARIANZAS Bi

        double[] varBeta = varianzaDeBetas(matVar, varianza);

        double[][] limites = Definirlimites(varBeta, ttablas, varianza, matC, imprimir);

        // Prueba de significancia individual
        double[] t = significanciaIndividual(varBeta, varianza, matC, ttablas, imprimir);

        double CuadradosMedios1 = SEC / (k - 1);
        double CuadradosMedios2 = SRC / (n - k);
        double F = CuadradosMedios1 / CuadradosMedios2;
        // H0: alphaAprox0 = alphaAprox1 = alphaAprox2 = ... = alphaAproxN = 0
        // H1: alphaAprox0 != alphaAprox1 != alphaAprox2 != ... != alphaAproxN != 0
        if (F > ftablas) {
            System.out.println("Se rechaza H0 ");
        } else {
            System.out.println("Se acepta H0 ");
        }
    }

    private static double HallarSTC(double[][] y) {
        double prom = YPromedio(y);
        double sum = 0.0;
        for (int i = 0; i < y.length; i++) {
            sum += Math.pow((y[i][0] - prom), 2);
        }
        return sum;
    }

    private static double YPromedio(double[][] y) {
        double sum = 0.0;
        for (int i = 0; i < y.length; i++) {
            sum += y[i][0];
        }
        return sum / y.length;
    }

    private static double[] significanciaIndividual(double[] varBeta, double varianza, double[][] matC, double ttablas,
            boolean imprimir) {
        if (imprimir) {
            System.out.println("----------------------------------");
            System.out.println("Prueba de significancia individual");
        }
        double[] t = new double[varBeta.length];
        for (int i = 0; i < t.length; i++) {
            t[i] = varBeta[i] / (Math.sqrt(varianza) * Math.sqrt(matC[i][i]));
            // System.out.println(redondearNum(t[i]) + "\t");
        }

        // H0: Baproxi = Bi0
        // H1: Baproxi != Bi0
        String[] pruebas = new String[varBeta.length];
        for (int i = 0; i < pruebas.length; i++) {
            if (t[i] > ttablas)
                pruebas[i] = "Se rechaza H0";
            else
                pruebas[i] = "Se rechaza H1";
            if (imprimir) {
                System.out.println(pruebas[i] + ":\t" + "t aprox " + redondearNum(t[i]) + " ttabla " + ttablas);
            }
        }
        return t;
    }

    private static double[][] Definirlimites(double[] varBeta, double ttablas, double varianza, double[][] matC,
            boolean imprimir) {
        double[][] aux = new double[varBeta.length][2];
        if (imprimir) {
            System.out.println("----------------------------------");
            System.out.println("Limites\nInferior\tSuperior");
        }
        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux[0].length; j++) {
                if (j % 2 == 0) {
                    aux[i][j] = varBeta[i] - ttablas * Math.sqrt(varianza) * Math.sqrt(matC[i][i]);
                } else {
                    aux[i][j] = varBeta[i] + ttablas * Math.sqrt(varianza) * Math.sqrt(matC[i][i]);
                }
                if (imprimir)
                    System.out.print(redondearNum(aux[i][j]) + "\t\t");
            }
            if (imprimir)
                System.out.println();

        }
        return aux;
    }

    private static double[] varianzaDeBetas(double[][] matrizVari, double Varianza) {
        double[] aux = new double[matrizVari.length];
        for (int i = 0; i < matrizVari.length; i++) {
            aux[i] = Varianza * matrizVari[i][i];
            // System.out.print(redondearNum(varBeta[i]) + "\t");
        }
        return aux;
    }

    private static double[][] ObtenerMatdevarianzas(double[][] matC, double varianza, boolean imprimir) {
        if (imprimir) {
            System.out.println("----------------------------------");
            System.out.println("Matriz de Varianzas");
        }
        for (int i = 0; i < matC.length; i++) {
            for (int j = 0; j < matC[0].length; j++) {
                matC[i][j] *= varianza;
                if (imprimir)
                    System.out.print(redondearNum(matC[i][j]) + "\t");
            }
            if (imprimir)
                System.out.println();

        }
        return matC;
    }

    private static double[][] ObtenerMatC(double[][] xmat) {
        double[][] aux = Operaciones.multiplicacionNxM(Operaciones.transpuesta(xmat), xmat);
        aux = Operaciones.invert(aux);
        return aux;
    }

    private static double SumatoriaSRCMat(double[][] srcMat, boolean imprimir) {
        int aux = 0;
        for (int i = 0; i < srcMat.length; i++) {
            for (int j = 0; j < srcMat[0].length; j++) {
                aux += srcMat[i][j];
            }
        }
        if (imprimir) {
            System.out.println("----------------------------------");
            System.out.println("SRC = " + aux);
        }
        return aux;
    }

    private static double[][] ObtenerSRCMat(double[][] y2, double[][] yaprox) {
        double[][] aux = Operaciones.restarA_B(y2, yaprox);
        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux[0].length; j++) {
                aux[i][j] = aux[i][j] * aux[i][j];
            }
        }
        return aux;
    }

    private static double[][] YAproximadas(double[] betas, double[][] x, int k, boolean imprimir) {
        double[][] yaprox = new double[x.length][1];
        for (int i = 0; i < yaprox.length; i++) {
            if (k == 1)
                yaprox[i][0] = betas[0] * x[i][0];
            if (k == 2)
                yaprox[i][0] = (x[i][0] * betas[0]) + (x[i][1] * betas[1]);
            // yaprox[i][0] = betas[0] + (x[i][1] * betas[1]) + (x[i][2] * betas[2]);
            if (k == 3)
                yaprox[i][0] = (x[i][0] * betas[0]) + (x[i][1] * betas[1]) + (x[i][2] * betas[2]);

            // yaprox[i][0] = betas[0] + (x[i][1] * betas[1]) + (x[i][2] * betas[2]) +
            // (x[i][3]
            // * betas[3]);
        }
        if (imprimir) {
            System.out.println("----------------------------------");
            System.out.println("Y aproximadas:");
            for (int i = 0; i < yaprox.length; i++) {
                for (int j = 0; j < yaprox[0].length; j++) {
                    System.out.println("y " + i + ": \t" + redondearNum(yaprox[i][j]));
                }

            }
        }

        return yaprox;

    }

    private static double[] HallarBetas(double[][] x, double[][] y, boolean imprimir) {
        double[][] daux = Operaciones.multiplicacionNxM(Operaciones.transpuesta(x), x);
        daux = Operaciones.invert(daux);
        double[][] aux = Operaciones.multiplicacionNxM(Operaciones.transpuesta(x), y);
        daux = Operaciones.multiplicacionNxM(daux, aux);
        if (imprimir) {
            daux = redondearMat(daux);
            System.out.println("----------------------------------");
            System.out.println("Matriz de Betas: ");
        }
        double[] betas = new double[daux.length];
        for (int i = 0; i < daux.length; i++) {
            for (int j = 0; j < daux[0].length; j++) {
                betas[i] = daux[i][j];
                System.out.println("B" + i + ":" + daux[i][j]);
            }
        }
        return betas;
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