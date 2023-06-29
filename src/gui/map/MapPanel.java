package gui.map;

import data.Bacteria;
import data.Food;
import data.RedBact;
import logic.Force;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MapPanel extends JPanel {


    public static final int RADIUS_BACTERIA = 10;
    public static final int FOOD_RADIUS = 10;
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 750;


    private Random random = new Random();
    public static ArrayList<Bacteria> red;
    public static ArrayList<Bacteria> yellow;
    public static ArrayList<Food> food;
    public MapPanel() {
        red = new ArrayList<>();
        yellow = new ArrayList<>();
        food = new ArrayList<>();
        setupView();
        // Force.update() - delete all bacteria and generate new
        Force.update();
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawBacteria(g, red);
        drawBacteria(g, yellow);
        drawFood(g);
    }

    private void drawFood(Graphics g) {
        for (int i = 0; i < food.size(); i++) {
            Food fd = food.get(i);
            g.setColor(fd.COLOR[fd.type]);
            g.fillOval((int) fd.x - FOOD_RADIUS, (int) fd.y - FOOD_RADIUS, FOOD_RADIUS * 2, FOOD_RADIUS * 2);
        }
    }

    private void setupView() {
        setBorder(new BevelBorder(BevelBorder.RAISED));
        setSize(WIDTH, HEIGHT);
    }



    private void drawBacteria(Graphics g, ArrayList<Bacteria> bacteria) {
        for (int i = 0; i < bacteria.size(); i++) {
            Bacteria bact = bacteria.get(i);
            g.setColor(bact.color);
            g.fillOval((int) bact.x - RADIUS_BACTERIA, (int) bact.y - RADIUS_BACTERIA, RADIUS_BACTERIA * 2, RADIUS_BACTERIA * 2);
        }
    }
}
