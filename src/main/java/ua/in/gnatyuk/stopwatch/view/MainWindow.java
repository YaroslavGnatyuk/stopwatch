package ua.in.gnatyuk.stopwatch.view;

import ua.in.gnatyuk.stopwatch.my_component.MainWidget;
import ua.in.gnatyuk.stopwatch.my_component.stapwatch_thread.MainWidgetStopwatchThread;

import javax.swing.*;

public class MainWindow extends JFrame {
    private final MainWidget mainWidget;

    public MainWindow() {
        mainWidget = new MainWidget();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1024, 768);
        setContentPane(mainWidget);
    }

    public static void main(String[] args) {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    MainWindow frame = new MainWindow();
                    frame.setVisible(true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
