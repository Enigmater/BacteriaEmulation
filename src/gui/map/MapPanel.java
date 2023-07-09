package gui.map;

import data.Bacteria.Bacteria;
import data.Food;
import data.Bacteria.GreenBact;
import gui.main.MainPanel;
import logic.Force;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.ArrayList;

public class MapPanel extends JPanel {
    public static int RADIUS_BACTERIA = 5;
    public static int FOOD_RADIUS = 5;
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 750;

    public static ArrayList<Bacteria> redBactList;
    public static ArrayList<Bacteria> yellowBactList;
    public static ArrayList<Bacteria> blueBactList;
    public static ArrayList<Bacteria> greenBactList;
    public static ArrayList<Food> foodList;
    public static JLabel countRedInfo;
    public static JLabel countYellowInfo;
    public static JLabel countBlueInfo;
    public static JLabel countGreenInfo;
    public MapPanel() {
        redBactList = new ArrayList<>();
        yellowBactList = new ArrayList<>();
        blueBactList = new ArrayList<>();
        greenBactList = new ArrayList<>();
        foodList = new ArrayList<>();
        setupView();
        setupLabel();
        Force.update();     // - delete all bacteria and generate new
        setVisible(true);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        RADIUS_BACTERIA = MainPanel.physParamPanel.getRadiusBacteria();
        FOOD_RADIUS = MainPanel.physParamPanel.getRadiusFood();
        drawFood(g);
        drawBacteria(g, greenBactList);
        drawBacteria(g, redBactList);
        drawBacteria(g, yellowBactList);
        drawBacteria(g, blueBactList);
        try {
            countRedInfo.setText("Count red: "  + redBactList.size());
            countYellowInfo.setText("Count yellow: "  + yellowBactList.size());
            countBlueInfo.setText("Count blue: " + blueBactList.size());
            countGreenInfo.setText("Count green: " + greenBactList.size());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
        countGreenInfo = new JLabel("Count пкууи: " + Integer.toString(greenBactList.size()));
        countGreenInfo.setForeground(Color.WHITE);
        this.add(countGreenInfo);
    }
    private void drawBacteria(Graphics g, ArrayList<Bacteria> bacteria) {
        for (Bacteria bact : bacteria) {
            g.setColor(bact.color);
            int x = (int) bact.x - RADIUS_BACTERIA;
            int y = (int) bact.y - RADIUS_BACTERIA;
            int diameter = RADIUS_BACTERIA * 2;
            g.fillOval(x, y, diameter, diameter);
            if (bact.type == 3) {
                g.setColor(new Color(0, 255, 0, 50));
                g.drawOval(x - GreenBact.healDistance / 2, y - GreenBact.healDistance / 2, diameter + GreenBact.healDistance, diameter + GreenBact.healDistance);
            }
        }
    }
}
