package inputs;

import gamestates.Statemethods;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private GamePanel gamePanel;
    private Statemethods method;

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.method = null;
    }

    public void setStateMethod(Statemethods method) {
        this.method = method;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (method != null) method.mouseClicked(mouseEvent);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (method != null) method.mousePressed(mouseEvent);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (method != null) method.mouseReleased(mouseEvent);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }
}
