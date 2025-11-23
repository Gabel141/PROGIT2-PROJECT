package framework.render;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Model extends Rectangle {
    
    private BufferedImage texture;

    public Model(int width, int height, String fileName) {
        super(width, height);

        if(fileName != null) {
            try {
                this.texture = ImageIO.read(new File("res/"+fileName+".png")); 
            } catch (IOException e) {
                System.err.println("[Render/Model]: ERROR LOADING MODEL:"+fileName);
            }
        }
    }

    private Model(int w, int h, BufferedImage texture) {
        super(w, h);
        this.texture = texture;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public Model flipTexture() {
        int h = texture.getHeight();
        int w = texture.getWidth();
        BufferedImage flipped = new BufferedImage(w, h, texture.getType());

        for(int x=0;x<w;x++) {
			for(int y=0;y<h;y++) {
				flipped.setRGB(x, y, texture.getRGB(w-1-x, y));
			}
		}

        return new Model(this.width, this.height, flipped);

    }
}
