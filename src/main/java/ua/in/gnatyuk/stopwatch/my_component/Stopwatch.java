package ua.in.gnatyuk.stopwatch.my_component;

import java.time.LocalTime;

import javax.swing.JPanel;

public abstract class Stopwatch extends JPanel {
	private LocalTime time;
	private static final long serialVersionUID = 4378038936775933313L;
	private boolean deleteThisStopwatch;

	public Stopwatch() {
		time = LocalTime.of(0, 0, 0);
	}

	public void restartStopwatch() {
		time = LocalTime.of(0, 0, 0);
	}

	public int getHours() {
		return time.getHour();
	}

	public int getMinutes() {
		return time.getMinute();
	}

	public int getSeconds() {
		return time.getSecond();
	}

	public int getNanos() {
		return time.getNano();
	}

	public void increaseHours(long i) {
		time.plusHours(i);
	}

	public void increaseMinutes(long i) {
		time.plusMinutes(i);
	}

	public void increaseSeconds(long i) {
		time = time.plusSeconds(i);
	}

	public void increaseNanos(long i) {
		time.plusNanos(i);
	}

	public LocalTime getTime() {
		return time;
	}

	public boolean isDeleteThisStopwatch() {
		return deleteThisStopwatch;
	}

	public void setDeleteThisStopwatch(boolean deleteThisStopwatch) {
		this.deleteThisStopwatch = deleteThisStopwatch;
	}
}
