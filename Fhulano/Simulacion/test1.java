package Fhulano.Simulacion;

import java.util.Scanner;

public class test1 {

    double e = 2.7182818284590452353602874713527;

    // 100 num ale -5 y 10
    //
    public static void main(String[] args) {
        double[][] t = new double[100][1];
        // double[][] prob;
        // double acum = 0.0;
        // double ran = Math.random();
        // int valorEntero = (int) (Math.random() * (N - M + 1) + M);
        Scanner entrada = new Scanner(System.in);
        int a = -5, b = 10;
        double meu = entrada.nextDouble();
        double lamda = 1 / meu;
        for (int i = 0; i < t.length; i++) {
            // t[i][0] = (Math.random() * (b - a) + a);
            t[i][0] = getNext(lamda);
        }
        for (int i = 0; i < t.length; i++) {
            // t[i][0] = a + t[i][0] * (b - a);
            System.out.println(t[i][0]);
        }

    }

    public static double getNext(double lamda) {
        return -lamda * Math.log(Math.random());
        // return Math.log(1 - rand.nextDouble()) / (-lambda);
    }
}