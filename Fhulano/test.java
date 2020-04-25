package Fhulano;

// import java.util.Random;

public class test {
    public static void main(String[] args) {
        // Random randomno = new Random();
        // System.out.println("Next Gaussian value: " + randomno.nextGaussian());
        // // randomno.ne
        double[][] x = datos.datosXn("1");
        // double[][] x = datos.datosYn("1");
        // PruebaDeEstacioneriedad pru = new PruebaDeEstacioneriedad(x, 1.984);
        // pru.setX(datos.datosYn("1"));
        // pru.prueba();
        // pru.setX(datos.datosYn("2"));
        // pru.prueba();
        // pru.setX(datos.datosYn("3"));
        // pru.prueba();

        // pru.setX(datos.datosXn("1"));
        // pru.prueba();
        // pru.setX(datos.datosXn("2"));
        // pru.prueba();
        // pru.setX(datos.datosXn("3"));
        // pru.prueba();
        // pru.setX(datos.datosXn("4"));
        // pru.prueba();
        // pru.setX(datos.datosXn("5"));
        // pru.prueba();
        // pru.setX(datos.datosXn("6"));
        // pru.prueba();
        // pru.setX(datos.datosXn("7"));
        // pru.prueba();
        // pru.setX(datos.datosXn("8"));
        // pru.prueba();
        // pru.setX(datos.datosXn("9"));
        // pru.prueba();
        // pru.setX(datos.datosXn("10"));
        // pru.prueba();
        // pru.setX(datos.datosXn("11"));
        // pru.prueba();
        // pru.setX(datos.datosXn("12"));
        // pru.prueba();
        // pru.setX(datos.datosXn("13"));
        // pru.prueba();

        // Y1=a1
        int n = x.length;
        pruebaT pt = new pruebaT(datos.datosX(), datos.datosXn("1"));
        // imp(datos.datosYn("1"));
        // imp(union(datos.datos1N(n), datos.datosXn("7")));
        // imp(pt.ObtenerMatC(union(datos.datos1N(n), datos.datosXn("7"))));

        // double[][] aux = union(datos.datos1N(n), datos.datosXn("1"));
        // aux = union(aux, datos.datosXn("3"));
        // aux = union(aux, datos.datosXn("8"));
        // aux = union(aux, datos.datosXn("12"));
        // aux = union(aux, datos.datosXn("2"));
        // aux = union(aux, datos.datosXn("5"));
        // pt.setXinput(aux);
        // pt.setYinput(datos.datosYn("2"));
        pt.prueba(1.984);

    }

    public static double[][] union(double[][] m1, double[][] m2) {
        double[][] matrix = new double[m1.length][m1[0].length + m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length + m2[0].length; j++) {
                if (j < m1[0].length) {
                    matrix[i][j] = m1[i][j];
                } else {
                    matrix[i][j] = m2[i][j - m1[0].length];
                }
            }
        }
        return matrix;

    }

    public static void imp(double[][] m1) {
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                System.out.print(m1[i][j] + "\t");
            }
            System.out.println();
        }
    }
}