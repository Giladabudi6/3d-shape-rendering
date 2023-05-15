package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {
    //TODO: note - we are using double for the distance etc. should we change it to int?
    private final Point location;
    private final Vector Vright;
    private final Vector Vup;
    private final Vector Vto;
    ImageWriter imageWriter;
    RayTracerBase rayTracerBase;
    private double height, width, distance;

    public Camera(Point location, Vector vto, Vector vup) {

        if (!isZero(vup.dotProduct(vto))) {
            throw new IllegalArgumentException("The two Vectors are not orthogonal");
        }
        this.location = location;
        Vup = vup.normalize();
        Vto = vto.normalize();
        Vright = Vup.crossProduct(Vto).scale(-1).normalize();
    }

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

    public Camera setVPSize(double width, double height) {
        this.height = height;
        this.width = width;
        return this;
    }

    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Camera setRayTracerBase(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }


    public Ray constructRay(int nX, int nY, int j, int i) {
        // nX represent rows and nY represents columns of the resolution
        // i represent rows and j represents columns of the view plane

        Point pIJ = location.add(Vto.scale(distance));

        double Ry = height / nY;
        double Rx = width / nX;

        double yI = -(i - ((nY - 1) / 2d)) * Ry;
        double xJ = (j - ((nX - 1) / 2d)) * Rx;

        if (xJ != 0)
            pIJ = pIJ.add(Vright.scale(xJ));
        if (yI != 0)
            pIJ = pIJ.add(Vup.scale(yI));

        Ray ray = new Ray(location, pIJ.subtract(location));

        return ray;

    }
//trdf
    void  renderImage(){
        if (location == null || Vright == null || Vup == null || Vto == null
                || imageWriter == null || rayTracerBase == null ||
                Double.isNaN(height) || Double.isNaN(width) || Double.isNaN(distance))
        {
            throw new NullPointerException("Field is null");
        }
        int y = 6;

    }

    public void printGrid(int interval, Color color) {
        // Check if the imageWriter is set before printing the grid
        if (imageWriter == null)
            throw new MissingResourceException("missing resource", "imageWriter", "name/nX/nY is missing");
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                if (i % interval == 0 || j % interval == 0)
                    // Write the specified color to the current pixel
                    imageWriter.writePixel(i, j, color);
            }
        }
    }

    public void writeToImage() {
        // Check if the imageWriter is set before writing the image
        if (imageWriter == null)
            throw new MissingResourceException("missing resource", "imageWriter", "name/nX/nY is missing");
        imageWriter.writeToImage();
    }
}
