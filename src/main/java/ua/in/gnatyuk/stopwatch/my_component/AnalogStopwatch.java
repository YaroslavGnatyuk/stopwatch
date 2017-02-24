package ua.in.gnatyuk.stopwatch.my_component;

import ua.in.gnatyuk.stopwatch.my_component.stapwatch_thread.AnalogStopwatchThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.SimpleDateFormat;

public class AnalogStopwatch extends Stopwatch {

    private AnalogStopwatchThread analogStopWatchThread;

    private final JButton btnStartPauseStopwatch;
    private final JButton btnStopStopwatch;
    private final JButton btnRestartStopwatch;
    private final JButton btnCloseStopwatch;

    private static final long serialVersionUID = 1L;
    private int status = 0;
    private static final int spacing = 35;
    private static final float radPerSecMin = (float) (Math.PI / 30.0);
    private static final float radPerNum = (float) (Math.PI / -6);
    private int size;
    private int centerX;
    private int centerY;

    SimpleDateFormat sf;

    int hour;
    int minute;
    int second;

    public AnalogStopwatch() {

        setBounds(0, 0, 269, 250);
        setLayout(null);
        analogStopWatchThread = new AnalogStopwatchThread(this);

        btnStartPauseStopwatch = new JButton();
        final URL pathStart = getClass().getResource("/img/play.png");
        btnStartPauseStopwatch.setIcon(new ImageIcon(pathStart));
        btnStartPauseStopwatch.setBounds(12, 196, 51, 42);
        add(btnStartPauseStopwatch);
        btnStartPauseStopwatch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!analogStopWatchThread.isRunning() && !analogStopWatchThread.isCancelled()){
                    btnStartPauseStopwatch.setIcon(new ImageIcon(getClass().getResource("/img/pause.png")));
                    analogStopWatchThread.execute();
                    analogStopWatchThread.setRunning(true);
                }else if (analogStopWatchThread.isRunning()) {
                    btnStartPauseStopwatch.setIcon(new ImageIcon(getClass().getResource("/img/play.png")));
                    analogStopWatchThread.setRunning(false);
                }
            }
        });

        btnStopStopwatch = new JButton();
        URL pathStop = getClass().getResource("/img/stop.png");
        btnStopStopwatch.setIcon(new ImageIcon(pathStop));
        btnStopStopwatch.setBounds(78, 196, 51, 42);
        btnStopStopwatch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (analogStopWatchThread.isRunning()) {
                    btnStartPauseStopwatch.setIcon(new ImageIcon(getClass().getResource("/img/play.png")));
                    setZeroTime();
                    analogStopWatchThread.setRunning(false);
                } else {
                    btnStartPauseStopwatch.setIcon(new ImageIcon(getClass().getResource("/img/play.png")));
                    setZeroTime();
                }
            }
        });
        add(btnStopStopwatch);

        btnRestartStopwatch = new JButton();
        URL pathRestart = getClass().getResource("/img/restart.png");
        btnRestartStopwatch.setIcon(new ImageIcon(pathRestart));
        btnRestartStopwatch.setBounds(141, 196, 51, 42);
        btnRestartStopwatch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(analogStopWatchThread.isRunning()) {
                    setZeroTime();
                }else if (!analogStopWatchThread.isRunning() && analogStopWatchThread.getState().equals(SwingWorker.StateValue.STARTED)){
                    analogStopWatchThread.setRunning(true);
                    setZeroTime();
                    btnStartPauseStopwatch.setIcon(new ImageIcon(getClass().getResource("/img/pause.png")));
                }
            }
        });
        add(btnRestartStopwatch);

        btnCloseStopwatch = new JButton();
        btnCloseStopwatch.setBounds(204, 196, 51, 42);
        URL pathToClose = getClass().getResource("/img/close.png");
        btnCloseStopwatch.setIcon(new ImageIcon(pathToClose));
        add(btnCloseStopwatch);
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        size = 260 - spacing;
        centerX = 270 / 2;
        centerY = 184 / 2 + 10;

        drawClockFace(g);
        drawNumberClock(g);

        hour = getTime().getHour();
        minute = getTime().getMinute();
        second = getTime().getSecond();

        if (status == 2) {
            drawHands(g, hour, minute, second, Color.RED, Color.YELLOW);
        } else {
            drawHands(g, hour, minute, second, Color.RED, Color.BLACK);
        }

        g.setColor(Color.BLACK);
        g.fillOval(centerX - 5, centerY - 5, 10, 10);
        g.setColor(Color.RED);
        g.fillOval(centerX - 3, centerY - 3, 6, 6);

    }

    public void increaseTime() {
        increaseSeconds(1);
        paint(this.getGraphics());
    }

    public void setZeroTime(){
        restartStopwatch();
        paint(this.getGraphics());
    }

    private void drawClockFace(Graphics g) {
        for (int sec = 0; sec < 60; sec++) {
            int ticStart;
            if (sec % 5 == 0) {
                ticStart = size / 2 - 10;
            } else {
                ticStart = size / 2 - 5;
            }

            if (status == 2) {
                drawRadius(g, centerX, centerY, radPerSecMin * sec, ticStart - 20, size / 2 - 20, Color.YELLOW);
            } else {
                drawRadius(g, centerX, centerY, radPerSecMin * sec, ticStart - 20, size / 2 - 20, Color.BLACK);
            }

        }
    }

    private void drawRadius(Graphics g, int x, int y, double angle,
                            int minRadius, int maxRadius, Color colorNumber) {
        float sine = (float) Math.sin(angle);
        float cosine = (float) Math.cos(angle);
        int dxmin = (int) (minRadius * sine);
        int dymin = (int) (minRadius * cosine);
        int dxmax = (int) (maxRadius * sine);
        int dymax = (int) (maxRadius * cosine);
        g.setColor(colorNumber);
        g.drawLine(x + dxmin, y + dymin, x + dxmax, y + dymax);
    }

    private void drawNumberClock(Graphics g) {
        for (int num = 12; num > 0; num--) {
            drawNum(g, radPerNum * num, num);
        }
    }

    private void drawNum(Graphics g, float angle, int n) {
        float sine = (float) Math.sin(angle);
        float cosine = (float) Math.cos(angle);
        int dx = (int) ((size / 2 - 20 - 25) * -sine);
        int dy = (int) ((size / 2 - 20 - 25) * -cosine);

        g.drawString("" + n, dx + centerX - 5, dy + centerY + 5);
    }

    private void drawHands(Graphics g, double hour, double minute, double second, Color colorSecond, Color colorMHour) {
        double rsecond = (second * 6) * (Math.PI) / 180;
        double rminute = ((minute + (second / 60)) * 6) * (Math.PI) / 180;
        double rhours = ((hour + (minute / 60)) * 30) * (Math.PI) / 180;

        g.setColor(colorSecond);
        g.drawLine(centerX, centerY, centerX + (int) (75 * Math.cos(rsecond - (Math.PI / 2))), centerY + (int) (75 * Math.sin(rsecond - (Math.PI / 2))));
        g.setColor(colorMHour);
        g.drawLine(centerX, centerY, centerX + (int) (60 * Math.cos(rminute - (Math.PI / 2))), centerY + (int) (60 * Math.sin(rminute - (Math.PI / 2))));
        g.drawLine(centerX, centerY, centerX + (int) (45 * Math.cos(rhours - (Math.PI / 2))), centerY + (int) (45 * Math.sin(rhours - (Math.PI / 2))));
    }

    public AnalogStopwatchThread getAnalogStopWatchThread() {
        return analogStopWatchThread;
    }

    public JButton getBtnCloseStopwatch() {
        return btnCloseStopwatch;
    }
}

