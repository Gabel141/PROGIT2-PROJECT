package game.entities;

public class Defender extends MovingEntity {

    int playerSpeed;

    public Defender(double x, double y, int h, int w, int playerSpeed) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.playerSpeed = playerSpeed;
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    @Override
    public void moveX(boolean right) {
        if (right)
            x += playerSpeed;
        else
            x -= playerSpeed;
    }
    @Override
    public void moveY(boolean up) {
        if (up)
            y -= playerSpeed;
        else
            y += playerSpeed;
    }


}
