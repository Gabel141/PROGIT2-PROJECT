package game.entities;

import game.utils.Directions;

public class Attacker extends MovingEntity {

    int playerSpeed;
    int playerCooldown;

    public Attacker(int x, int y, int h, int w, int playerSpeed) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.playerSpeed = playerSpeed;
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public int getCooldown() {
        return playerCooldown;
    }

    public void setPlayerCooldown(int cd) {
        playerCooldown = cd;
    }

    public Directions getFacing() {
        return facing;
    }

    @Override
    public void moveX(boolean right) {
        if (right) {
            x += playerSpeed;
            facing = Directions.RIGHT;
        }
        else{
            x -= playerSpeed;
            facing = Directions.LEFT;
        }
    }
    @Override
    public void moveY(boolean up) {
        if (up)
            y -= playerSpeed;
        else
            y += playerSpeed;
    }

}
