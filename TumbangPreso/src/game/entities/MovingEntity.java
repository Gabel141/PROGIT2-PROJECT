package game.entities;

import game.utils.Directions;

public abstract class MovingEntity extends StaticEntity {

    int xSpeed;
    int ySpeed;

    protected Directions facing;

    public abstract void moveX(boolean right);

    public abstract void moveY(boolean up);

    public abstract Directions getFacing();
}
