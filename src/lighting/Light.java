package lighting;

import primitives.Color;

abstract class Light {
    private Color intensity;
    // Light ctor
    protected Light(Color intensity) {
        this.intensity = intensity;
    }
    // getter
    public Color getIntensity() {
        return intensity;
    }

}
