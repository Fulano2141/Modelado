package Fhulano.Simulacion;

import java.util.Scanner;

public class binomial {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        n= 10 p 0.4
//        int i = 10; // = n
//        for (; i > 0; i--) {
        System.out.println("asd " + binomial(10, 0.4));
//        }
        in.close();
    }

    public static double binomial(int n, double p) {
        double q = 1 - p;
        double sem = Math.random(), ret = 0.0, inte;
//        int i = 0, val = 0;
//        while (inte < sem && n > ret) {
//            ret = i;
//            inte += factorial(n) / factorial((int) ret) * factorial((int) (n - ret)) * Math.pow(q, n - ret);
//            i++;
//        }
        inte = 0.0;

        for (int j = 0; j <= n; j++) {
//            double b = Math.pow(p, j) * Math.pow(q, n - j);
            inte = combina(n, j) * Math.pow(p, j) * Math.pow(q, n - j);
            System.out.println(inte);

        }

        return inte;
    }

    public static int combina(int n, int r) {
        int c;
        c = factorial(n) / (factorial(r) * factorial(n - r));
        return c;
    }

    private static int factorial(int number) {
        if (number == 0)
            return 1;
        return number * factorial(number - 1);
    }
}
