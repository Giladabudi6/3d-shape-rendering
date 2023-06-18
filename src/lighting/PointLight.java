package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Represents a point light source in a lighting system.
 * A point light emits light uniformly in all directions from a specific position.
 */
public class PointLight extends Light implements LightSource {

    private Point position;
    private double kC = 1, kL = 0, kQ = 0;

    /**
     * Constructs a PointLight with the specified intensity and position.
     *
     * @param intensity The intensity of the point light.
     * @param position  The position of the point light.
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * Sets the position of the point light.
     *
     * @param position The new position of the point light.
     * @return The updated PointLight instance.
     */
    public PointLight setPosition(Point position) {
        this.position = position;
        return this;
    }

    /**
     * Sets the constant attenuation factor of the point light.
     *
     * @param kC The new constant attenuation factor.
     * @return The updated PointLight instance.
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Sets the linear attenuation factor of the point light.
     *
     * @param kL The new linear attenuation factor.
     * @return The updated PointLight instance.
     */
    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Sets the quadratic attenuation factor of the point light.
     *
     * @param kQ The new quadratic attenuation factor.
     * @return The updated PointLight instance.
     */
    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * Returns the intensity of the point light at the specified point.
     * The intensity is calculated based on the distance between the point light and the specified point,
     * and the attenuation factors kC, kL, and kQ.
     *
     * @param p The point at which to calculate the intensity.
     * @return The intensity of the point light at the specified point.
     */
    public Color getIntensity(Point p) {
        double distance = p.distance(position);
        return getIntensity().scale(1 / (kC + (kL * distance) + kQ * distance * distance));
    }

    /**
     * Returns the direction from the specified point towards the point light source.
     *
     * @param p The point at which to calculate the direction.
     * @return The direction towards the point light source.
     */
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    /**
     * Returns the distance from the specified point to the point light source.
     *
     * @param point The point from which to calculate the distance.
     * @return The distance to the point light source.
     */
    public double getDistance(Point point) {
        return position.distance(point);
    }
}
