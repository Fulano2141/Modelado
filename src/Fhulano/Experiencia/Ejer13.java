package Fhulano.Experiencia;

import Fhulano.Operaciones;
import Fhulano.Simulacion.exponencial;
import Fhulano.Simulacion.uniforme;
import com.sun.istack.internal.Nullable;

public class Ejer13 {
    static class nodo {
        String nombre;
        double valor;
        nodo camA;
        nodo camB;

        public nodo(String nombre, double valor, nodo camA, @Nullable nodo camB) {
            this.nombre = nombre;
            this.valor = valor;
            this.camA = camA;
            if (camB != null) {
                this.camB = camB;
            }
        }
    }

    static int n = 5000;
    static double[][] tabA = {{100.0, (10.0 / 85.0)},
            {200.0, (17.0 / 85.0)},
            {300.0, (14.0 / 85.0)},
            {400.0, (35.0 / 85.0)},
            {500.0, (9.0 / 85.0)},};

    public static void main(String[] args) {
        double[] A = updateNumbers();
        double medA = mediaData(A), varA = varData(A), desA = Math.sqrt(varA);

        exponencial B = new exponencial(100, n);
        double medB = B.mediaData(), varB = B.variaData(), desB = Math.sqrt(varB);

        uniforme C = new uniforme(52, 435, n);
        double medC = C.mediaData(), varC = C.variaData(), desC = Math.sqrt(varC);

        uniforme d1 = new uniforme(0, 1, n);
        double[] D = setD(d1);
        double medD = mediaData(D), varD = varData(D), desD = Math.sqrt(varD);

        exponencial E = new exponencial(150, n);
        double medE = E.mediaData(), varE = E.variaData(), desE = Math.sqrt(varE);

        double[][] medvardes = {
                {medA, varA, desA},
                {medB, varB, desB},
                {medC, varC, desC},
                {medD, varD, desD},
                {medE, varE, desE}
        };
        treceA(medA, medB, medC, medD, medE);
        treceB(medA, medB, medC, medD, medE);
        treceC(medA, medB, medC, medD, medE);
//        nodo b = new nodo("B",C);
//        Operaciones.imprimir(medvardes);
    }

    private static void treceC(double medA, double medB, double medC, double medD, double medE) {
        nodo fin = new nodo("Final", 0.0, null, null);

        nodo apri = new nodo("B^2", medB, fin, null);

        nodo d1 = new nodo("D", medD, apri, null);

        nodo a1 = new nodo("A^3", Math.pow(medA, 3), d1, null);

        nodo c1 = new nodo("C", medC, a1, d1);

        nodo b1 = new nodo("B^2", Math.pow(medB, 2), c1, a1);

        nodo aini = new nodo("B", medB, b1, null);

        caminocorto(aini);
    }

    private static void treceB(double medA, double medB, double medC, double medD, double medE) {
        nodo fin = new nodo("Final", 0.0, null, null);

        nodo apri = new nodo("C", medC, fin, null);

        nodo a1 = new nodo("A", medA, apri, null);

        nodo e1 = new nodo("E", medE, a1, null);

        nodo d1 = new nodo("D^2", Math.pow(medD, 2), apri, null);
        nodo b1 = new nodo("B^3", Math.pow(medB, 3), a1, null);

        nodo c1 = new nodo("C", medC, e1, d1);
        nodo a2 = new nodo("A", medA, b1, null);

        nodo aini = new nodo("A", medA, c1, a2);

        caminocorto(aini);
    }

    private static void treceA(double medA, double medB, double medC, double medD, double medE) {
        nodo fin = new nodo("Final", 0.0, null, null);

        nodo apri = new nodo("A", medA, fin, null);

        nodo D1 = new nodo("D^3", Math.pow(medD, 3), apri, null);

        nodo b1 = new nodo("B", medB, apri, D1);

        nodo a1 = new nodo("A", medA, b1, null);
        nodo c1 = new nodo("C", medC, D1, null);

        nodo b2 = new nodo("B^2", Math.pow(medB, 2), a1, c1);

        nodo aini = new nodo("A", medA, b2, null);

        caminocorto(aini);
    }

    private static void caminocorto(nodo aini) {
        StringBuilder camino = new StringBuilder(aini.nombre + ", ");
        String neutro = "";
        nodo aux = aini;
        do {

//            System.out.println(neutro);
            if (aux.camB != null) {
                if (aux.camA.valor < aux.camB.valor) {
                    aux = aux.camA;
                } else {
                    aux = aux.camB;
                }
            } else {
                aux = aux.camA;
            }

            neutro = aux.nombre;
            if (!neutro.equals("Final")) {
                camino.append(aux.nombre).append(", ");
            }
        } while (!neutro.equals("Final"));
//        aux = aux.camA;
        System.out.println(camino);
    }

    private static double varData(double[] a) {
        double med = mediaData(a);
        double aux = 0.0;
        for (int i = 0; i < a.length; i++) {
            aux += Math.pow((a[i] - med), 2);
        }
        return aux / (n - 1);
    }

    private static double mediaData(double[] a) {
        double aux = 0.0;
        for (int i = 0; i < a.length; i++) {
            aux += a[i];
        }
        return aux / a.length;
    }


    private static double[] setD(uniforme d1) {
        double[] aux = new double[n];
        double[][] aux2 = d1.getData();
//        for(int i = 1)
        for (int i = 0; i < aux.length; i++) {
            aux[i] = (Math.pow(aux2[i][0], 0.185) - Math.pow((1 - aux2[i][0]), 0.185)) / (0.1975);
        }
        return aux;
    }

    public static double[] updateNumbers() {
        double[] numbers = new double[n];
        double[][] matrix = tabA;
        for (int j = 0; j < n; j++) {
            int positionSeleccionable = 0;
            double numRandom01 = Math.random();
            double resultOfSeleccionable = Math.max(numRandom01, matrix[0][1]) - Math.min(numRandom01, matrix[0][1]);
            for (int i = 0; i < matrix.length; i++) {
                if ((Math.max(numRandom01, matrix[i][1]) - Math.min(numRandom01, matrix[i][1])) < resultOfSeleccionable) {
                    positionSeleccionable = i;
                    resultOfSeleccionable = matrix[i][1];
                }
            }
            //lista.add(positionSeleccionable);
            numbers[j] = positionSeleccionable;
        }
        return numbers;
    }
}
