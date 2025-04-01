package main;

import entities.Ball;
import entities.BrickManager;
import entities.Paddle;
import input.KeyboardInput;
import input.MouseInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private final Paddle paddle;
    private final Ball ball;
    private final BrickManager brickManager;
    private final KeyboardInput keyboardInput;
    private boolean isPaused = false;
    private boolean isGameOver = false;
    private final JButton restartButton;

    public GamePanel() {
        paddle = new Paddle(350, 550, 100, 10);
        ball = new Ball(400, 300, 20, -2, -3);
        brickManager = new BrickManager(5, 8);

        keyboardInput = new KeyboardInput(this);
        addKeyListener(keyboardInput);
        addMouseMotionListener(new MouseInput(paddle));

        setFocusable(true);
        setLayout(null);

        // Pause Button
        JButton pauseButton = new JButton("Pause");
        pauseButton.setBounds(700, 10, 80, 30);
        pauseButton.addActionListener(_ -> togglePause());
        add(pauseButton);

        // Restart Button (Initially Hidden)
        restartButton = new JButton("Restart");
        restartButton.setBounds(350, 300, 100, 50);
        restartButton.addActionListener(_ -> restartGame());
        restartButton.setVisible(false);
        add(restartButton);

        Timer timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paddle.draw(g);
        ball.draw(g);
        brickManager.draw(g);

        if (isGameOver) {
            drawGameOverScreen(g);
        } else if (isPaused) {
            drawPauseScreen(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isPaused && !isGameOver) {
            keyboardInput.update();
            ball.move();
            ball.checkCollision(paddle, brickManager);
            if (ball.getY() > 600) {
                gameOver();
            }
        }
        repaint();
    }

    public void togglePause() {
        isPaused = !isPaused;
    }

    private void gameOver() {
        isGameOver = true;
        restartButton.setVisible(true);
    }

    private void restartGame() {
        isGameOver = false;
        restartButton.setVisible(false);
        ball.reset();
        brickManager.reset();
        paddle.reset();
        isPaused = false;
    }

    private void drawGameOverScreen(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Game Over", 300, 250);
    }

    private void drawPauseScreen(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("PAUSED", 350, 250);
    }

    public Paddle getPaddle() {
        return paddle;
    }
}
