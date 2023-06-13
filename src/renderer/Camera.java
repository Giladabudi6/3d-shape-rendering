package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Random;
import java.util.stream.IntStream;

import static primitives.Util.isZero;
import static renderer.Pixel.printInterval;

public class Camera {
    private final Point location;
    private final Vector Vright;
    private final Vector Vup;
    private final Vector Vto;
    ImageWriter imageWriter;
    RayTracerBase rayTracer;
    private double height, width, distance;
    // turns the improvements ON/OFF
    private boolean antiAliasing = false;
    private boolean MT = false;

    public Camera setantiAliasing(boolean antiAliasing) {
        this.antiAliasing = antiAliasing;
        return this;
    }
    public Camera setMT(boolean MT) {
        this.MT = MT;
        return this;
    }

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

    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }


    private List<Color> constructRayBeam(int nX, int nY, int j, int i, List<Ray> rays) {

        List<Color> colors = new LinkedList<Color>();


        double Ry = height / nY;   // SIZE OF THE PIXEL - HEIGHT
        double Rx = width / nX;    // SIZE OF THE PIXEL - WIDTH

        double yI = -(i - ((nY - 1) / 2d)) * Ry;  // CENTER OF THE PIXEL
        double xJ = (j - ((nX - 1) / 2d)) * Rx;   // CENTER OF THE PIXEL
        // TODO: check if the boundaries are available

        double minValueX = -Rx / 2;
        double maxValueX =  Rx / 2;

        double minValueY = -Ry / 2;
        double maxValueY =  Ry / 2;

        Random randomXY = new Random();
        for (int k = 0; k < 50; k++) {
            double randomX = minValueX + (maxValueX - minValueX) * randomXY.nextDouble();
            double randomY = minValueY + (maxValueY - minValueY) * randomXY.nextDouble();

            Point pIJ = location.add(Vto.scale(distance));

            if (!isZero(xJ + randomX))
                pIJ = pIJ.add(Vright.scale(xJ+ randomX));
            if (!isZero(yI + randomY))
                pIJ = pIJ.add(Vup.scale(yI + randomY));

            Ray ray = new Ray(location, pIJ.subtract(location));
            colors.add(castRay(ray));
        }
        return colors;
    }

   private Color averageColor(List<Color> colors) {
        double red = 0;
        double green = 0;
        double blue = 0;
        for (Color color : colors) {
            red+=color.getColor().getRed();
            green+=color.getColor().getGreen();
            blue+=color.getColor().getBlue();
        }
        red = red/colors.size();
        green = green/colors.size();
        blue = blue/colors.size();

        Color averageColor = new Color(red,green,blue);

        return averageColor;
    }



    public Ray constructRay(int nX, int nY, int j, int i) {
        // nX represent rows and nY represents columns of the resolution
        // i represent rows and j represents columns of the view plane

        Point pIJ = location.add(Vto.scale(distance));

        double Ry = height / nY;   // SIZE OF THE PIXEL - HEIGHT
        double Rx = width / nX;    // SIZE OF THE PIXEL - WIDTH

        double yI = -(i - ((nY - 1) / 2d)) * Ry;  // CENTER OF THE PIXEL
        double xJ = (j - ((nX - 1) / 2d)) * Rx;   // CENTER OF THE PIXEL

        if (!isZero(xJ))
            pIJ = pIJ.add(Vright.scale(xJ));
        if (!isZero(yI))
            pIJ = pIJ.add(Vup.scale(yI));


        Ray ray = new Ray(location, pIJ.subtract(location));

        return ray;

    }

    public void renderImage() {
        // Check if all the required resources are set before rendering the image
        if (location == null) {
            throw new MissingResourceException("missing resource", "location", "Location is missing");
        }

        if (Vright == null) {
            throw new MissingResourceException("missing resource", "Vright", "Vright is missing");
        }

        if (Vup == null) {
            throw new MissingResourceException("missing resource", "Vup", "Vup is missing");
        }

        if (Vto == null) {
            throw new MissingResourceException("missing resource", "Vto", "Vto is missing");
        }

        if (imageWriter == null) {
            throw new MissingResourceException("missing resource", "imageWriter", "Image writer is missing");
        }

        if (rayTracer == null) {
            throw new MissingResourceException("missing resource", "rayTracerBase", "Ray tracer base is missing");
        }

        if (Double.isNaN(height)) {
            throw new MissingResourceException("missing resource", "height", "Height is missing");
        }

        if (Double.isNaN(width)) {
            throw new MissingResourceException("missing resource", "width", "Width is missing");
        }

        if (Double.isNaN(distance)) {
            throw new MissingResourceException("missing resource", "distance", "Distance is missing");
        }

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        if (MT) {
            Pixel.initialize(nY, nX, printInterval);
            IntStream.range(0, nY).parallel().forEach(i -> {
                IntStream.range(0, nX).parallel().forEach(j -> {

                    Ray ray = constructRay(nX, nY, i, j);
                    Color color = castRay(ray);

                    if (antiAliasing) {
                        List<Ray> rays = new LinkedList<>();
                        rays.add(ray);
                        List<Color> colors = constructRayBeam(nX, nY, i, j, rays);
                        color = averageColor(colors);
                    }
                    Pixel.pixelDone();
                    Pixel.printPixel();

                    imageWriter.writePixel(i, j, color);

                });
            });
        }

        else {
            for (int i = 0; i < nX; i++) {
                for (int j = 0; j < nY; j++) {

                    // Cast a ray from the camera to the current pixel and get the color
                    Ray ray = constructRay(nX, nY, i, j);
                    Color color = castRay(ray);

                    if (antiAliasing) {
                        List<Ray> rays = new LinkedList<>();
                        rays.add(ray);
                        List<Color> colors = constructRayBeam(nX, nY, i, j, rays);
                        color = averageColor(colors);
                    }

                    imageWriter.writePixel(i, j, color);
                }
            }
        }
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

    public Color castRay(Ray ray) {
        // Use the ray tracer base to trace the given ray and return the resulting color

        return rayTracer.traceRay(ray);
    }
}
