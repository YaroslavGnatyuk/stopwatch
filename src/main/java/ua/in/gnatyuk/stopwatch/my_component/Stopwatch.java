package ua.in.gnatyuk.stopwatch.my_component;

import javax.swing.*;
import java.time.LocalTime;

public abstract class Stopwatch extends JPanel {
	private LocalTime time;
	private static final long serialVersionUID = 4378038936775933313L;

	public Stopwatch() {
		time = LocalTime.of(0, 0, 0);
	}

	public void restartStopwatch() {
		time = LocalTime.of(0, 0, 0);
	}

	public void increaseSeconds(long i) {
		time = time.plusSeconds(i);
	}

	public LocalTime getTime() {
		return time;
	}
}
