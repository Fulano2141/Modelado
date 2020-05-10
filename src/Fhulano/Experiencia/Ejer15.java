package Fhulano.Experiencia;

import Fhulano.Operaciones;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class Ejer15 {
    public static void main(String[] args) {
        int pob = 10100;
        ReedYFrost rf = new ReedYFrost(pob, 1, 0, 3);
        double[][] data = rf.datos();
        Operaciones.imprimir(data, true);
//        data = Operaciones.transpuesta(data);
        double[][] poblacion = dividirDat(data, 1);
        double[][] casos = dividirDat(data, 2);
        double[][] inmunes = dividirDat(data, 3);
        graficar(poblacion, casos, inmunes, pob);
    }

    private static void graficar(double[][] poblacion, double[][] casos, double[][] inmunes, int pob) {
        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("poblacion", poblacion);
        dataset.addSeries("casos", casos);
        dataset.addSeries("inmunes", inmunes);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.ORANGE);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.GREEN);
        renderer.setSeriesStroke(0, new BasicStroke(2));
        renderer.setSeriesStroke(1, new BasicStroke(2));
        renderer.setSeriesStroke(2, new BasicStroke(2));

        JFreeChart chart = ChartFactory.createXYLineChart("R&F", "Meses", "Poblacion", dataset);
        chart.getXYPlot().getRangeAxis().setRange(0, pob);
        ((NumberAxis) chart.getXYPlot().getRangeAxis()).setNumberFormatOverride(new DecimalFormat("#.0"));
        chart.getXYPlot().setRenderer(renderer);

        BufferedImage image = chart.createBufferedImage(600, 400);
        try {
            ImageIO.write(image, "png", new File("xy-chart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double[][] dividirDat(double[][] data, int i1) {
        double[][] aux = new double[data.length][2];
        for (int i = 0; i < data.length; i++) {
            aux[i][0] = data[i][0];
        }
        for (int i = 0; i < data.length; i++) {
            aux[i][1] = data[i][i1];
        }
        return Operaciones.transpuesta(aux);
    }

}
