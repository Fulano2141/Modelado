package Fhulano.Simulacion;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class normal10 {
    public static void main(String args[]) {
        Random random = new Random();
        List<Double> vecgenerados = new ArrayList<>();
        List<Double> xGenerados = new ArrayList<>();
//        double[] vecgenerados = new double[1000];

        double media = 50.0;
        double sigma = 5.0;
        double n = 1000;
        double euler = 2.7183;
        double pi = 3.1416;
//        for (int i = 0; i < 20; i++) {
//            double numaleatorio = random.nextDouble() * (1.0 - 0.0);
//            System.out.println("Num aleatorio " + numaleatorio);
//        }


        for (int i = 0; i < 1000; i++) {
            double numaleatorio = random.nextDouble() * (1.0 - 0.0);
            System.out.println("Numero generado " + numaleatorio);
            double c = 1.0;
            double acumulado = 0.00000;
            double deltaX = (6 * sigma) / n;
            while (acumulado < numaleatorio) {
                double valorvar = (Math.pow(euler, -((Math.pow(((c * deltaX) - media), 2)) / (2 * sigma * sigma))) * deltaX) * (1 / Math.pow(2 * pi * Math.pow(sigma, 2), 0.5));
                acumulado += valorvar;
                c++;
            }
            System.out.println("Las medias son " + c * deltaX);
            vecgenerados.add(acumulado);
            xGenerados.add(c * deltaX);
        }
//        Double suma1 = sumatotal(vecgenerados);
//        System.out.println("Suma total " + suma1);
        System.out.println(mostrarVector(vecgenerados));

        // Creamos la ventana
        JFrame ventana = new JFrame("Grafica");
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(Graficator.graficar(vecgenerados));
        ventana.add(Graficator.graficar2(vecgenerados, xGenerados));
        ventana.setVisible(true);


    }

    public static String mostrarVector(List<Double> vec) {
        StringBuilder cad = new StringBuilder();
        for (Double aDouble : vec) {
            cad.append(aDouble).append("\n");
        }
        return cad.toString();
    }

    /*    public static void mostrarVector(double[] vec){
            String cad="";
            for(int i=0;i<vec.length;i++){
                if(i%10==0){
                    cad+="\n";
                }
                else {
                    cad+="\t"+vec[i];
                }
            }
            System.out.println(cad);
        }*/
    public static Double sumatotal(List<Double> sumaTotal) {
        Double sum = 0.0;
        for (int i = 0; i < sumaTotal.size(); i++) {
            sum += sumaTotal.get(i);
        }
        return sum;

    }

    public static double generateNumber() {
        double media = 33;
        double sigma = 5.0;
        double n = 1000;
        double euler = 2.7183;
        double pi = 3.1416;
        double numaleatorio = Math.random() * (1.0 - 0.0);
        double c = 1.0;
        double acumulado = 0.00000;
        double deltaX = (6 * sigma) / n;
        double valorvar = 0.0;
        while (acumulado < numaleatorio) {
            valorvar = (Math.pow(euler, -((Math.pow(((c * deltaX) - media), 2)) / (2 * sigma * sigma))) * deltaX) * (1 / Math.pow(2 * pi * Math.pow(sigma, 2), 0.5));
            acumulado += valorvar;
            c++;
        }
        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
        separadoresPersonalizados.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.00", separadoresPersonalizados);


        return Double.parseDouble(df.format(c * deltaX));
    }
}
