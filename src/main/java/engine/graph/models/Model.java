package engine.graph.models;

import engine.Window;
import engine.graph.Render;
import engine.scene.Entity;
import engine.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public abstract class Model <T extends Entity> {
    private final ModelId id;

    private List<T> entitiesList;

    public Model(ModelId modelId) {
        this.id = modelId;
        entitiesList = new ArrayList<>();
    }
    public ModelId getId() {
        return this.id;
    }

    public List<T> getEntitiesList() {
        return entitiesList;
    }

    public void render(Window window, Scene scene, Render render) {
        for(T entity : getEntitiesList()) {
            renderEntity(window, scene, entity, render);
        }
    }

    public abstract void renderEntity(Window window, Scene scene, T entity, Render render);
}

