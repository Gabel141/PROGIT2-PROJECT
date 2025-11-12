package game.entities;

import java.awt.*;

public class Projectile extends MovingEntity {

    int SIZE = 10;
    int speed = 10;

    public Projectile(double x,double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, SIZE, SIZE);
    }

    @Override
    public void moveX(boolean right) {
        if (right)
            x += speed;
        else
            x -= speed;
    }
    @Override
    public void moveY(boolean up) {
        if (up)
            y -= speed;
        else
            y += speed;
    }

}
