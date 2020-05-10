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
import java.io.IOException;
import java.text.DecimalFormat;

public class exponencial {
    double media;
    int n;
    double[][] data;
    DefaultXYDataset Datos = new DefaultXYDataset();

    public exponencial(double lamda, int n) {
        this.media = lamda;
        this.n = n;
        data = new double[getN()][1];
        setData(disexponencial());
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void graficar() {
//        double[][] a = {{2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017},
//                {25, 29.1, 32.1, 32.9, 31.9, 25.5, 20.1, 18.4, 15.3, 11.4, 9.5}};
        double[][] mat = Operaciones.transpuesta(getData());
//        System.out.println("i: " + a.length + " j: " + a[0].length);
        Datos.addSeries("Exponencial", mat);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.ORANGE);
//        renderer.setSeriesPaint(1, Color.BLUE);
//        renderer.setSeriesPaint(2, Color.GREEN);
        renderer.setSeriesStroke(0, new BasicStroke(2));
//        renderer.setSeriesStroke(1, new BasicStroke(2));
//        renderer.setSeriesStroke(2, new BasicStroke(2));

        JFreeChart chart = ChartFactory.createXYLineChart("Exponencial", "", "", Datos);
        chart.getXYPlot().getRangeAxis().setRange(0, (1 / getMedia()) + 0.5);
        ((NumberAxis) chart.getXYPlot().getRangeAxis()).setNumberFormatOverride(new DecimalFormat("#.00"));
        chart.getXYPlot().setRenderer(renderer);

        BufferedImage image = chart.createBufferedImage(600, 400);
        try {
            ImageIO.write(image, "png", new File("xy-chart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double[][] disexponencial() {
        for (int i = 0; i < data.length; i++) {
            data[i][0] = nextExpo();
        }
        return data;
    }

    private double nextExpo() {
        return -getMedia() * Math.log(1.0 - Math.random());
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
        return getMedia();
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
        return 1.0 / Math.pow(media, -2);
    }

    public static void main(String[] args) {
        exponencial ex = new exponencial(50, 100);
//        double[][] expo = ex.getData();
        System.out.println(ex.mediaData() + " " + ex.mediaProb());
        System.out.println(ex.variaData() + " " + ex.desviacionProb());
//        ex.graficar();
    }
}
