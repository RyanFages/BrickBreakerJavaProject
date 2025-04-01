package main;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow() {
        setTitle("Brick Breaker");

        setUndecorated(false);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        GamePanel panel = new GamePanel();
        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        panel.requestFocusInWindow();
    }
}