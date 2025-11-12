package game.entities;

import java.awt.*;

public abstract class StaticEntity {
    double x, y, dx, dy;
    int size;
    Color color;

    public Color getColor() {
        return this.color;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public int getSize() {
        return this.size;
    }

}
