package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight extends Light {
    public static final AmbientLight NONE = new AmbientLight(new Color(java.awt.Color.BLACK), Double3.ZERO);

    public AmbientLight(Color IA, Double3 KA) {
        super(IA.scale(KA));
    }

    public AmbientLight(Color IA, double KA) {
        super(IA.scale(KA));
    }
}
