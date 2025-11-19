package game.entities;

public abstract class MovingEntity extends StaticEntity {

    int xSpeed;
    int ySpeed;

    public abstract void moveX(boolean right);

    public abstract void moveY(boolean up);
}
