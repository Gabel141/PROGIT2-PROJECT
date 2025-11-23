package framework;

import javax.swing.*;

public class DisplayManager {

    public static final int screenWidth = 1080; // 768 x 576 res
    public static final int screenHeight = 900;


    public static void createDisplay() {

        System.out.println("[RENDER][DisplayManager]: Creating Window...");
        JFrame window = new JFrame("Tumbang Preso");
        GameScreen gameScreen = new GameScreen();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(gameScreen);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
}
