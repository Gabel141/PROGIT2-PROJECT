package game.entities;

public abstract class MovingEntity extends StaticEntity {

    public abstract void moveX(boolean right);

    public abstract void moveY(boolean up);
}
