package ua.in.gnatyuk.stopwatch.my_component;

import ua.in.gnatyuk.stopwatch.my_component.stapwatch_thread.DigitalStopwatchThread;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class DigitalStopwatch extends Stopwatch {
	private final DigitalStopwatchThread digitalStopwatchThread;
	private final JLabel clockLabel = new JLabel();
	private final JButton btnStartPauseStopwatch;
	private final JButton btnRestartStopwatch;
	private final JButton btnStopStopwatch;
	private static final long serialVersionUID = 1L;
	private JButton btnCloseStopwatch;

	public JButton getBtnCloseStopwatch() {
		return btnCloseStopwatch;
	}

	public DigitalStopwatch() {
		this.setBounds(0, 0, 269, 250);
		
        Font f = new Font("Default", Font.PLAIN + Font.ROMAN_BASELINE, 47);
        
        digitalStopwatchThread = new DigitalStopwatchThread(this);
        setLayout(null);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setBounds(12, 37, 245, 128);
        clockLabel.setFont(f);
        clockLabel.setText("00:00:00");
        add(clockLabel);
        
        btnRestartStopwatch = new JButton();
		URL pathRestart = getClass().getResource("/img/restart.png");
        btnRestartStopwatch.setIcon(new ImageIcon(pathRestart));
        btnRestartStopwatch.setBounds(80, 196, 51, 42);
        btnRestartStopwatch.addActionListener(new ActionListener() {
			
        	public void actionPerformed(ActionEvent e) {
				if(digitalStopwatchThread.isRunning()) {
					clockLabel.setText("00:00:00");
					restartStopwatch();
				}
			}
		});
        
        btnStartPauseStopwatch = new JButton();
		final URL pathStart = getClass().getResource("/img/play.png");
        btnStartPauseStopwatch.setIcon(new ImageIcon(pathStart));
        btnStartPauseStopwatch.setBounds(12, 196, 51, 42);
        btnStartPauseStopwatch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(digitalStopwatchThread.isRunning()) {
					btnStartPauseStopwatch.setIcon(new ImageIcon(getClass().getResource("/img/play.png")));
        			digitalStopwatchThread.setRunning(false);
        		}else {
					btnStartPauseStopwatch.setIcon(new ImageIcon(getClass().getResource("/img/pause.png")));
        			digitalStopwatchThread.execute();
        			digitalStopwatchThread.setRunning(true);
        		}
        	}
        });
        
        add(btnStartPauseStopwatch);
        add(btnRestartStopwatch);
        
        btnStopStopwatch = new JButton();
		URL pathStop = getClass().getResource("/img/stop.png");
		btnStopStopwatch.setIcon(new ImageIcon(pathStop));
        btnStopStopwatch.setBounds(143, 196, 51, 42);

		btnStopStopwatch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(digitalStopwatchThread.isRunning()) {
        			restartStopwatch();
					btnStartPauseStopwatch.setIcon(new ImageIcon(getClass().getResource("/img/play.png")));
        			clockLabel.setText("00:00:00");
        			digitalStopwatchThread.setRunning(false);
				}else {
					btnStartPauseStopwatch.setIcon(new ImageIcon(getClass().getResource("/img/play.png")));
					restartStopwatch();
					clockLabel.setText("00:00:00");
				}
			}
		});
        add(btnStopStopwatch);
        
        btnCloseStopwatch = new JButton();
		URL pathToClose = getClass().getResource("/img/close.png");
		btnCloseStopwatch.setIcon(new ImageIcon(pathToClose));
        btnCloseStopwatch.setBounds(206, 196, 51, 42);
        add(btnCloseStopwatch);
	}
	
	 public void increaseTime() {
		 increaseSeconds(1);
		 clockLabel.setText(getTime().toString());
		 repaint();
	}

	public DigitalStopwatchThread getDigitalStopwatchThread() {
		return digitalStopwatchThread;
	}
}