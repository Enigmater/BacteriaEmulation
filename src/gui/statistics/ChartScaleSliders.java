package gui.statistics;

import javax.swing.*;
import java.awt.*;

public class ChartScaleSliders extends JPanel {
    private static final int MAX_TIMESCALE = 5;
    private static final int START_TIMESCALE = 1;
    private static final int MAX_COUNTSCALE = 5;
    private static final int START_COUNTSCALE = 1;
    private static JLabel timeScaleTitle = new JLabel("Time");
    private static JPanel timeScalePanel;
    private static JSlider timeScaleSlider;

    private static JLabel countScaleTitle = new JLabel("Count");
    private static JPanel countScalePanel;
    private static JSlider countScaleSlider;

    public ChartScaleSliders() {
        setupView();
        setupSliders();
        setVisible(true);
    }

    private void setupView() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(100, 300));
        setMaximumSize(new Dimension(500, 500));
        setBackground(Color.DARK_GRAY);
    }
    private void setupSliders() {
        setupTimeScalePanel();
        setupCountScalePanel();
    }
    private void setupTimeScalePanel() {
        timeScalePanel = new JPanel();
        timeScalePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        timeScalePanel.setBackground(Color.DARK_GRAY);
        timeScalePanel.setForeground(Color.WHITE);
        timeScaleTitle.setForeground(Color.WHITE);
        timeScalePanel.add(timeScaleTitle);
        setupTimeScaleSlider();
        this.add(timeScalePanel);
    }
    private void setupTimeScaleSlider() {
        timeScaleSlider = new JSlider(JSlider.VERTICAL, 1, MAX_TIMESCALE, START_TIMESCALE);
        timeScaleSlider.setBackground(Color.DARK_GRAY);
        timeScaleSlider.setForeground(Color.WHITE);
        timeScalePanel.add(timeScaleSlider);
    }
    private void setupCountScalePanel() {
        countScalePanel = new JPanel();
        countScalePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        countScalePanel.setBackground(Color.DARK_GRAY);
        countScalePanel.setForeground(Color.WHITE);
        countScaleTitle.setForeground(Color.WHITE);
        countScalePanel.add(countScaleTitle);
        setupCountScaleSlider();
        this.add(countScalePanel);
    }
    private void setupCountScaleSlider() {
        countScaleSlider = new JSlider(JSlider.VERTICAL, 1, MAX_COUNTSCALE, START_COUNTSCALE);
        countScaleSlider.setBackground(Color.DARK_GRAY);
        countScaleSlider.setForeground(Color.WHITE);
        countScalePanel.add(countScaleSlider);
    }
    public int getTimeScale() {
        return timeScaleSlider.getValue();
    }
    public int getCountScale() {
        return countScaleSlider.getValue();
    }
}
