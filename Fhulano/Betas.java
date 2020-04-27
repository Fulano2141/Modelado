package Fhulano;

public class Betas {
    public static double[][] HallarBetas(final double[][] x, final double[][] y, final boolean imprimir) {
        double[][] daux1 = Operaciones.multiplicacionNxM(Operaciones.transpuesta(x), x);
        // test.imp(Operaciones.redondearMat(daux1));    
        // System.out.println(daux1.length+" "+ daux1[0].length);    
        double[][] daux2 = Operaciones.invert(daux1);

        double[][] aux = Operaciones.multiplicacionNxM(Operaciones.transpuesta(x), y);
        double[][] daux = Operaciones.multiplicacionNxM(daux2, aux);
        if (imprimir) {
            // daux = Operaciones.redondearMat(daux);
            System.out.println("----------------------------------");
            System.out.println("Matriz de Betas: ");
        }
        for (int i = 0; i < daux.length; i++) {
            for (int j = 0; j < daux[0].length; j++) {
                if (imprimir)
                    System.out.print("B[" + i + "][" + j + "]" + ":" + daux[i][j] + "\t");
            }
            if (imprimir)
                System.out.println();

        }
        return daux;
    }
}