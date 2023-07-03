package gui.control;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class BacteriaParam extends JPanel {

    public static final int START_COUNT_RED = 20;
    private static final int MAX_COUNT_RED = 500;
    public static final int START_COUNT_YELLOW = 20;
    private static final int MAX_COUNT_YELLOW = 500;
    private static final int START_SPEED = 20;
    private static final int MAX_SPEED = 100;
    private static final int START_AGING_TIME = 700;
    private static final int MIN_AGING_TIME = 100;
    private static final int MAX_AGING_TIME = 1000;
    // red count
    private static JLabel countRedTitle = new JLabel("Red");
    private static JPanel countRedPanel;
    public static JSlider countRedSlider;
    private static JLabel countRedValue;
    // yellow count
    private static JLabel countYellowTitle = new JLabel("Yellow");
    private static JPanel countYellowPanel;
    public static JSlider countYellowSlider;
    private static JLabel countYellowValue;
    // bacteria speed
    private static JLabel speedTitle = new JLabel("Speed");
    private static JPanel speedPanel;
    private static JSlider speedSlider;
    private static JLabel speedValue;
    // speed of aging
    private static JLabel agingTimeTitle = new JLabel("Aging");
    private static JPanel agingTimePanel;
    private static JSlider agingTimeSlider;
    private static JLabel agingTimeValue;

    public BacteriaParam() {
        setupView();
        setupSliders();
        setVisible(true);
    }
    private void setupView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new TitledBorder("Bacteria Parameters"));
        setPreferredSize(new Dimension(300, 0));
        setMaximumSize(new Dimension(300, 1000));
    }
    private void setupSliders() {
        // count red bacteria slider
        setupRedPanel();
        // count yellow bacteria slider
        setupYellowPanel();
        // speed bacteria slider
        setupSpeedPanel();
        // speed of aging slieder
        setupAgingTimePanel();
    }
    private void setupRedPanel() {
        countRedPanel = new JPanel();
        countRedPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        countRedPanel.add(countRedTitle);
        setupRedSlider();
        setupRedValue();
        this.add(countRedPanel);
    }
    private void setupRedSlider() {
        countRedSlider = new JSlider(JSlider.HORIZONTAL, 0, MAX_COUNT_RED, START_COUNT_RED);
        countRedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                countRedValue.setText(Integer.toString(countRedSlider.getValue()));
            }
        });
        countRedPanel.add(countRedSlider);
    }
    private void setupRedValue() {
        countRedValue = new JLabel();
        countRedValue.setText(Integer.toString(countRedSlider.getValue()));
        countRedPanel.add(countRedValue);
    }
    private void setupYellowPanel() {
        countYellowPanel = new JPanel();
        countYellowPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        countYellowPanel.add(countYellowTitle);
        setupYellowSlider();
        setupYellowValue();
        this.add(countYellowPanel);
    }
    private void setupYellowSlider() {
        countYellowSlider = new JSlider(JSlider.HORIZONTAL, 0, MAX_COUNT_YELLOW, START_COUNT_YELLOW);
        countYellowSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                countYellowValue.setText(Integer.toString(countYellowSlider.getValue()));
            }
        });
        countYellowPanel.add(countYellowSlider);
    }
    private void setupYellowValue() {
        countYellowValue = new JLabel();
        countYellowValue.setText(Integer.toString(countYellowSlider.getValue()));
        countYellowPanel.add(countYellowValue);
    }
    private void setupSpeedPanel() {
        speedPanel = new JPanel();
        setLayout(new FlowLayout(FlowLayout.CENTER));
        speedPanel.add(speedTitle);
        setupSpeedSlider();
        setupSpeedLabel();
        this.add(speedPanel);
    }
    private void setupSpeedSlider() {
        speedSlider = new JSlider(JSlider.HORIZONTAL, 0, MAX_SPEED, START_SPEED);
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                speedValue.setText(Integer.toString(speedSlider.getValue()));
            }
        });
        speedPanel.add(speedSlider);
    }
    private void setupSpeedLabel() {
        speedValue = new JLabel();
        speedValue.setText(Integer.toString(speedSlider.getValue()));
        speedPanel.add(speedValue);
    }
    private void setupAgingTimePanel() {
        agingTimePanel = new JPanel();
        setLayout(new FlowLayout(FlowLayout.CENTER));
        agingTimePanel.add(agingTimeTitle);
        setupAgingTimeSlider();
        setupAgingTimeLabel();
        this.add(agingTimePanel);
    }
    private void setupAgingTimeSlider() {
        agingTimeSlider =  new JSlider(JSlider.HORIZONTAL, MIN_AGING_TIME, MAX_AGING_TIME, START_AGING_TIME);
        agingTimeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                agingTimeValue.setText(Integer.toString(agingTimeSlider.getValue()));
            }
        });
        agingTimePanel.add(agingTimeSlider);
    }
    private void setupAgingTimeLabel() {
        agingTimeValue = new JLabel(Integer.toString(agingTimeSlider.getValue()));
        agingTimePanel.add(agingTimeValue);
    }
    public int getCountRed() {
        return countRedSlider.getValue();
    }
    public int getCountYellow() {
        return countYellowSlider.getValue();
    }
    public float getSpeed() {
        return (float)speedSlider.getValue() / 10;
    }
    public int getAgingTime() {
        return Math.abs(agingTimeSlider.getValue() - MAX_AGING_TIME);
    }
}
