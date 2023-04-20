package game.invasion.survivetheinvasion.objects;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import game.invasion.survivetheinvasion.GameDisplay;
import game.invasion.survivetheinvasion.GameLoop;
import game.invasion.survivetheinvasion.animations.PlayerAnimator;
import game.invasion.survivetheinvasion.gamepanel.HealthBar;
import game.invasion.survivetheinvasion.gamepanel.Joystick;
import game.invasion.survivetheinvasion.R;
import game.invasion.survivetheinvasion.Utils;
import game.invasion.survivetheinvasion.states.PlayerState;

public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.00;
    public static final int MAX_HEALTH_POINTS = 3;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final Joystick joystick;
    private HealthBar healthBar;
    private int healthPoints;
    private PlayerAnimator animator;
    private PlayerState playerState;

    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius, PlayerAnimator animator) {
        super(context, ContextCompat.getColor(context, R.color.playerColor), positionX, positionY, radius);
        this.joystick = joystick;
        this.healthBar = new HealthBar(context, this);
        this.healthPoints = MAX_HEALTH_POINTS;
        this.animator = animator;
        this.playerState = new PlayerState(this);
    }

    public void update() {
        velocityX = joystick.getActuatorX() * MAX_SPEED;
        velocityY = joystick.getActuatorY() * MAX_SPEED;
        positionX += velocityX;
        positionY += velocityY;

        if (velocityX != 0 || velocityY != 0) {
            double distance = Utils.getDistanceBetweenPoints(0, 0, velocityX, velocityY);
            directionX = velocityX / distance;
            directionY = velocityY / distance;
        }
        playerState.update();
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        animator.draw(canvas, gameDisplay, this);

        healthBar.draw(canvas, gameDisplay);
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        if (healthPoints >= 0)
            this.healthPoints = healthPoints;
    }

    public PlayerState getPlayerState(){
        return playerState;
    }

    public double getSpeed() {
        return Math.sqrt(velocityX * velocityX + velocityY * velocityY);
    }
}
