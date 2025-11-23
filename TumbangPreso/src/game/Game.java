package game;

import framework.DisplayManager;
import framework.GameLoop;
import framework.gamestates.GameStateManager;
import framework.render.ModelManager;


public class Game {


    private static GameStateManager stateManager;


    public static void main(String[] args) {

        GameLoop loop = new GameLoop();
        loop.startGameThread();
        stateManager = new GameStateManager();
        ModelManager.init();
        DisplayManager.createDisplay();

    }

    public static GameStateManager getStateManager() {
        return stateManager;
    }

}