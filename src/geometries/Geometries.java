package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
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

    public List<Point> findIntersections(Ray ray) {
        // holds the intersection points
        LinkedList<Point> points = null;
        for (var geometry : shapesList) {
            var geometryList = geometry.findIntersections(ray);
            if (geometryList != null) {
                if (points == null) {
                    points = new LinkedList<>();
                }
                points.addAll(geometryList);
            }
        }
        return points;
    }
}
