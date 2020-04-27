package Fhulano;

// Prueba de raiz unitaria
public class PruebaDeEstacioneriedad {
    double[][] x;
    double ttablas;
    double[][] betasCal;

    public void setBetasCal(double[][] betasCal) {
        this.betasCal = betasCal;
    }

    public double[][] getBetasCal() {
        return betasCal;
    }

    // double[][] x = { { 20.0 }, { 27.0 }, { 22.0 }, { 24.0 }, { 28.0 }, { 33.0 },
    // { 40.0 }, { 42.0 }, { 48.0 },
    // { 52.0 }, { 50.0 }, { 47.0 }, { 58.0 }, { 67.0 }, { 77.0 }, { 73.0 }, { 72.0
    // }, { 80.0 }, { 83.0 },
    // { 80.0 }, };
    // double[][] y = { { 7.0 }, { -5.0 }, { 2.0 }, { 4.0 }, { 5.0 }, { 7.0 }, { 2.0
    // }, { 6.0 }, { 4.0 }, { -2.0 },
    // { -3.0 }, { 11.0 }, { 9.0 }, { 10.0 }, { -4.0 }, { -1.0 }, { 8.0 }, { 3.0 },
    // { -3.0 }, { 10.0 }, };
    public PruebaDeEstacioneriedad(double[][] x, double ttablas) {
        this.x = x;
        this.ttablas = ttablas;
    }

    public double[][] getX() {
        return this.x;
    }

    public void setX(double[][] x) {
        this.x = x;
    }

    public double getTtablas() {
        return this.ttablas;
    }

    public void setTtablas(double ttablas) {
        this.ttablas = ttablas;
    }

    @Override
    public String toString() {
        return "{" + " x='" + getX() + "'" + ",\n t de tablas = '" + getTtablas() + "'" + "}";
    }

    public void prueba(String pruebaD) {
//        double[][] x = getX();
        double[][] y = new double[x.length - 1][x[0].length];
        for (int i = 0; i < y.length; i++) {
            for (int j = 0; j < y[0].length; j++) {
                y[i][j] = x[i + 1][j] - x[i][j];
            }
        }
        setX(Operaciones.borrarfila(x, x.length - 1));
        double SRC = 0.0, STC = 0.0, SEC;
        double varianza;
        double[][] matC;
        int n = getX().length;
        int k = getX()[0].length;
        double[][] betas = Betas.HallarBetas(x, y, !true);
        setBetasCal(betas);
        // double ftablas = 2.1098;
        // Y aproximadas
//        double[][] ya = pruebaT.YAproximadas(betas, x, y[0].length, k, !true);
        /// ((Y) - (Yaproximadas)) al cuadrado = SRC
//        double[][] d = pruebaT.ObtenerSRCMat(y, ya);
        readtest(pruebaD);
//        SRC = pruebaT.SumatoriaSRCMat(d, false);
//        STC = pruebaT.HallarSTC(y);
//        SEC = STC - SRC;
//        varianza = SRC / (n - k);
//
//        pruebaT.srcstcsec(SRC, STC, SEC, !true);
//        // System.out.println("Varianza: \t" + varianza);
//
//        // C inversa de la multiplicacion de X transpuesta con X
//        matC = pruebaT.ObtenerMatC(x);
//        // MATRIZ DE VARIANZAS
//        double[][] matVar = pruebaT.ObtenerMatdevarianzas(matC, varianza, !true);
//        // Vector de VARIANZAS Bi
//
//        double[] varBeta = pruebaT.varianzaDeBetas(matVar, varianza);
//
//        // Prueba de significancia individual
//        // double[] t = pruebaT.significanciaIndividual(varBeta, varianza, matC,
//        // ttablas, true);
//        pruebaT.significanciaIndividual(varBeta, varianza, matC, getTtablas(), !true);
        // System.out.println("----------------------------------");
        // System.out.println("Prueba Fisher");
        // // H0: alphaAprox0 = alphaAprox1 = alphaAprox2 = ... = alphaAproxN = 0
        // // H1: alphaAprox0 != alphaAprox1 != alphaAprox2 != ... != alphaAproxN != 0
        // double CuadradosMedios1 = SEC / (k - 1);
        // double CuadradosMedios2 = SRC / (n - k);
        // double F = CuadradosMedios1 / CuadradosMedios2;
        // if (F > ftablas) {
        // System.out.println("Se rechaza H0 ");
        // } else {
        // System.out.println("Se acepta H0 ");
        // }
    }

    private void readtest(String da) {
        System.out.println("________________________________________");
        System.out.println(da);
        System.out.println("Resultado: "+ getBetasCal()[0][0]);
    }



    public void imprimirmat(double[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }
    }

}