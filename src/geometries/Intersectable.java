package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * The Intersectable class represents an abstract intersectable geometry.
 * It provides methods for finding intersections with rays.
 */
public abstract class Intersectable {

    /**
     * Finds the intersections between the given ray and the geometry.
     *
     * @param ray The ray for intersection calculation.
     * @return A list of intersection points as Point objects.
     */
    public List<Point> findIntersections(Ray ray) {
        List<GeoPoint> geoIntersections = findGeoIntersections(ray);
        return geoIntersections == null ? null : geoIntersections.stream().map(gp -> gp.point).toList();
    }

    /**
     * Finds the geometric intersections between the given ray and the geometry.
     *
     * @param ray The ray for intersection calculation.
     * @return A list of GeoPoint objects representing the geometric intersections.
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * Helper method for finding the geometric intersections between the given ray and the geometry.
     * Subclasses should implement this method to provide the specific intersection logic.
     *
     * @param ray The ray for intersection calculation.
     * @return A list of GeoPoint objects representing the geometric intersections.
     */
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }

    /**
     * The GeoPoint class represents a point of intersection between a ray and a geometry.
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * Constructs a GeoPoint object with the given geometry and intersection point.
         *
         * @param geometry The intersected geometry.
         * @param point    The intersection point.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint geoPoint)) return false;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
}
