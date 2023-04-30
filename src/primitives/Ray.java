package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

public class Ray {
    Point p0;
    Vector dir;

    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

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
        if(isZero(t)){
            throw new IllegalArgumentException("t is equal to 0 produce an illegal ZERO vector");
        }
        return p0.add(dir.scale(t));
    }
}
