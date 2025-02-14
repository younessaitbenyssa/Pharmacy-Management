package org.example;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

public class ChartC extends JPanel {

    private static final long serialVersionUID = 1L;

    public ChartC() {

        CategoryDataset dataset = createDataset();


        JFreeChart chart = ChartFactory.createBarChart(
                "Ventes de la semaine dernière",
                "Jours",
                "Nombre d'unités vendues",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );


        CategoryPlot plot = chart.getCategoryPlot();


        plot.setBackgroundPaint(Color.decode("#eeeeee"));


        chart.setBackgroundPaint(Color.decode("#eeeeee"));


        plot.setDomainGridlinePaint(Color.decode("#eeeeee"));
        plot.setRangeGridlinePaint(Color.decode("#eeeeee"));


        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setAxisLineVisible(false);
        domainAxis.setTickMarksVisible(false);

        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setAxisLineVisible(false);
        rangeAxis.setTickMarksVisible(false);


        BarRenderer renderer = new CustomBarRenderer();
        plot.setRenderer(renderer);


        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(Color.decode("#eeeeee"));

        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        
        dataset.addValue(10, "Ventes", "Lundi");
        dataset.addValue(15, "Ventes", "Mardi");
        dataset.addValue(20, "Ventes", "Mercredi");
        dataset.addValue(18, "Ventes", "Jeudi");
        dataset.addValue(25, "Ventes", "Vendredi");
        dataset.addValue(12, "Ventes", "Dimanche");

        return dataset;
    }

    static class CustomBarRenderer extends BarRenderer {
        private static final Color[] COLORS = {
                Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE, Color.CYAN, Color.MAGENTA
        };

        @Override
        public Paint getItemPaint(int row, int column) {
            return COLORS[column % COLORS.length];
        }
    }
}
