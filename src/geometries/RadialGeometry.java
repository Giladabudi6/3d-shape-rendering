package geometries;

/**
 * The RadialGeometry class is an abstract class that represents a radial geometry in 3D space.
 * It extends the Geometry class.
 */
public abstract class RadialGeometry extends Geometry {
    protected double radius;

    /**
     * Constructs a RadialGeometry object with the given radius.
     *
     * @param radius The radius of the radial geometry.
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}
