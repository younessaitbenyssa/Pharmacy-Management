package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

class RoundedLabel extends JLabel {
    private int arcWidth;
    private int arcHeight;

    public RoundedLabel(int arcWidth, int arcHeight) {
        super();
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(false); // Allow custom painting
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw rounded background
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcWidth, arcHeight));

        super.paintComponent(g);
    }

}

