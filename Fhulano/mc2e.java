package Fhulano;

import java.io.IOException;

// Minimos Cuadrados de 2 etapas
public class mc2e {
    public static void main(String[] args) throws IOException {
        double[][] pi;
        double[][] y = {{359.27, 102.96, 578.49,}, {415.76, 114.38, 650.86,}, {435.11, 118.23, 684.87,},
                {440.17, 120.45, 680.47,}, {410.66, 116.25, 642.19,}, {530.33, 140.27, 787.41,},
                {557.15, 143.84, 818.06,}, {472.80, 128.20, 712.16,}, {471.76, 126.65, 722.23,},
                {538.30, 141.05, 811.44,}, {547.76, 143.71, 816.36,}, {539.00, 142.37, 807.78,},
                {677.6, 173.13, 983.53,}, {943.85, 223.21, 1292.99,}, {893.42, 198.64, 1179.64,},
                {871.00, 191.89, 1134.78,}, {793.93, 181.27, 1053.16,},
                {850.36, 180.56, 1085.91,}, {967.42, 208.24, 1246.99,},
                {1102.61, 235.43, 1401.94,},};
        double[][] x = {{10.0, 3.06, 1.34, 8.48, 28.0}, {15.0, 3.19, 1.44, 9.16, 35.0},
                {20.0, 3.3, 1.54, 9.9, 37.0}, {25.0, 3.4, 1.71, 11.02, 36.0},
                {34.0, 3.48, 1.89, 11.64, 29.0}, {27.0, 3.6, 1.99, 12.73, 47.0},
                {28.0, 3.68, 2.22, 13.88, 50.0}, {40.0, 3.72, 2.43, 14.5, 35.0},
                {37.0, 3.92, 2.43, 15.47, 33.0}, {36.0, 4.15, 2.31, 16.61, 40.0},
                {32.0, 4.35, 2.39, 17.4, 38.0}, {30.0, 4.37, 2.63, 18.83, 37.0},
                {36.0, 4.59, 2.69, 20.62, 56.0}, {41.0, 5.23, 3.35, 23.76, 88.0},
                {45.0, 6.04, 2.81, 26.52, 62.0}, {48.0, 6.36, 3.38, 27.45, 51.0},
                {49.0, 7.04, 3.14, 30.28, 29.0}, {53.0, 7.81, 3.14, 25.4, 22.0},
                {57.0, 8.09, 6.19, 28.84, 38.0}, {66.0, 9.24, 6.69, 34.36, 41.0}};
        // pruebasEsta(x, y);
        double[][] xt = Operaciones.transpuesta(x);
        pi = Operaciones.invert(Operaciones.multiplicacionNxM(xt, x));
        pi = Operaciones.multiplicacionNxM(pi, Operaciones.multiplicacionNxM(xt, y));

        double[][] yestimados = Operaciones.multiplicacionNxM(x, pi);

        pruebasT(x, y, yestimados, "");
        // double[][] er = Operaciones.restarA_B(y, yestimados);
        // double sum = 0.0;
        for (int i = 0; i < yestimados.length; i++) {
            for (int j = 0; j < yestimados[0].length; j++) {
                // sum += er[i][j];
                System.out.print(yestimados[i][j] + "\t");
            }
            System.out.println();
        }
        // System.out.println(sum);

        // double[][] y1 = { { 359.27 }, { 415.76 }, { 435.11 }, { 440.17 }, { 410.66 },
        // { 530.33 }, { 557.15 },
        // { 472.80 }, { 471.76 }, { 538.30 }, { 547.76 }, { 539.00 }, { 677.6 }, {
        // 943.85 },
        // { 893.42 }, { 871.00 }, { 793.93 }, { 850.36 }, { 967.42 }, { 1102.61 } };
        // // w0 , x1, x3 , x4, y2, y3
        // double[][] x1 = { { 1.0, 10.0, 1.34, 8.48, 98.9956, 577.2675 },
        // { 1.0, 15.0, 1.44, 9.16, 112.7750, 648.3323 },
        // { 1.0, 20.0, 1.54, 9.9, 118.7574, 677.8518 },
        // { 1.0, 25.0, 1.71, 11.02, 119.7986, 682.3415 },
        // { 1.0, 34.0, 1.89, 11.64, 113.0597, 639.2604 },
        // { 1.0, 27.0, 1.99, 12.73, 139.1570, 791.0645 },
        // { 1.0, 28.0, 2.22, 13.88, 144.4618, 823.2056 },
        // { 1.0, 40.0, 2.43, 14.5, 125.9617, 713.7249 },
        // { 1.0, 37.0, 2.43, 15.47, 125.5909, 719.9472 },
        // { 1.0, 36.0, 2.31, 16.61, 139.6420, 800.5668 },
        // { 1.0, 32.0, 2.39, 17.4, 139.0219, 806.6671 },
        // { 1.0, 30.0, 2.63, 18.83, 136.1869, 799.1868 },
        // { 1.0, 36.0, 2.69, 20.62, 169.0255, 973.9971 },
        // { 1.0, 41.0, 3.35, 23.76, 227.6729, 1298.7739 },
        // { 1.0, 45.0, 2.81, 26.52, 205.7611, 1190.0791 },
        // { 1.0, 48.0, 3.38, 27.45, 195.6917, 1142.0951 },
        // { 1.0, 49.0, 3.14, 30.28, 175.8074, 1048.2664 },
        // { 1.0, 53.0, 3.14, 25.4, 185.4952, 1091.1394 },
        // { 1.0, 57.0, 6.19, 28.84, 210.2461, 1247.5536 },
        // { 1.0, 66.0, 6.69, 34.36, 236.3250, 1404.9325 } };
        // double[][] betas1 = Betas.HallarBetas(x1, y1, true);

        // double[][] y2 = { { 102.96 }, { 114.38 }, { 118.23 }, { 120.45 }, { 116.25,
        // }, { 140.27 }, { 143.84, },
        // { 128.20, }, { 126.65, }, { 141.05 }, { 143.71, }, { 142.37, }, { 173.13, },
        // { 223.21, }, { 198.64, }, { 191.89, }, { 181.27, }, { 180.56, }, { 208.24, },
        // { 235.43, }, };
        // // w5 , x5, y1
        // double[][] x2 = { { 1.0, 28.0, yestimados[0][0] }, { 1.0, 35.0,
        // yestimados[1][0] },
        // { 1.0, 37.0, yestimados[2][0] }, { 1.0, 36.0, yestimados[3][0] },
        // { 1.0, 29.0, yestimados[4][0] }, { 1.0, 47.0, yestimados[5][0] },
        // { 1.0, 50.0, yestimados[6][0] }, { 1.0, 35.0, yestimados[7][0] },
        // { 1.0, 33.0, yestimados[8][0] }, { 1.0, 40.0, yestimados[9][0] },
        // { 1.0, 38.0, yestimados[10][0] }, { 1.0, 37.0, yestimados[11][0] },
        // { 1.0, 56.0, yestimados[12][0] }, { 1.0, 88.0, yestimados[13][0] },
        // { 1.0, 62.0, yestimados[14][0] }, { 1.0, 51.0, yestimados[15][0] },
        // { 1.0, 29.0, yestimados[16][0] }, { 1.0, 22.0, yestimados[17][0] },
        // { 1.0, 38.0, yestimados[18][0] }, { 1.0, 41.0, yestimados[19][0] } };

        // double[][] betas2 = Betas.HallarBetas(x2, y2, true);

        // double[][] y3 = { { 578.49, }, { 650.86, }, { 684.87, }, { 680.47, }, {
        // 642.19, }, { 787.41, },
        // { 818.06, }, { 712.16, }, { 722.23, }, { 811.44, }, { 816.36, }, { 807.78, },
        // { 983.53, }, { 1292.99, }, { 1179.64, }, { 1134.78, }, { 1053.16, }, {
        // 1085.91, },
        // { 1246.99, }, { 1401.94, }, };
        // // w6, x5, x2, y2, y1
        // double[][] x3 = { { 1.0, 28.0, 3.06, yestimados[0][1], yestimados[0][0] },
        // { 1.0, 35.0, 3.19, yestimados[1][1], yestimados[1][0] },
        // { 1.0, 37.0, 3.3, yestimados[2][1], yestimados[2][0] },
        // { 1.0, 36.0, 3.4, yestimados[3][1], yestimados[3][0] },
        // { 1.0, 29.0, 3.48, yestimados[4][1], yestimados[4][0] },
        // { 1.0, 47.0, 3.6, yestimados[5][1], yestimados[5][0] },
        // { 1.0, 50.0, 3.68, yestimados[6][1], yestimados[6][0] },
        // { 1.0, 35.0, 3.72, yestimados[7][1], yestimados[7][0] },
        // { 1.0, 33.0, 3.92, yestimados[8][1], yestimados[8][0] },
        // { 1.0, 40.0, 4.15, yestimados[9][1], yestimados[9][0] },
        // { 1.0, 38.0, 4.35, yestimados[10][1], yestimados[10][0] },
        // { 1.0, 37.0, 4.37, yestimados[11][1], yestimados[11][0] },
        // { 1.0, 56.0, 4.59, yestimados[12][1], yestimados[12][0] },
        // { 1.0, 88.0, 5.23, yestimados[13][1], yestimados[13][0] },
        // { 1.0, 62.0, 6.04, yestimados[14][1], yestimados[14][0] },
        // { 1.0, 51.0, 6.36, yestimados[15][1], yestimados[15][0] },
        // { 1.0, 29.0, 7.04, yestimados[16][1], yestimados[16][0] },
        // { 1.0, 22.0, 7.81, yestimados[17][1], yestimados[17][0] },
        // { 1.0, 38.0, 8.09, yestimados[18][1], yestimados[18][0] },
        // { 1.0, 41.0, 9.24, yestimados[19][1], yestimados[19][0] } };
        // double[][] betas3 = Betas.HallarBetas(x3, y3, true);

    }

