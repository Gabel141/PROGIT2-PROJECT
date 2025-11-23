package framework.render;

import java.awt.Graphics;

import game.entities.MovingEntity;
import game.entities.StaticEntity;
import game.utils.Directions;

public class Renderer {
    
    public static void renderEntity(StaticEntity entity, Graphics graphics) {
        int posX = entity.getX();
        int posY = entity.getY();
        Model model = entity.getModel();

        if (entity instanceof MovingEntity) {
            if (((MovingEntity) entity).getFacing() == Directions.LEFT) {
                model = model.flipTexture();
            }
        }

        renderModel(model, posX, posY, graphics);
    }

    public static void renderModel(Model model, int posX, int posY, Graphics graphics) {
        graphics.drawImage(model.getTexture(), posX, posY, model.width, model.height, null);
    }


}
