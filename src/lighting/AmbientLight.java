package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;

public class AmbientLight {
    public AmbientLight(Color IA, Double3 KA) {
        intensity = IA.scale(KA);
    }

    public AmbientLight(Color IA, double KA) {
        intensity = IA.scale(KA);
    }

    Color intensity;
    public static final AmbientLight NONE = new AmbientLight(new Color(java.awt.Color.black),Double3.ZERO);

    public Color getIntensity() {
        return intensity;
    }
}
