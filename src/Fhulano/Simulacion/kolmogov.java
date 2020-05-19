package Fhulano.Simulacion;

import Fhulano.Operaciones;
import Fhulano.readxlxs;

import java.text.DecimalFormat;

public class kolmogov {

    public static void main(String[] args) {
        DecimalFormat dec = new DecimalFormat("#.0000");
        double[][] data = readxlxs.leerExcel("aulaK.xlsx");
        int n = data.length;
        double media = getMedia(data);
        double varianza = getVarianza(data, media);
        int intervalos = (int) Math.round(Math.sqrt(n));
        double maxNum = Operaciones.maximoNumero(data);
        double minNum = Operaciones.minimoNumero(data);
        double val = Double.parseDouble(dec.format((maxNum - minNum) / ((double) intervalos)));


        System.out.println("n: " + n + "\nMu: " + media + "\nVar: " + varianza + "\nNumeroInter: " + intervalos + "\nMax: " + maxNum + "\nMin: " + minNum + "\nInter: " + val + "\n");
        double[][] frecuencias = new double[(int) Math.round(val)][6];
//        double[][] frecuencias = new double[intervalos][6];
        double con = minNum;
        double temp = 0.0;
        double acum = 0.0;
        for (int i = 0; i < frecuencias.length; i++) {
            double a = con;
//            con += val;
            con += intervalos;
//            for (int j = 0; j < frecuencias.length; j++) {
            frecuencias[i][0] = Double.parseDouble(dec.format(con));
            frecuencias[i][1] = findInter(data, a, con);
            acum += frecuencias[i][1];
            frecuencias[i][2] = acum;
//            frecuencias[i][3] = acum / (n - 1);
            frecuencias[i][3] = acum / n;
//            }
//            temp += frecuencias[i][1];
        }

//        System.out.println(temp);

        double sigma = Math.sqrt(varianza);
        double euler = Math.E;
        double pi = Math.PI;
        double acumulado = 0.0;
        double deltaX = (6 * sigma) / n;
        double valorvar = 0.0;
        double rangoA = minNum;
        double menor = 1.0;
        double mayor = 0.0;
        for (int i = 0; i < frecuencias.length; i++) {

            double rangoB = frecuencias[i][0];
            double c = minNum;
//            while (acumulado < numaleatorio) {
            while (c < rangoB) {

                double elev = Math.pow((((c * deltaX) - media) / sigma), 2);

                valorvar = Math.pow(euler, -elev);
                valorvar = valorvar * deltaX;
                valorvar = valorvar * (1 / Math.sqrt(2 * pi * Math.pow(sigma, 2)));
                acumulado += valorvar;
//                System.out.println(c);
                c = c + 0.00002;
            }
//            System.out.println("________");
            rangoA = rangoB;
            frecuencias[i][4] = acumulado;
            frecuencias[i][5] = Math.abs(frecuencias[i][4] - frecuencias[i][3]);
            if (frecuencias[i][5] < menor)
                menor = frecuencias[i][5];
            if (frecuencias[i][5] > mayor)
                mayor = frecuencias[i][5];
        }
        Operaciones.imprimir(frecuencias, false);
        System.out.println("----");
        System.out.println("Min: " + dec.format(menor) + "\tMax: " + dec.format(mayor));
//        Operaciones.imprimir(frecuencias, true);
        // 1 - x = 0.95
        double Dtabla = 1.64;

        System.out.println("H0: Son iguales\nH1: Son diferentes");
        if (mayor > Dtabla) {
            System.out.println("Se rechaza H0 y se acepta H1");
        } else {
            System.out.println("Se rechaza H1 y se acepta H0");
        }

    }

    public static int findInter(double[][] data, double a, double b) {
        int con = 0;
        for (double[] datum : data) {
            if (datum[0] >= a && datum[0] < b)
                con++;
        }
        return con;
    }

    public static double getMedia(double[][] matrix) {
        double sum = 0;
        for (double[] doubles : matrix) {
            sum += doubles[0];
        }
        DecimalFormat dec = new DecimalFormat("#.0000");
        return Double.parseDouble(dec.format(sum / matrix.length));
    }

    public static double getVarianza(double[][] matrix, double media) {
        double sum = 0.0;
        for (int i = 0; i < matrix.length; i++) {
            sum += Math.pow((matrix[i][0] - media), 2);
        }
        DecimalFormat dec = new DecimalFormat("#.0000");
        return Double.parseDouble(dec.format(sum / (matrix.length - 1)));
    }
}
