package game.entities;

import java.awt.*;

import game.utils.Directions;

public class Projectile extends MovingEntity {

    int SIZE = 10;
    int xSpeed = 10;
    int ySpeed = 10;

    public Projectile(int x, int y, Color color) {
        this.x = x;
        this.y = y;
    }

    Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, SIZE, SIZE);
    }

    @Override
    public void moveX(boolean right) {
        if (right)
            x += xSpeed;
        else
            x -= xSpeed;
    }
    @Override
    public void moveY(boolean up) {
        if (up)
            y -= ySpeed;
        else
            y += ySpeed;
    }

    @Override
    public Directions getFacing() {
        return facing;
    }

}
