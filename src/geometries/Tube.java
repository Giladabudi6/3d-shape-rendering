package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube {
    final Ray axisRay;

    public Tube(Ray axisRay) {
        this.axisRay = axisRay;
    }

    public Vector getNormal(Point point){
        return null;
    }

}
