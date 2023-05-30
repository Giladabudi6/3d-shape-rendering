package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static java.awt.Color.GREEN;
import static java.awt.Color.green;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class RayTracerBasic extends RayTracerBase {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double DELTA = 0.1;
    private static final double INITIAL_K = 1;


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
        Ray backRay = new Ray(point, backVector);

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(backRay);
        if (intersections == null) {
            // there are no intersections
            return true;
        }
        double distanceToLightSource = lightSource.getDistance(gp.point);

        // Before checking the rest the default is that the first point is the closest
        for (int i = 0; i < intersections.size(); i++) {
            double current = intersections.get(i).point.distance(backRay.getP0());
            if (current < distanceToLightSource) {
                return false;
            }
        }
        return true;
    }


    private Ray constructRefractedRay(GeoPoint gp, Ray ray, Vector n) {
        Vector refractedVector = ray.getDir(); // from intersection to next geometry
        return new Ray(gp.point, refractedVector, n);
    }


    private Ray constructReflectedRay(GeoPoint gp, Ray ray, Vector n) {
        Vector v = ray.getDir();
        double nv = alignZero(v.dotProduct(n));

        // r = v - 2 * (v * n) * n
        Vector r = v.subtract(n.scale(2d * nv)).normalize();

        return new Ray(gp.point, r, n);
    }


    private GeoPoint findClosestIntersection(Ray ray) {
        // find all intersected geometries
        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return null;
        // from all geometries intersected by ray return the closest one
        return ray.findClosestGeoPoint(intersections);
    }


    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Material mat = gp.geometry.getMaterial();
        Double3 kr = mat.kR;
        Double3 kkr = k.product(kr);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            Ray reflectedRay = constructReflectedRay(gp, ray, gp.geometry.getNormal(gp.point));
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            // no geometry intersected by ray
            if (reflectedPoint == null) {
                return color.add(this.scene.background);
            }
            color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
        }
        Double3 kt = mat.kR;
        Double3 kkt = k.product(kt);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            Ray reflectedRay = constructReflectedRay(gp, ray, gp.geometry.getNormal(gp.point));
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);

            color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }
        return color;
    }


    private Double3 transparency(GeoPoint geopoint, LightSource light, Vector l, Vector n) {

        Vector backVector = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(backVector) > 0 ? DELTA : -DELTA);
        Point point = geopoint.point.add(delta);
        Ray backRay = new Ray(point, backVector);

        double lightDistance = light.getDistance(geopoint.point);

        var intersections = this.scene.geometries.findGeoIntersections(backRay);
        if (intersections == null)
            return Double3.ONE; //no intersections

        Double3 ktr = Double3.ONE;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr = ktr.product(gp.geometry.getMaterial().kT); //the more transparency the less shadow
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
            }
        }
        return ktr;
    }
}