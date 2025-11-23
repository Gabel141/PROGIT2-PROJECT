import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


// Main.java - Test runner
class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tumba ni Manong Presyo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 900);
            frame.setLocationRelativeTo(null);


            // Create start menu
            StartMenu startMenu = new StartMenu();


            // Create instructions menu
            InstructionsMenu instructionsMenu = new InstructionsMenu();


            // Add start menu first
            frame.add(startMenu);


            // Handle start button click - switch to instructions
            startMenu.setStartListener(e -> {
                frame.getContentPane().removeAll();
                frame.add(instructionsMenu);
                frame.revalidate();
                frame.repaint();
            });


            // Handle instructions start button click - would start game
            instructionsMenu.setStartGameListener(e -> {
                System.out.println("Starting game...");
                // This is where you would switch to your actual game
                // frame.getContentPane().removeAll();
                // frame.add(yourGamePanel);
                // frame.revalidate();
                // frame.repaint();
            });


            frame.setVisible(true);
        });
    }
}


// StartMenu.java
class StartMenu extends JPanel {
    private JButton startButton;
    private boolean buttonHovered = false;


    // Colors
    private final Color BG_COLOR = new Color(176, 224, 208);
    private final Color BUTTON_COLOR = new Color(255, 223, 99);
    private final Color BUTTON_HOVER_COLOR = new Color(255, 213, 79);
    private final Color TEXT_COLOR = Color.WHITE;
    private final Color SUBTITLE_COLOR = new Color(60, 60, 60);


    private ActionListener startListener;


    // Images
    private Image logoImage;


    public StartMenu() {
        setLayout(null);
        setBackground(BG_COLOR);


        // Load logo image
        try {
            System.out.println("Attempting to load logo image...");


            java.net.URL logoURL = getClass().getResource("/logoCanSlip.png");
            System.out.println("Logo URL from classpath: " + logoURL);


            if (logoURL != null) {
                logoImage = new ImageIcon(logoURL).getImage();
                System.out.println("Logo image loaded from classpath!");
            } else {
                // Try loading from file system directly
                System.out.println("Trying to load from file system...");
                String basePath = System.getProperty("user.dir");
                logoImage = new ImageIcon(basePath + "/src/resources/logoCanSlip.png").getImage();
                System.out.println("Logo image loaded from file system!");
            }


        } catch (Exception e) {
            System.out.println("Exception loading logo: " + e.getMessage());
            e.printStackTrace();
        }


        // Create start button
        startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 36));
        startButton.setBackground(BUTTON_COLOR);
        startButton.setForeground(SUBTITLE_COLOR);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));


        // Button hover effect
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonHovered = true;
                startButton.setBackground(BUTTON_HOVER_COLOR);
            }


            @Override
            public void mouseExited(MouseEvent e) {
                buttonHovered = false;
                startButton.setBackground(BUTTON_COLOR);
            }
        });


        add(startButton);
    }


    public void setStartListener(ActionListener listener) {
        this.startListener = listener;
        startButton.addActionListener(listener);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        int width = getWidth();
        int height = getHeight();


        // Draw title
        g2d.setColor(TEXT_COLOR);
        g2d.setFont(new Font("Arial", Font.BOLD, 60));
        FontMetrics fm = g2d.getFontMetrics();


        String title1 = "TUMBA NI";
        String title2 = "MANONG PRESYO";


        int x1 = (width - fm.stringWidth(title1)) / 2;
        int x2 = (width - fm.stringWidth(title2)) / 2;


        g2d.drawString(title1, x1, 100);
        g2d.drawString(title2, x2, 170);


        // Draw logo image if loaded, otherwise draw placeholders
        if (logoImage != null) {
            // Draw logo centered (bigger size)
            int logoWidth = 350;
            int logoHeight = 260;
            g2d.drawImage(logoImage,
                    (width - logoWidth) / 2,
                    height / 2 - 100,
                    logoWidth,
                    logoHeight,
                    this);
        } else {
            // Draw placeholders
            g2d.setColor(new Color(100, 100, 100));
            g2d.fillOval(width / 2 - 90, height / 2 - 70, 80, 100);


            g2d.setColor(new Color(255, 100, 80));
            g2d.fillOval(width / 2 + 20, height / 2 - 5, 60, 30);
        }


        // Draw subtitle
        g2d.setColor(SUBTITLE_COLOR);
        g2d.setFont(new Font("Arial", Font.PLAIN, 18));
        String subtitle = "This is a 2-player game played on the same device.";
        fm = g2d.getFontMetrics();
        int subX = (width - fm.stringWidth(subtitle)) / 2;
        g2d.drawString(subtitle, subX, height - 80);


        // Position button
        startButton.setBounds(width / 2 - 140, height - 180, 280, 70);
    }
}


// InstructionsMenu.java
class InstructionsMenu extends JPanel {

    private JButton startButton;
    private boolean buttonHovered = false;

    // Color palette
    private final Color BG_COLOR = new Color(176, 224, 208);
    private final Color BUTTON_COLOR = new Color(255, 223, 99);
    private final Color BUTTON_HOVER_COLOR = new Color(255, 213, 79);
    private final Color TEXT_COLOR = Color.WHITE;
    private final Color DARK_TEXT_COLOR = new Color(60, 60, 60);
    private final Color PLAYER1_COLOR = new Color(255, 50, 50);
    private final Color PLAYER2_COLOR = new Color(50, 150, 255);

