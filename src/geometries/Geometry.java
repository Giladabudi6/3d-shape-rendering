package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * The Geometry class represents a geometric object in three-dimensional space.
 * It extends the Intersectable class and provides common properties and methods for geometric entities.
 */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;
    private Material material = new Material();

    /**
     * Computes and returns the normal vector to the geometry at the given point.
     *
     * @param p The point on the geometry for which to calculate the normal vector.
     * @return The normal vector to the geometry at the given point.
     */
    public abstract Vector getNormal(Point p);

    /**
     * Returns the emission color of the geometry.
     *
     * @return The emission color.
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Sets the emission color of the geometry.
     *
     * @param emission The emission color to set.
     * @return The updated Geometry object.
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Returns the material of the geometry.
     *
     * @return The material.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the material of the geometry.
     *
     * @param material The material to set.
     * @return The updated Geometry object.
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
