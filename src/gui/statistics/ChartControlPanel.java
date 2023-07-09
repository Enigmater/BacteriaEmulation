package gui.statistics;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChartControlPanel extends JPanel implements ActionListener {

    private static JRadioButton radioButtonRed;
    private static JRadioButton radioButtonYellow;
    private static JRadioButton radioButtonBlue;
    private static JPanel buttonsPanel;
    public static ChartScaleSliders chartScaleSliders;

    public static String activeChart;
    private static final Color BG_COLOR = Color.DARK_GRAY;
    private static final Color FG_COLOR = Color.WHITE;
    public ChartControlPanel() {
        setupView();
        setupButtons();
        groupButtons();
        registerButtons();
        addButtons();
        setupSliders();
        setVisible(true);
    }

    private void setupView() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        TitledBorder tb = new TitledBorder("Choose bacteria");
        tb.setTitleColor(FG_COLOR);
        setBorder(tb);
        setPreferredSize(new Dimension(150, 500));
        setMaximumSize(new Dimension(500, 500));
    }

    private void setupButtons() {
        radioButtonRed = new JRadioButton("Red");
        radioButtonRed.setActionCommand("red");
        radioButtonRed.setSelected(true);
        radioButtonRed.setBackground(BG_COLOR);
        radioButtonRed.setForeground(FG_COLOR);
        activeChart = radioButtonRed.getActionCommand();

        radioButtonYellow = new JRadioButton("Yellow");
        radioButtonYellow.setActionCommand("yellow");
        radioButtonYellow.setBackground(BG_COLOR);
        radioButtonYellow.setForeground(FG_COLOR);

        radioButtonBlue = new JRadioButton("Blue");
        radioButtonBlue.setActionCommand("blue");
        radioButtonBlue.setBackground(BG_COLOR);
        radioButtonBlue.setForeground(FG_COLOR);
    }
    private void groupButtons() {
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonRed);
        buttonGroup.add(radioButtonYellow);
        buttonGroup.add(radioButtonBlue);
    }
    private void registerButtons() {
        radioButtonRed.addActionListener(this);
        radioButtonYellow.addActionListener(this);
        radioButtonBlue.addActionListener(this);
    }
    private void addButtons() {
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.DARK_GRAY);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(radioButtonRed);
        buttonsPanel.add(radioButtonYellow);
        buttonsPanel.add(radioButtonBlue);
        this.add(buttonsPanel);
    }
    private void setupSliders() {
        chartScaleSliders = new ChartScaleSliders();
        this.add(chartScaleSliders);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        activeChart = e.getActionCommand();
    }
}
