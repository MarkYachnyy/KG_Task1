package ru.cs.vsu.yachnyy_m_a.graphics;

import ru.cs.vsu.yachnyy_m_a.util.DrawUtils;

import java.awt.*;

public class Bus {
    private int route, x, y;
    private Color MAIN_COLOR;
    private Color GLASS_COLOR = new Color(168, 216, 255, 150);
    private Color SKIN_COLOR = new Color(246, 215, 197);
    private Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, LABEL_HEIGHT - 10);

    private static int BUS_LENGTH = 700;
    private static int BUS_HEIGHT = 200;
    private static int WHEEL_RADIUS = 37;
    private static int TYRE_THICKNESS = 10;
    private static int DOOR_WIDTH = 80;
    private static int WHEEL_ARC_THICKNESS = 10;
    private static int WHEEL_GAP = 5;
    private static int ARC_RADIUS = WHEEL_RADIUS + WHEEL_GAP + WHEEL_ARC_THICKNESS + 1;
    private static int OFFSET_FRONT_BACK = 5;
    private static int WINDOW_FRAME_THICKNESS = 4;
    private static int LABEL_HEIGHT = 30;
    private static int LABEL_WIDTH = 150;
    private static int BACK_LIGHT_RADIUS = 10;
    private static int FRONT_LIGHT_HEIGHT = 20;


    public Bus(int route, int x, int y, Color main_color) {
        this.route = route;
        this.x = x;
        this.y = y;
        this.MAIN_COLOR = main_color;
    }

    public void draw(Graphics2D g2d) {
        drawBusSide(g2d, true, false);
        drawBusSide(g2d, false, true);
    }

    private void drawWheelAndArc(int x0, int y0, Graphics g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillArc(x0 - ARC_RADIUS, y0 - ARC_RADIUS,
                (ARC_RADIUS) * 2,
                (ARC_RADIUS) * 2, 0, 180);

        g2d.setColor(MAIN_COLOR);

        g2d.fillArc(x0 - ARC_RADIUS + 1, y - ARC_RADIUS + 1,
                (ARC_RADIUS - 1) * 2,
                (ARC_RADIUS - 1) * 2, 0, 180);

        g2d.setColor(new Color(70, 70, 70));

        g2d.fillArc(x0 - ARC_RADIUS + 1 + WHEEL_ARC_THICKNESS, y - ARC_RADIUS + 1 + WHEEL_ARC_THICKNESS,
                (ARC_RADIUS - 1 - WHEEL_ARC_THICKNESS) * 2,
                (ARC_RADIUS - 1 - WHEEL_ARC_THICKNESS) * 2, 0, 180);

        g2d.setColor(Color.BLACK);

        g2d.fillOval(x0 - WHEEL_RADIUS, y - WHEEL_RADIUS, WHEEL_RADIUS * 2, WHEEL_RADIUS * 2);

        g2d.setColor(new Color(150, 150, 150));
        g2d.fillOval(x0 - WHEEL_RADIUS + TYRE_THICKNESS, y - WHEEL_RADIUS + TYRE_THICKNESS, WHEEL_RADIUS * 2 - TYRE_THICKNESS * 2, WHEEL_RADIUS * 2 - TYRE_THICKNESS * 2);

    }

    private void drawLabel(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        int label_x = x + BUS_LENGTH / 2 - 40 - OFFSET_FRONT_BACK - DOOR_WIDTH - LABEL_WIDTH - 10;
        int label_y = y - ARC_RADIUS - 20 - WINDOW_FRAME_THICKNESS - LABEL_HEIGHT;
        g2d.fillRect(label_x, label_y, LABEL_WIDTH, LABEL_HEIGHT);

        g2d.setColor(Color.BLACK);
        DrawUtils.drawStringInCenter(g2d, DEFAULT_FONT, String.valueOf(route), new Rectangle(label_x, label_y, LABEL_HEIGHT, LABEL_HEIGHT));
        DrawUtils.drawStringInCenter(g2d, DEFAULT_FONT, "Таск на 5", new Rectangle(label_x + LABEL_HEIGHT, label_y, LABEL_WIDTH - LABEL_HEIGHT, LABEL_HEIGHT));
        g2d.drawLine(label_x + LABEL_HEIGHT, label_y, label_x + LABEL_HEIGHT, label_y + LABEL_HEIGHT);
    }

    private void drawLights(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillArc(x + BUS_LENGTH / 2 - BACK_LIGHT_RADIUS, y - BACK_LIGHT_RADIUS * 2 - 20, BACK_LIGHT_RADIUS * 2, BACK_LIGHT_RADIUS * 2, 90, 180);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x - BUS_LENGTH / 2, y - 40 - FRONT_LIGHT_HEIGHT, FRONT_LIGHT_HEIGHT, FRONT_LIGHT_HEIGHT);
        g2d.fillArc(x - BUS_LENGTH / 2, y - 40 - FRONT_LIGHT_HEIGHT * 2, FRONT_LIGHT_HEIGHT * 2, FRONT_LIGHT_HEIGHT * 2, -90, 90);
    }

    private void drawDoor(int x0, int y0, int width, int height, Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(x0, y0, width, WINDOW_FRAME_THICKNESS * 2);
        g2d.fillRect(x0, y0, WINDOW_FRAME_THICKNESS * 2, height);
        g2d.fillRect(x0, y0 + height - WINDOW_FRAME_THICKNESS * 2, width, WINDOW_FRAME_THICKNESS * 2);
        g2d.fillRect(x0 + width - WINDOW_FRAME_THICKNESS * 2, y0, WINDOW_FRAME_THICKNESS * 2, height);
//        g2d.setColor(GLASS_COLOR);
//        g2d.fillRect(x0 + WINDOW_FRAME_THICKNESS * 2, y0 + WINDOW_FRAME_THICKNESS * 2,
//                width - WINDOW_FRAME_THICKNESS * 4, height - WINDOW_FRAME_THICKNESS * 4);
    }

    private void drawBusSide(Graphics2D g2d, boolean drawDoors, boolean drawSeats) {

        if (drawSeats) {
            for (int x0 = x - BUS_LENGTH / 2 + 180; x0 < x + BUS_LENGTH / 2 - 100; x0 += 80) {
                drawSeat(g2d, x0, y - ARC_RADIUS - 20, false);
            }

            int x0 = x - BUS_LENGTH / 2 + 70;

            drawSeat(g2d, x0, y - ARC_RADIUS - 20, true);
        }

        g2d.setStroke(new BasicStroke());
        g2d.setColor(MAIN_COLOR);

        Polygon colorPolygon1 = new Polygon();
        colorPolygon1.addPoint(x - BUS_LENGTH / 2, y);
        colorPolygon1.addPoint(x - BUS_LENGTH / 2, y - ARC_RADIUS - 20);
        colorPolygon1.addPoint(x + BUS_LENGTH / 2, y - ARC_RADIUS - 20);
        colorPolygon1.addPoint(x + BUS_LENGTH / 2, y - 10);
        colorPolygon1.addPoint(x + BUS_LENGTH / 2 - 40, y);
        g2d.fillPolygon(colorPolygon1);

        Polygon colorPolygon2 = new Polygon();
        colorPolygon2.addPoint(x - BUS_LENGTH / 2, y - ARC_RADIUS - 20);
        colorPolygon2.addPoint(x - BUS_LENGTH / 2 + OFFSET_FRONT_BACK, y - BUS_HEIGHT);
        colorPolygon2.addPoint(x + BUS_LENGTH / 2 - OFFSET_FRONT_BACK, y - BUS_HEIGHT);
        colorPolygon2.addPoint(x + BUS_LENGTH / 2, y - BUS_HEIGHT + 50);
        colorPolygon2.addPoint(x + BUS_LENGTH / 2, y - ARC_RADIUS - 20);
        colorPolygon2.addPoint(x + BUS_LENGTH / 2 - OFFSET_FRONT_BACK * 3, y - ARC_RADIUS - 20);
        colorPolygon2.addPoint(x + BUS_LENGTH / 2 - OFFSET_FRONT_BACK * 3, y - BUS_HEIGHT + OFFSET_FRONT_BACK * 2);
        colorPolygon2.addPoint(x - BUS_LENGTH / 2 + OFFSET_FRONT_BACK * 3, y - BUS_HEIGHT + OFFSET_FRONT_BACK * 2);
        colorPolygon2.addPoint(x - BUS_LENGTH / 2 + OFFSET_FRONT_BACK * 2, y - ARC_RADIUS - 20);
        g2d.fillPolygon(colorPolygon2);

        drawWheelAndArc(x - BUS_LENGTH / 2 + OFFSET_FRONT_BACK + 40 + ARC_RADIUS, y, g2d);
        drawWheelAndArc(x + BUS_LENGTH / 2 - OFFSET_FRONT_BACK - 50 - DOOR_WIDTH - ARC_RADIUS, y, g2d);


        Polygon window_polygon = new Polygon();
        window_polygon.addPoint(x - BUS_LENGTH / 2 + OFFSET_FRONT_BACK * 3, y - BUS_HEIGHT + OFFSET_FRONT_BACK * 2);
        window_polygon.addPoint(x - BUS_LENGTH / 2 + OFFSET_FRONT_BACK * 2, y - ARC_RADIUS - 20);
        window_polygon.addPoint(x + BUS_LENGTH / 2 - OFFSET_FRONT_BACK * 3, y - ARC_RADIUS - 20);
        window_polygon.addPoint(x + BUS_LENGTH / 2 - OFFSET_FRONT_BACK * 3, y - BUS_HEIGHT + OFFSET_FRONT_BACK * 2);

        g2d.setColor(GLASS_COLOR);
        g2d.fillPolygon(window_polygon);

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawPolygon(window_polygon);
        for (int x0 = x - BUS_LENGTH / 2 + 100; x0 < x + BUS_LENGTH / 2; x0 += 125) {
            g2d.drawLine(x0, y - BUS_HEIGHT + OFFSET_FRONT_BACK * 2, x0, y - ARC_RADIUS - 20);
        }


        g2d.setStroke(new BasicStroke());

        if (drawDoors) {
            drawDoor(x - BUS_LENGTH / 2 + OFFSET_FRONT_BACK + 40 + ARC_RADIUS * 2 + 20, y - BUS_HEIGHT + OFFSET_FRONT_BACK * 2, DOOR_WIDTH, BUS_HEIGHT - 2 * OFFSET_FRONT_BACK -
                    WINDOW_FRAME_THICKNESS, g2d);
            drawDoor(x + BUS_LENGTH / 2 - 40 - OFFSET_FRONT_BACK - DOOR_WIDTH, y - BUS_HEIGHT + OFFSET_FRONT_BACK * 2, DOOR_WIDTH, BUS_HEIGHT - 2 * OFFSET_FRONT_BACK -
                    WINDOW_FRAME_THICKNESS, g2d);
        }

        drawLights(g2d);
    }

    private void drawSeat(Graphics2D g2d, int x0, int y0, boolean driver) {

        g2d.setStroke(new BasicStroke());
        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(new Polygon(new int[]{x0 - 20, x0, x0 + 11, x0 - 2}, new int[]{y0, y0, y0 - 40, y0 - 45}, 4));
        int r = 7;
        g2d.fillOval(x0 + 5 - r, y0 - 42 - r, 2 * r, 2 * r);
        r = 14;
        g2d.setColor(SKIN_COLOR);
        g2d.fillOval(x0 + 2 - r, y0 - 55 - r, 2 * r, 2 * r);
        if (driver) {
            g2d.setColor(Color.WHITE);
            g2d.fillPolygon(new int[]{x0 + 2 - r * 2 / 3, x0 + 2 - r, x0 + 2 + r, x0 + 2 + r /3}, new int[]{y0 - 55 - r * 2 / 3, y0 - 55 - r, y0 - 55 - r, y0 - 55 - r * 2 / 3}, 4);
        }

        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(Color.BLUE);
        g2d.drawLine(x0, y0, x0 + 20, y0 - 60);

        if(driver){
            g2d.setColor(Color.BLACK);
            g2d.drawLine(x0 - 30, y0, x0 - 40, y0 - 30);
            g2d.setStroke(new BasicStroke(7));
            g2d.drawLine(x0 - 35, y0 - 15, x0 - 55, y0);
        }

    }
}
