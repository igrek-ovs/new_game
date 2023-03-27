package game.invasion.survivetheinvasion;

import game.invasion.survivetheinvasion.objects.GameObject;

public class GameDisplay {
    private double gameToDisplayCoordinateOffsetX;
    private double gameToDisplayCoordinateOffsetY;
    private double displayCenterX;
    private double displayCenterY;
    private double gameCenterX;
    private double gameCenterY;
    private GameObject centerObject;

    public GameDisplay(double widthPixels, double heightPixels, GameObject centerObject){
        this.centerObject = centerObject;
        displayCenterX = widthPixels/2.0;
        displayCenterY = heightPixels/2.0;
    }

    public void update(){
        gameCenterX = centerObject.getPositionX();
        gameCenterY = centerObject.getPositionY();

    gameToDisplayCoordinateOffsetX = displayCenterX - gameCenterX;
    gameToDisplayCoordinateOffsetY = displayCenterY - gameCenterY;
}

    public double gameToDisplayCoordinatesX(double x) {
        return x + gameToDisplayCoordinateOffsetX;
    }

    public double gameToDisplayCoordinatesY(double y) {
        return y + gameToDisplayCoordinateOffsetY;
    }
}
