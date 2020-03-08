
public class testing {
    public static void main(String argv[]) {
        double[][] a = new double[3][3];
        double[][] b = new double[3][3];
        a = Operaciones.cargadoA3x3();
        b = Operaciones.cargadoB3x3();
        double[][] d = Operaciones.restarA_B(a, b);
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d.length; j++) {
                System.out.print(d[i][j] + "\t");
            }
            System.out.println();
        }
    }
}