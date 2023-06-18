package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

/**
 * The Sphere class represents a sphere in 3D Cartesian coordinate system.
 * It extends the RadialGeometry class.
 */
public class Sphere extends RadialGeometry {
    private Point center;

    /**
     * Constructs a Sphere object with the given radius and center point.
     *
     * @param radius The radius of the sphere.
     * @param center The center point of the sphere.
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * Checks if the intersection point is very close to the ray's starting point.
     *
     * @param intersection The intersection point.
     * @param ray          The ray.
     * @return true if the intersection point is very close to the ray's starting point, false otherwise.
     */
    public boolean comparePoints(Point intersection, Ray ray) {
        double epsilon = 1E-10;
        double c = intersection.distance(ray.getP0());
        return intersection.distance(ray.getP0()) < epsilon;
    }

    @Override
    public Vector getNormal(Point point) {
        return center.subtract(point).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getP0(); // ray's starting point
        Point center = this.center; // the sphere's center point
        Vector V = ray.getDir(); // "the v vector" from the presentation

        // if p0 is on center, calculate with line parametric representation
        // the direction vector normalized.
        if (center.equals(p0)) {
            Point newPoint = ray.getPoint(radius);
            return List.of(new GeoPoint(this, newPoint));
        }

        Vector U = center.subtract(p0);
        double tm = V.dotProduct(U);
        double d = Math.sqrt(U.lengthSquared() - tm * tm);
        if (d >= radius) {
            return null;
        }

        double th = Math.sqrt(radius * radius - d * d);
        double t1 = tm - th;
        double t2 = tm + th;

        if (t1 > 0 && t2 > 0) {
            List<GeoPoint> intersections = new LinkedList<>();
            Point p1 = ray.getPoint(t1);
            if (!comparePoints(p1, ray)) {
                intersections.add(new GeoPoint(this, p1));
            }
            Point p2 = ray.getPoint(t2);
            if (!comparePoints(p2, ray)) {
                intersections.add(new GeoPoint(this, p2));
            }
            return intersections;
        }

        if (t1 > 0) {
            Point p1 = ray.getPoint(t1);
            if (!comparePoints(p1, ray)) {
                return List.of(new GeoPoint(this, p1));
            }
        }

        if (t2 > 0) {
            Point p2 = ray.getPoint(t2);
            if (!comparePoints(p2, ray)) {
                return List.of(new GeoPoint(this, p2));
            }
        }

        return null;
    }
}
