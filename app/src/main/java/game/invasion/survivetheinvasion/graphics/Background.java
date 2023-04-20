package game.invasion.survivetheinvasion.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import game.invasion.survivetheinvasion.objects.Player;


public class Background {

    private final double startX;
    private final double startY;
    private Bitmap background;
    private Player player;
    private int screenWidth, screenHeight;

    public Background(Context context, int screenWidth, int screenHeight, int resourceId, Player player) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.player = player;

        // Загрузка изображения фона
        background = BitmapFactory.decodeResource(context.getResources(), resourceId);
        // Изменение размера изображения фона до размера экрана
        background = Bitmap.createScaledBitmap(background, screenWidth, screenHeight, true);

        // Рассчитываем начальные координаты фона
        int bgWidth = background.getWidth();
        int bgHeight = background.getHeight();
        int centerX = (int) (player.getPositionX() - screenWidth / 2);
        int centerY = (int) (player.getPositionY() - screenHeight / 2);
        startX = centerX - (centerX % bgWidth) - bgWidth;
        startY = centerY - (centerY % bgHeight) - bgHeight;
    }

    public void draw(Canvas canvas) {
        int bgWidth = background.getWidth();
        int bgHeight = background.getHeight();
        int offsetX = (int) ((player.getPositionX() - screenWidth / 2 - startX) % bgWidth);
        int offsetY = (int) ((player.getPositionY() - screenHeight / 2 - startY) % bgHeight);
        if (offsetX < 0) {
            offsetX += bgWidth;
        }
        if (offsetY < 0) {
            offsetY += bgHeight;
        }

        // Рассчитываем количество повторов изображения фона по ширине и высоте
        int horizontalRepeats = (int) Math.ceil((float) screenWidth / bgWidth) + 2;
        int verticalRepeats = (int) Math.ceil((float) screenHeight / bgHeight) + 2;

        // Рисуем повторяющийся фон
        for (int i = 0; i < horizontalRepeats; i++) {
            for (int j = 0; j < verticalRepeats; j++) {
                canvas.drawBitmap(background, (float) (i * bgWidth + startX - offsetX), (float) (j * bgHeight + startY - offsetY), null);
            }
        }
    }
}


