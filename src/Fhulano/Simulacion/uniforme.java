package Fhulano.Simulacion;

import Fhulano.Operaciones;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;


import java.io.IOException;

public class uniforme {
    int a, b, n;
    double[][] data;
    DefaultXYDataset Datos = new DefaultXYDataset();

    public uniforme(int a, int b, int n) {
        this.a = a;
        this.b = b;
        this.n = n;
        setData(disuniforme());
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
        setData(disuniforme());
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
        setData(disuniforme());
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
        setData(disuniforme());
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public void graficar() {
        double[][] mat = Operaciones.transpuesta(getData());
//        System.out.println("i: " + a.length + " j: " + a[0].length);
        Datos.addSeries("Uniforme", mat);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.ORANGE);
//        renderer.setSeriesPaint(1, Color.BLUE);
//        renderer.setSeriesPaint(2, Color.GREEN);
        renderer.setSeriesStroke(0, new BasicStroke(2));
//        renderer.setSeriesStroke(1, new BasicStroke(2));
//        renderer.setSeriesStroke(2, new BasicStroke(2));

        JFreeChart chart = ChartFactory.createXYLineChart("Uniforme", "", "", Datos);
        chart.getXYPlot().getRangeAxis().setRange(0, 0.5);
        ((NumberAxis) chart.getXYPlot().getRangeAxis()).setNumberFormatOverride(new DecimalFormat("#.00"));
        chart.getXYPlot().setRenderer(renderer);

        BufferedImage image = chart.createBufferedImage(600, 400);
        try {
            ImageIO.write(image, "png", new File("xy-chart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double[][] disuniforme() {
        data = new double[getN()][2];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = (Math.random() * (getB() - getA()) + getA());
            data[i][1] = 1.0 / (getB() - getA());
//            System.out.println(t[i][0] + "\t" + t[i][1]);
        }
//        for (int i = 0; i < t.length; i++) {
//            // t[i][0] = a + t[i][0] * (b - a);
//
//        }
        return data;
    }

    public double mediaData() {
        double med = 0.0;
        double[][] da = getData();
        for (double[] doubles : da) {
            med += doubles[0];
        }
        return med / da.length;
    }

    public double mediaProb() {
        return (getA() + getB()) / 2.0;
    }

    public double variaData() {
        double med = mediaData();
        double sum = 0.0;
        double[][] da = getData();
        for (double[] doubles : da) {
            sum += Math.pow((doubles[0] - med), 2);
        }
        return sum / (getN() - 1);
    }

    public double desviacionProb() {
        return Math.pow((getB() - getA()), 2) / 12;
    }

    public static void main(String[] args) {
        uniforme uni = new uniforme(-5, 10, 100);
        double[][] unifo = uni.getData();
        System.out.println(uni.mediaData() + " " + uni.mediaProb());
        System.out.println(uni.variaData() + " " + uni.desviacionProb());
        uni.graficar();
    }
}
