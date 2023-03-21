package geometries;
import primitives.Point;
import primitives.Vector;
import primitives.Double3;

public class Plane {
    Point q0;

    //TODO: change from null in all usages.
    Vector normal = null;

    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    public Plane(Point a, Point b, Point c ) {
        q0 = a;
        normal = null;
    }

    public Vector getNormal(Point point) {
        Vector v = getNormal();
        return v;
    }
    public Vector getNormal(){
        return normal;
    }
}
