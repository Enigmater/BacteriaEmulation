package gui.info;

import gui.main.MainPanel;
import logic.Force;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CellInfoPanel extends JPanel {
    private static JLabel infoLabel;

    public CellInfoPanel() {
        setupView();
        setupInfo();
        setVisible(true);
    }

    public static void update() {
        String info = String.format("Average velocity: %f", Force.averageSpeed(MainPanel.mapPanel.red));
        infoLabel.setText(info);
    }
    private void setupView() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(new TitledBorder("Cell Info"));
        setPreferredSize(new Dimension(300, 0));
        setMaximumSize(new Dimension(300, 1000));
    }

    private void setupInfo() {
        this.infoLabel = new JLabel("-");
        add(this.infoLabel);
    }

    public void reset() {
        this.infoLabel.setText("-");
        this.setToolTipText(null);
    }
}
