package engine.graph;

import java.util.function.BiConsumer;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtils {
    public static void drawDisc(BiConsumer<Double, Double> glVertex, Runnable glColor, double x, double y, float r, int segments) {
        glBegin( GL_TRIANGLE_FAN );
        glColor.run();

        glVertex.accept(x, y);
        for( int n = 0; n <= segments; n++ ) {
            final double t = 2 * Math.PI * n / segments;
            glVertex.accept(
                    (x + Math.cos(t) * r),
                    (y + Math.sin(t) * r));
        }
        glEnd();
    }

    public static void drawDisc(BiConsumer<Double, Double> glVertex, Runnable glColor, double x, double y, float r) {
        drawDisc(glVertex, glColor, x, y, r, 40);
    }
}
