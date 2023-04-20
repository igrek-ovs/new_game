package game.invasion.survivetheinvasion.states;

import game.invasion.survivetheinvasion.objects.Enemy;

public class EnemyState {
    public enum State {
        NOT_MOVING,
        STARTED_MOVING_LEFT,
        STARTED_MOVING_RIGHT,
        IS_MOVING_LEFT,
        IS_MOVING_RIGHT
    }

    private Enemy enemy;
    private State state;

    public EnemyState(Enemy enemy) {
        this.enemy = enemy;
        this.state = State.NOT_MOVING;
    }

    public State getState() {
        return state;
    }


    public void update() {
        switch (state) {
            case NOT_MOVING:
                if (enemy.velocityX != 0 || enemy.velocityY != 0) {
                    if (enemy.getDirectionX() > 0) {
                        state = State.STARTED_MOVING_RIGHT;
                    } else {
                        state = State.STARTED_MOVING_LEFT;
                    }
                }
                break;
            case STARTED_MOVING_LEFT:
                if (enemy.velocityX != 0 || enemy.velocityY != 0) {
                    if (enemy.getDirectionX() < 0) {
                        state = State.IS_MOVING_LEFT;
                    }
                } else {
                    state = State.NOT_MOVING;
                }
                break;
            case STARTED_MOVING_RIGHT:
                if (enemy.velocityX != 0 || enemy.velocityY != 0) {
                    if (enemy.getDirectionX() > 0) {
                        state = State.IS_MOVING_RIGHT;
                    }
                } else {
                    state = State.NOT_MOVING;
                }
                break;
            case IS_MOVING_LEFT:
                if (enemy.velocityX == 0 && enemy.velocityY == 0) {
                    state = State.NOT_MOVING;
                } else if (enemy.getDirectionX() > 0) {
                    state = State.STARTED_MOVING_RIGHT;
                }
                break;
            case IS_MOVING_RIGHT:
                if (enemy.velocityX == 0 && enemy.velocityY == 0) {
                    state = State.NOT_MOVING;
                } else if (enemy.getDirectionX() < 0) {
                    state = State.STARTED_MOVING_LEFT;
                }
                break;
            default:
                break;
        }
    }
}
