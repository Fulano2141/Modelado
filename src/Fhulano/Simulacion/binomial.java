package Fhulano.Simulacion;

import java.util.Scanner;

public class binomial {
    double p;
    int n;

    public binomial(double p, int n) {
        this.p = p;
        this.n = n;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        binomial bi = new binomial(0.03, 10);
        double[][] arra = bi.tabla();
        for (int i = 0; i < arra.length; i++) {
            for (int j = 0; j < arra[0].length; j++) {
                System.out.print(arra[i][j] + "\t");
            }
            System.out.println();
        }
//        n= 10 p 0.4
//        int i = 10; // = n
//        for (; i > 0; i--) {
//        System.out.println("asd " + binomial(10, 0.4));
//        }
        in.close();
    }

    public double siguienteValor() {
        return tabla()[0][(int) (Math.random() * tabla().length)];
    }

    public double[][] tabla() {
        double q = 1 - getP();
        double prob = 0.0, sum = 0.0;
        double[][] mat = new double[getN()][2];

//        double sem = Math.random(), ret = 0.0;
//        while (inte < sem && n > ret) {
//            ret = i;
//            inte += factorial(n) / factorial((int) ret) * factorial((int) (n - ret)) * Math.pow(q, n - ret);
//            i++;
//        }

        for (int i = 0; i < getN(); i++) {
            prob = combina(getN(), i) * Math.pow(getP(), i) * Math.pow(q, getN() - i);
            sum += prob;
            mat[i][0] = prob;
            mat[i][1] = sum;
        }
        return mat;
    }

    public double binomial(int n, double p) {
        double q = 1 - p;
        double sem = Math.random(), ret = 0.0, inte;
//        int i = 0, val = 0;
//        while (inte < sem && n > ret) {
//            ret = i;
//            inte += factorial(n) / factorial((int) ret) * factorial((int) (n - ret)) * Math.pow(q, n - ret);
//            i++;
//        }
        inte = 0.0;

        for (int j = 0; j <= getN(); j++) {
//            double b = Math.pow(p, j) * Math.pow(q, n - j);
            inte += combina(n, j) * Math.pow(p, j) * Math.pow(q, n - j);
            System.out.println(inte);

        }

        return inte;
    }

    private int combina(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));

    }

    private int factorial(int number) {
        if (number == 0)
            return 1;
        return number * factorial(number - 1);
    }
}
