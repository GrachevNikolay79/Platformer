package gamestates;

import main.Game;
import ui.MenuButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Menu extends State implements Statemethods {
    private Game game;
    private MenuButton[] buttons = new MenuButton[3];

    public Menu(Game game) {
        super(game);
        this.game = game;
        loadButtons();
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, GameState.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, GameState.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 2, GameState.QUIT);
    }

    @Override
    public void update() {
        for (MenuButton mb : buttons)
            mb.update();
    }

    @Override
    public void draw(Graphics g) {
        for (MenuButton mb : buttons)
            mb.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                if (mb.isMousePressed())
                    mb.applyGameState();
                break;
            }
        }
        resrtButtons();
        game.setGameState(GameState.state);
    }

    private void resrtButtons() {
        for (MenuButton mb : buttons)
            mb.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons)
            if (isIn(e, mb))
                mb.setMouseOver(true);
            else
                mb.setMouseOver(false);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("KeyPressed in menu!");
        switch (e.getExtendedKeyCode()) {
            case KeyEvent.VK_ENTER:
                game.setGameState(GameState.PLAYING);
                break;
            case KeyEvent.VK_ESCAPE:
                //game.setGameState(GameState.MENU);
                game.Stop();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
