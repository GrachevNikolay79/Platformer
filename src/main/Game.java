package main;

import gamestates.GameState;
import gamestates.Menu;
import gamestates.Playing;

import java.awt.*;

public class Game implements Runnable {
    public static final int TILES_DEFAULT_SIZE = 32;
    public static final float SCALE = 2.0f;
    public static final int TILES_IN_WIDTH = 26;
    public static final int TILES_IN_HEIGHT = 14;
    public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public static final int GAME_WIDTH = TILES_IN_WIDTH * TILES_SIZE;
    public static final int GAME_HEIGHT = TILES_IN_HEIGHT * TILES_SIZE;
    private final GameWindow gameWindow;
    private final GamePanel gamePanel;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Thread gameThread;
    private Playing playing;
    private Menu menu;


    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        setGameState(GameState.PLAYING);

        startGameLoop();
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        switch (GameState.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case OPTIONS:

            case QUIT:
            default:
                System.exit(0);
                break;
        }
    }

    public void render(Graphics g) {
        switch (GameState.state) {
            case PLAYING:
                playing.draw(g);
                break;
            case MENU:
                menu.draw(g);
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        long timePerFrame = 1000000000 / FPS_SET;
        long timePerUpdate = 1000000000 / UPS_SET;
        long lastCheck = System.currentTimeMillis();
        long countTickPerFrame = 0;
        long countTickPerUpdate = 0;
        long now = 0, frames = 0, updates = 0;
        long lastTimeFrame = System.nanoTime();
        long lastTimeUpdate = System.nanoTime();

        while (true) {
            now = System.nanoTime();
            countTickPerFrame += (now - lastTimeFrame);
            lastTimeFrame = now;
            if (countTickPerFrame >= timePerFrame) {
                gamePanel.repaint();
                frames++;
                countTickPerFrame -= timePerFrame;
                lastTimeFrame = System.nanoTime();
            }

            now = System.nanoTime();
            countTickPerUpdate += (now - lastTimeUpdate);
            lastTimeUpdate = now;
            if (countTickPerUpdate >= timePerUpdate) {
                update();
                updates++;
                countTickPerUpdate -= timePerUpdate;
                lastTimeFrame = System.nanoTime();
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                System.out.println("FPS: " + frames + "\t  UPS: " + updates);
                frames = 0;
                updates = 0;
                lastCheck = System.currentTimeMillis();
            }
        }
    }


    public void windowFocusLost() {
        if (GameState.state == GameState.PLAYING)
            playing.getPlayer().resetDirBooleans();
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public void setGameState(GameState state) {
        GameState.state = state;
        gamePanel.setGameState(state);
    }

    public void Stop() {

    }
}

