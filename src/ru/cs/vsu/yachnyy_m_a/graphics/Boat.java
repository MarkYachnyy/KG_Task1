package ru.cs.vsu.yachnyy_m_a.graphics;

import java.awt.*;

public class Boat {

    private int xlb;
    private int ylb;
    private int velocity;
    public static int WIDTH = 70;
    private static int HEIGHT = WIDTH / 2;

    public Boat(int xlb, int ylb, int velocity) {
        this.xlb = xlb;
        this.ylb = ylb;
        this.velocity = velocity;
    }

    public int getXlb() {
        return xlb;
    }

    public void setXlb(int xlb) {
        this.xlb = xlb;
    }

    public void setYlb(int ylb) {
        this.ylb = ylb;
    }

    public int getVelocity() {
        return velocity;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(new Color(200, 200, 200));
        Polygon polygon1 = new Polygon();
        polygon1.addPoint(xlb, ylb);
        polygon1.addPoint(xlb, ylb - HEIGHT / 2);
        polygon1.addPoint(xlb + WIDTH, ylb - HEIGHT / 2);
        polygon1.addPoint(xlb + WIDTH - HEIGHT / 2, ylb);
        g2d.fillPolygon(polygon1);
        g2d.setColor(Color.WHITE);
        Polygon polygon2 = new Polygon();
        polygon2.addPoint(xlb + HEIGHT / 4, ylb - HEIGHT / 2);
        polygon2.addPoint(xlb + HEIGHT / 4, ylb - HEIGHT);
        polygon2.addPoint(xlb + HEIGHT / 4 + WIDTH / 2, ylb - HEIGHT);
        polygon2.addPoint(xlb + HEIGHT / 4 + WIDTH / 2 + HEIGHT / 4, ylb - HEIGHT / 2);
        g2d.fillPolygon(polygon2);
    }

}
