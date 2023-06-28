package gui.main;

import gui.main.MainPanel;

import javax.swing.*;

public class MainWindow extends JFrame implements Runnable{
    public static MainPanel mainPanel;
    public MainWindow() {
        setupView();
        setupPanel();
        setVisible(true);
    }

    private void setupView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        setTitle("PI-11 Tihonov");
        setSize(500, 500);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void setupPanel() {
        mainPanel = new MainPanel();
        this.add(mainPanel);
    }

    @Override
    public void run() {
        while (true) {
            repaint();
        }
    }
}
