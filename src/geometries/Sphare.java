package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphare {
    private Point center;
    private double radius;

    public Sphare(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Vector getNormal(Point point){
        return null;
    }
}
