package game.entities;

public class Attacker extends MovingEntity {

    int playerSpeed;

    public Attacker(double x, double y, int playerSpeed) {
        this.x = x;
        this.y = y;
        this.playerSpeed = playerSpeed;
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public void throwSlipper() {
        double dx = 10;
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
