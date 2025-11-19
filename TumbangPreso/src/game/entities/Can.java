package game.entities;

public class Can extends StaticEntity{

    private boolean wasHit;

    public boolean getHitStatus() {
        return wasHit;
    }

    public void setHitStatus(boolean hit) {
        wasHit = hit;
    }

}
