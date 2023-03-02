package primitives;

public class Point {
    final Double3 xyz;


    public Point(double x, double y, double z) {
        xyz = new Double3(x,y,z);
    }

    Point(Double3 xyz) {
        this.xyz = xyz;
    }
}
