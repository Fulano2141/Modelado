package Fhulano.Experiencia;

import Fhulano.Operaciones;
import Fhulano.Simulacion.exponencial;
import Fhulano.Simulacion.uniforme;
import com.sun.istack.internal.Nullable;

public class Ejer12 {
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
        nodo fin = new nodo("Final", 0.0, null, null);
        nodo apri = new nodo("A", medA, fin, null);

        nodo c1 = new nodo("C", medC, apri, null);
        nodo c2 = new nodo("C", medC, apri, null);
        nodo b1 = new nodo("B", medB, apri, null);

        nodo b2 = new nodo("B", medB, c1, b1);
        nodo a1 = new nodo("A", medA, b2, c2);

        nodo c3 = new nodo("C", medC, c1, null);
        nodo D1 = new nodo("D", medD, c3, b2);

        nodo b3 = new nodo("B", medB, c3, D1);
        nodo c4 = new nodo("C", medC, D1, a1);

        nodo aini = new nodo("A", medA, b3, c4);
        caminocorto(aini);

//        nodo b = new nodo("B",C);
        Operaciones.imprimir(medvardes);
    }

    private static void caminocorto(nodo aini) {
        String camino = aini.nombre + ", ";
        String neutro = "Aleatorio";
        nodo aux = aini;
        double sum = 0.0;
        do {
            neutro = aux.nombre;
//            System.out.println(neutro);
            sum += aux.valor;
            if (aux.camB != null) {
                if (aux.camA.valor < aux.camB.valor) {
                    aux = aux.camA;
                    camino += aux.nombre + ", ";
                } else {
                    aux = aux.camB;
                    camino += aux.nombre + ", ";
                }
            } else {
                aux = aux.camA;
                camino += aux.nombre + ", ";
            }

        } while (!neutro.equals("Final") && aux.camA != null && aux.camB != null);

        assert aux.camA != null;
        System.out.println(camino + aux.camA.nombre);
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
