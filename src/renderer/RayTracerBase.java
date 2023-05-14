package renderer;


import scene.Scene;
import primitives.Ray;
import primitives.Color;


public abstract class RayTracerBase {

    protected Scene scene;

    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }
    // TODO: check if the returned value is correct
    public Color traceRay(Ray ray){
        return null;
    }
}
