package ua.in.gnatyuk.stopwatch.my_component.stapwatch_thread;

import ua.in.gnatyuk.stopwatch.my_component.MainWidget;
import ua.in.gnatyuk.stopwatch.my_component.Stopwatch;

import javax.swing.*;
import java.util.List;

public class MainWidgetStopwatchThread extends SwingWorker<MainWidget, MainWidget> {
    MainWidget mainWindow;
    private boolean isRunning =true;

    public MainWidgetStopwatchThread(MainWidget mainWindow) {
        this.mainWindow = mainWindow;
    }

    protected MainWidget doInBackground() throws Exception {
        while (isRunning){
            System.out.println("In main thread!");
            List<JPanel> allStopwatch = mainWindow.getStopwatches();
            for (JPanel stopwatch:allStopwatch) {
                Stopwatch s = (Stopwatch) stopwatch;
                if (s.isDeleteThisStopwatch()){
                    System.out.println("One stopwatch should be delete!");
                    allStopwatch.remove(stopwatch);
                    mainWindow.remove(s);
                    mainWindow.repaint();
                }
            }
            Thread.sleep(500);
        }

        return mainWindow;

    }
}
