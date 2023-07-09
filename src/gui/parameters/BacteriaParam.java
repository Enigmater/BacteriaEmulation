package gui.parameters;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class BacteriaParam extends JPanel {

    public static final int START_COUNT_RED = 10;
    private static final int MAX_COUNT_RED = 100;
    public static final int START_COUNT_YELLOW = 20;
    private static final int MAX_COUNT_YELLOW = 100;
    private static final int START_SPEED = 20;
    private static final int MAX_SPEED = 100;
    private static final int START_AGING_TIME = 850;
    private static final int MIN_AGING_TIME = 100;
    private static final int MAX_AGING_TIME = 1000;
    // panels
    private static JPanel redPanel;
    private static JPanel preysPanel;
    // red count
    private static JLabel countRedTitle = new JLabel("R.Count");
    private static JPanel countRedPanel;
    public static JSlider countRedSlider;
    private static JLabel countRedValue;
    // red dmg
    private static JLabel dmgRedTitle = new JLabel("DMG");
    private static JPanel dmgRedPanel;
    public static JSlider dmgRedSlider;
    private static JLabel dmgRedValue;
    // yellow count
    private static JLabel countYellowTitle = new JLabel("Y.Count");
    private static JPanel countYellowPanel;
    public static JSlider countYellowSlider;
    private static JLabel countYellowValue;
    // yellow/blue health
    private static JLabel healthPreyTitle = new JLabel("Health");
    private static JPanel healthPreyPanel;
    public static JSlider healthPreySlider;
    private static JLabel healthPreyValue;
    // blue protection
    private static JLabel protectionBlueTitle = new JLabel("B.Protect");
    private static JPanel protectionBluePanel;
    private static JSlider protectionBlueSlider;
    private static JLabel protectionBlueValue;
    // green heal
    private static JLabel healGreenTitle = new JLabel("G.Heal");
    private static JPanel healGreenPanel;
    private static JSlider healGreenSlider;
    private static JLabel healGreenValue;
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
        setupPanels();
        setVisible(true);
    }
    private void setupView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new TitledBorder("Bacteria Parameters"));
        setPreferredSize(new Dimension(300, 300));
        setMaximumSize(new Dimension(300, 1000));
    }
    private void setupPanels() {
        setupRedPanel();
        setupPreysPanel();
        setupSpeedPanel();
        setupAgingTimePanel();
    }

    private void setupRedPanel() {
        redPanel = new JPanel();
        redPanel.setLayout(new BoxLayout(redPanel, BoxLayout.Y_AXIS));
        TitledBorder tb = new TitledBorder("Predators parameters");
        redPanel.setBorder(tb);
        setupRedCountPanel();
        setupDmgPanel();
        this.add(redPanel);
    }
    private void setupPreysPanel() {
        preysPanel = new JPanel();
        preysPanel.setLayout(new BoxLayout(preysPanel, BoxLayout.Y_AXIS));
        TitledBorder tb = new TitledBorder("Preys parameters");
        preysPanel.setBorder(tb);
        setupYellowCountPanel();
        setupHealthPanel();
        setupBlueProtectionPanel();
        setupGreenHealPanel();
        this.add(preysPanel);
    }

    private void setupGreenHealPanel() {
        healGreenPanel = new JPanel();
        healGreenPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        healGreenPanel.add(healGreenTitle);
        setupGreenHealSlider();
        setupGreenHealLabel();
        preysPanel.add(healGreenPanel);
    }
    private void setupGreenHealSlider() {
        healGreenSlider = new JSlider(JSlider.HORIZONTAL, 10, 100, 10);
        healGreenSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                healGreenValue.setText(Integer.toString(healGreenSlider.getValue()));
            }
        });
        healGreenPanel.add(healGreenSlider);
    }
    private void setupGreenHealLabel() {
        healGreenValue = new JLabel(Integer.toString(healGreenSlider.getValue()));
        healGreenPanel.add(healGreenValue);
    }

    private void setupBlueProtectionPanel() {
        protectionBluePanel = new JPanel();
        protectionBluePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        protectionBluePanel.add(protectionBlueTitle);
        setupBlueProtectionSlider();
        setupBlueProtectionLabel();
        preysPanel.add(protectionBluePanel);
    }
    private void setupBlueProtectionLabel() {
        protectionBlueValue = new JLabel(Float.toString((float)protectionBlueSlider.getValue() / 100));
        protectionBluePanel.add(protectionBlueValue);
    }
    private void setupBlueProtectionSlider() {
        protectionBlueSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 75);
        protectionBlueSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                protectionBlueValue.setText(Float.toString((float)protectionBlueSlider.getValue() / 100));
            }
        });
        protectionBluePanel.add(protectionBlueSlider);
    }

    private void setupRedCountPanel() {
        countRedPanel = new JPanel();
        countRedPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        countRedPanel.add(countRedTitle);
        setupRedCountSlider();
        setupRedCountValue();
        redPanel.add(countRedPanel);
    }
    private void setupRedCountSlider() {
        countRedSlider = new JSlider(JSlider.HORIZONTAL, 0, MAX_COUNT_RED, START_COUNT_RED);
        countRedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                countRedValue.setText(Integer.toString(countRedSlider.getValue()));
            }
        });
        countRedPanel.add(countRedSlider);
    }
    private void setupRedCountValue() {
        countRedValue = new JLabel(Integer.toString(countRedSlider.getValue()));
        countRedPanel.add(countRedValue);
    }

    private void setupHealthPanel() {
        healthPreyPanel = new JPanel();
        healthPreyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        healthPreyPanel.add(healthPreyTitle);
        setupHealthSlider();
        setupHealthLabel();
        preysPanel.add(healthPreyPanel);
    }
    private void setupHealthLabel() {
        healthPreyValue = new JLabel(Integer.toString(healthPreySlider.getValue()));
        healthPreyPanel.add(healthPreyValue);
    }
    private void setupHealthSlider() {
        healthPreySlider = new JSlider(JSlider.HORIZONTAL, 50, 150, 100);
        healthPreySlider.setMajorTickSpacing(50);
        healthPreySlider.setSnapToTicks(true);
        healthPreySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                healthPreyValue.setText(Integer.toString(healthPreySlider.getValue()));
            }
        });
        healthPreyPanel.add(healthPreySlider);
    }

    private void setupDmgPanel() {
        dmgRedPanel = new JPanel();
        dmgRedPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dmgRedPanel.add(dmgRedTitle);
        setupDmgSlider();
        setupDmgLabel();
        redPanel.add(dmgRedPanel);
    }
    private void setupDmgLabel() {
        dmgRedValue = new JLabel(Float.toString((float)dmgRedSlider.getValue() / 100));
        dmgRedPanel.add(dmgRedValue);
    }
    private void setupDmgSlider() {
        dmgRedSlider = new JSlider(JSlider.HORIZONTAL, 500, 3000, 750);
        dmgRedSlider.setMajorTickSpacing(50);
        dmgRedSlider.setSnapToTicks(true);
        dmgRedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                dmgRedValue.setText(Float.toString((float)dmgRedSlider.getValue() / 100));
            }
        });
        dmgRedPanel.add(dmgRedSlider);
    }

    private void setupYellowCountPanel() {
        countYellowPanel = new JPanel();
        countYellowPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        countYellowPanel.add(countYellowTitle);
        setupYellowCountSlider();
        setupYellowCountValue();
        preysPanel.add(countYellowPanel);
    }
    private void setupYellowCountSlider() {
        countYellowSlider = new JSlider(JSlider.HORIZONTAL, 0, MAX_COUNT_YELLOW, START_COUNT_YELLOW);
        countYellowSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                countYellowValue.setText(Integer.toString(countYellowSlider.getValue()));
            }
        });
        countYellowPanel.add(countYellowSlider);
    }
    private void setupYellowCountValue() {
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
    public float getDmg() {
        return (float)dmgRedSlider.getValue() / 100;
    }
    public int getHealth() {
        return healthPreySlider.getValue();
    }
    public float getProtection() {
        return (float) protectionBlueSlider.getValue() / 100;
    }
    public int getHeal() {
        return healGreenSlider.getValue();
    }
}
