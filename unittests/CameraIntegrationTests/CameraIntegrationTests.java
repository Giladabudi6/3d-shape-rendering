package CameraIntegrationTests;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//  Testing CameraIntegrationTests Class
public class CameraIntegrationTests {

/*
* method that receives as parameters two numbers representing the asked resolution and a shape.
* the method checks if there are any
* intersection between the ray starting from the camera and any geometry shape
* if there are intersections we will save the intersection points in the list. we will call this method in any of
* the following tests
*/
    private int countIntersectionsCameraGeometry(Camera camera, int Nx, int Ny, Intersectable geometry){
        int count = 0;
        List<Point> intersections;

        for (int i = 0; i < Nx; i++) {
            for (int j = 0; j < Ny; j++) {
                intersections = geometry.findIntersections(camera.constructRay(Nx, Ny, j, i));
                count +=  intersections.size();
            }
        }
        return count; //Return the number of points of intersection between the geometries and a ray from the camera
    }

    Point zeroPoint = new Point(0,0,0);
    @Test
    // Test methods for integration between Camera and sphere
    void testCameraSphereIntegration() {

        Camera camera1 = new Camera(zeroPoint, new Vector(0, 0, 1), new Vector(0, -1, 0));
        camera1.setVPSize(3,3);
        camera1.setVPDistance(1);

        Camera camera2 = new Camera(zeroPoint, new Vector(0, 0, -0.5), new Vector(0, -1, 0));
        camera2.setVPSize(3,3);
        camera2.setVPDistance(1);


        //TC01: Sphere r=1 (2 intersections)
        assertEquals(2,countIntersectionsCameraGeometry(camera1,3,3,new Sphere(1,new Point(0,0,-3))),"Wrong number of intersections");

        //TC02: Sphere r=2.5 (18 intersections)
        assertEquals(18,countIntersectionsCameraGeometry(camera2,3,3,new Sphere(2.5,new Point(0,0,-2.5))),"Wrong number of intersections");

        //TC03: Sphere r=2 (10 intersections)
        assertEquals(10,countIntersectionsCameraGeometry(camera2,3,3,new Sphere(2,new Point(0,0,-2))),"Wrong number of intersections");

        //TC04: Sphere r=4 (9 intersections)
        //TODO: find the correct point
        assertEquals(9,countIntersectionsCameraGeometry(camera2,3,3,new Sphere(4,new Point(0,0,0))),"Wrong number of intersections");

        //TC05: Sphere r=0.5 (0 intersections)
        assertEquals(0,countIntersectionsCameraGeometry(camera1,3,3,new Sphere(0.5,new Point(0,0,1))),"Wrong number of intersections");

    }


    @Test
    // Test methods for integration between Camera and Plane
    void testCameraPlaneIntegration() {

        Camera camera1 = new Camera(zeroPoint, new Vector(0, 0, 1), new Vector(0, -1, 0));
        camera1.setVPSize(3,3);
        camera1.setVPDistance(1);


        //TC06: Plane normal=(0,-1,0) (9 intersections)
        assertEquals(9,countIntersectionsCameraGeometry(camera1,3,3,new Plane(new Point(0,-2,0),new Vector(0,-1,0))),"Wrong number of intersections");

        //TC07: Plane normal=(0,-1,0.5) (9 intersections)
        assertEquals(9,countIntersectionsCameraGeometry(camera1,3,3,new Plane(new Point(0,-2,0),new Vector(0,-1,0.5))),"Wrong number of intersections");

        //TC08: Plane normal=(0,-1,1) (6 intersections)
        assertEquals(6,countIntersectionsCameraGeometry(camera1,3,3,new Plane(new Point(0,-2,0),new Vector(0,-1,0.5))),"Wrong number of intersections");

    }


    @Test
        // Test methods for integration between Camera and Triangle
    void testCameraTriangleIntegration() {

        Camera camera1 = new Camera(zeroPoint, new Vector(0, 0, 1), new Vector(0, -1, 0));
        camera1.setVPSize(3,3);
        camera1.setVPDistance(1);


        //TC09: Triangle (1 intersections)
        assertEquals(1,countIntersectionsCameraGeometry(camera1,3,3,new Triangle(new Point(0,1,-2),new Point(1,-1,-2),new Point(-1,-1,-2))),"Wrong number of intersections");

        //TC10: Triangle (2 intersections)
        assertEquals(2,countIntersectionsCameraGeometry(camera1,3,3,new Triangle(new Point(0,20,-2),new Point(1,-1,-2),new Point(-1,-1,-2))),"Wrong number of intersections");

    }


}

