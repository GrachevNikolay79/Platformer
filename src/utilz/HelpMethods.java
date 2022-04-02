package utilz;

import main.Game;

import java.awt.geom.Rectangle2D;

public class HelpMethods {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] levelData) {
        if (!isSolid(x, y, levelData))
            if (!isSolid(x + width, y + height, levelData))
                if (!isSolid(x + width, y, levelData))
                    if (!isSolid(x, y + height, levelData))
                        return true;

        return false;
    }

    private static boolean isSolid(float x, float y, int[][] levelData) {
        if (x < 0 || x >= Game.GAME_WIDTH) return true;
        if (y < 0 || y >= Game.GAME_HEIGHT) return true;
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;
        int value = levelData[(int) yIndex][(int) xIndex];
        if (value >= 48 || value < 0 || value != 11) return true;
        return false;
    }

    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitBox, float xSpeed) {
        int currentTile = (int) (hitBox.x / Game.TILES_SIZE);
        if (xSpeed > 0) { //right
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitBox.width);
            return tileXPos + xOffset - 1;
        }
        return currentTile * Game.TILES_SIZE; //else xSpeed<=0///left
    }

    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitBox, float airSpeed) {
        int currentTile = (int) (hitBox.y / Game.TILES_SIZE);
        if (airSpeed > 0) { //falling
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitBox.height);
            return tileYPos + yOffset - 1;
        }
        return currentTile * Game.TILES_SIZE; //else airSpeed<=0///jumping
    }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitBox, int[][] levelData) {
        // check the pixel below bottomLeft and bottomRight
        if (!isSolid(hitBox.x, hitBox.y + hitBox.height + 1, levelData))
            return isSolid(hitBox.x + hitBox.height, hitBox.y + hitBox.height + 1, levelData);

        return true;
    }
}

