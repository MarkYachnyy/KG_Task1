package ru.cs.vsu.yachnyy_m_a.graphics;

import java.awt.*;

public class Road {

    public static final int SECTION_WIDTH = 120;
    private static final int SIDEWALK_HEIGHT = 60;

    private int x,y;
    private int width, height;

    public Road(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g2d){
        for (int x0 = x; x0 < x + width; x0 += SECTION_WIDTH) {
            drawRoadSection(x0, y, g2d);
        }
    }

    private void drawRoadSection(int x0, int y0, Graphics2D g2d){
        g2d.setColor(new Color(150,150,150));
        g2d.fillRect(x0, y0, SECTION_WIDTH, SIDEWALK_HEIGHT);
        g2d.setColor(new Color(50,50,50));
        g2d.fillRect(x0, y0 + SIDEWALK_HEIGHT, SECTION_WIDTH, height - SIDEWALK_HEIGHT);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x0, y0 + SIDEWALK_HEIGHT + (height - SIDEWALK_HEIGHT) / 2 - 5, SECTION_WIDTH - 20, 10);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(x0, y0 - SIDEWALK_HEIGHT / 2, 7, SIDEWALK_HEIGHT);
        g2d.fillRect(x0, y0 - SIDEWALK_HEIGHT / 2, SECTION_WIDTH, 7);
    }
}
