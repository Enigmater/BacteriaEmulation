package gui.map;

import data.Bacteria;
import data.Food;
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
    public static ArrayList<Bacteria> redBactList;
    public static ArrayList<Bacteria> yellowBactList;
    public static ArrayList<Bacteria> blueBactList;
    public static ArrayList<Food> foodList;
    public static JLabel countRedInfo;
    public static JLabel countYellowInfo;
    public static JLabel countBlueInfo;
    public MapPanel() {
        redBactList = new ArrayList<>();
        yellowBactList = new ArrayList<>();
        blueBactList = new ArrayList<>();
        foodList = new ArrayList<>();
        setupView();
        setupLabel();
        Force.update();     // - delete all bacteria and generate new
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawBacteria(g, redBactList);
        drawBacteria(g, yellowBactList);
        drawBacteria(g, blueBactList);
        try {
            countRedInfo.setText("Count red: "  + Integer.toString(redBactList.size()));
            countYellowInfo.setText("Count yellow: "  + Integer.toString(yellowBactList.size()));
            countBlueInfo.setText("Count blue: " + Integer.toString(blueBactList.size()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        drawFood(g);
    }

    private void drawFood(Graphics g) {
        for (int i = 0; i < foodList.size(); i++) {
            Food fd = foodList.get(i);
            g.setColor(fd.COLOR[fd.type]);
            g.fillOval((int) fd.x - FOOD_RADIUS, (int) fd.y - FOOD_RADIUS, FOOD_RADIUS * 2, FOOD_RADIUS * 2);
        }
    }
    private void setupView() {
        setBorder(new BevelBorder(BevelBorder.RAISED));
        setBackground(Color.DARK_GRAY);
        setSize(WIDTH, HEIGHT);
    }
    private void setupLabel() {
        countRedInfo = new JLabel("Count red: " + Integer.toString(redBactList.size()));
        countRedInfo.setForeground(Color.WHITE);
        this.add(countRedInfo);
        countYellowInfo = new JLabel("Count yellow: " + Integer.toString(yellowBactList.size()));
        countYellowInfo.setForeground(Color.WHITE);
        this.add(countYellowInfo);
        countBlueInfo = new JLabel("Count blue: " + Integer.toString(blueBactList.size()));
        countBlueInfo.setForeground(Color.WHITE);
        this.add(countBlueInfo);
    }
    private void drawBacteria(Graphics g, ArrayList<Bacteria> bacteria) {
        for (int i = 0; i < bacteria.size(); i++) {
            Bacteria bact = bacteria.get(i);
            g.setColor(bact.color);
            g.fillOval((int) bact.x - RADIUS_BACTERIA, (int) bact.y - RADIUS_BACTERIA, RADIUS_BACTERIA * 2, RADIUS_BACTERIA * 2);
        }
    }
}
