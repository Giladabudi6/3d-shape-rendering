package primitives;

import java.util.List;
import java.util.Objects;
import geometries.Intersectable.GeoPoint;

import static primitives.Util.isZero;

/**
 * Represents a ray in three-dimensional space.
 */
public class Ray {
    Point p0;
    Vector dir;

    private static final double DELTA = 0.1;

    /**
     * Constructs a ray with the given starting point and direction.
     *
     * @param p0  The starting point of the ray.
     * @param dir The direction of the ray.
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * Constructs a ray with the given starting point, direction, and normal vector.
     * Adds a small delta value to the starting point to avoid self-intersections.
     *
     * @param p0  The starting point of the ray.
     * @param dir The direction of the ray.
     * @param n   The normal vector.
     */
    public Ray(Point p0, Vector dir, Vector n) {
        double delta = dir.dotProduct(n) >= 0 ? DELTA : -DELTA;
        this.p0 = p0.add(n.scale(delta));
        this.dir = dir;
    }

    /**
     * Checks if this ray is equal to another object.
     *
     * @param o The object to compare with.
     * @return True if the rays are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    /**
     * Computes the hash code of the ray.
     *
     * @return The hash code of the ray.
     */
    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    /**
     * Returns a string representation of the ray.
     *
     * @return The string representation of the ray.
     */
    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /**
     * Returns the starting point of the ray.
     *
     * @return The starting point of the ray.
     */
    public Point getP0() {
        return p0;
    }

    /**
     * Returns the direction of the ray.
     *
     * @return The direction of the ray.
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Computes the point on the ray at the given parameter value t.
     *
     * @param t The parameter value.
     * @return The point on the ray at the given parameter value.
     */
    public Point getPoint(double t) {
        if (isZero(t)) {
            return p0;
        }
        return p0.add(dir.scale(t));
    }

    /**
     * Finds the closest GeoPoint in a list of intersections to the ray's starting point.
     *
     * @param intersections The list of GeoPoints.
     * @return The closest GeoPoint to the ray's starting point.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections) {
        if (intersections.size() == 0)
            return null;
        GeoPoint closestGeoPoint = intersections.get(0);
        // Before checking the rest the default is that the first point is the closest
        double min = intersections.get(0).point.distanceSquared(this.p0);
        for (int i = 1; i < intersections.size(); i++) {
            double current = intersections.get(i).point.distanceSquared(this.p0);
            if (current < min) {
                min = current;
                closestGeoPoint = intersections.get(i);
            }
        }
        return closestGeoPoint;
    }

    /**
     * Finds the closest point in a list of points to the ray's starting point.
     *
     * @param points The list of points.
     * @return The closest point to the ray's starting point.
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }
}
