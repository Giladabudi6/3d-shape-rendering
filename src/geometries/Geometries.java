package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {
    private final List<Intersectable> shapesList;

    public Geometries() {
        shapesList = new LinkedList<Intersectable>();
    }

    public Geometries(Intersectable... geometries) {
        this();
        add(geometries);
    }

    public void add(Intersectable... geometries) {
        Collections.addAll(shapesList, geometries);
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // holds the intersection points
        LinkedList<GeoPoint> intersection = null;
        for (var shape : shapesList) {
            var geometryList = shape.findGeoIntersectionsHelper(ray);
            if (geometryList != null) {
                if (intersection == null) {
                    intersection = new LinkedList<>();
                }
                intersection.addAll(geometryList);
            }
        }
        return intersection;
    }
}

