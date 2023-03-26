package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{
    private double height;

    public Cylinder(Ray axisRay, double height) {
        super(axisRay);
        this.height = height;
    }

    public Vector getNormal(Point point){
        return null;
    }
}
