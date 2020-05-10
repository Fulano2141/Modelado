package Fhulano.Simulacion;

public class poisson {
    double mean;
    static int n = 10;

    public poisson(double mean) {
        this.mean = mean;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double matProb() {
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
            inte = Math.pow(Math.E, -getMean()) * Math.pow(mean, j) / factorial(j);
            System.out.println(inte + " i = " + j);

        }

        return inte;

    }

    public static void main(String[] args) {
        poisson po = new poisson(21);
        po.matProb();
    }


    private static int combina(int n, int r) {
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
