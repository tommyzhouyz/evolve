package engine.scene;

import engine.graph.models.ModelId;

import java.awt.*;
import java.awt.geom.Point2D;

public class SlimeEntity extends Entity {

    private Point2D.Double pos = new Point2D.Double(0, 0);

    private final double maxSpeed = 0.15;

    private double speed = 0;

    // set default location to up
    private double directionAngle = 20;

    // angle per sec
    private double turnSpeed = 270;

    // radius
    private float size = 10;

    private Color color = Color.CYAN.darker();
    @Override
    public ModelId getModelId() {
        return ModelId.SLIME;
    }

    /***
     * calculate dx and dy for each dt
     * @param dt dt in ms
     * @return
     */
    public Point2D.Double getMove2DComponent(long dt) {
        double x = Math.round(dt * Math.cos(2 * Math.PI * directionAngle / 360) * speed);
        double y = Math.round(dt * Math.sin(2 * Math.PI * directionAngle / 360) * speed);
        return new Point2D.Double(x, y);
    }

    public Point2D.Double getPos() {
        return pos;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setMaxSpeed() {
        speed = maxSpeed;
    }

    public void setZeroSpeed() {
        speed = 0;
    }

    public void turnRight(long diffTimeMillis) {
        setDirection(getDirection() - getTurnSpeed() * diffTimeMillis / 1000);
    }

    public void turnLeft(long diffTimeMillis) {
        setDirection(getDirection() + getTurnSpeed() * diffTimeMillis / 1000);
    }

    public void setDirection(double directionAngle) {
        // reduce the angle
        directionAngle =  directionAngle % 360;

        // force it to be the positive remainder, so that 0 <= angle < 360
        directionAngle = (directionAngle + 360) % 360;

        // force into the minimum absolute value residue class, so that -180 < angle <= 180
        if (directionAngle > 180)
            directionAngle -= 360;
        this.directionAngle = directionAngle;
    }

    // return direction in degrees
    public double getDirection() {
        return directionAngle;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setPos(double x, double y) {
        pos.x = x;
        pos.y = y;
    }

    public void setPos(Point2D.Double newPos) {
        pos.x = newPos.x;
        pos.y = newPos.y;
    }

    public double getTurnSpeed() {
        return turnSpeed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void bounceOff(double normX, double normY) {
        double directionX = Math.cos(2 * Math.PI * directionAngle / 360);
        double directionY = Math.sin(2 * Math.PI * directionAngle / 360);
        double normRatio = (directionX * normX + directionY * normY)/(normX * normX + normY * normY);
        double newDirX = directionX - 2 * normRatio * normX;
        double newDirY = directionY - 2 * normRatio * normY;
        setDirection(Math.atan2(newDirY, newDirX) * 180 / Math.PI);

    }

    public boolean checkIntersect(SlimeEntity otherSlime) {
        double distance = pos.distance(otherSlime.getPos());
        return !(distance > (otherSlime.getSize() + size));
    }

    public void InteractIfIntersect(SlimeEntity otherSlime) {
        if (!checkIntersect(otherSlime)) return;
        Point2D.Double otherPos = otherSlime.getPos();
//        otherPos.
//                bounceOff();

    }
}
