package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    public Color traceRay(Ray ray) {
        // Find intersections of the ray with scene geometry
        List<Point> intersections = this.scene.geometries.findIntersections(ray);

        if (intersections == null)
            return this.scene.background;

        // Find the closest intersection point
        Point closestPoint = ray.findClosestPoint(intersections);

        // Calculate and return the color at the closest point
        return calcColor(closestPoint);
    }

    private Color calcColor(Point point) {
        // Return the intensity of the ambient light in the scene
        return this.scene.ambientLight.getIntensity();
    }
}

