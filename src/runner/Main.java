package runner;

import gui.main.MainWindow;

public class Main{
    public static void main(String[] args) {
        new Thread(new MainWindow()).start();
    }
}