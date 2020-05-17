package Fhulano.Experiencia;

import Fhulano.Simulacion.bernoulli;

import java.text.DecimalFormat;
import java.util.List;

public class Ejer06 {

    public static void main(String[] args) {
        double cantidadParcial;
        double pesoTotal = 50.0;
        Object[][] data = {
//                {"Drogas", 5.0, 0, 70.0},
                {"Lapiz", 0.1, 0, 12.2},
                {"Cuaderno", 0.5, 0, 13.5},
                {"Libro", 0.6, 0, 14.0},
                {"Estuche", 0.3, 0, 3.0},
                {"Laptop", 15.0, 0, 100.0},
                {"Disco Duro", 4.0, 0, 3.0},
                {"Cargador", 0.1, 1, 3.0},
                {"Cartapacio", 0.7, 1, 10.0},
                {"Chamarra", 0.5, 1, 50.0},
                {"Paraguas", 0.4, 1, 10.0},
                {"Billetera", 0.4, 1, 90.0},
                {"Llaves", 0.1, 1, 10.0},
                {"Celular", 0.4, 1, 91.0},
                {"Audifonos", 0.2, 1, 10.0},
                {"Cables", 10.5, 1, 10.0},
                {"Tester", 20.0, 1, 20.0},
                {"Arduino", 0.1, 1, 10.1},
                {"Lentes-Estuche", 0.5, 1, 80.0},
                {"Ropa", 3.0, 1, 20.0},
                {"Guardapolvo", 1.0, 1, 10.0},
        };
        bernoulli be = new bernoulli(0.9, data.length);
        double satisfaccion = satisfacer(data);
        double total = tota(data);
        cantidadParcial = pesoTotal - 5.0;
        while (cantidadParcial >= 0.0 && cantidadParcial <= (pesoTotal - 0.5) && (satisfaccion > 0.0 && satisfaccion <= total)) {

            be.updateNumbers();
            int[] nums = be.getNumbers();
            for (int i = 0; i < data.length; i++) {
                data[i][2] = nums[i];
            }
            cantidadParcial = calcularPeso(data);
            satisfaccion = satisfacer(data);
            if (cantidadParcial > pesoTotal) {
                cantidadParcial = 2.0;
            }
        }
        showMatrix(data);
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(cantidadParcial) + "");
    }

    private static double tota(Object[][] data) {
        double aux = 0.0;
        for (Object[] datum : data) {
            aux = aux + (double) datum[3];
        }
        return aux;
    }

    private static double satisfacer(Object[][] data) {
        double aux = 0.0;
        for (Object[] datum : data) {
            aux = aux + (double) datum[3] * (int) datum[2];
        }
        return 0;
    }

    public static double calcularPeso(Object[][] data) {
        double aux = 0.0;
        for (Object[] datum : data) {
            aux = aux + (double) datum[1] * (int) datum[2];
        }
        return aux;
    }

//    public static String mostrarvector(List<Integer> lista) {
//        StringBuilder cad = new StringBuilder();
//        for (int i = 0; i < lista.size(); i++) {
//            if (i % 30 == 0) {
//                cad.append(lista.get(i)).append("\n");
//            } else
//                cad.append(lista.get(i)).append("   ");
//        }
//        return cad.toString();
//    }

//    public static void showMatrix(double m[][]) {
//
//        for (int i = 0; i < m.length; i++) {
//            for (int j = 0; j < m[0].length; j++) {
//                System.out.print(m[i][j] + "\t");
//            }
//            System.out.println();
//        }
//    }

    public static void showMatrix(Object[][] m) {

        for (Object[] objects : m) {
            if ((int) objects[2] == 1) {
                for (int j = 0; j < m[0].length - 2; j++) {
                    System.out.print(objects[j] + "\t");
                }
                System.out.println();
            }

        }
    }
}
