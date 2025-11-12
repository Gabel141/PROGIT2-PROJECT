package game;

import framework.render.GameScreen;
import game.entities.Projectile;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    public static void main(String[] args) {


        System.out.println("[RENDER][DisplayManager]: Creating Window...");
        JFrame window = new JFrame("Tumbang Preso");
        GameScreen gameScreen = new GameScreen();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(gameScreen);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        //DisplayManager.createDisplay();

        gameScreen.startGameThread();

    }
}