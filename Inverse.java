import java.text.DecimalFormat;
import java.util.Scanner;

public class Inverse {
    public static void main(String argv[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("Dimension de la Matriz");
        int n = input.nextInt();
        double a[][] = new double[n][n];
        System.out.println("Elementos de matriz ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("Elemento [" + i + "] [" + j + "]");
                a[i][j] = input.nextDouble();
            }

        }

        double d[][] = invert(a);
        d = arreglar(d);
        System.out.println("The inverse is: ");

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (d[i][j] == -0.0 || d[i][j] == 0.0)
                    System.out.print("0\t");
                else
                    System.out.print(d[i][j] + "\t");
            }
            System.out.println();
        }
        input.close();
    }

    private static double[][] arreglar(double[][] d) {
        DecimalFormat df = new DecimalFormat("#.00");
        double aux[][] = d;
        for (int i = 0; i < d.length; ++i) {
            for (int j = 0; j < d.length; ++j) {
                // String aux2 = d[i][j] + " ";
                // String[] aux3 = aux2.split("\\.");
                // char aux1;
                // try {
                // aux1 = aux3[1].charAt(3);
                // } catch (Exception e) {
                // aux1 = '0';
                // }
                // System.out.println(aux3[1]);
                // if (aux1 == '9') {
                // if (d[i][j] > 0) {
                // aux[i][j] = Double.parseDouble(df.format((d[i][j] + 0.01)));
                // } else {
                // aux[i][j] = Double.parseDouble(df.format((d[i][j] - 0.01)));
                // }
                // } else
                aux[i][j] = Double.parseDouble(df.format((d[i][j])));
            }
        }
        return aux;
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
}