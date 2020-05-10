package Fhulano.Experiencia;

import Fhulano.Simulacion.bernoulli;
import java.util.List;

public class Ejer06 {

    public static void main(String[] args) {
        double cantidadParcial;
        double pesoTotal = 50.0;
        Object[][] data = {
                {"Lapiz", 0.1, 1},
                {"Cuaderno", 0.5, 1},
                {"Libro", 0.6, 1},
                {"Estuche", 0.3, 1},
                {"Laptop", 15.0, 1},
                {"Disco Duro", 4.0, 1},
                {"Cargador", 0.1, 1},
                {"Cartapacio", 0.7, 1},
                {"Chamarra", 0.5, 1},
                {"Paraguas", 0.4, 1},
                {"Billetera", 0.4, 1},
                {"Llaves", 0.1, 1},
                {"Celular", 0.4, 1},
                {"Audifonos", 0.2, 1},
                {"Cables", 10.5, 1},
                {"Tester", 20.0, 1},
                {"Arduino", 0.1, 1},
                {"Lentes-Estuche", 0.5, 1},
                {"Drogas", 5.0, 1},
                {"Ropa", 3.0, 1},
                {"Guardapolvo", 1.0, 1},
        };
        bernoulli be = new bernoulli(0.9, data.length);
        cantidadParcial = pesoTotal - 5.0;
        while (cantidadParcial >= 0.0 && cantidadParcial <= (pesoTotal - 0.5)) {
            be.updateNumbers();

            int[] nums = be.getNumbers();
            for (int i = 0; i < data.length; i++) {
                data[i][2] = nums[i];
            }
            cantidadParcial = calcularPeso(data);
            if (cantidadParcial > pesoTotal) {
                cantidadParcial = 2.0;
            }
        }
        showMatrix(data);
        System.out.println(cantidadParcial + "");
    }

    public static double calcularPeso(Object[][] data) {
        double aux = 0.0;
        for (int i = 0; i < data.length; i++) {
            aux = aux + (double) data[i][1] * (int) data[i][2];
        }
        return aux;
    }

    public static String mostrarvector(List<Integer> lista) {
        String cad = "";
        for (int i = 0; i < lista.size(); i++) {
            if (i % 30 == 0) {
                cad += lista.get(i) + "\n";
            } else
                cad += lista.get(i) + "   ";
        }
        return cad;
    }

    public static void showMatrix(double m[][]) {

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void showMatrix(Object m[][]) {

        for (int i = 0; i < m.length; i++) {
            if ((int) m[i][2] == 1) {
                for (int j = 0; j < m[0].length - 1; j++) {
                    System.out.print(m[i][j] + "\t");
                }
                System.out.println();
            }

        }
    }
}
