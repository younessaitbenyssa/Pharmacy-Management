package org.example;


import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import BD.DBConnection;

public class PieChart extends JPanel {
    private static final long serialVersionUID = 6294689542092367723L;

    public PieChart(String title) {
        super();


        PieDataset dataset = createDataset();


        JFreeChart chart = ChartFactory.createPieChart(
                title,
                dataset,
                true,
                true,
                false
        );


        Color backgroundColor = Color.decode("#eeeeee");


        chart.setBackgroundPaint(backgroundColor);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(backgroundColor);
        plot.setOutlinePaint(null);


        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "{0}  ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        plot.setLabelGenerator(labelGenerator);


        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(backgroundColor);
        chartPanel.setPreferredSize(new java.awt.Dimension(300, 300));


        this.setLayout(new java.awt.BorderLayout());
        this.add(chartPanel, java.awt.BorderLayout.CENTER);
        this.setBackground(backgroundColor);
    }

    private PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        try (Connection conn = DBConnection.getConnection()) {

            int useId = Login.getid();


            String query = "SELECT categorie, stock FROM medicament WHERE user_id = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, useId);

                try (ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        String category = rs.getString("categorie");
                        double amount = rs.getDouble("stock");
                        dataset.setValue(category, amount);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataset;
    }



}
