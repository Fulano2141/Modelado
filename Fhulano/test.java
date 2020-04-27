package Fhulano;

// import java.util.Random;

public class test {

    public static void main(String[] args) {
        // Random randomno = new Random();
        // System.out.println("Next Gaussian value: " + randomno.nextGaussian());
//        PruebaDeEstacioneriedad pru = new PruebaDeEstacioneriedad(x, 1.984);
//        estacionariedad(pru);
//        pruebaT pt = new pruebaT(datos.datosXn("1"), datos.datosYn("1"));
//        pruebatstudent(pt);

//        prediccion();

        ars();

    }

    private static void ars() {
        double[][] x = datos.datosYn("2");
        double[][] y = datos.EstimadasYn("2");
        x = Operaciones.borrarfila(x, 0);
        y = Operaciones.borrarfila(y, y.length - 1);
        double[][] betas = Betas.HallarBetas(x, y, true);

    }


    private static void prediccion() {
        double[][] x = datos.datosX();
        double[][] y = datos.datosY();
        double[][] pi;
        double[][] xt = Operaciones.transpuesta(x);
        pi = Operaciones.invert(Operaciones.multiplicacionNxM(xt, x));
        pi = Operaciones.multiplicacionNxM(pi, Operaciones.multiplicacionNxM(xt, y));
        double[][] yestimados = Operaciones.multiplicacionNxM(x, pi);
        imp(yestimados);
        writexlxs.escribir(yestimados);
    }

    public static void imp(double[][] m1) {
        for (double[] doubles : m1) {
            for (int j = 0; j < m1[0].length; j++) {
                System.out.print(doubles[j] + "\t");
            }
            System.out.println();
        }
    }

    private static void pruebatstudent(pruebaT pt) {
//        double[][] x = datos.datosXn("1");
        int n = datos.datosXn("1").length;
//        imp(datos.datosYn("2"));
//        imp(union(datos.datos1N(n), datos.datosXn("7")));
//        imp(pt.ObtenerMatC(union(datos.datos1N(n), datos.datosXn("7"))));

        double[][] aux = union(datos.datos1N(n), datos.datosXn("5"));
        aux = union(aux, datos.datosXn("2"));
        aux = union(aux, datos.datosXn("12"));
        aux = union(aux, datos.datosXn("8"));
        pt.setXinput(aux);
        pt.setYinput(datos.datosYn("1"));
        pt.prueba(1.984, 2.139, "Ecuacion: y1 = a + ax5 + ax2 + ax12 + ax8");

        aux = union(datos.datos1N(n), datos.datosXn("1"));
        aux = union(aux, datos.datosXn("3"));
        aux = union(aux, datos.datosXn("8"));
        aux = union(aux, datos.datosXn("12"));
        aux = union(aux, datos.datosXn("2"));
        aux = union(aux, datos.datosXn("5"));
        pt.setXinput(aux);
        pt.setYinput(datos.datosYn("2"));
        pt.prueba(1.984, 1.906, "Ecuacion: y2 = a + ax1 + ax3 + ax8 + ax12 + ax2 + ax5");

        aux = union(datos.datos1N(n), datos.datosXn("2"));
        aux = union(aux, datos.datosXn("6"));
        aux = union(aux, datos.datosXn("12"));
        aux = union(aux, datos.datosXn("1"));
        pt.setXinput(aux);
        pt.setYinput(datos.datosXn("4"));
        pt.prueba(1.984, 2.139, "Ecuacion: x4 = a + x2 + x6 + x12 + x1");

        aux = union(datos.datos1N(n), datos.datosXn("9"));
        pt.setXinput(aux);
        pt.setYinput(datos.datosXn("5"));
        pt.prueba(1.984, 2.756, "Ecuacion: x5 = a + x9");

        aux = union(datos.datos1N(n), datos.datosXn("5"));
        pt.setXinput(aux);
        pt.setYinput(datos.datosXn("6"));
        pt.prueba(1.984, 2.756, "Ecuacion: x6 = a + x5");

        aux = union(datos.datos1N(n), datos.datosXn("3"));
        aux = union(aux, datos.datosXn("1"));
        aux = union(aux, datos.datosXn("2"));
        aux = union(aux, datos.datosXn("6"));
        pt.setXinput(aux);
        pt.setYinput(datos.datosXn("7"));
        pt.prueba(1.984, 2.139, "Ecuacion: x7 = a +x3 + x1 + x2 + x6");

        aux = union(datos.datos1N(n), datos.datosXn("4"));
        pt.setXinput(aux);
        pt.setYinput(datos.datosXn("8"));
        pt.prueba(1.984, 2.756, "Ecuacion: x8 = a + x4");

        aux = union(datos.datos1N(n), datos.datosXn("7"));
        pt.setXinput(aux);
        pt.setYinput(datos.datosXn("9"));
        pt.prueba(1.984, 2.756, "Ecuacion: x9 = a + x7");

        aux = union(datos.datos1N(n), datos.datosXn("4"));
        aux = union(aux, datos.datosXn("9"));
        pt.setXinput(aux);
        pt.setYinput(datos.datosXn("10"));
        pt.prueba(1.984, 2.756, "Ecuacion: x10 = a + x4 + x9");

        aux = union(datos.datos1N(n), datos.datosXn("6"));
        pt.setXinput(aux);
        pt.setYinput(datos.datosXn("11"));
        pt.prueba(1.984, 2.756, "Ecuacion: x11 = a + x6");

    }

    private static void estacionariedad(PruebaDeEstacioneriedad pru) {
        pru.setX(datos.datosYn("1"));
        pru.prueba("Datos PIB Nominal");
        pru.setX(datos.datosYn("2"));
        pru.prueba("Datos PIB Real");
        pru.setX(datos.datosXn("1"));
        pru.prueba("Datos Agricultura");
        pru.setX(datos.datosXn("2"));
        pru.prueba("Datos Petroleo y Gas");
        pru.setX(datos.datosXn("3"));
        pru.prueba("Datos Minerales");
        pru.setX(datos.datosXn("4"));
        pru.prueba("Datos Industria Manufacturera");
        pru.setX(datos.datosXn("5"));
        pru.prueba("Datos Electricidad, gas y agua");
        pru.setX(datos.datosXn("6"));
        pru.prueba("Datos Construccion");
        pru.setX(datos.datosXn("7"));
        pru.prueba("Datos Comercio");
        pru.setX(datos.datosXn("8"));
        pru.prueba("Datos Impuestos");
        pru.setX(datos.datosXn("9"));
        pru.prueba("Datos Establecimientos financieros");
        pru.setX(datos.datosXn("10"));
        pru.prueba("Datos Servicios bancarios imputados");
        pru.setX(datos.datosXn("11"));
        pru.prueba("Datos Transporte y comunicaciones");
        pru.setX(datos.datosXn("12"));
        pru.prueba("Datos Otros Servicios");

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


}