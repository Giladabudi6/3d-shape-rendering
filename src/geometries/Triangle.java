package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * The Triangle class represents a triangle in 3D Cartesian coordinate system.
 * It extends the Polygon class.
 */
public class Triangle extends Polygon {

    /**
     * Constructs a Triangle object with the given vertices.
     * The vertices must be ordered by edge path and the triangle must be convex.
     *
     * @param vertices The vertices of the triangle.
     * @throws IllegalArgumentException In any case of illegal combination of vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same point</li>
     *                                  <li>The vertices are not in the same plane</li>
     *                                  <li>The order of vertices is not according to edge path</li>
     *                                  <li>Three consequent vertices lay in the same line (180° angle between two consequent edges)</li>
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Triangle(Point... vertices) {
        super(vertices);
    }

    /**
     * Finds the intersections between a ray and the triangle.
     *
     * @param ray The ray for intersection calculation.
     * @return A list of intersection points between the ray and the triangle,
     *         or null if there are no intersections.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // Check if the ray intersects the plane of the triangle.
        List<GeoPoint> intersections = plane.findGeoIntersectionsHelper(ray);

        if (intersections == null) {
            return null;
        }

        // Create vectors from the same starting point to each vertex of the triangle.
        Vector v1 = vertices.get(0).subtract(ray.getP0());
        Vector v2 = vertices.get(1).subtract(ray.getP0());
        Vector v3 = vertices.get(2).subtract(ray.getP0());

        // Calculate the normals for each face of the pyramid formed by the three vectors,
        // and normalize them.
        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        // Get the direction vector of the ray.
        Vector v = ray.getDir();

        // Check if the directions of the vectors (obtained by subtracting the ray's vector
        // from each of the above vectors) are all equal.
        // If not, there is no intersection point between the ray and the triangle.
        if ((alignZero(v.dotProduct(n1)) > 0 && alignZero(v.dotProduct(n2)) > 0 && alignZero(v.dotProduct(n3)) > 0) ||
                (alignZero(v.dotProduct(n1)) < 0 && alignZero(v.dotProduct(n2)) < 0 && alignZero(v.dotProduct(n3)) < 0)) {
            intersections.get(0).geometry = this;
            return intersections;
        }

        return null;
    }
}
