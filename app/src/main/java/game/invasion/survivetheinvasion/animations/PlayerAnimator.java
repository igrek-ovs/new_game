package game.invasion.survivetheinvasion.animations;

import android.graphics.Canvas;

import game.invasion.survivetheinvasion.GameDisplay;
import game.invasion.survivetheinvasion.graphics.Sprite;
import game.invasion.survivetheinvasion.objects.Player;

public class PlayerAnimator {
    private static final int MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME = 5;
    private Sprite[] playerSpriteArrayLeft;
    private Sprite[] playerSpriteArrayRight;
    private int updatesBeforeNextFrame;
    private int idxMovingFrame = 0;

    public PlayerAnimator(Sprite[] playerSpriteArrayLeft, Sprite[] playerSpriteArrayRight) {
        this.playerSpriteArrayLeft = playerSpriteArrayLeft;
        this.playerSpriteArrayRight = playerSpriteArrayRight;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, Player player) {
        switch (player.getPlayerState().getState()) {
            case NOT_MOVING_RIGHT:
                updatesBeforeNextFrame--;
                if (updatesBeforeNextFrame == 0) {
                    updatesBeforeNextFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    toggleStandingFrame();
                }
                drawFrame(canvas, gameDisplay, player, playerSpriteArrayRight[idxMovingFrame]);
                break;
            case NOT_MOVING_LEFT:
                updatesBeforeNextFrame--;
                if (updatesBeforeNextFrame == 0) {
                    updatesBeforeNextFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    toggleStandingFrame();
                }
                drawFrame(canvas, gameDisplay, player, playerSpriteArrayLeft[idxMovingFrame]);
                break;
            case STARTED_MOVING_LEFT:
                updatesBeforeNextFrame = 5;
                drawFrame(canvas, gameDisplay, player, playerSpriteArrayLeft[idxMovingFrame]);
                break;
            case STARTED_MOVING_RIGHT:
                updatesBeforeNextFrame = 5;
                drawFrame(canvas, gameDisplay, player, playerSpriteArrayRight[idxMovingFrame]);
                break;
            case IS_MOVING_LEFT:
                updatesBeforeNextFrame--;
                if (updatesBeforeNextFrame == 0) {
                    updatesBeforeNextFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    toggleMovingFrame();
                }
                drawFrame(canvas, gameDisplay, player, playerSpriteArrayLeft[idxMovingFrame]);
                break;
            case IS_MOVING_RIGHT:
                updatesBeforeNextFrame--;
                if (updatesBeforeNextFrame == 0) {
                    updatesBeforeNextFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    toggleMovingFrame();
                }
                drawFrame(canvas, gameDisplay, player, playerSpriteArrayRight[idxMovingFrame]);
                break;
            default:
                break;
        }
    }

    private void toggleMovingFrame() {
        if (idxMovingFrame != 9)
            idxMovingFrame++;
        else {
            idxMovingFrame = 2;
        }
    }

    private void toggleStandingFrame() {
        if (idxMovingFrame == 0)
            idxMovingFrame = 1;
        else {
            idxMovingFrame = 1;
        }
    }

    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, Player player, Sprite sprite) {
        sprite.draw(canvas,
                (int) gameDisplay.gameToDisplayCoordinatesX(player.getPositionX()) - 66,
                (int) gameDisplay.gameToDisplayCoordinatesY(player.getPositionY()) - 64
        );
    }
}
