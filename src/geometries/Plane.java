package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The Plane class represents a geometric plane in 3D space.
 */
public class Plane extends Geometry {
    private Point q0;
    private Vector normal;

    /**
     * Constructs a Plane object with the specified base point and normal vector.
     *
     * @param q0     The base point of the plane.
     * @param normal The normal vector of the plane.
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * Constructs a Plane object from three given points on the plane.
     * The normal vector is calculated as the cross product of the vectors (a - b) and (a - c).
     *
     * @param a The first point on the plane.
     * @param b The second point on the plane.
     * @param c The third point on the plane.
     */
    public Plane(Point a, Point b, Point c) {
        q0 = a;
        normal = (a.subtract(b).crossProduct(a.subtract(c))).normalize();
    }

    /**
     * Computes the normal vector to the plane at the specified point.
     * This method is overridden from the parent Geometry class.
     *
     * @param point The point on the plane.
     * @return The normal vector to the plane.
     */
    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }

    /**
     * Returns the normal vector to the plane.
     *
     * @return The normal vector to the plane.
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Finds the geometric intersections between the given ray and the plane.
     * This method is overridden from the parent Geometry class.
     *
     * @param ray The ray for intersection calculation.
     * @return A list of GeoPoint objects representing the geometric intersections.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        if (q0.equals(P0)) {
            return null;
        }

        Vector n = normal;

        double nv = n.dotProduct(v);

        if (isZero(nv)) {
            return null;
        }

        Vector q0_p0 = q0.subtract(P0);
        double nP0Q0 = alignZero(n.dotProduct(q0_p0));

        if (isZero(nP0Q0)) {
            return null;
        }

        double t = alignZero(nP0Q0 / nv);

        if (t <= 0) {
            return null;
        }

        return List.of(new GeoPoint(this, ray.getPoint(t)));
    }
}
