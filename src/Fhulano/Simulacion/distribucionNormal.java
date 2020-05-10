package Fhulano.Simulacion;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class distribucionNormal {

    public static void main(String args[]) {
        Random random = new Random();
        List<Double> vecgenerados = new ArrayList<>();
//        List<Double> xGenerados = new ArrayList<>();

//        double [] vecgenerados=new double[1000];
        double media = 50.0;
        double sigma = 5.0;
        double n = 1000;
        double euler = 2.7183;
        double pi = 3.1416;
/*        for (int i=0;i<20;i++){
            double numaleatorio=random.nextDouble()*(1.0-0.0);
            System.out.println("Num aleatorio "+numaleatorio);
        }*/


        for (int i = 0; i < 1000; i++) {
            double numaleatorio = random.nextDouble() * (1.0 - 0.0);
            System.out.println("Numero generado " + numaleatorio);
            double c = 1.0;
            double acumulado = 0.00000;
            double deltaX = (6 * sigma) / n;
            while (acumulado < numaleatorio) {
                double valorvar = (Math.pow(euler, -((Math.pow(((c * deltaX) - media), 2)) / (2 * sigma * sigma))) * deltaX) * (1 / Math.pow(2 * pi * Math.pow(sigma, 2), 0.5));
                ;
                //               acumulado+=(Math.pow(euler,-(Math.pow(((c*deltaX)-media)/sigma,2)))*deltaX)*(1/Math.pow(2*pi*Math.pow(sigma,2),0.5));
                acumulado += valorvar;
//                System.out.println("Acumulado "+acumulado);
//                System.out.println("Numero generado "+numaleatorio);
//                acumulado+=Math.pow(Math.E,Math.pow(-((deltaX-media)/sigma),2))*(6*sigma/n)/Math.sqrt(2*Math.PI*Math.pow(sigma,2));
//                acumulado+=(6*sigma)/10000*Math.pow(-0.5*Math.pow((c*(6*sigma/10000))),2);
                c++;
            }
            System.out.println("Las medias son " + c * deltaX);
            vecgenerados.add(acumulado);
//            xGenerados.add(c * deltaX);
        }
//       Double suma1=sumatotal(vecgenerados);
//        System.out.println("Suma total "+suma1);
        System.out.println(mostrarVector(vecgenerados));

        // Creamos la ventana
        JFrame ventana = new JFrame("Grafica");
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(Graficator.graficar(vecgenerados));
//        ventana.add(Graficator.graficar2(vecgenerados, xGenerados));
        ventana.setVisible(true);


    }

    public static String mostrarVector(List<Double> vec) {
        String cad = "";
        for (int i = 0; i < vec.size(); i++) {
            cad += vec.get(i) + "\n";
        }
        return cad;
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


}
