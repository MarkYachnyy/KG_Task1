package ru.cs.vsu.yachnyy_m_a.graphics;

import java.awt.*;

public class Bus {
    private int route, x, y;
    private Color main_color;

    private static int BUS_LENGTH = 600;
    private static int BUS_HEIGHT = 200;
    private static int WHEEL_RADIUS = 35;
    private static int DOOR_WIDTH = 60;
    private static int WHEEL_ARC_WIDTH = 10;
    private static int WHEEL_GAP = 5;

    public Bus(int route, int x, int y, Color main_color) {
        this.route = route;
        this.x = x;
        this.y = y;
        this.main_color = main_color;
    }

    public void draw(Graphics2D g2d) {

        g2d.setColor(main_color);
        Polygon polygon1 = new Polygon();
        polygon1.addPoint(x - BUS_LENGTH / 2, y);
        polygon1.addPoint(x - BUS_LENGTH / 2, y - 50);
        polygon1.addPoint(x - BUS_LENGTH / 2 + 5, y - BUS_HEIGHT);
        polygon1.addPoint(x + BUS_LENGTH / 2 - 5, y - BUS_HEIGHT);
        polygon1.addPoint(x + BUS_LENGTH / 2, y - BUS_HEIGHT + 50);
        polygon1.addPoint(x + BUS_LENGTH / 2, y - 10);
        polygon1.addPoint(x + BUS_LENGTH / 2 - 40, y);
        g2d.fillPolygon(polygon1);

        g2d.setColor(Color.BLACK);
        g2d.fillArc(x - BUS_LENGTH / 2 + 40, y - WHEEL_RADIUS - WHEEL_GAP - WHEEL_ARC_WIDTH - 1,
                (WHEEL_RADIUS + WHEEL_GAP + WHEEL_ARC_WIDTH + 1) * 2,
                (WHEEL_RADIUS + WHEEL_GAP + WHEEL_ARC_WIDTH + 1) * 2, 0, 180);


        g2d.fillArc(x + BUS_LENGTH / 2 - 40 - DOOR_WIDTH - 20 - (WHEEL_RADIUS + WHEEL_GAP + WHEEL_ARC_WIDTH + 1) * 2,
                y - WHEEL_RADIUS - WHEEL_GAP - WHEEL_ARC_WIDTH - 1,
                (WHEEL_RADIUS + WHEEL_GAP + WHEEL_ARC_WIDTH + 1) * 2,
                (WHEEL_RADIUS + WHEEL_GAP + WHEEL_ARC_WIDTH + 1) * 2, 0, 180);

        g2d.setColor(main_color);

        g2d.fillArc(x - BUS_LENGTH / 2 + 40 + 1, y - WHEEL_RADIUS - WHEEL_GAP - WHEEL_ARC_WIDTH,
                (WHEEL_RADIUS + WHEEL_GAP + WHEEL_ARC_WIDTH) * 2,
                (WHEEL_RADIUS + WHEEL_GAP + WHEEL_ARC_WIDTH) * 2, 0, 180);


        g2d.fillArc(x + BUS_LENGTH / 2 - 40 - DOOR_WIDTH - 20 - (WHEEL_RADIUS + WHEEL_GAP + WHEEL_ARC_WIDTH) * 2 - 1,
                y - WHEEL_RADIUS - WHEEL_GAP - WHEEL_ARC_WIDTH,
                (WHEEL_RADIUS + WHEEL_GAP + WHEEL_ARC_WIDTH) * 2,
                (WHEEL_RADIUS + WHEEL_GAP + WHEEL_ARC_WIDTH) * 2, 0, 180);

        g2d.setColor(new Color(70,70,70));

    }
}
