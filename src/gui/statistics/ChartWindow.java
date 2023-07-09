package gui.statistics;

import gui.control.ControlPanel;
import logic.Statistics;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ChartWindow extends JFrame implements Runnable {

    private static JPanel jPanel;
    public static JPanel chartPanel;
    public static ChartControlPanel controlPanel;
    private static Statistics statistics = new Statistics();
    private static final Color BG_COLOR = Color.DARK_GRAY;
    private static final Color FG_COLOR = Color.WHITE;
    public static int TIME_SCALE = 5;
    public static int COUNT_SCALE = 2;

    public ChartWindow() {
        setupView();
        setupPanel();
        setupControl();
        setupCharts();
        setVisible(true);
    }

    private void setupPanel() {
        jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
        this.add(jPanel);
    }

    private void setupControl() {
        controlPanel = new ChartControlPanel();
        controlPanel.setBackground(BG_COLOR);
        jPanel.add(controlPanel);
    }

    private void setupCharts() {
        chartPanel = new JPanel();
        chartPanel.setBackground(BG_COLOR);
        TitledBorder tb = new TitledBorder("Chart");
        tb.setTitleColor(FG_COLOR);
        chartPanel.setBorder(tb);
        jPanel.add(chartPanel);
    }

    private void setupView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        setTitle("Chart");
        setSize(1000, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void run() {
        while (true) {
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (ControlPanel.isStart) drawChart(g);
    }

    private void drawChart(Graphics g) {
        g.setColor(Color.GRAY);
        int xZero = chartPanel.getX() + 25;
        int yZero = chartPanel.getSize().height + 10;
        int xMax = chartPanel.getX() + chartPanel.getSize().width - 10;
        int yMax = 50;
        // X axis
        g.drawLine(xZero, yZero, xMax, yZero);
        // Y axis
        g.drawLine(xZero, yZero, xZero, yMax);
        Map<Integer, Integer> stat = new HashMap<>();
        switch (controlPanel.activeChart) {
            case "red":
                stat = statistics.statisticsRed;
                g.setColor(Color.RED);
                break;
            case "yellow":
                stat = statistics.statisticsYellow;
                g.setColor(Color.YELLOW);
                break;
            case "blue":
                stat = statistics.statisticsBlue;
                g.setColor(Color.BLUE);
                break;
        }
        // x - count bacteria
        // y - time
        int x1 = xZero + 10;
        int y1 = yZero - 10;
        int x2, y2;
        TIME_SCALE = controlPanel.chartScaleSliders.getTimeScale();
        COUNT_SCALE = controlPanel.chartScaleSliders.getCountScale();
        for (Map.Entry<Integer, Integer> item : stat.entrySet()) {
            x2 = xZero + item.getKey() * TIME_SCALE + 10;     // count
            y2 = yZero - item.getValue() * COUNT_SCALE - 10;   // time
            g.drawLine(x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;
            if (TIME_SCALE == 1 && x1 > xMax) {
                Statistics.clear();
                break;
            }
        }
    }
}
