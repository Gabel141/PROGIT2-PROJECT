package framework;

import framework.input.KeyboardInput;
import game.Game;

import javax.swing.JPanel;
import java.awt.*;

public class GameScreen extends JPanel {

    // SCREEN SETTINGS

    final int screenWidth = 1080; // 768 x 576 res
    final int screenHeight = 900;

    int FPS = 60;

    KeyboardInput key = new KeyboardInput();

    // SCREEN PANEL SETTINGS

    public GameScreen() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.decode("#b5d4cb"));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(key);
        
    }

    // RENDERS THE GRAPHICS FOR EACH ENTITY

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Game.getStateManager().render(g);
        repaint();
    }


}
