package lighting;

import primitives.Color;

/**
 * Represents a light source in a lighting system.
 * Light is an abstract class that provides common functionality and properties for different types of lights.
 */
abstract class Light {
    private Color intensity;

    /**
     * Constructs a Light with the specified intensity.
     *
     * @param intensity The intensity of the light.
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Returns the intensity of the light.
     *
     * @return The intensity of the light.
     */
    public Color getIntensity() {
        return intensity;
    }
}
