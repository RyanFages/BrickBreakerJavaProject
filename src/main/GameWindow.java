package main;

import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow() {
        setTitle("Brick Breaker");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GamePanel panel = new GamePanel();
        add(panel);
        setVisible(true);
    }
}
