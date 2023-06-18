package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Represents a light source in a lighting system.
 * A light source emits light and provides information about its intensity, direction, and distance from a point.
 */
public interface LightSource {

    /**
     * Returns the intensity of the light at the specified point.
     *
     * @param p The point at which to calculate the intensity.
     * @return The intensity of the light at the specified point.
     */
    public Color getIntensity(Point p);

    /**
     * Returns the direction from the specified point towards the light source.
     *
     * @param p The point at which to calculate the direction.
     * @return The direction towards the light source.
     */
    public Vector getL(Point p);

    /**
     * Returns the distance from the specified point to the light source.
     *
     * @param point The point from which to calculate the distance.
     * @return The distance to the light source.
     */
    public double getDistance(Point point);
}
