package game.invasion.survivetheinvasion.objects;

import android.content.Context;

import androidx.core.content.ContextCompat;

import game.invasion.survivetheinvasion.GameLoop;
import game.invasion.survivetheinvasion.Joystick;
import game.invasion.survivetheinvasion.R;

public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.00;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final Joystick joystick;

    public Player(Context context, Joystick joystick,  double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.playerColor), positionX, positionY, radius);
        this.joystick = joystick;
    }

    public void update() {
        velocityX = joystick.getActuatorX() * MAX_SPEED;
        velocityY = joystick.getActuatorY() * MAX_SPEED;
        positionX += velocityX;
        positionY += velocityY;
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
