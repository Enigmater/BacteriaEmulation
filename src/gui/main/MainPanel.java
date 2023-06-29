package gui.main;

import gui.control.BacteriaParam;
import gui.control.FoodParam;
import gui.control.PhysParam;
import gui.control.ControlPanel;
import gui.map.MapPanel;
import javax.swing.*;

public class MainPanel extends JPanel {
    // group panels (left, central, right) have X_AXIS
    public static JPanel leftGroupPanel;
    public static JPanel centerGroupPanel;
    public static JPanel rightGroupPanel;
    //
    public static PhysParam physParamPanel;
    public static BacteriaParam bacteriaParamPanel;
    public static FoodParam foodParamPanel;
    public static ControlPanel controlPanel;
    public static MapPanel mapPanel;

    public MainPanel() {
        setupView();
        setupGroupPanel();

        setupMapPanel();
        setupPhysParamPanel();
        setupBacteriaParamPanel();
        setupFoodParamPanel();
        setupControlPanel();
        setVisible(true);
    }
    private void setupView() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }
    private void setupGroupPanel() {
        leftGroupPanel = new JPanel();
        leftGroupPanel.setLayout(new BoxLayout(leftGroupPanel, BoxLayout.Y_AXIS));
        this.add(leftGroupPanel);

        centerGroupPanel = new JPanel();
        centerGroupPanel.setLayout(new BoxLayout(centerGroupPanel, BoxLayout.Y_AXIS));
        this.add(centerGroupPanel);

        rightGroupPanel = new JPanel();
        rightGroupPanel.setLayout(new BoxLayout(rightGroupPanel, BoxLayout.Y_AXIS));
        this.add(rightGroupPanel);
    }
    private void setupPhysParamPanel() {
        physParamPanel = new PhysParam();
        rightGroupPanel.add(physParamPanel);
    }
    private void setupBacteriaParamPanel() {
        bacteriaParamPanel = new BacteriaParam();
        rightGroupPanel.add(bacteriaParamPanel);
    }
    private void setupFoodParamPanel() {
        foodParamPanel = new FoodParam();
        rightGroupPanel.add(foodParamPanel);
    }
    private void setupMapPanel() {
        mapPanel = new MapPanel();
        leftGroupPanel.add(mapPanel);
    }
    private void setupControlPanel() {
        controlPanel = new ControlPanel();
        rightGroupPanel.add(controlPanel);
    }
}
