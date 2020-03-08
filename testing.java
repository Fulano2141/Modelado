public class testing {
    public static void main(String argv[]) {

        double[][] x = new double[3][3], y = new double[3][3];
        double[][] d = Operaciones.multiplicacionNxM(Operaciones.transpuesta(x), x);
        d = Operaciones.invert(d);
        double[][] aux = Operaciones.multiplicacionNxM(Operaciones.transpuesta(x), y);
        d = Operaciones.multiplicacionNxM(d, aux);

        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d.length; j++) {
                System.out.print(d[i][j] + "\t");
            }
            System.out.println();
        }

    }

}