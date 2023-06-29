package gui.control;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class BacteriaParam extends JPanel {

    public static final int START_COUNT_RED = 20;
    public static final int START_COUNT_YELLOW = 20;
    public static final int START_SPEED = 5;
    // red count
    private static JLabel countRedTitle = new JLabel("Red");
    private static JPanel countRedPanel;
    private static JSlider countRedSlider;
    private static JLabel countRedValue;
    // yellow count
    private static JLabel countYellowTitle = new JLabel("Yellow");
    private static JPanel countYellowPanel;
    private static JSlider countYellowSlider;
    private static JLabel countYellowValue;
    // bacteria speed
    private static JLabel speedTitle = new JLabel("Speed");
    private static JPanel speedPanel;
    private static JSlider speedSlider;
    private static JLabel speedValue;

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
        countRedSlider = new JSlider(JSlider.HORIZONTAL, 0, 500, START_COUNT_RED);
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
        countYellowSlider = new JSlider(JSlider.HORIZONTAL, 0, 500, START_COUNT_YELLOW);
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
        speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, START_SPEED);
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
    public int getCountRed() {
        return countRedSlider.getValue();
    }
    public int getCountYellow() {
        return countYellowSlider.getValue();
    }
    public float getSpeed() {
        return (float)speedSlider.getValue() / 100;
    }
}
