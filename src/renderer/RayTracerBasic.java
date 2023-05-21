package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static java.awt.Color.GREEN;
import static java.awt.Color.green;
import static primitives.Util.alignZero;

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
        return calcColor(closestPoint, ray);
    }


    private Color calcColor(GeoPoint gp, Ray ray) {
        // Return the intensity of the ambient light in the scene
        Color color = calcLocalEffects(gp, ray);
        return scene.ambientLight.getIntensity().add(color);

    }

    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir ();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point).normalize();
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color Li = lightSource.getIntensity(gp.point);
                // TODO: check if "material is correct instead of the "mat" it was
                color = color.add(Li.scale(calcDiffusive(material, nl, Li)),Li.scale(calcSpecular(material, n, l, v, nl,Li));

            }
        }
        return color;

    }

    private Color calcDiffusive(Material material, double nl, Color Li) {
        Double3 diffusion = material.kD.scale(Math.abs(nl));
        return Li.scale(diffusion);
    }

    private Color calcSpecular(Material material, Vector n, Vector l, Vector v, double nl, Color Li) {
        Vector r = l.subtract(n.scale(nl).scale(2)).normalize();
        double max = Math.max(0, -v.dotProduct(r));
        double maxNs = Math.pow(max, material.nShininess);
        Double3 maxOfNsKs = material.kS.scale(maxNs);
        return Li.scale(maxOfNsKs);
    }

}



