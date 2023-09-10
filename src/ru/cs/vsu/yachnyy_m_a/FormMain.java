package ru.cs.vsu.yachnyy_m_a;

import ru.cs.vsu.yachnyy_m_a.graphics.DrawPanel;

import javax.swing.*;
import java.awt.*;

public class FormMain extends JFrame {

    private JPanel PanelMain;

    public FormMain() {
        this.setContentPane(PanelMain);
        this.setTitle("Task1");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        DrawPanel PanelPaint = new DrawPanel();

        PanelMain.add(PanelPaint);

        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    }
}
