package geometries;
import primitives.Point;
import primitives.Vector;
import primitives.Double3;

public class Plane {
    Point q0;
    Vector normal;

    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }


    public Vector getNormal(Point point) {
        Vector v = new Vector();
    }
    public Vector getNormal(){
        return normal;
    }
}
