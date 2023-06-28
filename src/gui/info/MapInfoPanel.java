package gui.info;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MapInfoPanel extends JPanel {
    private JLabel info;

    public MapInfoPanel() {
        setupView();
        setupInfo();
        setVisible(true);
    }

    private void setupInfo() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(new TitledBorder("Map Info"));
        setPreferredSize(new Dimension(300, 0));
        setMaximumSize(new Dimension(300, 1000));
    }

    private void setupView() {
        this.info = new JLabel("-", SwingConstants.LEFT);
        add(this.info);
    }

    public void reset() {
        this.info.setText("-");
        this.setToolTipText(null);
    }
}
