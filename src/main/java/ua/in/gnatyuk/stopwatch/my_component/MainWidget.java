package ua.in.gnatyuk.stopwatch.my_component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
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

        final JPanel stopwatchPanel_0 = new JPanel();
        stopwatchPanel_0.setLayout(null);
        stopwatchPanel_0.setBounds(57, 178, 269, 250);
        stopwatchPanel_0.setBackground(Color.GRAY);
        this.add(stopwatchPanel_0);

        final JPanel stopwatchPanel_1 = new JPanel();
        stopwatchPanel_1.setLayout(null);
        stopwatchPanel_1.setBounds(380, 178, 269, 250);
        stopwatchPanel_1.setBackground(Color.GRAY);
        this.add(stopwatchPanel_1);

        final JPanel stopwatchPanel_2 = new JPanel();
        stopwatchPanel_2.setLayout(null);
        stopwatchPanel_2.setBounds(707, 178, 269, 250);
        stopwatchPanel_2.setBackground(Color.GRAY);
        this.add(stopwatchPanel_2);

        final JPanel stopwatchPanel_3 = new JPanel();
        stopwatchPanel_3.setLayout(null);
        stopwatchPanel_3.setBounds(57, 439, 269, 250);
        stopwatchPanel_3.setBackground(Color.GRAY);
        this.add(stopwatchPanel_3);

        final JPanel stopwatchPanel_4 = new JPanel();
        stopwatchPanel_4.setLayout(null);
        stopwatchPanel_4.setBounds(380, 439, 269, 250);
        stopwatchPanel_4.setBackground(Color.GRAY);
        this.add(stopwatchPanel_4);

        final JPanel stopwatchPanel_5 = new JPanel();
        stopwatchPanel_5.setLayout(null);
        stopwatchPanel_5.setBounds(709, 439, 269, 250);
        stopwatchPanel_5.setBackground(Color.GRAY);
        this.add(stopwatchPanel_5);

        stopwatches = new ArrayList<>(6);
        stopwatches.add(stopwatchPanel_0);
        stopwatches.add(stopwatchPanel_1);
        stopwatches.add(stopwatchPanel_2);
        stopwatches.add(stopwatchPanel_3);
        stopwatches.add(stopwatchPanel_4);
        stopwatches.add(stopwatchPanel_5);

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


        btnAddNewStopWatch.addActionListener(e -> {
            analogStopwatchRadioBtn.setVisible(true);
            digitalStopwatchRadioBtn.setVisible(true);
            btnAddNewStopWatch.setVisible(false);
            btnCreateStopwatch.setVisible(true);
        });

        btnCreateStopwatch.addActionListener(e -> {
            if (analogStopwatchRadioBtn.isSelected()) {
                btnCreateStopwatch.setVisible(false);
                btnAddNewStopWatch.setVisible(true);
                for (final JPanel stopwatch : stopwatches) {
                    if (stopwatch.getComponentCount() < 1) {
                        final AnalogStopwatch analogStopwatch = new AnalogStopwatch();
                        analogStopwatch.getBtnCloseStopwatch().addActionListener(e1 -> {
                            analogStopwatch.getAnalogStopWatchThread().cancel(true);
                            analogStopwatch.getAnalogStopWatchThread().setRunning(false);
                            stopwatch.removeAll();
                            stopwatch.repaint();
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
                        digitalStopwatch.getBtnCloseStopwatch().addActionListener(e1 -> {
                            digitalStopwatch.getDigitalStopwatchThread().cancel(true);
                            digitalStopwatch.getDigitalStopwatchThread().setRunning(false);
                            stopwatch.removeAll();
                            stopwatch.repaint();
                        });
                        stopwatch.add(digitalStopwatch);
                        repaint();
                        break;
                    }
                }
                analogStopwatchRadioBtn.setVisible(false);
                digitalStopwatchRadioBtn.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(stopwatchPanel_1, "You should select type of stopwatch");
            }
        });


        analogStopwatchRadioBtn.addActionListener(e -> {
            if (digitalStopwatchRadioBtn.isSelected()) {
                digitalStopwatchRadioBtn.setSelected(false);
            }
        });

        digitalStopwatchRadioBtn.addActionListener(e -> {
            if (analogStopwatchRadioBtn.isSelected()) {
                analogStopwatchRadioBtn.setSelected(false);
            }
        });
    }
}
