package Fhulano;

public class datos {
    public static double[][] datosX() {
        double[][] data = readxlxs.leerExcels("data.xlsx", "x");
        return data;
    }

    public static double[][] datosY() {
        double[][] data = readxlxs.leerExcels("data.xlsx", "y");
        return data;
    }

    public static double[][] datosYn(String n) {
        double[][] data = readxlxs.leerExcels("data.xlsx", ("y" + n));
        return data;
    }

    public static double[][] datosXn(String n) {
        double[][] data = readxlxs.leerExcels("data.xlsx", ("x" + n));
        return data;
    }

    public static double[][] datos1N(int n) {
        double[][] data = new double[n][1];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = 1.0;
        }
        return data;
    }

}