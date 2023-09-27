package ru.cs.vsu.yachnyy_m_a.graphics;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class City {

    private int x, y;
    private int width, height;
    public LinkedList<Section> sections;
    private static final Random random = new Random();

    private static int BEACH_HEIGHT = 40;
    public static int SECTION_WIDTH;
    private static int[][] POLYGON_DELTAS = new int[][]{{0, -40}, {30, 0}, {0, -80}, {80, 0}, {0, 70}, {10, 0}, {0, -230},
            {100, 30}, {0, 160}, {20, 0}, {0, -100}, {100, 0}, {0, 80}, {20, 0}, {0, -90}, {100, 0}, {0, 50},
            {10, 0}, {0, -100}, {60, 0}, {0, 100}, {60, -10}, {0, 60}, {20, 0}, {0, -40}, {120, 0}, {0, -120},
            {150, 30}, {0, 200}, {40, 0}, {0, -80}, {20, 0}, {0, -60}, {80, 0}, {80, 20}, {0, -60}, {80, 0}, {80, -80},
            {0, 250}, {10, 0}, {0, -60}, {50, 0}, {0, 60}, {10, 0}, {0, -60}, {120, -30}, {0, 60}, {30, 20}};

    static {
        SECTION_WIDTH = 0;
        for (int[] delta : POLYGON_DELTAS) {
            SECTION_WIDTH += delta[0];
        }
    }

    public void setWidth(int width) {
        if (width > this.width) {
            sections = new LinkedList<>();
            for (int x0 = x - SECTION_WIDTH; x0 < x + width; x0 += SECTION_WIDTH) {
                Section section = new Section(x0, this.y + height);
                this.sections.add(section);
                System.out.println("city's height: " + height);
            }
        }
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private class Section {

        int Xlb, Ylb;

        private class Umbrella {

            final static int RADIUS = 8;
            int x, y;
            Color color;

            public void draw(Graphics2D g2d) {
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(x, y, x, y - 5);
                g2d.setColor(color);
                g2d.fillArc(x - RADIUS, y - 5 - RADIUS, RADIUS * 2, RADIUS * 2, 0, 180);
                g2d.setStroke(new BasicStroke());
            }

            public Umbrella(int x, int y, Color color) {
                this.x = x;
                this.y = y;
                this.color = color;
            }
        }

        private static class Sunbed {
            int x, y;
            Color color;

            public static final int WIDTH = 10;
            public static final int HEIGHT = 14;

            public void draw(Graphics2D g2d) {
                g2d.setColor(color);
                g2d.fillRect(x, y, WIDTH, HEIGHT);
            }

            public Sunbed(int x, int y, Color color) {
                this.x = x;
                this.y = y;
                this.color = color;
            }
        }

        private List<Umbrella> umbrellas;
        private List<Sunbed> sunbeds;

        public void draw(Graphics2D g2d) {
            Polygon polygon = new Polygon();
            polygon.addPoint(Xlb + SECTION_WIDTH, Ylb);
            polygon.addPoint(Xlb, Ylb);
            int Xc = Xlb;
            int Yc = Ylb - BEACH_HEIGHT;
            for (int[] delta : POLYGON_DELTAS) {
                Xc += delta[0];
                Yc += delta[1];
                polygon.addPoint(Xc, Yc);
            }
            g2d.setColor(new Color(0, 95, 158));
            g2d.fillPolygon(polygon);
            g2d.setColor(new Color(252, 221, 118));
            g2d.fillRect(this.Xlb, this.Ylb - BEACH_HEIGHT, SECTION_WIDTH, BEACH_HEIGHT);
            for (Sunbed sunbed : sunbeds) {
                sunbed.draw(g2d);
            }
            for (Umbrella umbrella : umbrellas) {
                umbrella.draw(g2d);
            }
        }

        public Section(int Xlb, int Ylb) {
            Section.this.Xlb = Xlb;
            Section.this.Ylb = Ylb;
            umbrellas = new LinkedList<>();
            sunbeds = new LinkedList<>();
            int n = 10;
            for (int i = 0; i < n; i++) {
                if (Math.random() < 0.7) {
                    Sunbed sunbed = new Sunbed(Xlb + SECTION_WIDTH * i / n + SECTION_WIDTH / 2 / n + Sunbed.WIDTH / 2 - random.nextInt(Sunbed.WIDTH),
                            Ylb - BEACH_HEIGHT / 2 - Sunbed.HEIGHT + random.nextInt(Sunbed.HEIGHT), randColor());
                    sunbeds.add(sunbed);
                    if (Math.random() < 0.5) {
                        umbrellas.add(new Umbrella(sunbed.x - 3 + random.nextInt(2) * (6 + Sunbed.WIDTH), sunbed.y + Sunbed.HEIGHT / 2, randColor()));
                    }
                }
            }
        }

        public void moveRight(int dx) {
            this.Xlb += dx;
            for (Sunbed sunbed : sunbeds) {
                sunbed.x += dx;
            }
            for (Umbrella umbrella: umbrellas){
                umbrella.x += dx;
            }
        }
    }

    public City(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sections = new LinkedList<>();
        for (int x0 = -SECTION_WIDTH; x0 < width; x0 += SECTION_WIDTH) {
            this.sections.add(new Section(x0, height));
        }
    }

    public void draw(Graphics2D g2d) {

        g2d.setColor(new Color(168, 216, 255));
        g2d.fillRect(x, y, width, height);
        for (Section section : this.sections) {
            section.draw(g2d);
        }
    }

    public void moveRight(int dx) {
        for (Section section : this.sections) {
            section.moveRight(dx);
        }
        if (sections.getLast().Xlb > x + this.width) {
            sections.removeLast();
        }
        if (sections.getFirst().Xlb >= x) {
            sections.addFirst(new Section(-SECTION_WIDTH, this.y + this.height));
        }
    }

    private Color randColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

}
