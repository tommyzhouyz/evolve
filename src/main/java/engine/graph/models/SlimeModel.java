package engine.graph.models;

import engine.Window;
import engine.graph.Render;
import engine.graph.RenderUtils;
import engine.scene.Scene;
import engine.scene.SlimeEntity;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.lwjgl.opengl.GL11.*;

public class SlimeModel extends Model<SlimeEntity>{
    public SlimeModel() {
        super(ModelId.SLIME);
    }

    @Override
    public void renderEntity(Window window, Scene scene, SlimeEntity entity, Render render) {
        Point2D.Double pos = entity.getPos();
        Color color = entity.getColor();
        float radius = entity.getSize();
            RenderUtils.drawDisc(
                    render::glVertex,
                    () -> glColor4f(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, color.getAlpha()/255f),
                    pos.x,
                    pos.y,
                    radius);
            glBegin(GL_LINES);
                glColor4f(1f,0,0,0);
                render.glVertex(pos.x, pos.y);
                render.glVertex(
                        pos.x + Math.cos(2 * Math.PI * entity.getDirection() / 360) * radius,
                        pos.y + Math.sin(2 * Math.PI * entity.getDirection() / 360) * radius
                );
            glEnd();
    }
}
