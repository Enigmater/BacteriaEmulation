package gui.parameters;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class FoodParam extends JPanel {
    public static final int START_MAXCOUNT_FOOD = 80;
    public static final int MAX_MAXCOUNT_FOOD = 100;
    public static final int START_SPAWNRATE = 20;
    public static final int MAX_SPAWNRATE = 20;
    // max count food
    private static JLabel maxCountFoodTitle = new JLabel("Max food");
    private static JPanel maxCountFoodPanel;
    private static JSlider maxCountFoodSlider;
    private static JLabel maxCountFoodValue;
    // rate spawn food
    private static JLabel SpawnrateTitle = new JLabel("Rate");
    private static JPanel SpawnratePanel;
    private static JSlider SpawnrateSlider;
    private static JLabel SpawnrateValue;

    public FoodParam() {
        setupView();
        setupSliders();
        setVisible(true);
    }
    private void setupView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new TitledBorder("Food Parameters"));
        setPreferredSize(new Dimension(300, 0));
        setMaximumSize(new Dimension(300, 550));
    }
    private void setupSliders() {
        // count red bacteria slider
        setupMaxCountFoodPanel();
        // count yellow bacteria slider
        setupSpawnratePanel();
    }
    private void setupMaxCountFoodPanel() {
        maxCountFoodPanel = new JPanel();
        maxCountFoodPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        maxCountFoodPanel.add(maxCountFoodTitle);
        setupMaxCountFoodSlider();
        setupMaxCountFoodValue();
        this.add(maxCountFoodPanel);
    }
    private void setupMaxCountFoodSlider() {
        maxCountFoodSlider = new JSlider(JSlider.HORIZONTAL, 0, MAX_MAXCOUNT_FOOD, START_MAXCOUNT_FOOD);
        maxCountFoodSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                maxCountFoodValue.setText(Integer.toString(maxCountFoodSlider.getValue()));
            }
        });
        maxCountFoodPanel.add(maxCountFoodSlider);
    }
    private void setupMaxCountFoodValue() {
        maxCountFoodValue = new JLabel();
        maxCountFoodValue.setText(Integer.toString(maxCountFoodSlider.getValue()));
        maxCountFoodPanel.add(maxCountFoodValue);
    }
    private void setupSpawnratePanel() {
        SpawnratePanel = new JPanel();
        SpawnratePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        SpawnratePanel.add(SpawnrateTitle);
        setupSpawnrateSlider();
        setupSpawnrateValue();
        this.add(SpawnratePanel);
    }
    private void setupSpawnrateSlider() {
        SpawnrateSlider = new JSlider(JSlider.HORIZONTAL, 0, MAX_SPAWNRATE, START_SPAWNRATE);
        SpawnrateSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                SpawnrateValue.setText(Integer.toString(SpawnrateSlider.getValue()));
            }
        });
        SpawnratePanel.add(SpawnrateSlider);
    }
    private void setupSpawnrateValue() {
        SpawnrateValue = new JLabel();
        SpawnrateValue.setText(Integer.toString(SpawnrateSlider.getValue()));
        SpawnratePanel.add(SpawnrateValue);
    }
    public int getMaxcountFood() {
        return maxCountFoodSlider.getValue();
    }
    public int getSpawnrate() {
        return Math.abs(SpawnrateSlider.getValue() - SpawnrateSlider.getMaximum());
    }
}
