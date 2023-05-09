package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GeometriesTest {
    // Test method for findIntersections method

    @Test
    void testFindIntersections() {
        Plane plane = new Plane(new Point(0, 7, 0), new Vector(0, 1, 0));
        Triangle triangle = new Triangle(new Point(1, 0, 0), new Point(-1, 0, 0), new Point(0, 0, 2));
        Sphere sphere = new Sphere(2, new Point(0, 3, 0));

        //Polygon polygon = new Polygon(new Point(2,3,0),new Point(0,3,0),new Point(0,3,2));
        //Tube tube = new Tube(new Ray(new Point(0,-3,0),new Vector (0,0,1)),1);
        //Cylinder cylinder = new Cylinder(new Ray(new Point(0,-3,0),new Vector (0,0,1)),1, 6);

        Geometries emptyGeometries = new Geometries();   // empty collection of shapes
        Geometries geometries = new Geometries(plane, triangle, sphere);   // collection of shapes - plane, triangle, sphere


        // ============ Equivalence Partitions Tests ==============
        // TC01:  Empty shapes collection (0 points)
        assertNull(emptyGeometries.findIntersections(new Ray(new Point(0, 0, 1), new Vector(0, 1, 0))), "There is zero intersections");

        // TC02:  The ray doesn't intersect the shapes (0 points)
        assertNull(geometries.findIntersections(new Ray(new Point(0, 10, 1), new Vector(0, 1, 0))), "There is zero intersections between the Ray and the shapes");


        // TC03:  The ray intersect one shape (1 point)
        assertEquals(1, geometries.findIntersections(new Ray(new Point(0, 6, 1), new Vector(0, 1, 0))).size(), "There is one intersections between the Ray and the shapes");


        // TC04:  The ray intersect all the shapes (4 points)
        assertEquals(4, geometries.findIntersections(new Ray(new Point(0, -1, 1), new Vector(0, 1, 0))).size(), "There is four intersections between the Ray and the shapes");


        // ============================ Boundary Values Tests ========================//

        // TC05  The ray intersect part of the shapes (3 points)
        assertEquals(3, geometries.findIntersections(new Ray(new Point(0, 1, 1), new Vector(0, 1, 0))).size(), "There is three intersections between the Ray and the shapes");


    }


}