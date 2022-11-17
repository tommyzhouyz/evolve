package game;

import engine.IAppLogic;
import engine.Window;
import engine.graph.Render;
import engine.graph.models.SlimeModel;
import engine.scene.Scene;
import engine.scene.SlimeEntity;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static engine.Engine.WORLD_LENGTH;
import static org.lwjgl.glfw.GLFW.*;

public class SlimeEvolve implements IAppLogic{


    private SlimeEntity slimeEntity;

    private List<SlimeEntity> npcSlimes;

    private Random rand;


    public SlimeEvolve() {
    }

    @Override
    public void cleanup() {
    }

    @Override
    public void init(Window window, Scene scene, Render render) {
        slimeEntity = new SlimeEntity();
        slimeEntity.setPos(0, 0);
        scene.addModel(new SlimeModel());
        scene.addEntity(slimeEntity);
        npcSlimes = new ArrayList<>();
        rand = new Random();
        for(int i=0; i < 10 ; i++) {
            SlimeEntity npcSlime = new SlimeEntity();
            npcSlime.setDirection(rand.nextDouble() * 360);
            npcSlime.setMaxSpeed();
            npcSlime.setPos((rand.nextDouble() - 0.5) * WORLD_LENGTH / 2.3, (rand.nextDouble() - 0.5) * WORLD_LENGTH / 2.3);
            npcSlime.setColor(npcSlime.getColor().darker().darker());
            npcSlimes.add(npcSlime);
            scene.addEntity(npcSlime);
        }
    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {

        if ( window.isKeyPressed(GLFW_KEY_W) ) {
            slimeEntity.setMaxSpeed();
        }
        if ( window.isKeyPressed(GLFW_KEY_S) ) {
            slimeEntity.setZeroSpeed();
        }
        if ( window.isKeyPressed(GLFW_KEY_A) ) {
            slimeEntity.turnLeft(diffTimeMillis);
        }
        if ( window.isKeyPressed(GLFW_KEY_D) ) {
            slimeEntity.turnRight(diffTimeMillis);
        }
    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {
//        System.out.println(slimeEntity.getPos().toString() + slimeEntity.getDirection());
        updateSlime(window, scene, diffTimeMillis, slimeEntity);
        for (SlimeEntity npcSlime: npcSlimes) {
            controlNpcSlime(npcSlime, diffTimeMillis);
            updateSlime(window, scene, diffTimeMillis, npcSlime);
        }
    }

    public void updateSlime(Window window, Scene scene, long diffTimeMillis, SlimeEntity slimeEntity) {
        Point2D.Double currentPos = slimeEntity.getPos();
        Point2D.Double deltaMoved = slimeEntity.getMove2DComponent(diffTimeMillis);
        Point2D.Double nextPos = new Point2D.Double(currentPos.x + deltaMoved.x, currentPos.y + deltaMoved.y);
        // Check if out of boundaries;
        int halfWorldLength = WORLD_LENGTH / 2;
        if (Math.abs(nextPos.x) + slimeEntity.getSize() > halfWorldLength)
            if (nextPos.x > 0) {
                nextPos.x = halfWorldLength - slimeEntity.getSize();
                slimeEntity.bounceOff(-1, 0);
            } else {
                nextPos.x = -halfWorldLength + slimeEntity.getSize();
                slimeEntity.bounceOff(1, 0);
            }
        if (Math.abs(nextPos.y) + slimeEntity.getSize() > halfWorldLength)
            if (nextPos.y > 0) {
                nextPos.y = halfWorldLength - slimeEntity.getSize();
                slimeEntity.bounceOff(0, -1);
            } else {
                nextPos.y = -halfWorldLength + slimeEntity.getSize();
                slimeEntity.bounceOff(0, 1);
            }
        slimeEntity.setPos(nextPos);
    }

    public void controlNpcSlime(SlimeEntity npcSlime, long diffTimeMillis) {
        double randomDouble = rand.nextDouble();
        if (randomDouble < 0.2) npcSlime.turnLeft(diffTimeMillis);
        else if (randomDouble > 0.8) npcSlime.turnRight(diffTimeMillis);
    }
}