package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * The Cylinder class represents a cylinder in three-dimensional space.
 * It extends the Tube class and adds a height parameter.
 */
public class Cylinder extends Tube {
    private double height;

    /**
     * Constructs a Cylinder object with the specified axis ray, radius, and height.
     *
     * @param axisRay The axis ray of the cylinder.
     * @param radius  The radius of the cylinder.
     * @param height  The height of the cylinder.
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * Computes and returns the normal vector to the cylinder at the given point.
     *
     * @param point The point on the cylinder for which to calculate the normal vector.
     * @return The normal vector to the cylinder at the given point.
     */
    public Vector getNormal(Point point) {
        return null;
    }
}
