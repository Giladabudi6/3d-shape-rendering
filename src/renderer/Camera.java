package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.*;
import java.util.stream.IntStream;

import static primitives.Util.isZero;
import static renderer.Pixel.printInterval;

/**
 * Represents a camera in a 3D scene.
 */
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
    private boolean superSampling = false;

    private int recursionDepth = 5;


    /**
     * Sets the recursion depth for ray tracing.
     *
     * @param recursionDepth The recursion depth
     * @return The camera object
     */
    public Camera setRecursionDepth(int recursionDepth) {
        this.recursionDepth = recursionDepth;
        return this;
    }


    /**
     * Enables or disables anti-aliasing.
     *
     * @param antiAliasing True to enable anti-aliasing, false to disable it
     * @return The camera object
     */
    public Camera setAntiAliasing(boolean antiAliasing) {
        this.antiAliasing = antiAliasing;
        return this;
    }

    /**
     * Enables or disables multi-threading.
     *
     * @param MT True to enable multi-threading, false to disable it
     * @return The camera object
     */
    public Camera setMT(boolean MT) {
        this.MT = MT;
        return this;
    }

    /**
     * Enables or disables super-sampling.
     *
     * @param superSampling True to enable super-sampling, false to disable it
     * @return The camera object
     */
    public Camera setSuperSampling(boolean superSampling) {
        this.superSampling = superSampling;
        return this;
    }

    /**
     * Creates a camera with the specified location, view direction, and up direction.
     *
     * @param location The location of the camera
     * @param vto      The view direction vector of the camera
     * @param vup      The up direction vector of the camera
     * @throws IllegalArgumentException if the view direction and up direction vectors are not orthogonal
     */
    public Camera(Point location, Vector vto, Vector vup) {
        if (!isZero(vup.dotProduct(vto))) {
            throw new IllegalArgumentException("The two Vectors are not orthogonal");
        }
        this.location = location;
        Vup = vup.normalize();
        Vto = vto.normalize();
        Vright = Vup.crossProduct(Vto).scale(-1).normalize();
    }

    /**
     * Returns the location of the camera.
     *
     * @return The location of the camera
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Returns the right vector of the camera.
     *
     * @return The right vector of the camera
     */
    public Vector getVright() {
        return Vright;
    }

    /**
     * Returns the up vector of the camera.
     *
     * @return The up vector of the camera
     */
    public Vector getVup() {
        return Vup;
    }

    /**
     * Returns the view direction vector of the camera.
     *
     * @return The view direction vector of the camera
     */
    public Vector getVto() {
        return Vto;
    }

    /**
     * Returns the height of the view plane.
     *
     * @return The height of the view plane
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the width of the view plane.
     *
     * @return The width of the view plane
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the distance from the camera to the view plane.
     *
     * @return The distance from the camera to the view plane
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets the size of the view plane.
     *
     * @param width  The width of the view plane
     * @param height The height of the view plane
     * @return The camera object
     */
    public Camera setVPSize(double width, double height) {
        this.height = height;
        this.width = width;
        return this;
    }

    /**
     * Sets the distance from the camera to the view plane.
     *
     * @param distance The distance from the camera to the view plane
     * @return The camera object
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * Sets the image writer for the camera.
     *
     * @param imageWriter The image writer
     * @return The camera object
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Sets the ray tracer for the camera.
     *
     * @param rayTracer The ray tracer
     * @return The camera object
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }


    /**
     * Applies anti-aliasing technique to a given ray by generating additional rays within a pixel and averaging the colors.
     *
     * @param ray The primary ray
     * @param nX  The number of rays to generate horizontally within the pixel
     * @param nY  The number of rays to generate vertically within the pixel
     * @param i   The row index of the pixel
     * @param j   The column index of the pixel
     * @return The averaged color of the rays
     */
    private Color antiAliasing(Ray ray, int nX, int nY, int i, int j) {
        List<Ray> rays = new LinkedList<>();
        rays.add(ray);
        List<Color> colors = antiAliasingHelper(nX, nY, i, j, rays);
        return averageColor(colors);
    }


    /**
     * Performs super-sampling technique by generating additional rays within a pixel and recursively averaging the colors.
     *
     * @param ray              The primary ray
     * @param nX               The number of rays to generate horizontally within the pixel
     * @param nY               The number of rays to generate vertically within the pixel
     * @param i                The row index of the pixel
     * @param j                The column index of the pixel
     * @param recursionDepth   The recursion depth for super-sampling
     * @return The averaged color of the rays
     */
    private Color superSampling(Ray ray, int nX, int nY, int i, int j, int recursionDepth) {
        List<Ray> rays = new LinkedList<>();
        rays.add(ray);
        return averageColor(superSamplingHelper(nX, nY, i, j, rays, recursionDepth));
    }



    /**
     * Helper method for performing anti-aliasing by generating additional rays within a pixel.
     *
     * @param nX   The number of rays to generate horizontally within the pixel
     * @param nY   The number of rays to generate vertically within the pixel
     * @param j    The column index of the pixel
     * @param i    The row index of the pixel
     * @param rays The list of rays to store the generated rays
     * @return The list of colors obtained by casting rays
     */
    private List<Color> antiAliasingHelper(int nX, int nY, int j, int i, List<Ray> rays) {

        List<Color> colors = new LinkedList<Color>();


        double Ry = height / nY;   // SIZE OF THE PIXEL - HEIGHT
        double Rx = width / nX;    // SIZE OF THE PIXEL - WIDTH

        double yI = -(i - ((nY - 1) / 2d)) * Ry;  // CENTER OF THE PIXEL
        double xJ = (j - ((nX - 1) / 2d)) * Rx;   // CENTER OF THE PIXEL
        // TODO: check if the boundaries are available

        double minValueX = -Rx / 2;
        double maxValueX = Rx / 2;

        double minValueY = -Ry / 2;
        double maxValueY = Ry / 2;

        Random randomXY = new Random();
        for (int k = 0; k < 50; k++) {
            double randomX = minValueX + (maxValueX - minValueX) * randomXY.nextDouble();
            double randomY = minValueY + (maxValueY - minValueY) * randomXY.nextDouble();

            Point pIJ = location.add(Vto.scale(distance));

            if (!isZero(xJ + randomX))
                pIJ = pIJ.add(Vright.scale(xJ + randomX));
            if (!isZero(yI + randomY))
                pIJ = pIJ.add(Vup.scale(yI + randomY));

            Ray ray = new Ray(location, pIJ.subtract(location));
            colors.add(castRay(ray));
        }
        return colors;
    }


    /**
     * Calculates the average color from a list of colors.
     *
     * @param colors The list of colors.
     * @return The average color.
     */
    private Color averageColor(List<Color> colors) {
        double red = 0;
        double green = 0;
        double blue = 0;
        for (Color color : colors) {
            red += color.getColor().getRed();
            green += color.getColor().getGreen();
            blue += color.getColor().getBlue();
        }
        red = red / colors.size();
        green = green / colors.size();
        blue = blue / colors.size();

        Color averageColor = new Color(red, green, blue);

        return averageColor;
    }

    /**
     * Constructs a ray from the camera's location to a specific pixel on the view plane.
     *
     * @param nX The number of rows in the resolution.
     * @param nY The number of columns in the resolution.
     * @param j  The column index of the pixel on the view plane.
     * @param i  The row index of the pixel on the view plane.
     * @return The constructed ray.
     */
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

    /**
     * Renders the image by casting rays from the camera to each pixel.
     * Performs anti-aliasing and super-sampling if enabled.
     * Throws MissingResourceException if any required resource is missing.
     */
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
                        superSampling = false;
                        color = antiAliasing(ray, nY, i, j, recursionDepth);
                    }


                    if (superSampling) {
                        antiAliasing = false;
                        color = superSampling(ray, nX, nY, i, j, recursionDepth);
                    }

                    Pixel.pixelDone();
                    Pixel.printPixel();

                    imageWriter.writePixel(i, j, color);

                });

            });
        } else {
            for (int i = 0; i < nX; i++) {
                for (int j = 0; j < nY; j++) {

                    // Cast a ray from the camera to the current pixel and get the color

                    Ray ray = constructRay(nX, nY, i, j);
                    Color color = castRay(ray);

                    if (antiAliasing) {
                        superSampling = false;
                        color = antiAliasing(ray, nY, i, j, recursionDepth);
                    }

                    if (superSampling) {
                        antiAliasing = false;
                        color = superSampling(ray, nX, nY, i, j, recursionDepth);
                    }

                    imageWriter.writePixel(i, j, color);

                }
            }
        }
    }

    /**
     * Prints a grid on the image with the specified interval and color.
     * Throws MissingResourceException if the imageWriter is not set.
     *
     * @param interval The interval between grid lines.
     * @param color    The color of the grid lines.
     */
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

    /**
     * Writes the rendered image to the image file.
     * Throws MissingResourceException if the imageWriter is not set.
     */
    public Color castRay(Ray ray) {
        // Use the ray tracer base to trace the given ray and return the resulting color

        return rayTracer.traceRay(ray);
    }


    /**
     * Helper method for super-sampling that performs the sub-sampling process.
     * Returns a list of colors representing the sampled colors in the sub-pixels.
     *
     * @param nX                    The number of pixels in the X direction
     * @param nY                    The number of pixels in the Y direction
     * @param j                     The current pixel's X coordinate
     * @param i                     The current pixel's Y coordinate
     * @param rays                  The list of rays to be traced for each sub-pixel
     * @param currentRecursionDepth The current recursion depth
     * @return A list of colors representing the sampled colors in the sub-pixels
     */
    private List<Color> superSamplingHelper(int nX, int nY, int j, int i, List<Ray> rays, int currentRecursionDepth) {
        List<Color> colors = new LinkedList<>();

        double Ry = height / nY;   // SIZE OF THE PIXEL - HEIGHT
        double Rx = width / nX;    // SIZE OF THE PIXEL - WIDTH

        double yI = -(i - ((nY - 1) / 2d)) * Ry;  // CENTER OF THE PIXEL
        double xJ = (j - ((nX - 1) / 2d)) * Rx;   // CENTER OF THE PIXEL

        Color pixelColor = getColorAtPixel(xJ, yI, Rx, Ry, currentRecursionDepth);
        if (pixelColor != null) {
            colors.add(pixelColor);
        } else {
            if (currentRecursionDepth < recursionDepth) {
                double halfRx = Rx / 2;
                double halfRy = Ry / 2;

                // Subdivide the pixel into four quadrants
                List<Ray> subRays = new ArrayList<>(rays);
                subRays.add(new Ray(location, Vright.scale(halfRx).add(Vup.scale(halfRy))));
                subRays.add(new Ray(location, Vright.scale(halfRx).subtract(Vup.scale(halfRy))));
                subRays.add(new Ray(location, Vright.scale(-halfRx).add(Vup.scale(halfRy))));
                subRays.add(new Ray(location, Vright.scale(-halfRx).subtract(Vup.scale(halfRy))));

                colors.addAll(superSamplingHelper(nX, nY, j, i, subRays, currentRecursionDepth + 1));
            } else {
                // If recursion depth is 5, return the average color of the edges to each quarter

                List<Color> colorsForAverage = new LinkedList<>();

                Color topLeftColor = castRayAtPixel(xJ, yI);
                Color topRightColor = castRayAtPixel(xJ + Rx, yI);
                Color bottomLeftColor = castRayAtPixel(xJ, yI + Ry);
                Color bottomRightColor = castRayAtPixel(xJ + Rx, yI + Ry);
                colorsForAverage.add(topLeftColor);
                colorsForAverage.add(topRightColor);
                colorsForAverage.add(bottomLeftColor);
                colorsForAverage.add(bottomRightColor);

                colors.add(averageColor(colorsForAverage));
            }
        }

        return colors;
    }

    /**
     * Returns the color at the specified pixel by casting rays to the four corners
     * and comparing their colors. If all four corners have the same color, that color is returned.
     * Otherwise, null is returned to indicate different colors at the corners.
     *
     * @param x              The X coordinate of the pixel's top-left corner
     * @param y              The Y coordinate of the pixel's top-left corner
     * @param width          The width of the pixel
     * @param height         The height of the pixel
     * @param recursionDepth The current recursion depth
     * @return The color at the pixel if all four corners have the same color, otherwise null
     */
    private Color getColorAtPixel(double x, double y, double width, double height, int recursionDepth) {
        Color topLeftColor = castRayAtPixel(x, y);
        Color topRightColor = castRayAtPixel(x + width, y);
        Color bottomLeftColor = castRayAtPixel(x, y + height);
        Color bottomRightColor = castRayAtPixel(x + width, y + height);

        if (topLeftColor.equals(topRightColor) && topLeftColor.equals(bottomLeftColor) && topLeftColor.equals(bottomRightColor)) {
            return topLeftColor; // All four corners have the same color
        }

        // Return null to indicate different colors at the corners
        return null;
    }


    /**
     * Casts a ray at a specific pixel coordinate (x, y) on the view plane.
     *
     * @param x The x-coordinate of the pixel.
     * @param y The y-coordinate of the pixel.
     * @return The color resulting from casting the ray at the pixel.
     */
    private Color castRayAtPixel(double x, double y) {
        Point pIJ = location.add(Vto.scale(distance));

        if (!isZero(x))
            pIJ = pIJ.add(Vright.scale(x));
        if (!isZero(y))
            pIJ = pIJ.add(Vup.scale(y));

        Ray ray = new Ray(location, pIJ.subtract(location));
        return castRay(ray);
    }
}