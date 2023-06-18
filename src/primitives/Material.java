package primitives;

/**
 * Represents the material properties of an object.
 * The material determines how the object interacts with light, including its reflection, refraction, and diffusion.
 */
public class Material {
    public int nShininess = 0;
    public Double3 kS = Double3.ZERO;
    public Double3 kD = Double3.ZERO;
    public Double3 kT = Double3.ZERO;
    public Double3 kR = Double3.ZERO;

    /**
     * Sets the transparency level of the material.
     *
     * @param kT The transparency level represented as a Double3.
     * @return The updated Material instance.
     */
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Sets the transparency level of the material.
     *
     * @param kT The transparency level represented as a double.
     * @return The updated Material instance.
     */
    public Material setkT(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * Sets the reflection level of the material.
     *
     * @param kR The reflection level represented as a Double3.
     * @return The updated Material instance.
     */
    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Sets the reflection level of the material.
     *
     * @param kR The reflection level represented as a double.
     * @return The updated Material instance.
     */
    public Material setkR(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * Sets the diffuse light factor of the material.
     *
     * @param kD The diffuse light factor represented as a Double3.
     * @return The updated Material instance.
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Sets the diffuse light factor of the material.
     *
     * @param kD The diffuse light factor represented as a double.
     * @return The updated Material instance.
     */
    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Sets the specular light factor of the material.
     *
     * @param kS The specular light factor represented as a Double3.
     * @return The updated Material instance.
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Sets the specular light factor of the material.
     *
     * @param kS The specular light factor represented as a double.
     * @return The updated Material instance.
     */
    public Material setkS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Sets the shininess level of the material.
     *
     * @param nShininess The shininess level.
     * @return The updated Material instance.
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
