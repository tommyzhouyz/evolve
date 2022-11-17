package engine.graph.models;

import engine.Window;
import engine.graph.Render;
import engine.scene.DummyEntity;
import engine.scene.Scene;

import static org.lwjgl.opengl.GL11.*;

public class DummyModel extends Model<DummyEntity>{
    public DummyModel() {
        super(ModelId.UNKNOWN);
    }

    @Override
    public void renderEntity(Window window, Scene scene, DummyEntity entity, Render render) {
        float color = entity.getColor();
//            System.out.println("Color is" + color);
        glBegin(GL_QUADS);
            glColor4f(color, color, color, 0);
            glVertex2f(-0.5f, 0.5f);
            glVertex2f(0.5f, 0.5f);
            glVertex2f(0.5f, -0.5f);
            glVertex2f(-0.5f, -0.5f);
        glEnd();
    }
}
