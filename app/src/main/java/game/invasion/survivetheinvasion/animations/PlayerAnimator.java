package game.invasion.survivetheinvasion.animations;

import android.graphics.Canvas;

import game.invasion.survivetheinvasion.GameDisplay;
import game.invasion.survivetheinvasion.gamepanel.PlayerState;
import game.invasion.survivetheinvasion.graphics.Sprite;
import game.invasion.survivetheinvasion.objects.Player;

public class PlayerAnimator {
    private static final int MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME = 5;
    private Sprite[] playerSpriteArray;
    private int updatesBeforeNextFrame;
    private int idxMovingFrame = 0;

    public PlayerAnimator(Sprite[] playerSpriteArray) {
        this.playerSpriteArray = playerSpriteArray;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, Player player) {
        switch (player.getPlayerState().getState()) {
            case NOT_MOVING:
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[2]);
                break;
            case STARTED_MOVING:
                updatesBeforeNextFrame = 5;
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[idxMovingFrame]);
                break;
            case IS_MOVING:
                updatesBeforeNextFrame--;
                if (updatesBeforeNextFrame == 0) {
                    updatesBeforeNextFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    toggleMovingFrame();
                }
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[idxMovingFrame]);
                break;
            default:
                break;
        }
    }

    private void toggleMovingFrame() {
        if(idxMovingFrame==0)
            idxMovingFrame =1;
        else{
            idxMovingFrame = 0;
        }
    }

    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, Player player, Sprite sprite) {
        sprite.draw(canvas,
                (int) gameDisplay.gameToDisplayCoordinatesX(player.getPositionX()) - 66,
                (int) gameDisplay.gameToDisplayCoordinatesY(player.getPositionY()) - 64
        );
    }
}
