package Fhulano;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import java.io.File;

import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.*;
import org.jfree.chart.plot.*;

import java.io.*;

public class XYChart {
    public static void main(String[] args) {
        double[][] data = {{2, 10}, {3, 10}, {4, 10},};
        grafo("Distribucion triangular", data, "Paso 1 del prestamo", "X", "Y", "XYchart.jpg");
    }

    public static void grafo(String serieNom, double[][] data, String title, String titleX, String titleY, String nomArch) {
        XYSeries series = new XYSeries(serieNom);
//        series.add(1, 10);
//        series.add(2, 20);
//        series.add(3, 10);
        for (double[] datum : data) {
            series.add(datum[0], datum[1]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createXYAreaChart(title, titleX, titleY, dataset, PlotOrientation.VERTICAL,
                true, true, false);
        try {
            ChartUtilities.saveChartAsJPEG(new File(nomArch), chart, 600,400);
        } catch (IOException e) {
            System.err.println("Error creando grafico.");
        }
    }

}
