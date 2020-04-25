package Fhulano;

public class pruebaT {
    double[][] xinput;
    double[][] yinput;

    public pruebaT(double[][] xinput, double[][] yinput) {
        this.xinput = xinput;
        this.yinput = yinput;
    }

    public double[][] getXinput() {
        return this.xinput;
    }

    public void setXinput(double[][] xinput) {
        this.xinput = xinput;
    }

    public double[][] getYinput() {
        return this.yinput;
    }

    public void setYinput(double[][] yinput) {
        this.yinput = yinput;
    }

    public void prueba(double ttabla) {
        double[][] cp = ObtenerMatC(getXinput());
        double[][] bet = Betas.HallarBetas(getXinput(), getYinput(), false);
        int n = getXinput().length;
        int k = getXinput()[0].length;
        // System.out.println("K = " + k + " N = " + n);
        double[][] yap = YAproximadas(bet, getXinput(), getYinput()[0].length, k, !true);
        double[][] srcmat = ObtenerSRCMat(getYinput(), yap);
        double src = SumatoriaSRCMat(srcmat, false);
        double var = src / (n - k);
        // System.out.println(src);
        // double[][] matvar = ObtenerMatdevarianzas(cp, var, false);
        double[] varbet = varianzaDeBetas(cp, var);
        significanciaIndividual(varbet, var, cp, ttabla, true);
    }

    @Override
    public String toString() {
        return "{" + " xinput='" + getXinput() + "'" + ", yinput='" + getYinput() + "'" + "}";
    }

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
                aux[i][j] = Math.pow(aux[i][j], 2);
            }
        }
        return aux;
    }

    public static double[][] YAproximadas(double[][] betas, double[][] x, int ysize, int k, boolean imprimir) {
        double[][] yaprox = new double[x.length][ysize];
        boolean unos = true;
        for (int i = 0; i < x.length; i++) {
            if (x[i][0] == 1.0)
                unos = true;
            else {
                unos = false;
                break;
            }
        }
        if (unos) {
            k--;
        }
        // System.out.println(x.length + "\t" + ysize);
        // for (int i = 0; i < yaprox.length; i++) {
        // for (int j = 0; j < yaprox[0].length; j++) {
        // double aux = 0.0;
        // for (int l = 0; l < x[0].length; l++) {
        // aux = aux + betas[j][l] * x[i][l];
        // }
        // yaprox[i][j] = aux;
        // }
        // }
        for (int i = 0; i < yaprox.length; i++) {
            switch (k) {
                case 1:
                    yaprox[i][0] = betas[0][0] * x[i][0] + betas[1][0] * x[i][1];
                    break;
                case 2:
                    yaprox[i][0] = betas[0][0] * x[i][0] + betas[1][0] * x[i][1] + betas[2][0] * x[i][2];
                    break;
                case 3:
                    break;
                case 4:
                    yaprox[i][0] = (x[i][0] * betas[0][0]) + (x[i][1] * betas[1][0]) + (x[i][2] * betas[2][0])
                            + (x[i][3] * betas[3][0]);
                    break;
                case 5:
                    yaprox[i][0] = (x[i][0] * betas[0][0]) + (x[i][1] * betas[1][0]) + (x[i][2] * betas[2][0])
                            + (x[i][3] * betas[3][0]) + (x[i][4] * betas[4][0]);
                    break;
                case 6:
                    yaprox[i][0] = (x[i][0] * betas[0][0]) + (x[i][1] * betas[1][0]) + (x[i][2] * betas[2][0])
                            + (x[i][3] * betas[3][0]);

                    break;
                case 7:
                    break;
            }
        }
        if (imprimir) {
            System.out.println("----------------------------------");
            System.out.println("Y aproximadas:");
            for (int i = 0; i < yaprox.length; i++) {
                // for (int j = 0; j < yaprox[0].length; j++) {
                System.out.print("y " + (i + 1) + ": \t" + Operaciones.redondearNum(yaprox[i][0]) + "\t");
                // }
                System.out.println();
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
        sum = sum / y.length;
        return sum;
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
                System.out.println(pruebas[i] + ":\t" + "t aprox " + t[i] + " ttabla " + ttablas);
            }
        }
        return t;
    }

    public static double HallarSEC(double[][] y) {

        return 0;
    }

}