package gui.info;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class EventsInfoPanel extends JScrollPane {
    private JList<String> events = new JList<String>(new DefaultListModel<String>());

    public EventsInfoPanel() {
        setupView();
        setupEvents();
        setVisible(true);
    }
    private void setupView() {
        setBorder(new TitledBorder("Events Info"));
        setPreferredSize(new Dimension(300, 0));
        setMaximumSize(new Dimension(300, 1000));
    }

    private void setupEvents() {
        this.events.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.events.setLayoutOrientation(JList.VERTICAL);
        this.events.setBackground(new Color(0xEEEEEE));
        setViewportView(this.events);
    }

    public void reset() {
        ((DefaultListModel<String>)this.events.getModel()).clear();
    }
}
