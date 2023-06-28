package logic;

import data.Bacteria;
import gui.main.MainPanel;
import gui.info.CellInfoPanel;
import gui.map.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Force {
    private static Timer timer;
    private static int timerDelay = 5;
    private static final int COUNT_BACTERIA = 200;
    private static final float SPEED = 4f;
    private static final float BORDER = 50;
    private static final float GRAVITY = 5;
    public static void update() {
        deleteBacteria(MainPanel.mapPanel.red);
        generateBacteria(MainPanel.mapPanel.red, Color.red);

        deleteBacteria(MainPanel.mapPanel.yellow);
        generateBacteria(MainPanel.mapPanel.yellow, Color.yellow);
    }
    public static void start() {
        if (timer == null) {
            timer = new Timer(timerDelay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //moveBacteria(MainPanel.mapPanel.black);
                    gravityRule(MainPanel.mapPanel.red, MainPanel.mapPanel.red, GRAVITY * -0.1f);
                    gravityRule(MainPanel.mapPanel.red, MainPanel.mapPanel.yellow, GRAVITY * -0.01f);
                    gravityRule(MainPanel.mapPanel.yellow, MainPanel.mapPanel.red, GRAVITY * 0.01f);
                    //gravityRule(MainPanel.mapPanel.yellow, MainPanel.mapPanel.yellow, -0.01f);
                    MainPanel.mapPanel.repaint();
                    CellInfoPanel.update();
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

    public static void generateBacteria(ArrayList<Bacteria> bacteria, Color color){
        for (int i = 0; i < COUNT_BACTERIA; i++) {
            // generate coordinate
            float x = (float) (Math.random() * MapPanel.WIDTH) + 25;
            float y = (float) (Math.random() * MapPanel.HEIGHT) + 25;
            // add bacteria in list
            bacteria.add(new Bacteria(x, y, color));
        }
    }

    // Average speed all bacteria
    public static float averageSpeed(ArrayList<Bacteria> bacteria) {
        float magnitude = 0;
        for (int i = 0; i < bacteria.size(); i++) {
            Bacteria bact = bacteria.get(i);
            magnitude += (float)Math.sqrt(bact.dirX * bact.dirX + bact.dirY * bact.dirY);
        }
        return magnitude / 20;
    }
    public static void moveBacteria(ArrayList<Bacteria> bacteria){
        MapPanel mapPanel = MainPanel.mapPanel;
        for (int i = 0; i < bacteria.size(); i++) {
            Bacteria bact = bacteria.get(i);
            bact.x += bact.dirX;
            bact.y += bact.dirY;
            //bact.dirX = (float)(Math.random() * 2 + 1);
            //bact.dirY = (float)(Math.random() * 2 + 1);

            // normalization speed
            /*float magnitude = (float)Math.sqrt(bact.dirX * bact.dirX + bact.dirY * bact.dirY);
            if(magnitude > 5f) {
                bact.dirX /= magnitude;
                bact.dirY /= magnitude;
            }*/

            // border repulsion
            if (bact.x < BORDER) {
                bact.dirX += SPEED * 0.05f;
                if (bact.x < 0) {
                    bact.x = -bact.x + MapPanel.RADIUS_BACTERIA;
                    bact.dirX *= -0.5f;
                }
            }
            else if (bact.x > mapPanel.getWidth() - BORDER) {
                bact.dirX -= SPEED * 0.05f;
                if (bact.x > mapPanel.getWidth()) {
                    bact.x = mapPanel.getWidth() * 2 - bact.x - MapPanel.RADIUS_BACTERIA;
                    bact.dirX *= -0.5f;
                }
            }

            if (bact.y < BORDER) {
                bact.dirY += SPEED * 0.05f;
                if(bact.y < 0) {
                    bact.y = -bact.y + MapPanel.RADIUS_BACTERIA;
                    bact.dirY *= -0.5f;
                }
            }
            else if (bact.y > mapPanel.getHeight() - BORDER) {
                bact.dirY -= SPEED * 0.05f;
                if (bact.y > mapPanel.getHeight()) {
                    int mapPanelHeight = MainPanel.controlPanel.getHeight();
                    bact.y = mapPanel.getHeight() * 2 - bact.y - 100 - mapPanel.RADIUS_BACTERIA;
                    bact.dirY *= -0.5f;
                }
            }
            bacteria.set(i, bact);
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
                    if (r > MapPanel.RADIUS_BACTERIA * 2 && r < 160) {
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
        // border repulsion
        if (a.x < BORDER) {
            a.dirX += SPEED * 0.05f;
            if (a.x < 0) {
                a.x = -a.x + MapPanel.RADIUS_BACTERIA;
                a.dirX *= -0.5f;
            }
        }
        else if (a.x > MainPanel.mapPanel.getWidth() - BORDER) {
            a.dirX -= SPEED * 0.05f;
            if (a.x > MainPanel.mapPanel.getWidth()) {
                a.x = MainPanel.mapPanel.getWidth() * 2 - a.x - MapPanel.RADIUS_BACTERIA;
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
        else if (a.y > MainPanel.mapPanel.getHeight() - BORDER) {
            a.dirY -= SPEED * 0.05f;
            if (a.y > MainPanel.mapPanel.getHeight()) {
                int mapPanelHeight = MainPanel.controlPanel.getHeight();
                a.y = MainPanel.mapPanel.getHeight() * 2 - a.y - 100 - MainPanel.mapPanel.RADIUS_BACTERIA;
                a.dirY *= -0.5f;
            }
        }
    }
    private static void deleteBacteria(ArrayList<Bacteria> bacteria) {
        bacteria.clear();
    }
}
