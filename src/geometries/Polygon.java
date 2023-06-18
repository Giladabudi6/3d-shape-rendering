package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * The Polygon class represents a two-dimensional polygon in a 3D Cartesian coordinate system.
 * It extends the Geometry class.
 *
 * The polygon is defined by a list of vertices ordered by edge path. The polygon must be convex.
 */
public class Polygon extends Geometry {
    /**
     * List of polygon's vertices
     */
    protected final List<Point> vertices;
    /**
     * Associated plane in which the polygon lies
     */
    protected final Plane plane;
    private final int size;

    /**
     * Constructs a polygon based on the given vertices.
     * The list of vertices must be ordered by edge path, and the polygon must be convex.
     *
     * @param vertices The list of vertices according to their order by edge path.
     * @throws IllegalArgumentException If the combination of vertices is illegal:
     *                                  - Less than 3 vertices
     *                                  - Consequent vertices are in the same point
     *                                  - The vertices are not in the same plane
     *                                  - The order of vertices is not according to edge path
     *                                  - Three consequent vertices lie in the same line (180° angle between two consequent edges)
     *                                  - The polygon is concave (not convex)
     */
    public Polygon(Point... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        size = vertices.length;

        // Generate the plane according to the first three vertices and associate the polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon.
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (size == 3) return; // No need for more tests for a Triangle

        Vector n = plane.getNormal();
        // Subtracting any subsequent points will throw an IllegalArgumentException because of the zero vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // The cross product of any subsequent edges will throw an IllegalArgumentException because of the zero vector if they connect three vertices that lie in the same line.
        // Generate the direction of the polygon according to the angle between the last and first edge being less than 180°. It is determined by the sign of its dot product with the normal.
        // If all the rest consequent edges generate the same sign, the polygon is convex.
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (var i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lie in the same plane");
            // Test that the consequent edges have the same sign
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }

    /**
     * Computes the normal vector to the polygon at the specified point.
     * This method is overridden from the parent Geometry class.
     *
     * @param point The point on the polygon.
     * @return The normal vector to the polygon.
     */
    @Override
    public Vector getNormal(Point point) {
        return plane.getNormal();
    }

    /**
     * Finds the geometric intersections between the given ray and the polygon.
     * This method is overridden from the parent Geometry class.
     *
     * @param ray The ray for intersection calculation.
     * @return A list of GeoPoint objects representing the geometric intersections.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }
}
