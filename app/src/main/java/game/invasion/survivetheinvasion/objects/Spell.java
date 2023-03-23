package game.invasion.survivetheinvasion.objects;

import android.content.Context;

import androidx.core.content.ContextCompat;

import game.invasion.survivetheinvasion.GameLoop;
import game.invasion.survivetheinvasion.R;
import game.invasion.survivetheinvasion.Utils;


public class Spell extends Circle {

    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND * 0.6;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;

    public Spell(Context context, Player spellcaster) {
        super(context, ContextCompat.getColor(context, R.color.spellColor), spellcaster.getPositionX(), spellcaster.getPositionY(), 14);

        velocityX = spellcaster.getDirectionX() * MAX_SPEED;
        velocityY = spellcaster.getDirectionY() * MAX_SPEED;
    }

    @Override
    public void update() {
        positionX += velocityX;
        positionY += velocityY;


    }
}
