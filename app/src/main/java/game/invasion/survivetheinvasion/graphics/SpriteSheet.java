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
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_sprite_sheet_scaled_2x_pngcrushed, bitmapOptions);
    }

    public Sprite[] getPlayerSpriteArray() {
        Sprite[] spriteArray = new Sprite[3];
        spriteArray[0] = new Sprite(this, new Rect(0, 0, 1 * 132, 128));
        spriteArray[1] = new Sprite(this, new Rect(132, 0, 2 * 132, 128));
        spriteArray[2] = new Sprite(this, new Rect(264, 0, 3 * 132, 128));
        return spriteArray;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
