package ru.cs.vsu.yachnyy_m_a;

import ru.cs.vsu.yachnyy_m_a.graphics.Bus;
import ru.cs.vsu.yachnyy_m_a.util.SwingUtils;

import javax.swing.*;
import java.awt.*;

public class FormMain extends JFrame {

    private JPanel PanelMain;
    private JButton ButtonPause;
    private JSpinner SpinnerBusSpeed;
    private JPanel PanelPaintContainer;
    private JPanel PanelPaint;

    public static final int PANEL_BORDER = 8;

    public FormMain() {
        this.setContentPane(PanelMain);
        this.setTitle("Task1");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        PanelPaint = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                SwingUtils.setFixedSize(PanelPaint, PanelPaintContainer.getWidth() - PANEL_BORDER, PanelPaintContainer.getHeight() - PANEL_BORDER);
                g2d.setColor(Color.BLUE);
                g2d.fillRect(0, 0, PanelPaint.getWidth(), PanelPaint.getHeight());
                Bus bus = new Bus(10, PanelPaint.getWidth() / 2, PanelPaint.getHeight() / 2 + 75, Color.WHITE);
                bus.draw(g2d);
                Bus bus1 = new Bus(12, PanelPaint.getWidth() / 2, PanelPaint.getHeight() / 2 + 600, Color.WHITE);
                bus1.draw(g2d);
            }
        };

        PanelPaintContainer.add(new JScrollPane(PanelPaint));
    }
}
