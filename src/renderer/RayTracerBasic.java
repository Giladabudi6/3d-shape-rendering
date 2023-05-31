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
    private static final Double3 INITIAL_K = Double3.ONE ;


    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    public Color traceRay(Ray ray) {

        // Find intersections of the ray and the closest intersection geoPoint
        // Calculate and return the color at the closest point
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background
                : calcColor(closestPoint, ray);
    }


    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    //private Color calcColor(GeoPoint gp, Ray ray);

    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(intersection, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }


    private Color calcLocalEffects(GeoPoint gp, Ray ray,Double3 k) {
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
                Double3 ktr = transparency(gp, lightSource, l, n);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color Li = lightSource.getIntensity(gp.point).scale(ktr);
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
        Ray backRay = new Ray(gp.point, backVector, n);

        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(backRay);
        if (intersections == null) {
            // there are no intersections
            return true;
        }
        double distanceToLightSource = lightSource.getDistance(gp.point);

        // Before checking the rest the default is that the first point is the closest
        for (int i = 0; i < intersections.size(); i++) {
            if (intersections.get(i).geometry.getMaterial().kT.equals(Double3.ZERO)) {
                return true;
            }
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

        Vector n = gp.geometry.getNormal(gp.point);

        Double3 kr = mat.kR;
        Double3 kkr = k.product(kr);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            Ray reflectedRay = constructReflectedRay(gp, ray, n);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            // no geometry intersected by ray
            if (reflectedPoint == null) {
                return color.add(this.scene.background);
            }
            color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
        }

        Double3 kt = mat.kT;
        Double3 kkt = k.product(kt);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            Ray refractedRay = constructRefractedRay(gp, ray, n);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            // no geometry intersected by ray
            if (refractedPoint == null) {
                return color.add(this.scene.background);
            }

            color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }
        return color;
    }


    private Double3 transparency(GeoPoint geopoint, LightSource light, Vector l, Vector n) {

        Vector backVector = l.scale(-1); // from point to light source
        Ray backRay = new Ray(geopoint.point, backVector,n);

        double lightDistance = light.getDistance(geopoint.point);

        var intersections = this.scene.geometries.findGeoIntersections(backRay);
        if (intersections == null)
            return Double3.ONE; //no intersections

        Double3 ktr = Double3.ONE;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr = ktr.product(gp.geometry.getMaterial().kT);  // the more transparency the less shadow
                if (ktr.lowerThan(MIN_CALC_COLOR_K))
                    return Double3.ZERO;
            }
        }
        return ktr;
    }
}