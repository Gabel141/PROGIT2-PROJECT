package framework.render;

import framework.input.KeyboardInput;
import game.entities.Attacker;
import game.entities.Projectile;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameScreen extends JPanel implements Runnable {

    // SCREEN SETTINGS

    final int originalTileSize = 16; // 16 x 16
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48 x 48

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int screenWidth = tileSize * maxScreenCol; // 768 x 576 res
    final int screenHeight = tileSize * maxScreenRow;

    final List<Projectile> projectiles = new ArrayList<>();


    //default player variables

    Attacker player1 = new Attacker(100, 100, 4);

    int player1X = 100;
    int player1Y = 100;
    int player1Speed = 4;

    public GameScreen() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.decode("#b5d4cb"));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(key1);

    }

    // RUNTIME AND TICKS

    static Thread gameThread;

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {

        if (key1.p1UP) {
            player1.moveY(true);
        }
        else if (key1.p1DOWN) {
            player1.moveY(false);
        }
        if (key1.p1RIGHT) {
            player1.moveX(true);
        }
        else if (key1.p1LEFT) {
            player1.moveX(false);
        }

        if (key1.p1THROW) {
            System.out.println("pew");
            projectiles.add(new Projectile(player1.getX() - 5, player1.getY() - 5, Color.white));
        }

        for(Projectile p: projectiles) {
            p.moveX(true);
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);

        g2.fillRect((int)player1.getX(), (int)player1.getY(), tileSize, tileSize);

        g2.dispose();

        for(Projectile p: projectiles) {
            g.setColor(p.getColor());
            g.fillOval((int)p.getX(), (int)p.getY(), p.getSize(), p.getSize());
        }
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    //FPS
    int FPS = 60;

    KeyboardInput key1 = new KeyboardInput();

}
