package ua.in.gnatyuk.stopwatch.view;

import ua.in.gnatyuk.stopwatch.my_component.MainWidget;

import javax.swing.*;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private final MainWidget mainWidget;

    private MainWindow() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        mainWidget = new MainWidget();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1024, 768);
        setContentPane(mainWidget);
        this.setResizable(false);
    }

    public static void main(String[] args) {
        try {
            SwingUtilities.invokeAndWait(() -> {
                MainWindow frame = new MainWindow();
                frame.setVisible(true);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
