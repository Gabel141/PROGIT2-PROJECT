package game.entities;

public class Can extends StaticEntity{

    private boolean wasHit;

    public Can(int x, int y, int h, int w) {
        this.x = x;
        this.y = y;
        this.height = h;
        this.width = w;
    }

    public boolean getHitStatus() {
        return wasHit;
    }

    public void setHitStatus(boolean hit) {
        wasHit = hit;
    }

}
