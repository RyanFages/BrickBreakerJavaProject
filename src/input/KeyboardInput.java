package input;

import main.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {
    private GamePanel gamePanel;
    private boolean leftPressed, rightPressed;

    public KeyboardInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void update() {
        if (leftPressed) gamePanel.getPaddle().moveLeft();
        if (rightPressed) gamePanel.getPaddle().moveRight();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = true;
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = true;
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) gamePanel.togglePause();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = false;
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
