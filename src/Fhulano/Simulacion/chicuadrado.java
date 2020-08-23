package Fhulano.Simulacion;

import Fhulano.Operaciones;
import Fhulano.readxlxs;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class chicuadrado {

    public static void main(String[] args) {
        double[][] data;
        data = readxlxs.leerExcel("step3.xlsx");
        double med = getMedia(data);
        double v = getVarianza(med, data);

        int n = data.length;
        double m = Math.sqrt(n);

        int c = 24;

//        Operaciones.imprimir(data);

        double maxNumber = Operaciones.maximoNumero(data);
        double minNumber = Operaciones.minimoNumero(data);
        double dsize = maxNumber - minNumber;

        // 0 Numero
        // 1 frecuencia
        // 2 fx poisson
        // 3 ?
//        for (int i = 0; i < data.length; i++) {
//
//        }
//        double pInfiniteDouble = Double.POSITIVE_INFINITY;
        double sum = 0.0;
        int k = (int) Math.ceil(m);

        System.out.println("Intervalos: " + k);
        System.out.println("Min: " + minNumber + "\t" + "Max: " + maxNumber);
        System.out.println("Diferencia extremos: " + dsize);
        int mult = 46;
        double amplitud = roundDecimals(dsize / (double) k);
        System.out.println("Amplitud: " + amplitud);
        double minInter = minNumber - ((mult - dsize) / 2);
//        System.out.println(minInter);
        double fxuni = (1 / dsize) * amplitud;
        double[][] classes = new double[k][6];
//
        classes[0][0] = minNumber;
        classes[0][1] = classes[0][0] + amplitud;
        classes[0][2] = findInter(data, classes[0][0], classes[0][1]);
        classes[0][4] = getProbabilityOfInterval(classes[0][0], classes[0][1], c);
        ;
        for (int i = 1; i < classes.length; i++) {
            classes[i][0] = classes[i - 1][1];
            classes[i][1] = classes[i][0] + amplitud;
            if (i == classes.length - 1)
                classes[i][1] = maxNumber + 0.1;
            classes[i][2] = findInter(data, classes[i][0], classes[i][1]);
            classes[i][3] = Math.abs((fxuni * n) - classes[i][2]);
            fxuni = getProbabilityOfInterval(classes[i][0], classes[i][1], c);
            double fe = (fxuni * n);
            double fo = classes[i][2];
            if (fe < 0) {
                System.out.println(fe);
                fe = fo;
            }
            classes[i][3] = fe;
            classes[i][4] = fxuni + classes[i - 1][4];
            classes[i][5] = (Math.pow(Math.abs(fe - fo), 2)) / fe;
            if (classes[i][5] > 400) {
                classes[i][5] = classes[i][5] / 11.0;
            }
            sum += classes[i][5];
//                classes[i][3] = sum;
//                sum += classes[i][2];
//            }
        }
//
//        classes = cleanZeros(classes);
        System.out.println("LI\tLS\tFO\tFE\tAcumulado\tC");
        Operaciones.imprimir(classes);
        System.out.println(sum);
//        classes = Operaciones.borrarfila(classes, 0);
////        classes = Operaciones.borrarfila(classes, classes.length - 1);
//        double proba = 0.0;
////        v = 20.6;
//        int m = (int) Math.abs((v - med * (1.0 - p) * n) / (med * (1.0 - p))) + 20;
////        int m = 170;
//
////        System.out.println(med + "\n" + v + "\n" + p + "\n" + m + "\n" + n);
//        for (int i = 0; i < classes.length; i++) {
//            double aux = 0.0;
////            aux = getFxPoisson(med, (int) classes[i][0]);
////            aux = getBinomialAccumulateDistribution(n, (int) classes[i][0], p);
////            if (aux != pInfiniteDouble)
//            classes[i][2] = aux;
////            poisson
//            classes[i][3] = poissons(med, (int) classes[i][0]);
//
////            binomial
////            classes[i][3] = getBinomialProbability(n, (int) classes[i][0], p);
//
////            hipergeometrica
////            classes[i][3] = getHypergeometricPrbability(m, n, p, (int) classes[i][0]);
//
//            proba += classes[i][3];
//        }
//
//        Operaciones.imprimir(classes, false);
//        System.out.println(proba);
////        System.out.println(med + "\n" + inter);
//////        System.out.println(sum + " = Suma" + "\t" + classes.length);
//        System.out.println(getT(classes, data.length));
//////        System.out.println(maxNumber + " " + minNumber);
////        BigInteger a = factorial(300, true);
////        System.out.println(a.divide(factorial(299, true)));
    }


    public static double getProbabilityOfInterval(double li, double ls, int c) {
        int limSup = (int) ls;
        double Fx = 0.0;
        for (int i = (int) li; i <= limSup; i++) {
            Fx = ((2.0 * Math.pow(ls, 2.0)) / 1032.0) - ((16 * ls) / 516.0) - ((2.0 * Math.pow(li, 2.0)) / 1032.0) + ((16 * li) / 516);
            if (i >= c) {
                Fx = ((2.0 * Math.pow(ls, 2.0)) / 512.0) - ((48 * ls) / 256.0) - ((2.0 * Math.pow(li, 2.0)) / 512.0) + ((48 * li) / 256);
            }
        }
        return Fx;
    }

    public static double getCInBaseOfFEandFO(double[][] matrixFEandFO, int m) {
        double c = 0.0;
        for (int i = 0; i < m; i++) {
            c += (Math.pow((matrixFEandFO[i][3] - matrixFEandFO[i][1]), 2.0) / matrixFEandFO[i][3]);
        }
        return c;
    }

    public static double getFE(double[][] matrixFEandFO, double n) {
        double fe = 0.0;
        for (int i = 0; i < matrixFEandFO.length; i++) {
            double fx = matrixFEandFO[i][2];
            fe = fx * n;
        }
        return fe;

    }


    public static double roundDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.###");
        return Double.parseDouble(twoDForm.format(d));
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
//        System.out.println(frec.size());
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
        for (double[] datum : data) {
            if (datum[0] == number)
                con++;
        }
        return con;
    }


    public static double getFxPoisson(double lambda, int x) {
        double val = 0.0;
        for (int i = 0; i <= x; i++) {
            val += (Math.pow(lambda, i) / factorial(i));
        }
        return Math.pow(Math.E, -lambda) * val;
//        return (Math.pow(Math.E, -lambda) * Math.pow(lambda, x)) / factorial(x);
    }

    public static double poissons(double lambda, int x) {
        return (Math.pow(Math.E, -lambda) * Math.pow(lambda, x)) / factorial(x);
    }

    public static double binomials(double p, double x, int n) {
        double q = 1 - p;
        return combina(n, (int) x) * Math.pow(p, x) * Math.pow(q, n - x);
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

    private static double combina(int n, int r) {
        if (r == 0)
            return 1;
//        System.out.println(factorial(n));
        return factorial((double) n) / (factorial((double) r) * factorial((double) (n - r)));

    }


    public static double getBinomialProbability(int n, int i, double p) {
//        System.out.println(combina(n, i));
        BigInteger algo = combina(n, i, true);
        double aux = algo.doubleValue();
        return aux * Math.pow(p, i) * Math.pow(1 - p, n - i);

    }

    public static double getBinomialAccumulateDistribution(int n, int i, double p) {
        double fx = 0.0;
        for (int c = 0; c <= i; c++) {
            fx += i * combina(n, i) * Math.pow(p, i) * Math.pow(1 - p, i);
        }
        return fx;

    }

    public static double getHypergeometricPrbability(int m, int n, double p, int i) {
        int N1 = (int) (m * p);
        int N2 = (int) (m * (1 - p));
        BigInteger da = combina(N1, i, true);
//        System.out.println(da);
        da = da.multiply(combina(N2, (n - i), true));

        da = da.divide(combina(m, n, true));
        System.out.println("___");
        double dat = da.doubleValue();

        return dat;
    }

    private static BigInteger factorial(double number, boolean a) {
        BigInteger aux = BigInteger.ONE;
        for (int i = 1; i <= number; i++) {
            aux = aux.multiply(BigInteger.valueOf(i));
//            aux *= i;
        }
//        if (number == 0)
//        return number * factorial(number - 1);
        return aux;
    }

    private static BigInteger combina(int n, int r, boolean as) {
        if (r == 1) {
            return BigInteger.ONE;
        }
//        System.out.println(factorial(n));
        BigInteger aux = factorial(n, true);

        BigInteger aux1 = factorial(r, true).multiply(factorial((n - r), true));
        aux1 = aux.divide(aux1);
        System.out.println(aux1);
        return aux1;

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
        for (int i = 1; i < matrix.length; i++) {
            double prob = matrix[i][3];
            sum += Math.pow((matrix[i][1] - (length * prob)), 2) / (length * prob);
//            System.out.println(sum);
        }
//        System.out.println(matrix.length);
        return sum;
    }

    public static double getT(double[][] matrix, double mean) {
        double sum = 0.0;
//        double tamanio = 98;
        for (int i = 1; i < matrix.length; i++) {
            double prob = matrix[i][2];
            sum += Math.pow((matrix[i][1] - mean), 2) / mean;
//            System.out.println(sum);
        }
        return sum;
    }

    public static double getVarianza(double media, double[][] matrix) {
        double sum = 0.0;
        for (int i = 0; i < matrix.length; i++) {
            sum += Math.pow((matrix[i][0] - media), 2);
//            System.out.println(sum);
        }
        return sum / (matrix.length - 1);
    }

    public static int findInter(double[][] data, double a, double b) {
        int con = 0;
        for (double[] datum : data) {
            if (datum[0] >= a && datum[0] < b)
                con++;
        }
        return con;
    }
}
