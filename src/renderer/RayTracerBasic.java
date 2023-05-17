package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static java.awt.Color.GREEN;
import static java.awt.Color.green;

public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    public Color traceRay(Ray ray) {

        // Find intersections of the ray with scene geometry
        var intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return scene.background;

        // Find the closest intersection point
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);

        // Calculate and return the color at the closest point
        return calcColor(closestPoint);
    }



    private Color calcColor(GeoPoint gp) {
        // Return the intensity of the ambient light in the scene
        Color color = gp.geometry.getEmission();
        return scene.ambientLight.getIntensity().add(color);

    }
}

