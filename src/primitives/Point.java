package primitives;

import java.util.Objects;

/**
 * Represents a point in three-dimensional space.
 */
public class Point {
    public static final Point ZERO = new Point(0, 0, 0);
    final Double3 xyz;

    /**
     * Constructs a Point with the specified coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param z The z-coordinate of the point.
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Returns the x-coordinate of the point.
     *
     * @return The x-coordinate of the point.
     */
    public Double getX() {
        return xyz.d1;
    }

    /**
     * Returns the y-coordinate of the point.
     *
     * @return The y-coordinate of the point.
     */
    public Double getY() {
        return xyz.d2;
    }

    /**
     * Returns the z-coordinate of the point.
     *
     * @return The z-coordinate of the point.
     */
    public Double getZ() {
        return xyz.d3;
    }

    /**
     * Checks if this point is equal to another object.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return Objects.equals(xyz, point.xyz);
    }

    /**
     * Computes the hash code of the point.
     *
     * @return The hash code of the point.
     */
    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    /**
     * Returns a string representation of the point.
     *
     * @return The string representation of the point.
     */
    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    /**
     * Computes the vector difference between this point and another point.
     *
     * @param p2 The point to subtract from this point.
     * @return The vector difference between the two points.
     */
    public Vector subtract(Point p2) {
        Double3 updatePoint = xyz.subtract(p2.xyz);
        Vector newVector = new Vector(updatePoint.d1, updatePoint.d2, updatePoint.d3);
        return newVector;
    }

    /**
     * Computes the point obtained by adding a vector to this point.
     *
     * @param p2 The vector to add to this point.
     * @return The new point obtained by adding the vector to this point.
     */
    public Point add(Vector p2) {
        Double3 updatePoint = xyz.add(p2.xyz);
        Point newPoint = new Point(updatePoint.d1, updatePoint.d2, updatePoint.d3);
        return newPoint;
    }

    /**
     * Computes the squared distance between this point and another point.
     *
     * @param p2 The other point.
     * @return The squared distance between the two points.
     */
    public double distanceSquared(Point p2) {
        double x = (xyz.d1 - p2.xyz.d1) * (xyz.d1 - p2.xyz.d1);
        double y = (xyz.d2 - p2.xyz.d2) * (xyz.d2 - p2.xyz.d2);
        double z = (xyz.d3 - p2.xyz.d3) * (xyz.d3 - p2.xyz.d3);
        return (x + y + z);
    }

    /**
     * Computes the distance between this point and another point.
     *
     * @param p2 The other point.
     * @return The distance between the two points.
     */
    public double distance(Point p2) {
        return Math.sqrt(distanceSquared(p2));
    }
}
