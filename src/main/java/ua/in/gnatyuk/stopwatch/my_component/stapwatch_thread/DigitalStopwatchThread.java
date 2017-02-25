package ua.in.gnatyuk.stopwatch.my_component.stapwatch_thread;

import ua.in.gnatyuk.stopwatch.my_component.DigitalStopwatch;

import javax.swing.*;
import java.time.LocalTime;
import java.util.List;

public class DigitalStopwatchThread extends SwingWorker<LocalTime, DigitalStopwatch> {
	private boolean isRunning = false;
	private DigitalStopwatch clock ;

    public DigitalStopwatchThread(DigitalStopwatch clock) {
        this.clock = clock;
    }

	@Override
	protected LocalTime doInBackground() throws Exception {
		while(!isCancelled()) {
			while (isRunning) {
				publish(clock);
				Thread.sleep(1000);
			}
		}
		return clock.getTime();
	}

	@Override
	protected void process(List<DigitalStopwatch> chunks) {
		chunks.forEach(DigitalStopwatch::increaseTime);
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean running) {
		this.isRunning = running;
	}
}
