package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphare extends RadialGeometry{
    private Point center;

    public Sphare(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    public Vector getNormal(Point point){
        return center.subtract(point).normalize();
    }
}
