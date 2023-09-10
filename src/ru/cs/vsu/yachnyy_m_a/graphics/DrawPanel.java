package ru.cs.vsu.yachnyy_m_a.graphics;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class DrawPanel extends JPanel {

    public static final int PANEL_BORDER = 8;
    private static final int ROAD_HEIGHT = 500;
    private static final int RIVER_HEIGHT = 200;

    private Timer AnimationTimer;
    private int RoadOffset = 0;
    private int CityOffset = 0;

    private java.util.Queue<Boat> boats;
    private final double BoatSpawnChance = 0.005;

    public DrawPanel(){
        AnimationTimer = new Timer(15, a -> {
            RoadOffset = (RoadOffset + 20) % Road.SECTION_WIDTH;
            CityOffset = (CityOffset + 1) % City.SECTION_WIDTH;

            if(Math.random() < BoatSpawnChance){
                boats.add(new Boat(-Boat.WIDTH, this.getHeight() - ROAD_HEIGHT - RIVER_HEIGHT/2 + (RIVER_HEIGHT/4 - new Random().nextInt(RIVER_HEIGHT/2)), 2));
            }

            moveBoats();
            DrawPanel.this.repaint();
        });

        boats = new LinkedList<>();
        AnimationTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        City city = new City(CityOffset - City.SECTION_WIDTH,0, this.getWidth() + City.SECTION_WIDTH, this.getHeight() - ROAD_HEIGHT - RIVER_HEIGHT);
        city.draw(g2d);


        for(Boat boat: boats){
            boat.draw(g2d);
        }

        Road road = new Road(RoadOffset - Road.SECTION_WIDTH,this.getHeight() - ROAD_HEIGHT, this.getWidth() + Road.SECTION_WIDTH, ROAD_HEIGHT);
        road.draw(g2d);

        Bus bus = new Bus(10, this.getWidth() / 2, this.getHeight() - ROAD_HEIGHT / 4, Color.GREEN);
        bus.draw(g2d);
    }

    private void moveBoats(){
        for (Boat boat: boats){
            boat.setXlb(boat.getXlb() + boat.getVelocity());
        }
        if(boats.size() > 0 && boats.peek().getXlb() > this.getWidth()){
            boats.poll();
        }
    }
}
