package ru.cs.vsu.yachnyy_m_a.graphics;

import java.awt.*;

public class City {

    private int x, y;
    private int width, height;

    private static int BEACH_HEIGHT = 40;
    public static int SECTION_WIDTH;
    private static int[][] POLYGON_DELTAS = new int[][]{{0, -40}, {30, 0}, {0, -80}, {80, 0}, {0, 70}, {10, 0}, {0, -230},
            {100, 30}, {0, 160}, {20, 0}, {0, -100}, {100, 0}, {0, 80}, {20, 0}, {0, -90}, {100, 0}, {0, 50},
            {10,0}, {0, -100}, {60, 0}, {0, 100}, {60, -10}, {0, 60}, {20,0}, {0, -40}, {120, 0}, {0, -120},
            {150, 30}, {0, 200}, {40, 0}, {0, -80}, {20,0}, {0, -60}, {80, 0}, {80, 20}, {0, -60}, {80, 0}, {80, -80},
            {0, 250}, {10,0}, {0, -60}, {50, 0}, {0,60}, {10, 0}, {0, -60}, {120, -30}, {0, 60}, {30, 20}};

    static {
        SECTION_WIDTH = 0;
        for (int[] delta : POLYGON_DELTAS) {
            SECTION_WIDTH += delta[0];
        }
    }

    public City(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g2d) {

        g2d.setColor(new Color(168, 216, 255));
        g2d.fillRect(x, y, width, height);
        for (int Xlb = x; Xlb < x + width; Xlb += SECTION_WIDTH) {
            drawCitySection(Xlb, y + height - BEACH_HEIGHT, g2d);
        }
        g2d.setColor(new Color(252, 221, 118));
        g2d.fillRect(x, y + height - BEACH_HEIGHT, width, BEACH_HEIGHT);
    }

    private void drawCitySection(int Xlb, int Ylb, Graphics2D g2d) {
        Polygon polygon = new Polygon();
        polygon.addPoint(Xlb + SECTION_WIDTH, Ylb);
        polygon.addPoint(Xlb, Ylb);
        int Xc = Xlb;
        int Yc = Ylb;
        for (int[] delta : POLYGON_DELTAS) {
            Xc += delta[0];
            Yc += delta[1];
            polygon.addPoint(Xc, Yc);
        }
        g2d.setColor(new Color(0, 95, 158));
        g2d.fillPolygon(polygon);
    }

}
