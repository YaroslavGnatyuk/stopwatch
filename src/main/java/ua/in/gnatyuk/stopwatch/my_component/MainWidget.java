package ua.in.gnatyuk.stopwatch.my_component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class MainWidget extends JPanel {
    private static final long serialVersionUID = 4844643587908521649L;
    private List<JPanel> stopwatches;
    private final JRadioButton analogStopwatchRadioBtn;
    private final JRadioButton digitalStopwatchRadioBtn;
    private final JButton btnAddNewStopWatch;
    private final JButton btnCreateStopwatch;

    public MainWidget() {
        setBounds(100, 100, 1024, 768);

        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(null);

        final JPanel panel_0 = new JPanel();
        panel_0.setLayout(null);
        panel_0.setBounds(57, 178, 269, 250);
        panel_0.setBackground(Color.GRAY);
        this.add(panel_0);

        final JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBounds(380, 178, 269, 250);
        panel_1.setBackground(Color.GRAY);
        this.add(panel_1);

        final JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBounds(707, 178, 269, 250);
        panel_2.setBackground(Color.GRAY);
        this.add(panel_2);

        final JPanel panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.setBounds(57, 439, 269, 250);
        panel_3.setBackground(Color.GRAY);
        this.add(panel_3);

        final JPanel panel_4 = new JPanel();
        panel_4.setLayout(null);
        panel_4.setBounds(380, 439, 269, 250);
        panel_4.setBackground(Color.GRAY);
        this.add(panel_4);

        final JPanel panel_5 = new JPanel();
        panel_5.setLayout(null);
        panel_5.setBounds(709, 439, 269, 250);
        panel_5.setBackground(Color.GRAY);
        this.add(panel_5);

        stopwatches = new ArrayList<JPanel>(6);
        stopwatches.add(panel_0);

        stopwatches.add(panel_1);
        stopwatches.add(panel_2);
        stopwatches.add(panel_3);
        stopwatches.add(panel_4);
        stopwatches.add(panel_5);

        analogStopwatchRadioBtn = new JRadioButton("Analog stopwatch");
        analogStopwatchRadioBtn.setBounds(286, 103, 187, 23);
        analogStopwatchRadioBtn.setVisible(false);
        this.add(analogStopwatchRadioBtn);

        digitalStopwatchRadioBtn = new JRadioButton("Digital stopwatch");
        digitalStopwatchRadioBtn.setBounds(578, 103, 188, 23);
        digitalStopwatchRadioBtn.setVisible(false);
        this.add(digitalStopwatchRadioBtn);

        btnAddNewStopWatch = new JButton("Add new stopwatch");
        btnAddNewStopWatch.setBounds(405, 38, 188, 57);
        this.add(btnAddNewStopWatch);


        btnCreateStopwatch = new JButton("Create");
        btnCreateStopwatch.setBounds(424, 38, 169, 57);
        btnCreateStopwatch.setVisible(false);
        this.add(btnCreateStopwatch);


        btnAddNewStopWatch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                analogStopwatchRadioBtn.setVisible(true);
                digitalStopwatchRadioBtn.setVisible(true);
                btnAddNewStopWatch.setVisible(false);
                btnCreateStopwatch.setVisible(true);
            }
        });

        btnCreateStopwatch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (analogStopwatchRadioBtn.isSelected()) {
                    btnCreateStopwatch.setVisible(false);
                    btnAddNewStopWatch.setVisible(true);
                    for (final JPanel stopwatch : stopwatches) {
                        if (stopwatch.getComponentCount() < 1) {
                            final AnalogStopwatch analogStopwatch = new AnalogStopwatch();
                            analogStopwatch.getBtnCloseStopwatch().addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    analogStopwatch.getAnalogStopWatchThread().cancel(true);
                                    analogStopwatch.getAnalogStopWatchThread().setRunning(false);
                                    stopwatch.removeAll();
                                    stopwatch.repaint();
                                }
                            });
                            stopwatch.add(analogStopwatch);
                            repaint();
                            break;
                        }
                    }
                    analogStopwatchRadioBtn.setVisible(false);
                    digitalStopwatchRadioBtn.setVisible(false);
                } else if (digitalStopwatchRadioBtn.isSelected()) {
                    btnCreateStopwatch.setVisible(false);
                    btnAddNewStopWatch.setVisible(true);
                    for (final JPanel stopwatch : stopwatches) {
                        if (stopwatch.getComponentCount() < 1) {
                            final DigitalStopwatch digitalStopwatch = new DigitalStopwatch();
                            digitalStopwatch.getBtnCloseStopwatch().addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    digitalStopwatch.getDigitalStopwatchThread().cancel(true);
                                    digitalStopwatch.getDigitalStopwatchThread().setRunning(false);
                                    stopwatch.removeAll();
                                    stopwatch.repaint();
                                }
                            });
                            stopwatch.add(digitalStopwatch);
                            repaint();
                            break;
                        }
                    }
                    analogStopwatchRadioBtn.setVisible(false);
                    digitalStopwatchRadioBtn.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(panel_1, "You should select type of stopwatch");
                }
            }
        });


        analogStopwatchRadioBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (digitalStopwatchRadioBtn.isSelected()) {
                    digitalStopwatchRadioBtn.setSelected(false);
                }
            }
        });

        digitalStopwatchRadioBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (analogStopwatchRadioBtn.isSelected()) {
                    analogStopwatchRadioBtn.setSelected(false);
                }
            }
        });
    }

    public List<JPanel> getStopwatches() {
        return stopwatches;
    }
}
