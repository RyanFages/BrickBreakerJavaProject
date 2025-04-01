package entities;

import java.awt.*;

public class Paddle {
    private int x, y, width, height;
    private final int speed = 8;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void moveLeft(int offsetX) {
        int leftBoundary = offsetX;
        if (x > leftBoundary) {
            x -= speed;
        }
    }

    public void moveRight(int offsetX) {
        int rightBoundary = offsetX + Constants.WINDOW_WIDTH - width;
        if (x < rightBoundary) {
            x += speed;
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getWidth() {
        return width;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void reset() {
        x = 350;
    }
}
