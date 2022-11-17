package game;

import engine.IAppLogic;
import engine.Window;
import engine.graph.Render;
import engine.graph.models.DummyModel;
import engine.scene.DummyEntity;
import engine.scene.Scene;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

public class DummyGame implements IAppLogic{

    private int direction = 0;

    private DummyEntity colorEntity;


    public DummyGame() {
    }

    @Override
    public void cleanup() {
    }

    @Override
    public void init(Window window, Scene scene, Render render) {
        colorEntity = new DummyEntity();
        scene.addModel(new DummyModel());
        scene.addEntity(colorEntity);
    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {
        if ( window.isKeyPressed(GLFW_KEY_UP) ) {
            direction = 1;
        } else if ( window.isKeyPressed(GLFW_KEY_DOWN) ) {
            direction = -1;
        } else {
            direction = 0;
        }
    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {
        float color = colorEntity.getColor();
        color += direction * 0.01f;
        if (color > 1) {
            color = 1.0f;
        } else if ( color < 0 ) {
            color = 0.0f;
        }
        colorEntity.setColor(color);
    }
}