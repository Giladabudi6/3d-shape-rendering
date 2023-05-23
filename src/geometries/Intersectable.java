package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class Intersectable {

    public List<Point> findIntersections(Ray ray) {
        var geoIntersections = findGeoIntersections(ray);
        return geoIntersections == null ? null : geoIntersections.stream().map(gp -> gp.point).toList();
    }


    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray){return null;}


    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        // GeoPoint constructor
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

        // GeoPoint toString method
        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }

    }

}
