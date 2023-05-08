package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {
    //TODO: note - we are using double for the distance etc. should we change it to int?
    private Point location;
    private Vector Vright, Vup, Vto;
    private double height, width, distance;

    public Point getLocation() {
        return location;
    }

    public Vector getVright() {
        return Vright;
    }

    public Vector getVup() {
        return Vup;
    }

    public Vector getVto() {
        return Vto;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getDistance() {
        return distance;
    }

    public Camera(Point location, Vector vup, Vector vto) {

        if (!isZero(vup.dotProduct(vto))) {
            throw new IllegalArgumentException("The two Vectors are not orthogonal");
        }
        this.location = location;
        Vup = vup.normalize();
        Vto = vto.normalize();
        Vright = Vup.crossProduct(Vto).normalize();
    }

    public Camera setVPSize(double width, double height) {
        this.height = height;
        this.width = width;
        return this;
    }

    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i) {
        // nX represent rows and nY represents columns of the resolution
        // i represent rows and j represents columns of the view plane


        Point pIJ = location.add(Vto.scale(distance));

        double Ry = height / nY;
        double Rx = width / nX;

        double yI = -(i - ((nY - 1) / 2)) * Ry;
        double xJ = (j - ((nX - 1) / 2)) * Rx;

        if (xJ != 0)
            pIJ = pIJ.add(Vright.scale(xJ));
        if (yI != 0)
            pIJ = pIJ.add(Vup.scale(yI));

        Ray ray = new Ray(location, pIJ.subtract(location));

        //pIJ = location.add(Vright.scale(j).add(Vup.scale(i)));

        return ray;

    }

}