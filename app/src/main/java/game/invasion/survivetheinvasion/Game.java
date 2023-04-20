package game.invasion.survivetheinvasion;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.invasion.survivetheinvasion.animations.PlayerAnimator;
import game.invasion.survivetheinvasion.gamepanel.Joystick;
import game.invasion.survivetheinvasion.gamepanel.Performance;
import game.invasion.survivetheinvasion.graphics.Background;
import game.invasion.survivetheinvasion.graphics.SpriteSheet;
import game.invasion.survivetheinvasion.objects.Circle;
import game.invasion.survivetheinvasion.objects.Enemy;
import game.invasion.survivetheinvasion.gamepanel.GameOver;
import game.invasion.survivetheinvasion.objects.Player;
import game.invasion.survivetheinvasion.objects.Spell;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final Joystick joystick;
    //private final Enemy enemy;
    private GameLoop gameLoop;
    private Context context;
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private List<Spell> spellList = new ArrayList<Spell>();
    private int joystickPointerId = 0;
    private GameOver gameOver;
    private Performance performance;
    private GameDisplay gameDisplay;
    private Background bg;

    public Game(Context context) {
        super(context);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        gameLoop = new GameLoop(this, surfaceHolder);



        this.context = context;

        performance = new Performance(context, gameLoop);

        //Initialize joystick
        joystick = new Joystick(275, 1000, 70, 40);
        SpriteSheet spriteSheet = new SpriteSheet(context);

        PlayerAnimator playerAnimator = new PlayerAnimator(spriteSheet.getPlayerSpriteArray());

        player = new Player(context, joystick, 400, 400, 50, playerAnimator);
        //enemy = new Enemy(getContext(), player, 1000, 1000, 30);

        gameOver = new GameOver(context);

        //Initialize game display!
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);


        /*background = new Background(context, displayMetrics.widthPixels, displayMetrics.heightPixels,R.drawable.img, player);*/
        bg = new Background(context, displayMetrics.widthPixels, displayMetrics.heightPixels,R.drawable.img, player);



        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        if(gameLoop.getState().equals(Thread.State.TERMINATED)){

            gameLoop = new GameLoop(this, holder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        bg.draw(canvas);
        player.draw(canvas, gameDisplay);
        joystick.draw(canvas);

        for (Enemy enemy : enemyList) {
            enemy.draw(canvas, gameDisplay);
        }

        for (Spell spell : spellList) {
            spell.draw(canvas, gameDisplay);
        }

        if(player.getHealthPoints()<=0)
        {
            gameOver.draw(canvas);
        }

    }



    public void update() {
        /*background.update();*/

        if(player.getHealthPoints()<=0){
            return;
        }
        joystick.update();
        player.update();

        if (Enemy.readyToSpawn()) {
            enemyList.add(new Enemy(getContext(), player));
        }

        for (Enemy enemy : enemyList) {
            enemy.update();
        }

        for (Spell spell : spellList) {
            spell.update();
        }

        Iterator<Enemy> iteratorEnemy = enemyList.iterator();
        while (iteratorEnemy.hasNext()) {
            Circle enemy = iteratorEnemy.next();
            if (Circle.isColliding(enemy, player)) {
                iteratorEnemy.remove();
                player.setHealthPoints(player.getHealthPoints() - 1);
                continue;
            }

            Iterator<Spell> iteratorSpell = spellList.iterator();
            while (iteratorSpell.hasNext()) {
                Circle spell = iteratorSpell.next();
                if (Circle.isColliding(spell, enemy)) {
                    iteratorSpell.remove();
                    iteratorEnemy.remove();
                    break;
                }
            }
        }
        gameDisplay.update();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (joystick.getIsPressed()) {
                    spellList.add(new Spell(getContext(), player));
                } else if (joystick.isPressed((double) event.getX(), (double) event.getY())) {
                    joystickPointerId = event.getPointerId(event.getActionIndex());
                    joystick.setIsPressed(true);
                } else {
                    spellList.add(new Spell(getContext(), player));
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (joystick.getIsPressed()) {
                    joystick.setActuator((double) event.getX(), (double) event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if(joystickPointerId == event.getPointerId(event.getActionIndex())){
                    joystick.setIsPressed(false);
                    joystick.resetActuator();
                }

                return true;
        }

        return super.onFilterTouchEventForSecurity(event);
    }

    public void pause() {
        gameLoop.stopLoop();
    }
}
