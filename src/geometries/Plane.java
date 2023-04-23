package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Double3;

import java.util.List;

public class Plane {
    Point q0;
    Vector normal = null;

    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    public Plane(Point a, Point b, Point c ) {
        q0 = a;
        normal = (a.subtract(b).crossProduct(a.subtract(c))).normalize();  // calculate the normalize normal
    }

    public Vector getNormal(Point point) {
        Vector v = getNormal();
        return v;
    }

    public Vector getNormal(){
        return normal;
    }

    public List<Point> findIntsersections (Ray ray){
        return null;

    }
}
