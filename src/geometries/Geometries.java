package geometries;

import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * The Geometries class represents a collection of intersectable geometries.
 * It extends the Intersectable class and allows adding multiple geometries to form a composite object.
 */
public class Geometries extends Intersectable {
    private final List<Intersectable> geometries;

    /**
     * Constructs an empty Geometries object.
     */
    public Geometries() {
        geometries = new LinkedList<>();
    }

    /**
     * Constructs a Geometries object with the specified geometries.
     *
     * @param geometries The geometries to add to the collection.
     */
    public Geometries(Intersectable... geometries) {
        this();
        add(geometries);
    }

    /**
     * Adds the specified geometries to the collection.
     *
     * @param geometries The geometries to add.
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(this.geometries, geometries);
    }

    /**
     * Finds the intersection points between the ray and the geometries in the collection.
     *
     * @param ray The ray for intersection calculation.
     * @return A list of GeoPoint objects representing the intersection points.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // Holds the resulting intersection points
        LinkedList<GeoPoint> result = null;

        for (var geometry : geometries) {
            List<GeoPoint> intersections = geometry.findGeoIntersectionsHelper(ray);
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
