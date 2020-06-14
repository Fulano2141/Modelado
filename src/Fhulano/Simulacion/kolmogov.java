package Fhulano.Simulacion;

import Fhulano.Operaciones;
import Fhulano.readxlxs;

import java.text.DecimalFormat;
import java.util.Random;

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
        double menor = 1.0;
        double mayor = 4.4153;

        System.out.println("n: " + n + "\nMu: " + media + "\nVar: " + varianza + "\nNumeroInter: " + intervalos + "\nMax: " + maxNum + "\nMin: " + minNum + "\nInter: " + val + "\n");
//        double[][] frecuencias = new double[(int) Math.round(val)][6];
        double[][] frecuencias = new double[intervalos][6];
        double con = minNum;
        double temp = 0.0;
        double acum = 0.0;
        for (int i = 0; i < frecuencias.length; i++) {
            double a = con;
            con += val;
//            con += intervalos;
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
//        double rangoA = minNum;
        double _mayor = 0.0;
        double beta = getBeta(media, varianza);
        double alpha = getAlpha(beta, media);

        beta = 1.0;
        alpha = 3.0;

        for (int i = 0; i < frecuencias.length; i++) {

            double rangoB = frecuencias[i][0];
            double c = minNum;

//            while (c < rangoB) {
//                double elev = Math.pow((((c) - media) / sigma), 2);
//                valorvar = Math.pow(euler, -elev);
//                valorvar = valorvar * deltaX;
//                valorvar = valorvar / Math.sqrt(2 * pi * varianza);
//                acumulado += valorvar;
////                System.out.println(c);
////                c = c + 0.00005;
//                c++;
//            }


//            System.out.println("________");
//            rangoA = rangoB;
//            frecuencias[i][4] = acumulado;
//            frecuencias[i][4] = frecuencias[i][3] - Math.random() * 0.001;

            frecuencias[i][4] = rho(alpha) * rho(beta) / rho(alpha + beta);
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

        System.out.println("H0: Son iguales\tH1: Son diferentes\nR =>");
        if (mayor > Dtabla) {
            System.out.println("Se rechaza H0 y se acepta H1");
        } else {
            System.out.println("Se rechaza H1 y se acepta H0");
        }
    }

    private static double rho(double alpha) {
        double sum = 0.0;
        double valor = 0.0;
        do {

        } while (sum < valor);
        return factorial(alpha - 1.0);
    }

    private static double factorial(double number) {
        double aux = 1;
        for (int i = 1; i <= number; i++) {
//            aux = aux.multiply(BigInteger.valueOf(i));
            aux *= i;
        }
//        if (number == 0)
//            return 1;
//        return number * factorial(number - 1);
        return aux;
    }

    private static double getAlpha(double beta, double media) {
        return (beta * media) / (1 - media);
    }

    private static double getBeta(double media, double varianza) {
        return (media - 2 * Math.pow(media, 2) + Math.pow(media, 3) - varianza + varianza * media) / varianza;
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
