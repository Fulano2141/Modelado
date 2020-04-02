package Fhulano;

public class Betas {
    public static double[] HallarBetas(final double[][] x, final double[][] y, final boolean imprimir) {
        double[][] daux = Operaciones.multiplicacionNxM(Operaciones.transpuesta(x), x);
        daux = Operaciones.invert(daux);
        final double[][] aux = Operaciones.multiplicacionNxM(Operaciones.transpuesta(x), y);
        daux = Operaciones.multiplicacionNxM(daux, aux);
        if (imprimir) {
            daux = Operaciones.redondearMat(daux);
            System.out.println("----------------------------------");
            System.out.println("Matriz de Betas: ");
        }
        final double[] betas = new double[daux.length];
        for (int i = 0; i < daux.length; i++) {
            for (int j = 0; j < daux[0].length; j++) {
                betas[i] = daux[i][j];
                if (imprimir)
                    System.out.println("B" + i + ":" + daux[i][j]);
            }
        }
        return betas;
    }
}