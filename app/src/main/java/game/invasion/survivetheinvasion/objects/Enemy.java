package game.invasion.survivetheinvasion.objects;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import game.invasion.survivetheinvasion.GameDisplay;
import game.invasion.survivetheinvasion.GameLoop;
import game.invasion.survivetheinvasion.R;
import game.invasion.survivetheinvasion.animations.PlayerAnimator;
import game.invasion.survivetheinvasion.animations.SquishiAnimator;
import game.invasion.survivetheinvasion.states.EnemyState;
import game.invasion.survivetheinvasion.states.PlayerState;

public class Enemy extends Circle {

    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND * 0.6;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private static final double SPAWNS_PER_MINUTE = 15;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE/60;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    private SquishiAnimator animator;
    private EnemyState enemyState;

    private final Player player;

    public Enemy(Context context, Player player, double positionX, double positionY, double radius, SquishiAnimator animator) {
        super(context, ContextCompat.getColor(context, R.color.enemyColor), positionX, positionY, radius);
        this.player = player;
        this.animator = animator;
        this.enemyState = new EnemyState(this);
    }

    public Enemy(Context context, Player player) {
        super(context, ContextCompat.getColor(context, R.color.enemyColor), Math.random() * 2000, Math.random() * 2000, 30);
        this.player = player;
    }

    public static boolean readyToSpawn() {
        if(updatesUntilNextSpawn <= 0){
            updatesUntilNextSpawn += UPDATES_PER_SPAWN;
            return true;
        } else {
            updatesUntilNextSpawn--;
            return false;
        }
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        animator.draw(canvas, gameDisplay, this);
    }

    @Override
    public void update() {
        double distanceToPlayerX = player.getPositionX() - this.positionX;
        double distanceToPlayerY = player.getPositionY() - this.positionY;
        double distanceToPlayer = GameObject.getDistanceBetweenObjects(this, player);

        double directionX = distanceToPlayerX/distanceToPlayer;
        double directionY = distanceToPlayerY/distanceToPlayer;

        if(distanceToPlayer>0){
            velocityX = directionX * MAX_SPEED;
            velocityY = directionY * MAX_SPEED;
        } else {
            velocityX = 0;
            velocityY = 0;
        }

        positionX += velocityX;
        positionY += velocityY;
    }

    public EnemyState getEnemyState(){
        return enemyState;
    }
}
