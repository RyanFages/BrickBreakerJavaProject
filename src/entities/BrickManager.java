package entities;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BrickManager {
    Brick[][] bricks;
    private final int rows;
    private final int cols;
    private final int brickWidth = 50;
    private final int brickHeight = 50;

    public BrickManager(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        bricks = new Brick[rows][cols];

        int startX = 50;
        int startY = 50;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                bricks[i][j] = new Brick(
                        startX + j * (brickWidth + 5),
                        startY + j * (brickHeight + 5),
                        brickWidth,
                        brickHeight);
            }
        }
    }

    public void draw(Graphics g) {
        for (Brick[] row : bricks) {
            for (Brick brick : row) {
                brick.draw(g);
            }
        }
    }

    public List<Brick> getBricks() {
        List<Brick> brickList = new ArrayList<>();
        for (Brick[] row : bricks) {
            Collections.addAll(brickList, row);
        }
        return brickList;
    }


    public void reset() {
        for (Brick[] row : bricks) {
            for (Brick brick : row) {
                brick.reset();
            }
        }
    }

}
