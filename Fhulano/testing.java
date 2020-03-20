package Fhulano;

public class testing {
    public static void main(String args[]) {
        // double[][] x = new double[3][3];
        double[][] x = { { 1.0, 10.0, 3.0, 1.0 }, { 1.0, 5.0, 1.5, 1.0 }, { 1.0, 10.0, 1.0, 1.0 },
                { 1.0, 20.0, 2.0, 1.0 }, { 1.0, 15.0, 4.0, 1.0 }, { 1.0, 10.0, 2.0, 1.0 }, { 1.0, 6.0, 0.0, 0.0 },
                { 1.0, 25.0, 1.0, 1.0 }, { 1.0, 30.0, 2.0, 1.0 }, { 1.0, 10.0, 1.0, 1.0 }, { 1.0, 10.0, 1.0, 1.0 },
                { 1.0, 20.0, 1.0, 1.0 }, { 1.0, 20.0, 0.5, 1.0 }, { 1.0, 20.0, 2.0, 1.0 }, { 1.0, 30.0, 0.5, 1.0 },
                { 1.0, 20.0, 1.0, 0.0 }, { 1.0, 10.0, 1.0, 1.0 }, };

        // double[][] y = new double[3][3];
        double[][] y = { { 72.0 }, { 63.0 }, { 65.0 }, { 68.0 }, { 78.0 }, { 64.0 }, { 56.0 }, { 59.0 }, { 60.0 },
                { 85.0 }, { 67.0 }, { 61.0 }, { 65.0 }, { 79.0 }, { 61.0 }, { 52.0 }, { 65.0 } };

        double[][] d = x;
        // double[][] d = Operaciones.multiplicacionNxM(Operaciones.transpuesta(x), x);
        // d = Operaciones.invert(d);
        // double[][] aux = Operaciones.multiplicacionNxM(Operaciones.transpuesta(x),
        // y);
        // d = Operaciones.multiplicacionNxM(d, aux);

        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                System.out.print(d[i][j] + "\t");
            }
            System.out.println();
        }
        // System.out.println(Arrays.deepToString(args));
    }

}