package game.invasion.survivetheinvasion.gamepanel;

import game.invasion.survivetheinvasion.objects.Player;

public class PlayerState {
    public enum State {
        NOT_MOVING,
        STARTED_MOVING_LEFT,
        STARTED_MOVING_RIGHT,
        IS_MOVING_LEFT,
        IS_MOVING_RIGHT
    }

    private Player player;
    private State state;

    public PlayerState(Player player) {
        this.player = player;
        this.state = State.NOT_MOVING;
    }

    public State getState() {
        return state;
    }


    public void update() {
        switch (state) {
            case NOT_MOVING:
                if (player.velocityX != 0 || player.velocityY != 0) {
                    if (player.getDirectionX() > 0) {
                        state = State.STARTED_MOVING_RIGHT;
                    } else {
                        state = State.STARTED_MOVING_LEFT;
                    }
                }
                break;
            case STARTED_MOVING_LEFT:
                if (player.velocityX != 0 || player.velocityY != 0) {
                    if (player.getDirectionX() < 0) {
                        state = State.IS_MOVING_LEFT;
                    }
                } else {
                    state = State.NOT_MOVING;
                }
                break;
            case STARTED_MOVING_RIGHT:
                if (player.velocityX != 0 || player.velocityY != 0) {
                    if (player.getDirectionX() > 0) {
                        state = State.IS_MOVING_RIGHT;
                    }
                } else {
                    state = State.NOT_MOVING;
                }
                break;
            case IS_MOVING_LEFT:
                if (player.velocityX == 0 && player.velocityY == 0) {
                    state = State.NOT_MOVING;
                } else if (player.getDirectionX() > 0) {
                    state = State.STARTED_MOVING_RIGHT;
                }
                break;
            case IS_MOVING_RIGHT:
                if (player.velocityX == 0 && player.velocityY == 0) {
                    state = State.NOT_MOVING;
                } else if (player.getDirectionX() < 0) {
                    state = State.STARTED_MOVING_LEFT;
                }
                break;
            default:
                break;
        }
    }
}
