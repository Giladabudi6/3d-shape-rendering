/**
 * Represents a directional light source in a lighting system.
 * A directional light is a light source that emits light uniformly in a specific direction,
 * as if it were infinitely far away and its rays were parallel.
 */
package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    /**
     * Constructs a DirectionalLight with the specified intensity and direction.
     *
     * @param intensity The intensity of the directional light.
     * @param direction The direction of the directional light.
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction;
    }

    /**
     * Returns the intensity of the directional light at the specified point.
     * Since a directional light emits light uniformly in all directions,
     * the intensity is constant and independent of the point.
     *
     * @param p The point at which to calculate the intensity.
     * @return The intensity of the directional light.
     */
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    /**
     * Returns the direction from the specified point towards the directional light source.
     * Since a directional light emits light uniformly in a specific direction,
     * the direction is constant and independent of the point.
     *
     * @param p The point at which to calculate the direction.
     * @return The direction towards the directional light source.
     */
    public Vector getL(Point p) {
        return direction.normalize();
    }

    /**
     * Returns the distance from the specified point to the directional light source.
     * Since a directional light is considered infinitely far away,
     * the distance is always positive infinity.
     *
     * @param point The point from which to calculate the distance.
     * @return The distance to the directional light source (always positive infinity).
     */
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
}
