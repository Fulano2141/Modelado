package Fhulano.Simulacion;

public class gamma {
    static int n = 1000;

    public static void main(String[] args) {
        System.out.println(numAle(0.0, 1.0));
    }

    public static double numAle(double alpha, double beta) {
        double aux = 0.0;
        double sig = 0.0;
        double del = 6 * sig / n;
        double raiz = Math.sqrt(2 * Math.PI * Math.pow(sig, 2));
        for (int i = 1; i <= n; i++) {
            aux = 0.0;
            double rand = Math.random();
            System.out.println("Numero generado: " + rand);
            while (aux < rand) {
                double e = ((Math.pow(beta, alpha)) / factorial((int) alpha)) * Math.pow(del, alpha - 1)
                        * Math.pow(Math.E, -beta * del);
                aux += (e * del / raiz);
            }
            // System.out.println("Las medias son "+c*del);
            // vecgenerados.add(aux);
            // xGenerados.add(c*del);

        }
        return aux;
    }

    private static int factorial(int number) {
        if (number == 0)
            return 1;
        return number * factorial(number - 1);
    }
}