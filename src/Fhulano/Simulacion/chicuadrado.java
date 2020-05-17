package Fhulano.Simulacion;

import Fhulano.Operaciones;
import Fhulano.readxlxs;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class chicuadrado {
    public static void main(String[] args) {
        double[][] data = readxlxs.leerExcel("autos.xlsx");
        double med = getMedia(data);
        double inter = Math.sqrt(data.length);
//        med = 1.0 / med;

//        Operaciones.imprimir(data);
        double maxNumber = Operaciones.maximoNumero(data);
        double minNumber = Operaciones.minimoNumero(data);
        int k = (int) maxNumber;


        // 0 Numero
        // 1 frecuencia
        // 2 fx poisson
        // 3 ?
//        for (int i = 0; i < data.length; i++) {
//
//        }
        double pInfiniteDouble = Double.POSITIVE_INFINITY;
        double[][] classes = new double[k + 1][2];
        double sum = 0.0;
        for (int i = 0; i < classes.length; i++) {
//            if (findNumber(data, i + 1) > 0.0) {
            classes[i][0] = i;
            classes[i][1] = findNumber(data, i);
//                classes[i][2] = getFxPoisson(med, (int) classes[i][1]);
//                classes[i][3] = sum;
//                sum += classes[i][2];
//            }

        }

        classes = cleanZeros(classes);
//        Operaciones.imprimir(classes);
//        classes = Operaciones.borrarfila(classes, 0);
//        classes = Operaciones.borrarfila(classes, classes.length - 1);

        for (int i = 0; i < classes.length; i++) {
            double aux = getFxPoisson(med, (int) classes[i][0]);
            if (aux != pInfiniteDouble)
                classes[i][2] = aux;
//            classes[i][3] = poissons(med, (int) classes[i][0]);
        }

//        Operaciones.imprimir(classes, false);
//        System.out.println(med + "\n" + inter);
////        System.out.println(sum + " = Suma" + "\t" + classes.length);
//        System.out.println(getT(classes, data.length));
////        System.out.println(maxNumber + " " + minNumber);

        System.out.println(factorial(22));


    }

    private static double[][] cleanZeros(double[][] classes) {
        int con = 0;
        ArrayList<String> frec = new ArrayList<>();
        for (int i = 0; i < classes.length; i++) {
            if (classes[i][1] != 0.0) {
                frec.add(i + "-" + classes[i][1]);
                con++;
            }
        }
        System.out.println(frec.size());
        double[][] aux = new double[con][4];
        for (int i = 0; i < aux.length; i++) {
            String[] arrSplit = frec.get(i).split("-");
            aux[i][0] = Double.parseDouble(arrSplit[0]);
            aux[i][1] = Double.parseDouble(arrSplit[1]);
        }
        return aux;
    }


    public static int findNumber(double[][] data, double number) {
        int con = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i][0] == number)
                con++;
        }
        return con;
    }

    public static int findInter(double[][] data, double a, double b) {
        int con = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i][0] > a && data[i][0] < b)
                con++;
        }
        return con;
    }

    public static double getFxPoisson(double lambda, int x) {
        double val = 0.0;
        for (int i = 0; i <= x; i++) {
//            val += (Math.pow(lambda, i) / factorial(i));
        }
        return Math.pow(Math.E, -lambda) * val;
//        return (Math.pow(Math.E, -lambda) * Math.pow(lambda, x)) / factorial(x);
    }

    public static double poissons(double lambda, int x) {
        double aux = factorial(x).longValueExact();
        return (Math.pow(Math.E, -lambda) * Math.pow(lambda, x)) / aux;
    }

    private static BigInteger factorial(long number) {
        BigInteger aux = BigInteger.ONE;
        for (int i = 1; i <= number; i++) {
            aux = aux.multiply(BigInteger.valueOf(i));
//            aux *= i;
        }
//        if (number == 0)
//            return 1;
//        return number * factorial(number - 1);
        return aux;
    }


    public static double getMedia(double[][] matrix) {
        double sum = 0;
        for (double[] doubles : matrix) {
            sum += doubles[0];
        }
        DecimalFormat dec = new DecimalFormat("#.000");
        return Double.parseDouble(dec.format(sum / matrix.length));
    }

    public static double getT(double[][] matrix, int length) {
        double sum = 0.0;
//        double tamanio = 98;
        for (int i = 1; i < 60; i++) {
            sum += Math.pow(matrix[i][1] - length * matrix[i][2], 2) / (length * matrix[i][2]);
        }
        return sum;
    }


}