    private ActionListener startGameListener;

    public InstructionsMenu() {
        setLayout(null);
        setBackground(BG_COLOR);
        createStartButton();
        add(startButton);
    }

    private void createStartButton() {
        startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 36));
        startButton.setBackground(BUTTON_COLOR);
        startButton.setForeground(DARK_TEXT_COLOR);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonHovered = true;
                startButton.setBackground(BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonHovered = false;
                startButton.setBackground(BUTTON_COLOR);
            }
        });
    }

    public void setStartGameListener(ActionListener listener) {
        this.startGameListener = listener;
        startButton.addActionListener(listener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = prepareGraphics(g);

        int width = getWidth();
        int y = 40;

        drawTitle(g2d, width, y);
        y += 60;

        y = drawPlayerSection(
                g2d,
                "ATTACKER (Player 1)",
                "/player1.png",
                PLAYER1_COLOR,
                new String[]{
                        "Moves up and down using W and S.",
                        "Use W, A, S, D to retrieve your slipper.",
                        "Press Space Bar to throw the slipper toward the can.",
                        "Your goal: Hit the can!"
                },
                y
        );

        y = drawPlayerSection(
                g2d,
                "GUARD (Player 2)",
                "/player2.png",
                PLAYER2_COLOR,
                new String[]{
                        "Moves up and down using the ↑ and ↓ keys.",
                        "Use ←, ↑, ↓, → to chase after the attacker.",
                        "Your goal: Block the slipper and protect the can!"
                },
                y
        );

        y = drawImageSection(
                g2d,
                "Slipper",
                "/slipper.png",
                new String[]{
                        "The slipper moves up and down, to show the",
                        "direction of where it will be thrown."
                },
                y
        );

        y = drawImageSection(
                g2d,
                "Score",
                "/Scoring.png",
                new String[]{
                        "Guard earns 1 point when:",
                        "  • The attacker misses the can during their throw.",
                        "  • The guard catches the can while the can has been reset and",
                        "    before returning to the throwing line.",
                        "Attacker earns 1 point when:",
                        "  • The attacker knocks down the can, retrieves their slipper,",
                        "    and returns to the throwing line safely without being",
                        "    caught by the guard."
                },
                y
        );

        drawObjectiveSection(g2d, y);

        // ✅ Added start button position (ONLY CHANGE)
        startButton.setBounds(width / 2 - 140, getHeight() - 120, 280, 70);
    }

    private Graphics2D prepareGraphics(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        return g2d;
    }

    private void drawTitle(Graphics2D g2d, int width, int y) {
        g2d.setColor(TEXT_COLOR);
        g2d.setFont(new Font("Arial", Font.BOLD, 42));
        String title = "HOW TO PLAY:";
        int textWidth = g2d.getFontMetrics().stringWidth(title);
        g2d.drawString(title, (width - textWidth) / 2, y);
    }

    private int drawPlayerSection(Graphics2D g2d, String header, String imagePath,
                                  Color fallbackColor, String[] instructions, int startY) {

        g2d.setColor(DARK_TEXT_COLOR);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString(header, 30, startY);
        startY += 40;

        Image img = loadImage(imagePath, "src/resources/" + imagePath.substring(1));
        if (img != null) {
            g2d.drawImage(img, 30, startY - 25, 45, 45, this);
        } else {
            g2d.setColor(fallbackColor);
            g2d.fillRect(30, startY - 20, 40, 40);
        }

        g2d.setColor(DARK_TEXT_COLOR);
        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        return drawMultilineText(g2d, instructions, 80, startY - 10) + 40;
    }

    private int drawImageSection(Graphics2D g2d, String title, String imagePath,
                                 String[] instructions, int startY) {

        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString(title, 30, startY);

        Image img = loadImage(imagePath, "src/resources/" + imagePath.substring(1));
        int imgY = startY - 25;

        if (img != null) {
            g2d.drawImage(img, 120, imgY, 30, 30, this);
        } else {
            g2d.setColor(new Color(120, 120, 120));
            g2d.fillRect(120, imgY + 7, 25, 25);
        }

        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        return drawMultilineText(g2d, instructions, 30, startY + 30) + 40;
    }

    private void drawObjectiveSection(Graphics2D g2d, int y) {
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Objective", 30, y);

        Image img = loadImage("/Target.png", "src/resources/Target.png");
        if (img != null) {
            g2d.drawImage(img, 165, y - 25, 30, 30, this);
        } else {
            g2d.setColor(new Color(120, 120, 120));
            g2d.fillOval(165, y - 18, 25, 25);
        }

        y += 30;
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        g2d.drawString("The game is played in rounds. The guard earns points", 30, y);
        g2d.drawString("based on the attacker's performance. The first player to", 30, y + 20);
        g2d.drawString("reach 3 points is declared the winner.", 30, y + 40);
    }

    private Image loadImage(String classpathLocation, String fallbackPath) {
        try {
            java.net.URL url = getClass().getResource(classpathLocation);
            return (url != null)
                    ? new ImageIcon(url).getImage()
                    : new ImageIcon(fallbackPath).getImage();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private int drawMultilineText(Graphics2D g2d, String[] lines, int x, int y) {
        for (String line : lines) {
            g2d.drawString(line, x, y);
            y += 22;
        }
        return y;
    }
}


