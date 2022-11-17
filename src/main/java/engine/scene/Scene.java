package engine.scene;

import engine.graph.models.Model;
import engine.graph.models.ModelId;

import java.util.HashMap;
import java.util.Map;

public class Scene {
    private Map<ModelId, Model> modelMap;
    public Scene() {
        this.modelMap = new HashMap<>();
    }

    public void addEntity(Entity entity) {
        ModelId modelId = entity.getModelId();
        Model model = modelMap.get(modelId);
        if (model == null) {
            throw new RuntimeException("Could not find model [" + modelId + "]");
        }
        model.getEntitiesList().add(entity);
    }

    public void addModel(Model model) {
        modelMap.put(model.getId(), model);
    }

    public Map<ModelId, Model> getModelMap() {
        return modelMap;
    }

    public void cleanup() {
        // Nothing to be done here yet
    }
}