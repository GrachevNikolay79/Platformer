package inputs;

import gamestates.Statemethods;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;
    private Statemethods method;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.method = null;
    }

    public void setStateMethod(Statemethods method) {
        this.method = method;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (method != null) method.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (method != null) method.keyReleased(e);
    }
}
