package primitives;


import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;

public class Ray {
    Point p0;
    Vector dir;

    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

/*    public Point getPoint() {
        return this.p0;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    public Point getPoint(double t) {
        if (isZero(t)) {
            return p0;
        }
        return p0.add(dir.scale(t));
    }

    public Point findClosestPoint(List<Point> points){
        if(points.size() == 0)
            return null;
        Point closestPoint = points.get(0);
        double min = points.get(0).distance(this.p0);
        for (int i=1; i<points.size();i++){
            double current = points.get(i).distance(this.p0);
            if(current < min){
                min = current;
                closestPoint = points.get(i);
            }
        }
        return closestPoint;
    }

}
