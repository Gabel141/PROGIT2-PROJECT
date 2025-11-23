package game.entities;

import framework.render.Model;

public class StaticEntity {

    private Model model;

    protected int x, y;
    protected int width, height;


    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}
