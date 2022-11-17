package engine.graph;

import engine.graph.models.Model;
import org.lwjgl.opengl.GL;
import engine.Window;
import engine.scene.Scene;

import static engine.Engine.WORLD_LENGTH;
import static org.lwjgl.opengl.GL11.*;

public class Render {

    private double scaleX;
    private double scaleY;

    public Render(Window window) {
        GL.createCapabilities();
        scaleX = 2 / (double) window.getWidth();
        scaleY = 2 / (double) window.getHeight();
    }

    public void cleanup() {
        // Nothing to be done here yet
    }

    public void render(Window window, Scene scene) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        int halfWorldLength = WORLD_LENGTH / 2;
        // draw boarder
        glBegin(GL_QUADS);
            glColor4f(0.1f, 0.4f, 0.1f, 0);
            glVertex(-halfWorldLength, halfWorldLength);
            glVertex(halfWorldLength, halfWorldLength);
            glVertex(halfWorldLength, -halfWorldLength);
            glVertex(-halfWorldLength, -halfWorldLength);
        glEnd();

        for( Model model: scene.getModelMap().values()) {
            model.render(window, scene, this);
        }
    }

    public void glVertex(double x, double y) {
        glVertex2f((float) (x * scaleX), (float)( y * scaleY));
    }
}