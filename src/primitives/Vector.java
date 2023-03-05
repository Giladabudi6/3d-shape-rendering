package primitives;

public class Vector extends Point {
    Vector(Double3 xyz) {
        super(xyz);
        if (xyz == Double3.ZERO) {
            throw new IllegalArgumentException("invalid coordinates for a vector");
        }
    }

    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz == (Double3.ZERO)) {
            throw new IllegalArgumentException("invalid coordinates for a vector");
        }
    }
//TODO check if its private
    public Vector add(Vector v1) {
        double x = xyz.d1 + v1.xyz.d1;
        double y = xyz.d2 + v1.xyz.d2;
        double z = xyz.d3 + v1.xyz.d3;
        return new Vector (x,y,z);
    }

    private Vector scale(double toScale) {
        xyz.scale(toScale);
        Vector newVector = new Vector(xyz);
        return newVector;
    }

    private double dotProduct(Double3 toScale) {
        double scalar = (xyz.d1 * toScale.d1) + (xyz.d2 * toScale.d2) + (xyz.d3 * toScale.d3);
        return scalar;
    }

    private Vector crossProduct(Vector v2) {
        double x = xyz.d2 * v2.xyz.d3 - xyz.d3 * v2.xyz.d2;
        double y = xyz.d3 * v2.xyz.d1 - xyz.d1 * v2.xyz.d3;
        double z = xyz.d1 * v2.xyz.d2 - xyz.d2 * v2.xyz.d1;
        Vector newVector = new Vector(x, y, z);
        return newVector;
    }

    private double lengthSquared() {
        double length = (xyz.d1 * xyz.d1) + (xyz.d2 * xyz.d2) + (xyz.d3 * xyz.d3);
        return length;
    }

    private double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize() {
        double length = length();
        double x = (xyz.d1 / length);
        double y = (xyz.d2 / length);
        double z = (xyz.d3 / length);
        return new Vector(x, y, z);
    }

}
