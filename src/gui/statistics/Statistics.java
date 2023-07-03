package gui.statistics;

import data.Bacteria;
import gui.map.MapPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Statistics {
    public static Map<Integer, Integer> statisticsRed;
    public static Map<Integer, Integer> statisticsYellow;
    public static Map<Integer, Integer> statisticsBlue;
    private static Timer timer;
    public static int time;

    public Statistics() {
        statisticsRed = new HashMap<>();
        statisticsYellow = new HashMap<>();
        statisticsBlue = new HashMap<>();
        time = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addValue(statisticsRed, time, MapPanel.redBactList);
                addValue(statisticsYellow, time, MapPanel.yellowBactList);
                addValue(statisticsBlue, time, MapPanel.blueBactList);
                time += timer.getDelay() / 1000;
            }
        });
        timer.start();
    }

    public static void clear() {
        statisticsRed.clear();
        statisticsYellow.clear();
        statisticsBlue.clear();
    }
    public void addValue(Map<Integer, Integer> src, int time, ArrayList<Bacteria> bact) {
        src.put(time, bact.size());
    }
}