    private static void pruebasT(double[][] x, double[][] y, double[][] pi, String dat) {

        double[][] cp = pruebaT.ObtenerMatC(x);
        // double[][] bet = Betas.HallarBetas(x, y, false);
        int n = x.length;
        int k = x[0].length;
        System.out.println("K = " + k + " N = " + n);
        double[][] yap = pi;
        double[][] srcmat = pruebaT.ObtenerSRCMat(y, yap);
        double src = pruebaT.SumatoriaSRCMat(srcmat, true);
        double var = src / n - k;
        // double[][] matvar = ObtenerMatdevarianzas(cp, var, false);
        double[] varbet = pruebaT.varianzaDeBetas(cp, var);

        pruebaT.significanciaIndividual(varbet, var, cp, 2.1315, true, dat);
    }

    public static void pruebasEsta(double[][] x, double[][] y) {
        double[][] y1 = new double[x.length][1], y2 = new double[x.length][1], y3 = new double[x.length][1],
                x1 = new double[x.length][1], x2 = new double[x.length][1],
                x3 = new double[x.length][1], x4 = new double[x.length][1],
                x5 = new double[x.length][1];
        for (int i = 0; i < y1.length; i++) {
            y1[i][0] = y[i][0];
        }
        for (int i = 0; i < y2.length; i++) {
            y2[i][0] = y[i][1];
        }
        for (int i = 0; i < y3.length; i++) {
            y3[i][0] = y[i][2];
        }
        for (int i = 0; i < x1.length; i++) {
            x1[i][0] = x[i][0];
        }
        for (int i = 0; i < x2.length; i++) {
            x2[i][0] = x[i][1];
        }
        for (int i = 0; i < x3.length; i++) {
            x3[i][0] = x[i][2];
        }
        for (int i = 0; i < x4.length; i++) {
            x4[i][0] = x[i][3];
        }
        for (int i = 0; i < x5.length; i++) {
            x5[i][0] = x[i][4];
        }
        double ttablas = 2.1009;
        PruebaDeEstacioneriedad pr = new PruebaDeEstacioneriedad(x1, 0.0);
        pr.setX(y1);
        pr.setTtablas(ttablas);
        pr.prueba("");
        pr.setX(y2);
        pr.setTtablas(ttablas);
        pr.prueba("");
        pr.setX(y3);
        pr.setTtablas(ttablas);
        pr.prueba("");

        // pr.setX(x1);
        // pr.setTtablas(ttablas);
        // pr.prueba();
        // pr.setX(x2);
        // pr.setTtablas(ttablas);
        // pr.prueba();
        // pr.setX(x3);
        // pr.setTtablas(ttablas);
        // pr.prueba();
        // pr.setX(x4);
        // pr.setTtablas(ttablas);
        // pr.prueba();
        // pr.setX(x5);
        // pr.setTtablas(ttablas);
        // pr.prueba();

    }

}