package logic;

import data.Bacteria;
import gui.main.MainPanel;
import gui.control.PhysParam;
import gui.map.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Force {
    private static Timer timer;
    private static int TIMER_DELAY = 5;
    private static final float SPEED = 4f;
    private static final float BORDER = 50;

    //
    private static float GRAVITY = 5;
    private static float RADIUS_INTERACTION = 100;

    public static void update() {
        // get count red bacteria
        int countRed = 200;
        try {
            countRed = MainPanel.bacteriaParamPanel.getCountRed();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        // generate red
        deleteBacteria(MainPanel.mapPanel.red);
        generateBacteria(MainPanel.mapPanel.red, countRed, Color.red);

        // get count yellow bacteria
        int countYellow = 200;
        try {
            countYellow = MainPanel.bacteriaParamPanel.getCountYellow();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        // generate yellow
        deleteBacteria(MainPanel.mapPanel.yellow);
        generateBacteria(MainPanel.mapPanel.yellow, countYellow, Color.yellow);
    }
    public static void start() {
        if (timer == null) {
            timer = new Timer(TIMER_DELAY, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setParameters();
                    // if g > 0 - repulsion
                    // else - attractive
                    gravityRule(MainPanel.mapPanel.red, MainPanel.mapPanel.red, GRAVITY * -0.07f);          // red attractive to red
                    gravityRule(MainPanel.mapPanel.red, MainPanel.mapPanel.yellow, GRAVITY * -0.01f);       // red attractive to yellow
                    gravityRule(MainPanel.mapPanel.yellow, MainPanel.mapPanel.red, GRAVITY * 0.01f);        // yellow repulsion to red
                    MainPanel.mapPanel.repaint();
                }
            });
        }
        timer.start();
    }
    public static void pause() {
        timer.stop();
    }
    public static void stop() {
        timer.stop();
        update();
    }
    public static void generateBacteria(ArrayList<Bacteria> bacteria, int countBacteria, Color color){
        for (int i = 0; i < countBacteria; i++) {
            // generate coordinate
            float x = (float) (Math.random() * MapPanel.WIDTH) + 25;
            float y = (float) (Math.random() * MapPanel.HEIGHT) + 25;
            // add bacteria in list
            bacteria.add(new Bacteria(x, y, color));
        }
    }

    // attraction and repulsion for bacteria
    // F = G * 1 / r
    public static void gravityRule(ArrayList<Bacteria> bact1, ArrayList<Bacteria> bact2, float G) {
        for (int i = 0; i < bact1.size(); i++) {
            float fx = 0;
            float fy = 0;
            Bacteria a = bact1.get(i);
            for (int j = 0; j < bact2.size(); j++) {
                if (i != j) {
                    Bacteria b = bact2.get(j);
                    // find radius between a & b
                    float rx = a.x - b.x;
                    float ry = a.y - b.y;
                    float r = (float) (Math.sqrt(rx * rx + ry * ry));
                    if (r > 0 && r < RADIUS_INTERACTION) {
                        float F = G / r;
                        fx += F * rx;
                        fy += F * ry;
                    }
                }
            }
            a.dirX = (a.dirX + fx) * 0.5f;
            a.dirY = (a.dirY + fy) * 0.5f;
            a.x += a.dirX;
            a.y += a.dirY;

            borderRepulsion(a);
        }
    }
    private static void borderRepulsion(Bacteria a) {
        int mapPanelWidth = MainPanel.mapPanel.getWidth();
        int mapPanelHeight = MainPanel.mapPanel.getHeight();
        // border repulsion
        if (a.x < BORDER) {
            a.dirX += SPEED * 0.05f;
            if (a.x < 0) {
                a.x = -a.x + MapPanel.RADIUS_BACTERIA;
                a.dirX *= -0.5f;
            }
        }
        else if (a.x > mapPanelWidth - BORDER) {
            a.dirX -= SPEED * 0.05f;
            if (a.x > mapPanelWidth) {
                a.x = mapPanelWidth * 2 - a.x - MapPanel.RADIUS_BACTERIA;
                a.dirX *= -0.5f;
            }
        }

        if (a.y < BORDER) {
            a.dirY += SPEED * 0.05f;
            if(a.y < 0) {
                a.y = -a.y + MapPanel.RADIUS_BACTERIA;
                a.dirY *= -0.5f;
            }
        }
        else if (a.y > mapPanelHeight - BORDER) {
            a.dirY -= SPEED * 0.05f;
            if (a.y > mapPanelHeight) {
                a.y = mapPanelHeight * 2 - a.y - MainPanel.mapPanel.RADIUS_BACTERIA;
                a.dirY *= -0.5f;
            }
        }
    }
    private static void deleteBacteria(ArrayList<Bacteria> bacteria) {
        bacteria.clear();
    }

    private static void setParameters() {
        setGravity();
        setRadiusInteraction();
        setTimerDelay();
    }

    private static void setRadiusInteraction() {
        RADIUS_INTERACTION = MainPanel.physParamPanel.getRadiusInteractionValue();
    }

    private static void setGravity() {
        GRAVITY = MainPanel.physParamPanel.getGravityValue();
    }

    private static void setTimerDelay() {
        TIMER_DELAY = MainPanel.controlPanel.getTimerDelay();
        timer.setDelay(TIMER_DELAY);
    }
}
