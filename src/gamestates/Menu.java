package gamestates;

import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Menu extends State implements Statemethods {
    Game game;

    public Menu(Game game) {
        super(game);
        this.game = game;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawString("MENU", Game.GAME_WIDTH / 2, 200);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

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
