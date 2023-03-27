package game.invasion.survivetheinvasion.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import game.invasion.survivetheinvasion.GameDisplay;
import game.invasion.survivetheinvasion.R;

public class GameOver {

    private Context context;

    public GameOver(Context context) {
        this.context = context;
    }

    public void draw(Canvas canvas) {
        String text = "U DIED! XD";
        float x = 800;
        float y = 200;
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.gameOverColor);

        paint.setColor(color);
        float textSize = 130;
        paint.setTextSize(textSize);
        canvas.drawText(text, x, y, paint);
    }
}
