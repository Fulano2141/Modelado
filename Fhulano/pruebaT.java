package Fhulano;

public class pruebaT {
    public static double[][] Definirlimites(double[] varBeta, double ttablas, double varianza, double[][] matC,
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
                    System.out.print(Operaciones.redondearNum(aux[i][j]) + "\t\t");
            }
            if (imprimir)
                System.out.println();

        }
        return aux;
    }

    public static double[] varianzaDeBetas(double[][] matrizVari, double Varianza) {
        double[] aux = new double[matrizVari.length];
        for (int i = 0; i < matrizVari.length; i++) {
            aux[i] = Varianza * matrizVari[i][i];
            // System.out.print(redondearNum(varBeta[i]) + "\t");
        }
        return aux;
    }

    public static double[][] ObtenerMatdevarianzas(double[][] matC, double varianza, boolean imprimir) {
        if (imprimir) {
            System.out.println("----------------------------------");
            System.out.println("Matriz de Varianzas");
        }
        for (int i = 0; i < matC.length; i++) {
            for (int j = 0; j < matC[0].length; j++) {
                matC[i][j] *= varianza;
                if (imprimir)
                    System.out.print(Operaciones.redondearNum(matC[i][j]) + "\t");
            }
            if (imprimir)
                System.out.println();

        }
        return matC;
    }

    public static double[][] ObtenerMatC(double[][] xmat) {
        double[][] aux = Operaciones.multiplicacionNxM(Operaciones.transpuesta(xmat), xmat);
        aux = Operaciones.invert(aux);
        return aux;
    }

    public static double SumatoriaSRCMat(double[][] srcMat, boolean imprimir) {
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

    public static double[][] ObtenerSRCMat(double[][] y2, double[][] yaprox) {
        double[][] aux = Operaciones.restarA_B(y2, yaprox);
        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux[0].length; j++) {
                aux[i][j] = aux[i][j] * aux[i][j];
            }
        }
        return aux;
    }

    public static double[][] YAproximadas(double[] betas, double[][] x, int k, boolean imprimir) {
        double[][] yaprox = new double[x.length][1];
        boolean unos = true;
        for (int i = 0; i < x.length; i++) {
            if (x[i][0] == 1.0)
                unos = true;
            else {
                unos = false;
                break;
            }
        }
        // System.out.println(unos);
        for (int i = 0; i < yaprox.length; i++) {
            if (k == 1) {
                if (unos)
                    yaprox[i][0] = betas[0] + (betas[1] * x[i][0]);
                else
                    yaprox[i][0] = betas[0] * x[i][0];
            }
            if (k == 2) {
                if (unos)
                    yaprox[i][0] = betas[0] + (x[i][1] * betas[1]) + (x[i][2] * betas[2]);
                else
                    yaprox[i][0] = (x[i][0] * betas[0]) + (x[i][1] * betas[1]);
            }

            if (k == 3) {
                if (unos)
                    yaprox[i][0] = betas[0] + (x[i][1] * betas[1]) + (x[i][2] * betas[2]) + (x[i][3] * betas[3]);
                else
                    yaprox[i][0] = (x[i][0] * betas[0]) + (x[i][1] * betas[1]) + (x[i][2] * betas[2]);
            }

            // yaprox[i][0] = betas[0] + (x[i][1] * betas[1]) + (x[i][2] * betas[2]) +
            // (x[i][3]
            // * betas[3]);
            // yaprox[i][0] = betas[0] + (x[i][1] * betas[1]) + (x[i][2] * betas[2]);
        }
        if (imprimir) {
            System.out.println("----------------------------------");
            System.out.println("Y aproximadas:");
            for (int i = 0; i < yaprox.length; i++) {
                for (int j = 0; j < yaprox[0].length; j++) {
                    System.out.println("y " + i + ": \t" + Operaciones.redondearNum(yaprox[i][j]));
                }

            }
        }

        return yaprox;

    }

    public static void srcstcsec(double sRC, double sTC, double sEC, boolean imprimir) {
        if (imprimir) {
            System.out.println("----------------------------------");
            System.out.println("SRC: " + sRC);
            System.out.println("STC: " + sTC);
            System.out.println("SEC: " + sEC);
        }
    }

    public static double HallarSTC(double[][] y) {
        double prom = YPromedio(y);
        double sum = 0.0;
        for (double[] ds : y) {
            sum += Math.pow((ds[0] - prom), 2);
        }
        return sum;
    }

    public static double YPromedio(double[][] y) {
        double sum = 0.0;
        for (int i = 0; i < y.length; i++) {
            sum += y[i][0];
        }
        return sum / y.length;
    }

    public static double[] significanciaIndividual(double[] varBeta, double varianza, double[][] matC, double ttablas,
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
                System.out.println(
                        pruebas[i] + ":\t" + "t aprox " + Operaciones.redondearNum(t[i]) + " ttabla " + ttablas);
            }
        }
        return t;
    }

}