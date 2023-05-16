package geometries;

public abstract class RadialGeometry extends Geometry {
    protected double radius;

    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}
