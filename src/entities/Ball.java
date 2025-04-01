package entities;

import java.awt.*;
import java.util.List;

public class Ball {
    private int x;
    private int y;
    private final int diameter;
    private int dx, dy;

    public Ball(int x, int y, int diameter, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.dx = dx;
        this.dy = dy;
    }

    public void move(int offsetX, int offsetY) {
        x += dx;
        y += dy;

        int leftBoundary = offsetX;
        int rightBoundary = offsetX + Constants.WINDOW_WIDTH - diameter;
        int topBoundary = offsetY;


        // Wall collision
        if (x <= leftBoundary || x >= rightBoundary) {
            dx = -dx;
        }
        if (y <= topBoundary) {
            dy = -dy;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
    }

    public void checkCollision(Paddle paddle, BrickManager brickManager) {
        if (getBounds().intersects(paddle.getBounds())) {
            reverseYDirection();
        }
        List<Brick> bricks = brickManager.getBricks();
        handleBrickCollision(bricks);
    }

    public void handleBrickCollision(List<Brick> bricks) {
        for (Brick brick : bricks) {
            if (!brick.isDestroyed() && getBounds().intersects(brick.getBounds())) {
                brick.destroy();
                Rectangle ballBounds = getBounds();
                Rectangle brickBounds = brick.getBounds();

                // Calculate overlap distances
                double overlapLeft = ballBounds.getMaxX() - brickBounds.getMinX();
                double overlapRight = brickBounds.getMaxX() - ballBounds.getMinX();
                double overlapTop = ballBounds.getMaxY() - brickBounds.getMinY();
                double overlapBottom = brickBounds.getMaxY() - ballBounds.getMinY();

                // Find the smallest overlap to determine collision side
                double minOverlapX = Math.min(overlapLeft, overlapRight);
                double minOverlapY = Math.min(overlapTop, overlapBottom);

                if (minOverlapX < minOverlapY) {
                    // Horizontal collision - reverse X direction
                    reverseXDirection();
                } else {
                    // Vertical collision - reverse Y direction
                    reverseYDirection();
                }
                break; // Exit loop after handling the collision
            }
        }
    }

    private void reverseYDirection() {
        dy = -dy;
    }

    private void reverseXDirection() {
        dx = -dx;
    }


    public void reset() {
        x = 400;
        y = 300;
        dx = -2;
        dy = -3;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, diameter, diameter);
    }
}
