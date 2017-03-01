package ua.in.gnatyuk.stopwatch.my_component.stapwatch_thread;

import ua.in.gnatyuk.stopwatch.my_component.AnalogStopwatch;

import javax.swing.*;
import java.time.LocalTime;
import java.util.List;

public class AnalogStopwatchThread extends SwingWorker<LocalTime, AnalogStopwatch> {
    private boolean isRunning = false;
    private AnalogStopwatch clock;

    public AnalogStopwatchThread(AnalogStopwatch clock) {
        this.clock = clock;
    }

    @Override
    protected LocalTime doInBackground() throws Exception {
        while (!isCancelled()) {
            while (isRunning) {
                publish(clock);
                Thread.sleep(1000);
            }
        }
        return clock.getTime();
    }

    @Override
    protected void process(List<AnalogStopwatch> chunks) {
        chunks.forEach(AnalogStopwatch::increaseTime);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        this.isRunning = running;
    }
}
