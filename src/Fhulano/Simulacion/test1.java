package Fhulano.Simulacion;

import java.util.Scanner;

public class test1 {

    // 100 num ale -5 y 10
    //
    public static void main(String[] args) {
//        double[][] t = new double[100][1];
//        // double[][] prob;
//        // double acum = 0.0;
//        // double ran = Math.random();
//        // int valorEntero = (int) (Math.random() * (N - M + 1) + M);
//        Scanner entrada = new Scanner(System.in);
//        // int a = -5, b = 10;
//        // double meu = entrada.nextDouble();
//        // double lamda = 1 / meu;
//        System.out.println("Alpha");
//        double alp = entrada.nextDouble();
//        System.out.println("Beta");
//        double bet = entrada.nextDouble();
//        for (int i = 0; i < t.length; i++) {
//            // t[i][0] = (Math.random() * (b - a) + a);
//            t[i][0] = getNextExp(alp, bet);
//        }
//        for (int i = 0; i < t.length; i++) {
//            // t[i][0] = a + t[i][0] * (b - a);
//            System.out.println(t[i][0]);
//        }
//        entrada.close();

        double media = 265.0261;
        double varianza = 3350.4407;

        double beta = getBeta(media, varianza);
        double alpha = getAlpha(beta, media);

        System.out.println(alpha + "\n" + beta);
    }

    private static double getAlpha(double beta, double media) {
        return (beta * media) / (1 - media);
    }

    private static double getBeta(double media, double varianza) {
        return (media - 2 * Math.pow(media, 2) + Math.pow(media, 3) - varianza + varianza * media) / varianza;
    }

    public static double getNext(double lamda) {
        return -lamda * Math.log(1 - Math.random());
        // return Math.log(1 - rand.nextDouble()) / (-lambda);
    }

    public static double getNextExp(double alpha, double beta) {
        return Math.pow(-Math.log(Math.random()), 1 / alpha);
    }
}