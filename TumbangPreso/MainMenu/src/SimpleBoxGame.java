import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SimpleBoxGame extends JPanel implements KeyListener, ActionListener {
    private final int BOX_SIZE = 50;

    // Player 1 (WASD + X)
    private int p1x = 100, p1y = 300;
    private double p1Angle = -90;
    private boolean p1Increasing = true;
    private int p1BulletsLeft = 2;
    private int p1Score = 0;

    // Player 2 (Arrow keys + M)
    private int p2x = 700, p2y = 300;
    private double p2Angle = 0; // 360° aim
    private int p2BulletsLeft = 0;
    private int p2Score = 0;

    // Track key presses for smooth movement
    private final boolean[] keys = new boolean[256];

    // Obstacles
    private final List<Rectangle> obstacles = new ArrayList<>();
    private final Random rand = new Random();

    // Score obstacle
    private final Rectangle scoreObstacle = new Rectangle(1100, 250, 50, 200);

    // Restricted area for Player 1 when ammo is full
    private final Rectangle restrictedArea = new Rectangle(300, 0, 200, 1080);

    // Bullet class
    private static class Bullet {
        double x, y, dx, dy;
        final int SIZE = 10;
        Color color;
        boolean moving = true;
        double traveled = 0;
        final double maxDistance = 500;
        String shooter;

        Bullet(double x, double y, double dx, double dy, Color color, String shooter) {
            this.x = x; this.y = y;
            this.dx = dx; this.dy = dy;
            this.color = color;
            this.shooter = shooter;
        }

        void move(int screenWidth, int screenHeight) {
            if (moving) {
                x += dx;
                y += dy;
                traveled += Math.sqrt(dx * dx + dy * dy);
                if (traveled >= maxDistance || x < 0 || y < 0 || x > screenWidth || y > screenHeight) {
                    moving = false;
                    dx = 0; dy = 0;
                }
            }
        }

        Rectangle getBounds() {
            return new Rectangle((int)x, (int)y, SIZE, SIZE);
        }
    }

    private final List<Bullet> bullets = new ArrayList<>();
    private final Timer timer = new Timer(30, this);

    public SimpleBoxGame() {
        setBackground(new Color(34, 139, 34));
        setFocusable(true);
        addKeyListener(this);
        timer.start();

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screen.width;
        int screenHeight = screen.height;
        for (int i = 0; i < 10; i++) {
            int width = 50 + rand.nextInt(50);
            int height = 50 + rand.nextInt(50);
            int x = rand.nextInt(screenWidth - width - 200);
            int y = rand.nextInt(screenHeight - height);
            obstacles.add(new Rectangle(x, y, width, height));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Obstacles
        g.setColor(new Color(139, 69, 19));
        for (Rectangle r : obstacles) g.fillRect(r.x, r.y, r.width, r.height);

        // Score obstacle
        g.setColor(Color.RED);
        g.fillRect(scoreObstacle.x, scoreObstacle.y, scoreObstacle.width, scoreObstacle.height);

        // Restricted area if Player 1 has full ammo
        if (p1BulletsLeft == 2) {
            g.setColor(Color.MAGENTA);
            g.drawRect(restrictedArea.x, restrictedArea.y, restrictedArea.width, restrictedArea.height);
            g.drawString("Restricted Area", restrictedArea.x + 10, 20);
        }

        // Bullets
        for (Bullet b : bullets) {
            g.setColor(b.color);
            g.fillOval((int)b.x, (int)b.y, b.SIZE, b.SIZE);
        }

        // Players
        g.setColor(Color.GREEN);
        g.fillRect(p1x, p1y, BOX_SIZE, BOX_SIZE);
        g.setColor(Color.CYAN);
        g.fillRect(p2x, p2y, BOX_SIZE, BOX_SIZE);

        // Arrows
        drawArrow(g, p1x + BOX_SIZE, p1y + BOX_SIZE / 2, p1Angle, Color.YELLOW);
        drawArrow(g, p2x + BOX_SIZE / 2, p2y + BOX_SIZE / 2, p2Angle, Color.ORANGE);

        // Bullet UI
        drawBulletGUI(g, p1x, p1y - 20, Color.YELLOW, p1BulletsLeft);
        drawBulletGUI(g, p2x, p2y - 20, Color.ORANGE, p2BulletsLeft);

        // Scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("P1 Score: " + p1Score, 50, 50);
        g.drawString("P2 Score: " + p2Score, 1200, 50);
        g.drawString("P2 Ammo: " + p2BulletsLeft, 1200, 80);
    }

    private void drawArrow(Graphics g, int cx, int cy, double angle, Color color) {
        g.setColor(color);
        int arrowLength = 50;
        double rad = Math.toRadians(angle);
        int dx = (int)(Math.cos(rad) * arrowLength);
        int dy = (int)(Math.sin(rad) * arrowLength);
        g.drawLine(cx, cy, cx + dx, cy + dy);
    }

    private void drawBulletGUI(Graphics g, int x, int y, Color color, int bulletsLeft) {
        g.setColor(Color.WHITE);
        g.drawRect(x, y, 50, 10);
        g.setColor(color);
        g.fillRect(x, y, bulletsLeft * 25, 10);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key < keys.length) keys[key] = true;

        // Shooting
        if (key == KeyEvent.VK_X && p1BulletsLeft > 0) {
            shootArrow(p1x + BOX_SIZE, p1y + BOX_SIZE / 2, p1Angle, Color.YELLOW, "P1");
            p1BulletsLeft--;
        }
        if (key == KeyEvent.VK_M && p2BulletsLeft > 0) {
            shootArrow(p2x + BOX_SIZE / 2, p2y + BOX_SIZE / 2, p2Angle, Color.ORANGE, "P2");
            p2BulletsLeft--;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key < keys.length) keys[key] = false;
    }

    @Override public void keyTyped(KeyEvent e) {}

    private boolean collidesWithObstacles(int x, int y) {
        Rectangle playerRect = new Rectangle(x, y, BOX_SIZE, BOX_SIZE);
        for (Rectangle r : obstacles) if (playerRect.intersects(r)) return true;
        return playerRect.intersects(scoreObstacle);
    }

    private void collectBulletsWhileMoving() {
        Rectangle p1Rect = new Rectangle(p1x, p1y, BOX_SIZE, BOX_SIZE);
        Rectangle p2Rect = new Rectangle(p2x, p2y, BOX_SIZE, BOX_SIZE);

        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()) {
            Bullet b = it.next();

            if (b.getBounds().intersects(p2Rect) && (!b.moving || "P1".equals(b.shooter))) {
                p2BulletsLeft = Math.min(p2BulletsLeft + 1, 2);
                it.remove();
                continue;
            }

            if (!b.moving && "P1".equals(b.shooter) && b.getBounds().intersects(p1Rect)) {
                p1BulletsLeft = Math.min(p1BulletsLeft + 1, 2);
                it.remove();
            }
        }
    }

    private void shootArrow(int cx, int cy, double angle, Color color, String shooter) {
        double speed = 10;
        double rad = Math.toRadians(angle);
        double dx = Math.cos(rad) * speed;
        double dy = Math.sin(rad) * speed;
        bullets.add(new Bullet(cx - 5, cy - 5, dx, dy, color, shooter));
    }

    private void resetRound(String scorer) {
        p1x = 100; p1y = 300;
        p2x = 700; p2y = 300;
        bullets.clear();
        p1BulletsLeft = 2;
        p2BulletsLeft = 0;
        System.out.println(scorer + " scored! New round started.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        // Smooth movement Player 1
        int newP1X = p1x;
        int newP1Y = p1y;
        if (keys[KeyEvent.VK_W]) newP1Y -= 5;
        if (keys[KeyEvent.VK_S]) newP1Y += 5;
        if (keys[KeyEvent.VK_A]) newP1X -= 5;
        if (keys[KeyEvent.VK_D]) newP1X += 5;
        Rectangle attempted = new Rectangle(newP1X, newP1Y, BOX_SIZE, BOX_SIZE);
        if (!collidesWithObstacles(newP1X, newP1Y) &&
                !(p1BulletsLeft == 2 && attempted.intersects(restrictedArea))) {
            p1x = Math.max(0, Math.min(newP1X, screen.width - BOX_SIZE));
            p1y = Math.max(0, Math.min(newP1Y, screen.height - BOX_SIZE));
        }

        // Smooth movement Player 2
        int newP2X = p2x;
        int newP2Y = p2y;
        if (keys[KeyEvent.VK_UP]) newP2Y -= 5;
        if (keys[KeyEvent.VK_DOWN]) newP2Y += 5;
        if (keys[KeyEvent.VK_LEFT]) newP2X -= 5;
        if (keys[KeyEvent.VK_RIGHT]) newP2X += 5;
        if (!collidesWithObstacles(newP2X, newP2Y)) {
            p2x = Math.max(0, Math.min(newP2X, screen.width - BOX_SIZE));
            p2y = Math.max(0, Math.min(newP2Y, screen.height - BOX_SIZE));
        }

        // Player 1 arrow swing
        p1Angle += p1Increasing ? 2 : -2;
        if (p1Angle >= 90 || p1Angle <= -90) p1Increasing = !p1Increasing;

        // Player 2 circular aim
        p2Angle += 5;
        if (p2Angle >= 360) p2Angle -= 360;

        // Move bullets and collisions
        Rectangle p1Rect = new Rectangle(p1x, p1y, BOX_SIZE, BOX_SIZE);
        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()) {
            Bullet b = it.next();
            b.move(screen.width, screen.height);

            for (Rectangle r : obstacles) {
                if (b.getBounds().intersects(r)) {
                    b.moving = false; b.dx = 0; b.dy = 0;
                }
            }

            if (b.color == Color.YELLOW && b.getBounds().intersects(scoreObstacle)) {
                p1Score++;
                resetRound("Player 1");
                return;
            }

            if (b.color == Color.ORANGE && b.getBounds().intersects(p1Rect)) {
                p2Score++;
                resetRound("Player 2");
                return;
            }
        }

        collectBulletsWhileMoving();
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Box Game - Smooth Movement & Restricted Area");
        SimpleBoxGame game = new SimpleBoxGame();
        frame.add(game);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
