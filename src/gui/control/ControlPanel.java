package gui.control;

import gui.main.MainPanel;
import logic.Force;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private JButton startButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JButton updateButton;

    public ControlPanel() {
        setupView();
        setupButtons();
        addButtons();
        setVisible(true);
    }

    private void setupView() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        //setBorder(new TitledBorder("Buttons"));
        setPreferredSize(new Dimension(0, 100));
        setMaximumSize(new Dimension(1000, 100));
    }

    private void setupButtons() {
        setupStartButton();
        setupPauseButton();
        setupStopButton();
        setupUpdateButton();
    }

    private void setupStartButton() {
        // START
        this.startButton = new JButton();
        startButton.setIcon(new ImageIcon("src\\resources\\play.png"));
        startButton.setPreferredSize(new Dimension(32, 32));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Force.start();
                startButton.setEnabled(false);
                pauseButton.setEnabled(true);
                stopButton.setEnabled(true);
                updateButton.setEnabled(false);
            }
        });
    }
    private void setupPauseButton() {
        // PAUSE
        this.pauseButton = new JButton();
        pauseButton.setIcon(new ImageIcon("src\\resources\\pause.png"));
        pauseButton.setPreferredSize(new Dimension(32, 32));
        pauseButton.setEnabled(false);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Force.pause();
                startButton.setEnabled(true);
                pauseButton.setEnabled(false);
                stopButton.setEnabled(true);
                updateButton.setEnabled(true);
            }
        });
    }
    private void setupStopButton() {
        // STOP
        this.stopButton = new JButton();
        stopButton.setIcon(new ImageIcon("src\\resources\\stop.png"));
        stopButton.setPreferredSize(new Dimension(32, 32));
        stopButton.setEnabled(false);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Force.stop();
                MainPanel.cellInfoPanel.reset();

                startButton.setEnabled(true);
                pauseButton.setEnabled(false);
                stopButton.setEnabled(false);
                updateButton.setEnabled(true);
            }
        });
    }
    private void setupUpdateButton() {
        // UPDATE
        this.updateButton = new JButton();
        updateButton.setIcon(new ImageIcon("src\\resources\\update.png"));
        updateButton.setPreferredSize(new Dimension(32, 32));
        updateButton.setEnabled(true);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Force.update();
                startButton.setEnabled(true);
                pauseButton.setEnabled(false);
                stopButton.setEnabled(false);
                updateButton.setEnabled(true);
            }
        });
    }
    private void addButtons() {
        add(updateButton);
        add(startButton);
        add(pauseButton);
        add(stopButton);
    }
}
