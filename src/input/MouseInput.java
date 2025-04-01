package input;

import entities.Paddle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseMotionListener {
    private Paddle paddle;

    public MouseInput(Paddle paddle) {
        this.paddle = paddle;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paddle.setX(e.getX() - paddle.getWidth() / 2);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }
}
