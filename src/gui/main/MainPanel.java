package gui.main;

import gui.control.ControlPanel;
import gui.map.MapPanel;
import javax.swing.*;
import gui.info.*;

import java.awt.*;

public class MainPanel extends JPanel {

    public static JPanel leftGroupPanel;
    public static JPanel centerGroupPanel;
    public static JPanel rightGroupPanel;
    public static MapInfoPanel mapInfoPanel;
    public static CellInfoPanel cellInfoPanel;
    public static EventsInfoPanel eventInfoPanel;
    public static ControlPanel controlPanel;
    public static MapPanel mapPanel;

    public MainPanel() {
        setupView();
        setupGroupPanel();
        setupMapPanel();
        setupCellInfoPanel();
        //setupEventInfoPanel();
        //setupMapInfoPanel();
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

    private void setupMapInfoPanel() {
        mapInfoPanel = new MapInfoPanel();
        centerGroupPanel.add(mapInfoPanel);
    }

    private void setupEventInfoPanel() {
        eventInfoPanel = new EventsInfoPanel();
        centerGroupPanel.add(eventInfoPanel);
    }

    private void setupCellInfoPanel() {
        cellInfoPanel = new CellInfoPanel();
        rightGroupPanel.add(cellInfoPanel);
    }

    private void setupMapPanel() {
        mapPanel = new MapPanel();
        leftGroupPanel.add(mapPanel);
    }

    private void setupControlPanel() {
        controlPanel = new ControlPanel();
        leftGroupPanel.add(controlPanel);
    }
}
