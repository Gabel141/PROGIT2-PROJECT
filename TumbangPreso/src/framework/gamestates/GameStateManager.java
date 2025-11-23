package framework.gamestates;

import java.awt.Graphics;
import java.util.Stack;

import game.states.MenuState;

public class GameStateManager {
    
    private Stack<GameState> states;

    public GameStateManager() {
        this.states = new Stack<GameState>();
        this.states.add(new MenuState(this));
    }

    public void addState(GameState state) {
        this.states.add(state);
    }

    public void clearState() {
        this.states.clear();
    }

    public GameState getActiveState() {
        return this.states.peek();
    }

    public void tick() {
        this.states.peek().tick();
    }

    public void render(Graphics graphics) {
        this.states.peek().render(graphics);
    }

    public void keyPressed(int key) {
        this.states.peek().keyPressed(key);
    }

    public void keyReleased(int key) {
        this.states.peek().keyReleased(key);
    }

}
