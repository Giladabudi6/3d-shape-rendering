package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Tube extends RadialGeometry {
    final Ray axisRay;

    /**
     * Constructs a Tube object with the given axis ray and radius.
     *
     * @param axisRay The axis ray of the tube.
     * @param radius  The radius of the tube.
     */
    public Tube(Ray axisRay, double radius) {
        super(radius);
        this.axisRay = axisRay;
    }

    /**
     * Calculates and returns the normal vector to the tube at the given point.
     *
     * @param point The point on the tube's surface.
     * @return The normal vector at the given point.
     */
    public Vector getNormal(Point point) {
        double scalar = axisRay.getDir().dotProduct(point.subtract(axisRay.getP0()));
        if (scalar != 0) {
            // Calculate and return the normalized normal vector using the projection of the point
            // onto the axis ray and subtracting it from the point.
            return point.subtract(axisRay.getP0().add(axisRay.getDir().scale(scalar))).normalize();
        } else {
            // If the scalar is zero, the point is on the axis ray.
            // Return the normalized vector from the point to the axis ray's starting point.
            return point.subtract(axisRay.getP0()).normalize();
        }
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
