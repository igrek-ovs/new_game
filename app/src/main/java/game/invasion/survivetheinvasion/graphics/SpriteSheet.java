package game.invasion.survivetheinvasion.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import game.invasion.survivetheinvasion.R;


public class SpriteSheet {
    private Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.everything_spritesheet_scaled, bitmapOptions);
    }

    public Sprite[] getPlayerSpriteArrayLeft() {
        Sprite[] spriteArray = new Sprite[8];
        spriteArray[0] = new Sprite(this, new Rect(0, 120, 1 * 132, 248));
        spriteArray[1] = new Sprite(this, new Rect(1*132, 120, 2 * 132, 248));
        spriteArray[2] = new Sprite(this, new Rect(2*132, 120, 3 * 132, 248));
        spriteArray[3] = new Sprite(this, new Rect(3*132, 120, 4 * 132, 248));
        spriteArray[4] = new Sprite(this, new Rect(4*132, 120, 5 * 132, 248));
        spriteArray[5] = new Sprite(this, new Rect(5*132, 120, 6 * 132, 248));
        spriteArray[6] = new Sprite(this, new Rect(6*132, 120, 7 * 132, 248));
        spriteArray[7] = new Sprite(this, new Rect(7*132, 120, 8 * 132, 248));

        return spriteArray;
    }

    public Sprite[] getPlayerSpriteArrayRight() {
        Sprite[] spriteArray = new Sprite[8];
        spriteArray[0] = new Sprite(this, new Rect(0, 248, 1 * 132, 376));
        spriteArray[1] = new Sprite(this, new Rect(1*132, 248, 2 * 132, 376));
        spriteArray[2] = new Sprite(this, new Rect(2*132, 248, 3 * 132, 376));
        spriteArray[3] = new Sprite(this, new Rect(3*132, 248, 4 * 132, 376));
        spriteArray[4] = new Sprite(this, new Rect(4*132, 248, 5 * 132, 376));
        spriteArray[5] = new Sprite(this, new Rect(5*132, 248, 6 * 132, 376));
        spriteArray[6] = new Sprite(this, new Rect(6*132, 248, 7 * 132, 376));
        spriteArray[7] = new Sprite(this, new Rect(7*132, 248, 8 * 132, 376));

        return spriteArray;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
