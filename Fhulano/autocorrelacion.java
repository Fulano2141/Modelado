package Fhulano;

public class autocorrelacion {
    public static void main(String args[]) {
        int n, k;
        boolean imprimir = true;
        double a1p, a2p, a3p, ttablas, varianza;
        double SRC;
        // double STC, SEC;
        double[] betas, varBeta;
        double[][] matC, matVar, d, ya, y, x;

        System.out.println("---------------------------------------------------------- AR simple");
        y = cargarDatos.datosARSimpleYT();
        x = cargarDatos.datosARSimpleXT();
        n = x.length;
        // double ftablas = 2.1098;
        betas = Betas.HallarBetas(x, y, false);
        SRC = 0.0;
        // STC = 0.0;
        // SEC = 0.0;
        k = x[0].length;
       
        // t de tablas con 95% de confianza
        // ttablas = 2.1448;
        // t de tablas con 90% de confianza
        ttablas = 1.7613;
        // Y aproximadas

        yuleWalker asWalker = new yuleWalker(betas[0], betas[1], betas[2]);
        System.out.println(asWalker.toString());
        double[][] gamass = asWalker.gAmas(!imprimir);
        for (int i = 0; i < gamass.length; i++) {
            for (int j = 0; j < gamass[0].length; j++) {
                System.out.println("G[" + i + "]:" + Operaciones.redondearNum(gamass[i][j]));
            }
        }
        ya = pruebaT.YAproximadas(betas, x, k, !imprimir);
        d = pruebaT.ObtenerSRCMat(y, ya);
        SRC = pruebaT.SumatoriaSRCMat(d, !imprimir);
        varianza = SRC / (n - k);
        matC = pruebaT.ObtenerMatC(x);
        matVar = pruebaT.ObtenerMatdevarianzas(matC, varianza, !imprimir);
        varBeta = pruebaT.varianzaDeBetas(matVar, varianza);
        System.out.println("GL T-Student: " + "(" + n + "-" + betas.length + ") = " + (n - k));
        pruebaT.significanciaIndividual(varBeta, varianza, matC, ttablas, imprimir);

        System.out.println("---------------------------------------------------------- AR parcial");
        imprimir = false;
        y = cargarDatos.datosDYParaDT_1();
        x = cargarDatos.datosDT_1();
        betas = Betas.HallarBetas(x, y, imprimir);
        a1p = betas[0];

        y = cargarDatos.datosDYParaDT_2();
        x = cargarDatos.datosDT_2();
        betas = Betas.HallarBetas(x, y, imprimir);
        a2p = betas[0];

        y = cargarDatos.datosDYParaDT_3();
        x = cargarDatos.datosDT_3();
        betas = Betas.HallarBetas(x, y, imprimir);
        a3p = betas[0];

        yuleWalker aWalker = new yuleWalker(a1p, a2p, a3p);
        System.out.println(aWalker.toString());

        
        double[][] gamas = aWalker.gAmas(false);
        for (int i = 0; i < gamas.length; i++) {
            for (int j = 0; j < gamas[0].length; j++) {
                System.out.println("G[" + i + "]:" + Operaciones.redondearNum(gamas[i][j]));
            }
        }

    }

}