package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry{
    final Ray axisRay;

    public Tube(Ray axisRay, double radius) {
        super(radius);
        this.axisRay = axisRay;
    }

    public Vector getNormal(Point point){

        double scalar = axisRay.getDir().dotProduct(point.subtract(axisRay.getP0()));
        if(scalar != 0) {
            return point.subtract(axisRay.getP0().add(axisRay.getDir().scale(axisRay.getDir().dotProduct(point.subtract(axisRay.getP0())))));
        }
        else
            return point.subtract(axisRay.getP0());
    }

}
