package Fhulano.Simulacion;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class normal {
    double media, sigma;
    int n;
    double[] numbers;
    double[][] tabProb;

    public normal(double media, double sigma, int n) {
        this.media = media;
        this.sigma = sigma;
        this.n = n;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public double getSigma() {
        return sigma;
    }

    public void setSigma(double sigma) {
        this.sigma = sigma;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double[] getNumbers() {
        return numbers;
    }

    public void setNumbers(double[] numbers) {
        this.numbers = numbers;
    }

    public double[][] getTabProb() {
        return tabProb;
    }

    public void setTabProb(double[][] tabProb) {
        this.tabProb = tabProb;
    }

    public static void main(String[] args) {
        normal nor = new normal(0.0, 5.0, 100);
        double[] al = nor.updateNumbers();
        for (int i = 0; i<al.length;i++){
            System.out.println(al[i]);
        }
//        System.out.println(numAle(0.0, 1.0));
    }

    public double[] updateNumbers() {
        double[] aux = new double[getN()];
        for (int i = 0; i < getN(); i++) {
            double numaleatorio = Math.random();
            double c = 1.0;
            double acumulado = 0.00000;
            double deltaX = (6 * sigma) / n;
            while (acumulado < numaleatorio) {
                double valorvar = (Math.pow(Math.E, -((Math.pow(((c * deltaX) - media), 2)) / (2 * sigma * sigma))) * deltaX) * (1 / Math.pow(2 * Math.PI * Math.pow(sigma, 2), 0.5));
                //               acumulado+=(Math.pow(euler,-(Math.pow(((c*deltaX)-media)/sigma,2)))*deltaX)*(1/Math.pow(2*pi*Math.pow(sigma,2),0.5));
                acumulado += valorvar;
//                acumulado += Math.pow(Math.E, Math.pow(-((deltaX - media) / sigma), 2)) * (6 * sigma / n) / Math.sqrt(2 * Math.PI * Math.pow(sigma, 2));
//                acumulado += (6 * sigma) / 10000 * Math.pow(-0.5 * Math.pow((c * (6 * sigma / 10000))), 2);
                c++;
            }
//            System.out.println("Las medias son " + c * deltaX);
            aux[i] = acumulado;
//            vecgenerados.add(acumulado);
//            xGenerados.add(c * deltaX);
        }
//       Double suma1=sumatotal(vecgenerados);
//        System.out.println("Suma total "+suma1);
//        System.out.println(mostrarVector(vecgenerados));


        return aux;
    }

    public static double numAle(double mu, double sig) {
        int n = 1000;
        double aux = 0.0;
        double del = 6 * sig / n;
        double raiz = Math.sqrt(2 * Math.PI * Math.pow(sig, 2));
        for (int i = 1; i <= n; i++) {
            aux = 0.0;
            double rand = Math.random();

            while (aux < rand) {
                double elev = -(Math.pow((i * del - mu) / sig, 2));
                double e = Math.pow(Math.E, elev);
                aux += (e * del / raiz);
            }
            // System.out.println("Las medias son "+c*del);
            // vecgenerados.add(aux);
            // xGenerados.add(c*del);
        }
        System.out.println("Numero generado: " + aux);

        return aux;
    }
}