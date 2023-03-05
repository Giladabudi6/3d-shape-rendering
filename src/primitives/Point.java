package primitives;

import jdk.incubator.vector.VectorSpecies;

import java.util.Objects;

public class Point {
    final Double3 xyz;

    //cons
    public Point(double x, double y, double z) {
        xyz = new Double3(x,y,z);
    }

    Point(Double3 xyz) {
        this.xyz = xyz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return Objects.equals(xyz, point.xyz);
    }
    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }
//todo check if the subtract is currect
    private Vector subtract(Point p2){
        Double3 updatePoint = xyz.subtract(p2.xyz);
        Vector newVector = new Vector(updatePoint.d1 , updatePoint.d2, updatePoint.d3);
        return newVector;
    }

    private Point add(Vector p2){
        Double3 updatePoint = xyz.add(p2.xyz);
        Point newPoint = new Point(updatePoint.d1 , updatePoint.d2, updatePoint.d3);
        return newPoint;
    }

    private double distanceSquared(Point p2){
        double x = (xyz.d1 + p2.xyz.d1) * (xyz.d1 + p2.xyz.d1);
        double y = (xyz.d2 + p2.xyz.d2) * (xyz.d2 + p2.xyz.d2);
        double z = (xyz.d3 + p2.xyz.d3) * (xyz.d3 + p2.xyz.d3);
        return (x+y+z);
    }
    private double distance(Point p2) {
        return Math.sqrt(distanceSquared(p2));
    }
}
