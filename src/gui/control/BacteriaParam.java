package gui.control;

import data.Bacteria;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class BacteriaParam extends JPanel {

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

    public BacteriaParam() {
        setupView();
        setupSliders();
        setVisible(true);
    }

    private void setupView() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(new TitledBorder("Bacteria Parameters"));
        setPreferredSize(new Dimension(300, 0));
        setMaximumSize(new Dimension(300, 1000));
    }

    private void setupSliders() {
        // count red bacteria slider
        this.add(countRedTitle);
        setupRedPanel();
        // count yellow bacteria slider
        this.add(countYellowTitle);
        setupYellowPanel();
    }
    private void setupRedPanel() {
        countRedPanel = new JPanel();
        setupRedSlider();
        setupRedValue();
        this.add(countRedPanel);
    }
    private void setupRedSlider() {
        countRedSlider = new JSlider(JSlider.HORIZONTAL, 0, 500, 200);
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
        setupYellowSlider();
        setupYellowValue();
        this.add(countYellowPanel);
    }

    private void setupYellowSlider() {
        countYellowSlider = new JSlider(JSlider.HORIZONTAL, 0, 500, 200);
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

    public int getCountRed() {
        return countRedSlider.getValue();
    }

    public int getCountYellow() {
        return countYellowSlider.getValue();
    }
}
