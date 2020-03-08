import java.text.DecimalFormat;

public class Operaciones {
    public static double[][] sumarAB(double[][] a, double[][] b) {
        double c[][] = new double[a.length][a.length];
        for (int i = 0; i < c.length; ++i) {
            for (int j = 0; j < c.length; ++j) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }

    public static double[][] restarA_B(double[][] a, double[][] b) {
        double c[][] = new double[a.length][a.length];
        for (int i = 0; i < c.length; ++i) {
            for (int j = 0; j < c.length; ++j) {
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        return c;
    }

    public static double[][] multiplicar(double[][] a, double[][] b) {
        double c[][] = new double[a.length][a.length];
        for (int i = 0; i < c.length; ++i) {
            for (int j = 0; j < c.length; ++j) {
                double aux = 0.0;
                for (int sum = 0; sum < c.length; sum++) {
                    aux += a[i][sum] * b[sum][j];
                }
                c[i][j] = aux;
            }
        }
        return c;
    }

    // Matriz:
    // 2 0 1
    // 3 0 0
    // 5 1 1
    public static double[][] cargadoA3x3() {
        double[][] a = new double[3][3];
        a[0][0] = 2;
        a[0][1] = 0;
        a[0][2] = 1;
        a[1][0] = 3;
        a[1][1] = 0;
        a[1][2] = 0;
        a[2][0] = 5;
        a[2][1] = 1;
        a[2][2] = 1;
        return a;
    }

    // Matriz:
    // 1 0 1
    // 1 2 1
    // 1 1 0
    public static double[][] cargadoB3x3() {
        double[][] a = new double[3][3];
        a[0][0] = 1;
        a[0][1] = 0;
        a[0][2] = 1;
        a[1][0] = 1;
        a[1][1] = 2;
        a[1][2] = 1;
        a[2][0] = 1;
        a[2][1] = 1;
        a[2][2] = 0;
        return a;
    }
    // DecimalFormat df = new DecimalFormat("#.00");
    // aux[i][j] = Double.parseDouble(df.format((d[i][j])));

}