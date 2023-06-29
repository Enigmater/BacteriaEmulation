package gui.control;

import gui.main.MainPanel;
import logic.Force;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    // buttons (update, start, pause, stop)
    private JButton startButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JButton updateButton;

    // speed
    private static JLabel speedTitle = new JLabel("Speed");
    private static JPanel speedPanel;
    private static JSlider speedSlider;
    private static JLabel speedValue;

    public ControlPanel() {
        setupView();
        setupButtons();
        setupSpeedPanel();
        setVisible(true);
    }

    private void setupView() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBorder(new TitledBorder("Control buttons"));
        setPreferredSize(new Dimension(0, 100));
        setMaximumSize(new Dimension(300, 100));
    }

    private void setupButtons() {
        setupStartButton();
        setupPauseButton();
        setupStopButton();
        setupUpdateButton();
        addButtons();
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

    private void setupSpeedPanel() {
        speedPanel = new JPanel();
        speedPanel.add(speedTitle);
        setupSpeedSlider();
        setupSpeedLabel();
        this.add(speedPanel);
    }
    private void setupSpeedSlider() {
        speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 95);
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

    public int getTimerDelay() {
        return Math.abs(speedSlider.getValue() - speedSlider.getMaximum());
    }
}
