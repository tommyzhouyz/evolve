package engine.scene;

import engine.graph.models.ModelId;

public class DummyEntity extends Entity {

    private float color = 0.0f;
    @Override
    public ModelId getModelId() {
        return ModelId.UNKNOWN;
    }

    public float getColor() {
        return color;
    }

    public void setColor(float color) {
        this.color = color;
    }
}
