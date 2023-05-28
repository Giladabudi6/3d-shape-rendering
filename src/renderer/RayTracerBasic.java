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

    private static final double DELTA = 0.1;


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
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point).normalize();
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if (unshaded(gp, l, n, lightSource)) {
                    Color Li = lightSource.getIntensity(gp.point);
                    color = color.add(Li.scale(calcDiffusive(material, nl)), Li.scale(calcSpecular(material, n, l, v, nl)));
                }
            }
        }
        return color;

    }

    private Double3 calcDiffusive(Material material, double nl) {
        Double3 diffusion = material.kD.scale(Math.abs(nl));
        //return Li.scale(diffusion);
        return diffusion;

    }

    private Double3 calcSpecular(Material material, Vector n, Vector l, Vector v, double nl) {
        Vector r = l.subtract(n.scale(nl).scale(2)).normalize();
        double max = Math.max(0, -v.dotProduct(r));
        double maxNs = Math.pow(max, material.nShininess);
        Double3 maxOfNsKs = material.kS.scale(maxNs);
        //return Li.scale(maxOfNsKs);
        return maxOfNsKs;
    }

    private boolean unshaded(GeoPoint gp, Vector l, Vector n, LightSource lightSource) {

        Vector backVector = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(backVector) > 0 ? DELTA : -DELTA);
        Point point = gp.point.add(delta);
        Ray shadowRay = new Ray(point, backVector);

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(shadowRay);
        if (intersections == null) {
            // there are no intersections
            return true;
        }
        double distanceToLightSource = lightSource.getDistance(gp.point);

        // Before checking the rest the default is that the first point is the closest
        for (int i = 0; i < intersections.size(); i++) {
            double current = intersections.get(i).point.distance(shadowRay.getP0());
            if (current < distanceToLightSource) {
                return false;
            }
        }
        return true;
    }

}



