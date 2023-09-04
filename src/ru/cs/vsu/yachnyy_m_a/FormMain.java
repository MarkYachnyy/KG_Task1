package ru.cs.vsu.yachnyy_m_a;

import ru.cs.vsu.yachnyy_m_a.util.SwingUtils;

import javax.swing.*;
import java.awt.*;

public class FormMain extends JFrame{
    private JPanel PanelMain;
    private JButton ButtonPause;
    private JSpinner SpinnerBusSpeed;
    private JPanel PanelPaintContainer;
    private JPanel PanelPaint;

    public FormMain(){
        this.setContentPane(PanelMain);
        this.setTitle("Task1");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        PanelPaint = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                SwingUtils.setFixedSize(PanelPaint, PanelPaintContainer.getWidth(), PanelPaintContainer.getHeight());
                g2d.setColor(Color.RED);
                g2d.fillRect(0,0, PanelPaint.getWidth(), PanelPaint.getHeight());
            }
        };

        PanelPaintContainer.add(PanelPaint);
    }
}
