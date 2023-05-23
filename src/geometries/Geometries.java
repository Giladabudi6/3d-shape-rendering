package geometries;

import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {
    private final List<Intersectable> geometries;

    public Geometries() {
        geometries = new LinkedList<Intersectable>();
    }

    public Geometries(Intersectable... geometries) {
        this();
        add(geometries);
    }

    public void add(Intersectable... geometries) {
        Collections.addAll(this.geometries, geometries);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // holds the result points
        LinkedList<GeoPoint> result = null;
        for (var geometry : geometries) {
            List <GeoPoint> intersections = geometry.findGeoIntersectionsHelper(ray);
            if (intersections != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }
                result.addAll(intersections);
            }
        }
        return result;
    }
}

