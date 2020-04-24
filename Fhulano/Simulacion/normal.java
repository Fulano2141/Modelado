package Fhulano.Simulacion;

public class normal {
    static int n = 1000;

    public static void main(String[] args) {
        System.out.println(numAle(0.0, 1.0));
    }

    public static double numAle(double mu, double sig) {
        double aux = 0.0;
        double del = 6 * sig / n;
        double raiz = Math.sqrt(2 * Math.PI * Math.pow(sig, 2));
        for (int i = 1; i <= n; i++) {
            aux = 0.00000;
            double rand = Math.random();
            System.out.println("Numero generado " + rand);
            while (aux < rand) {
                double elev = -(Math.pow((i * del - mu) / sig, 2));
                double e = Math.pow(Math.E, elev);
                aux += (e * del / raiz);
            }
            // System.out.println("Las medias son "+c*del);
            // vecgenerados.add(aux);
            // xGenerados.add(c*del);

        }
        return aux;
    }
}