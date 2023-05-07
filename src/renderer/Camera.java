package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.isZero;

public class Camera {
    //TODO: note - we are using double for the distance etc. should we change it to int?
    private Point location;
    private Vector Vright,Vup,Vto;
    private double height,width,distance;

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
        if(!isZero(Vup.dotProduct(Vto))) {
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

    public Camera setVPDistance ( double distance){
            this.distance = distance;
            return this;
    }
    public Ray constructRay(int nX, int nY, int j, int i){
    // nX represent rows and nY represents columns of the resolution
    // i represent rows and j represents columns of the view plane
        return null;

    }

}