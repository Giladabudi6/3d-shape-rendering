
/**

 Represents ambient light in a lighting system.
 Ambient light is a constant light that is present everywhere in the scene,
 providing a base level of illumination to all objects.
 */
package lighting;
        import primitives.Color;
        import primitives.Double3;

public class AmbientLight extends Light {


    /**
     * A constant representing no ambient light, i.e., black color with zero intensity.
     */
    public static final AmbientLight NONE = new AmbientLight(new Color(java.awt.Color.BLACK), Double3.ZERO);

    /**
     * Constructs an AmbientLight with the specified intensity and ambient reflection coefficient.
     *
     * @param IA The intensity of the ambient light.
     * @param KA The ambient reflection coefficient.
     */
    public AmbientLight(Color IA, Double3 KA) {
        super(IA.scale(KA));
    }

    /**
     * Constructs an AmbientLight with the specified intensity and ambient reflection coefficient.
     *
     * @param IA The intensity of the ambient light.
     * @param KA The ambient reflection coefficient.
     */
    public AmbientLight(Color IA, double KA) {
        super(IA.scale(KA));
    }
}