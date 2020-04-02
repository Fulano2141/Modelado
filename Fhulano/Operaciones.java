package Fhulano;

import java.text.DecimalFormat;

public class Operaciones {

    public static double redondearNum(double d) {
        double aux;
        DecimalFormat df = new DecimalFormat("#.0000");
        aux = Double.parseDouble(df.format((d)));
        return aux;
    }

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
        double c[][] = new double[a.length][a[0].length];
        for (int i = 0; i < c.length; ++i) {
            for (int j = 0; j < c[0].length; ++j) {
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        return c;
    }

    public static double[][] multiplicarNxN(double[][] a, double[][] b) {
        double c[][] = new double[a.length][a.length];
        for (int i = 0; i < a[0].length; ++i) {
            for (int j = 0; j < b[0].length; ++j) {
                double aux = 0.0;
                for (int sum = 0; sum < c.length; sum++) {
                    aux += a[i][sum] * b[sum][j];
                }
                c[i][j] = aux;
            }
        }
        return c;
    }

    public static double[][] multiplicacionNxM(double[][] ds, double[][] x) {
        double[][] c = new double[ds.length][x[0].length];
        if (ds[0].length == x.length) {
            for (int i = 0; i < ds.length; i++) {
                for (int j = 0; j < x[0].length; j++) {
                    for (int k = 0; k < ds[0].length; k++) {
                        c[i][j] += ds[i][k] * x[k][j];
                    }
                }
            }
        }
        return c;
    }

    public static double[][] cargadoA3x3() {
        // Matriz:
        // 2 0 1
        // 3 0 0
        // 5 1 1
        double[][] a = { { 2, 0, 1 }, { 3, 0, 0 }, { 5, 1, 1 } };
        return a;
    }

    public static double[][] cargadoB3x3() {
        // Matriz:
        // 1 0 1
        // 1 2 1
        // 1 1 0
        double[][] a = { { 1, 0, 1 }, { 1, 2, 1 }, { 1, 1, 0 } };
        return a;
    }

    public static double[][] identidad(int n) {
        double ide[][] = new double[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j)
                    ide[i][j] = 1;
                else
                    ide[i][j] = 0;

            }
        }
        return ide;
    }

    public static double trazaA(double mat[][]) {
        double traza = 0.0;
        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat.length; ++j) {
                if (i == j)
                    traza += mat[i][j];
            }
        }
        return traza;
    }

    public static double[][] fadeva(double[][] d) {
        return null;
    }

    public static double[][] transpuestaMxM(double[][] d) {
        double[][] aux = new double[d.length][d.length];
        for (int i = 0; i < d.length; ++i) {
            for (int j = 0; j < d.length; ++j) {
                aux[i][j] = d[j][i];
            }
        }
        return aux;
    }

    // public static double[][] transpuesta(double[][] d, int m, int n) {
    // double[][] aux = new double[m][n];
    // for (int i = 0; i < m; ++i) {
    // for (int j = 0; j < n; ++j) {
    // aux[i][j] = d[j][i];
    // }
    // }
    // return aux;
    // }

    public static double[][] transpuesta(double[][] m) {
        double[][] matrix = new double[m[0].length][m.length];
        for (int j = 0; j < m[0].length; j++) {
            for (int i = 0; i < m.length; i++) {
                matrix[j][i] = m[i][j];
            }
        }

        return matrix;

    }

    public static double[][] invert(double a[][]) {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;
        // Transform the matrix into an upper triangle
        gaussian(a, index);
        // Update the matrix b[i][j] with the ratios stored
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];
        // Perform backward substitutions
        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }

                x[j][i] /= a[index[j]][j];
            }
        }
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                if (x[i][j] == -0.0)
                    x[i][j] = 0.0;
            }
        }

        return x;
    }

    // Method to carry out the partial-pivoting Gaussian
    // elimination. Here index[] stores pivoting order.
    public static void gaussian(double a[][], int index[]) {
        int n = index.length;
        double c[] = new double[n];
        // Initialize the index
        for (int i = 0; i < n; ++i)
            index[i] = i;
        // Find the rescaling factors, one from each row
        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1)
                    c1 = c0;
            }
            c[i] = c1;
        }
        // Search the pivoting element from each column
        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {

                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }
            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }

    public static double[][] redondearMat(double[][] d) {
        DecimalFormat df = new DecimalFormat("#.0000");
        double aux[][] = d;
        for (int i = 0; i < d.length; ++i) {
            for (int j = 0; j < d[0].length; ++j) {
                aux[i][j] = Double.parseDouble(df.format((d[i][j])));
            }
        }
        return aux;
    }
    // DecimalFormat df = new DecimalFormat("#.00");
    // aux[i][j] = Double.parseDouble(df.format((d[i][j])));

}