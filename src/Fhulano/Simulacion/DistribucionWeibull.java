package Fhulano.Simulacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;

public class DistribucionWeibull {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Alpha: ");
        double alpha = in.nextInt();
        System.out.print("Beta: ");
        double beta = in.nextInt();
        // Random random = new Random();
        List<Double> listY = new ArrayList<>();
        List<Double> listX = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            double aleatorio = Math.random();
            // System.out.println("Numero aleatorio " + aleatorio);
            // System.out.println("Logaritmo natural " + Math.pow(Math.log(aleatorio),
            // 0.3));
            double x = Math.log(1 - aleatorio);
            x = Math.pow(Math.abs(x), 1 / alpha);
            // System.out.println("X: " + x);
            x = (-beta * x);

            listX.add(x);
            listY.add(aleatorio);
            in.close();
        }
        JFrame ventana = new JFrame("Grafica");
        ventana.setSize(1000, 700);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ventana.add(Graficator.graficar(vecgenerados));
        ventana.add(Graficator.graficar2(listY, listX));
        ventana.setVisible(true);

    }

}