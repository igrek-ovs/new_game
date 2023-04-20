package game.invasion.survivetheinvasion.animations;

import android.graphics.Canvas;

import game.invasion.survivetheinvasion.GameDisplay;
import game.invasion.survivetheinvasion.graphics.Sprite;
import game.invasion.survivetheinvasion.objects.Enemy;

public class SquishiAnimator {
    private static final int MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME = 5;
    private Sprite[] squishiSpriteArrayLeft;
    private Sprite[] squishiSpriteArrayRight;
    private int updatesBeforeNextFrame;
    private int idxMovingFrame = 0;

    public SquishiAnimator(Sprite[] squishiSpriteArrayLeft, Sprite[] squishiSpriteArrayRight) {
        this.squishiSpriteArrayLeft = squishiSpriteArrayLeft;
        this.squishiSpriteArrayRight = squishiSpriteArrayRight;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, Enemy enemy) {
        switch (enemy.getEnemyState().getState()) {
            case NOT_MOVING:
                drawFrame(canvas, gameDisplay, enemy, squishiSpriteArrayRight[idxMovingFrame]);
                break;
            case STARTED_MOVING_LEFT:
                updatesBeforeNextFrame = 5;
                drawFrame(canvas, gameDisplay, enemy, squishiSpriteArrayLeft[idxMovingFrame]);
                break;
            case STARTED_MOVING_RIGHT:
                updatesBeforeNextFrame = 5;
                drawFrame(canvas, gameDisplay, enemy, squishiSpriteArrayRight[idxMovingFrame]);
                break;
            case IS_MOVING_LEFT:
                updatesBeforeNextFrame--;
                if (updatesBeforeNextFrame == 0) {
                    updatesBeforeNextFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    toggleMovingFrame();
                }
                drawFrame(canvas, gameDisplay, enemy, squishiSpriteArrayLeft[idxMovingFrame]);
                break;
            case IS_MOVING_RIGHT:
                updatesBeforeNextFrame--;
                if (updatesBeforeNextFrame == 0) {
                    updatesBeforeNextFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    toggleMovingFrame();
                }
                drawFrame(canvas, gameDisplay, enemy, squishiSpriteArrayRight[idxMovingFrame]);
                break;
            default:
                break;
        }
    }

    private void toggleMovingFrame() {
        if (idxMovingFrame != 7)
            idxMovingFrame++;
        else {
            idxMovingFrame = 0;
        }
    }



    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, Enemy enemy, Sprite sprite) {
        sprite.draw(canvas,
                (int) gameDisplay.gameToDisplayCoordinatesX(enemy.getPositionX()) - 66,
                (int) gameDisplay.gameToDisplayCoordinatesY(enemy.getPositionY()) - 64
        );
    }
}
