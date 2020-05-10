package Fhulano.Simulacion;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.List;

public class Graficator{



    public static ChartPanel graficar(List<Double> listvalores){
        XYSeries values=new XYSeries("Distribución normal");
        double value=0.0;
        for(int i=0;i<listvalores.size();i++){

            values.add(value,listvalores.get(i));
            value+=0.1;
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(values);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "Grafica",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = xylineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(1.0f));
        plot.setRenderer(renderer);

        ChartPanel panel = new ChartPanel(xylineChart);
        return  panel;
    }

    public static ChartPanel graficar2(List<Double> listvalores,List<Double> xList){
        XYSeries values=new XYSeries("Distribución normal");
        for(int i=0;i<listvalores.size();i++){
            values.add(xList.get(i),listvalores.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(values);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "Grafica",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = xylineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(1.0f));
        plot.setRenderer(renderer);

        ChartPanel panel = new ChartPanel(xylineChart);
        return  panel;
    }

}


