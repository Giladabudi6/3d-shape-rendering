package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Represents a spotlight in a lighting system.
 * A spotlight is a type of point light that emits light in a specific direction within a narrow beam.
 */
public class SpotLight extends PointLight implements LightSource {

    private Vector direction;
    private double narrowBeam = 1;

    /**
     * Sets the narrowness of the beam for the spotlight.
     * A higher value produces a narrower beam, while a lower value produces a wider beam.
     *
     * @param narrow The narrowness of the spotlight beam.
     * @return The updated SpotLight instance.
     */
    public SpotLight setNarrowBeam(double narrow) {
        this.narrowBeam = narrow;
        return this;
    }

    /**
     * Constructs a SpotLight with the specified intensity, position, and direction.
     *
     * @param intensity The intensity of the spotlight.
     * @param position  The position of the spotlight.
     * @param direction The direction of the spotlight.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction;
    }

    /**
     * Returns the intensity of the spotlight at the specified point.
     * The intensity is calculated based on the distance between the spotlight and the specified point,
     * the attenuation factors, and the alignment of the spotlight direction with the vector from the spotlight to the point.
     *
     * @param p The point at which to calculate the intensity.
     * @return The intensity of the spotlight at the specified point.
     */
    public Color getIntensity(Point p) {
        Vector vector = getL(p);
        return super.getIntensity(p).scale(Math.pow(Math.max(0, this.direction.normalize().dotProduct(vector)), narrowBeam));
    }

    /**
     * Returns the distance from the specified point to the spotlight.
     * This method overrides the implementation in the PointLight class.
     *
     * @param point The point from which to calculate the distance.
     * @return The distance to the spotlight.
     */
    public double getDistance(Point point) {
        return super.getDistance(point);
    }
}
