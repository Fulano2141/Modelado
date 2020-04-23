package Fhulano.Simulacion;

public class normal {
    static int n = 1000;

    public static void main(String[] args) {
        System.out.println(Math.random());
        System.out.println(numAle(0.0, 1.0));
    }

    public static double numAle(double mu, double sig) {
        double aux = 0.0;
        double del = 6 * sig / n;
        double raiz = Math.sqrt(2 * Math.PI * Math.pow(sig, 2));

        for (int i = 1; i <= n; i++) {
            double elev = -(Math.pow((i * del - mu) / sig, 2));
            double e = Math.pow(Math.E, elev);
            aux += e * del / raiz;
            if (i == 10)
                System.out.println(aux);
        }
        return aux;
    }
}