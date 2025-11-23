package game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import framework.DisplayManager;
import framework.gamestates.GameState;
import framework.gamestates.GameStateManager;

public class MenuState extends GameState {

    private String[] menuOptions;
    private int selected;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        System.out.println("[MenuState]: LOG : Creating Menu");
    }

    @Override
    public void init() {
        this.menuOptions = new String[] {"Start", "Exit"};
        this.selected = 0;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics graphics) {
		graphics.setColor(new Color(51, 153, 255));
		graphics.fillRect(0, 0, DisplayManager.screenWidth, DisplayManager.screenHeight);
		
		graphics.setFont(new Font("Arail", Font.PLAIN, 42));
		
		for(int i=0;i<menuOptions.length;i++) {
			if(selected == i) graphics.setColor(Color.GREEN);
			else graphics.setColor(Color.WHITE);
			
			graphics.drawString(menuOptions[i], DisplayManager.screenWidth/2 -200, 100 +i*120);
		}
    }

	@Override
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			if(selected > 0) selected--;
		}
		else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			if(selected < menuOptions.length-1) selected++;
		}
        else if(key == KeyEvent.VK_ENTER) {
            if (selected == 0) {
                gsm.addState(new InGameState(gsm));
            } 
            else if (selected == 1) {
                System.exit(0);
            }

        }
	}

	@Override
	public void keyReleased(int key) {}

    @Override
    public void mouseClicked(int click) {}
    
}
