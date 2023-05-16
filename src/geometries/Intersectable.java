package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

public interface Intersectable {
    List<Point> findIntersections(Ray ray);

}
