package gui.map;

import data.Bacteria;
import logic.Force;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MapPanel extends JPanel {


    public static final int RADIUS_BACTERIA = 10;
    public static final int WIDTH = 1236;
    public static final int HEIGHT = 693;


    private Random random = new Random();
    public static ArrayList<Bacteria> red;
    public static ArrayList<Bacteria> yellow;
    public MapPanel() {
        red = new ArrayList<>();
        yellow = new ArrayList<>();
        setupView();
        Force.generateBacteria(red, Color.red);
        Force.generateBacteria(yellow, Color.yellow);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawBacteria(g, red);
        drawBacteria(g, yellow);
    }

    private void setupView() {
        //setBorder(new TitledBorder("Map"));
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
