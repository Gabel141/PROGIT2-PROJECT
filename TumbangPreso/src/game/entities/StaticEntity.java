package game.entities;

import java.awt.*;

public abstract class StaticEntity {
    double x, y, dx, dy;
    int width, height;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
