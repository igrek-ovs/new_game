package game.invasion.survivetheinvasion.objects;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import game.invasion.survivetheinvasion.GameLoop;
import game.invasion.survivetheinvasion.Joystick;
import game.invasion.survivetheinvasion.R;
import game.invasion.survivetheinvasion.Utils;

public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.00;
    public static final int MAX_HEALTH_POINTS = 10;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final Joystick joystick;
    private HealthBar healthBar;
    private int healthPoints;

    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.playerColor), positionX, positionY, radius);
        this.joystick = joystick;
        this.healthBar = new HealthBar(context, this);
        this.healthPoints = MAX_HEALTH_POINTS;
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
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        healthBar.draw(canvas);
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        if (healthPoints >= 0)
            this.healthPoints = healthPoints;
    }
}
