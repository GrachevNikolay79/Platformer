package main;

import gamestates.GameState;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class GamePanel extends JPanel {

    private final MouseInputs mouseInputs;
    private final KeyboardInputs keyboardInputs;
    private final Game game;

    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this);
        keyboardInputs = new KeyboardInputs(this);
        setPanelSize();
        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        this.game = game;
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public void updateGame() {

    }

    public Game getGame() {
        return game;
    }

    public void setGameState(GameState state) {
        GameState.state = state;
        switch (GameState.state) {
            case PLAYING:
                mouseInputs.setStateMethod(game.getPlaying());
                keyboardInputs.setStateMethod(game.getPlaying());
                break;
            case MENU:
                mouseInputs.setStateMethod(game.getMenu());
                keyboardInputs.setStateMethod(game.getMenu());
                break;
            case OPTIONS:

            case QUIT:

                break;


        }
    }
}
//HOW TO CREATE a MENU - Episode #12
//24:12