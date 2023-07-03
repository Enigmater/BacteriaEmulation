package runner;

import gui.main.MainWindow;
import gui.statistics.ChartWindow;

public class Main{
    public static void main(String[] args) {
        new Thread(new MainWindow()).start();
        new Thread(new ChartWindow()).start();
    }
}