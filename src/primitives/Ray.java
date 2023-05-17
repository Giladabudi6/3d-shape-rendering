package primitives;


import java.util.List;
import java.util.Objects;
import geometries.Intersectable.GeoPoint;

import static primitives.Util.isZero;

public class Ray {
    Point p0;
    Vector dir;

    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    public Point getPoint(double t) {
        if (isZero(t)) {
            return p0;
        }
        return p0.add(dir.scale(t));
    }

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

    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

}
