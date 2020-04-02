package Fhulano;

public class PruebaDeEstacioneriadad {
    public static void main(String args[]) {
        double[][] y = { { 7.0 }, { -5.0 }, { 2.0 }, { 4.0 }, { 5.0 }, { 7.0 }, { 2.0 }, { 6.0 }, { 4.0 }, { -2.0 },
                { -3.0 }, { 11.0 }, { 9.0 }, { 10.0 }, { -4.0 }, { -1.0 }, { 8.0 }, { 3.0 }, { -3.0 }, { 10.0 }, };

        double[][] x = { { 20.0 }, { 27.0 }, { 22.0 }, { 24.0 }, { 28.0 }, { 33.0 }, { 40.0 }, { 42.0 }, { 48.0 },
                { 52.0 }, { 50.0 }, { 47.0 }, { 58.0 }, { 67.0 }, { 77.0 }, { 73.0 }, { 72.0 }, { 80.0 }, { 83.0 },
                { 80.0 }, };

        double SRC = 0.0, STC = 0.0, SEC;
        double varianza;
        double[][] matC;
        int n = x.length;
        System.out.println(n);
        int k = 1;
        boolean imprimir = true;
        // t de tablas con 95% de confianza con 16 GL
        double ttablas = 2.1098;
        // double ftablas = 2.1098;
        double[] betas = Betas.HallarBetas(x, y, imprimir);

        // Y aproximadas
        double[][] ya = pruebaT.YAproximadas(betas, x, k, imprimir);

        /// ((Y) - (Yaproximadas)) al cuadrado = SRC
        double[][] d = pruebaT.ObtenerSRCMat(y, ya);

        SRC = pruebaT.SumatoriaSRCMat(d, false);
        STC = pruebaT.HallarSTC(y);
        SEC = STC - SRC;
        varianza = SRC / (n - k);

        pruebaT.srcstcsec(SRC, STC, SEC, imprimir);
        // System.out.println("Varianza: \t" + varianza);

        // C inversa de la multiplicacion de X transpuesta con X
        matC = pruebaT.ObtenerMatC(x);
        // MATRIZ DE VARIANZAS
        double[][] matVar = pruebaT.ObtenerMatdevarianzas(matC, varianza, imprimir);
        // Vector de VARIANZAS Bi

        double[] varBeta = pruebaT.varianzaDeBetas(matVar, varianza);

        // Prueba de significancia individual
        // double[] t = pruebaT.significanciaIndividual(varBeta, varianza, matC,
        // ttablas, imprimir);
        pruebaT.significanciaIndividual(varBeta, varianza, matC, ttablas, imprimir);
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

}