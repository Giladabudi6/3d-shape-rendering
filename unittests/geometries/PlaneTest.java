package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Plane class
 */

class PlaneTest {

    // Test method for  constructor of Vector
    @Test
    void testVector() {
        // ============ Equivalence Partitions Tests ==============

        // =============== Boundary Values Tests ==================
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);

        Point p4 = new Point(2, 0, 0);
        Point p5 = new Point(3, 0, 0);

        // contractor who get 2 point in same line
        assertThrows(IllegalArgumentException.class, () -> {
            new Plane(p1, p2, p3);
        });

        // contractor who get points in same line
        assertThrows(IllegalArgumentException.class, () -> {
            new Plane(p1, p4, p5);
        });
    }

    // Test method for GetNormal method
    @Test
    void TestGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // Test method for GetNormal method

        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(0, 0, 1);

        Plane pla = new Plane(p1, p2, p3);
        assertEquals(new Vector(1, 1, 1).normalize(), pla.getNormal(p1));
    }

    @Test
    void TestFindIntersections() {
        Plane plane = new Plane(new Point(0, 0, 1), new Vector(0, 0, 1));
        // ================ EP: The Ray must be neither orthogonal nor parallel to the plane ==================

        //TC01: Ray intersects the plane
        assertEquals(List.of(new Point(1, 1, 1)), plane.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 1, 1))), "there is one intersection between the Plane and Ray");

        // TC02: Ray does not intersect the plane
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 0), new Vector(-1, -1, -1))), "there is zero intersections between the Plane and Ray");

        // ============================ Boundary Values Tests ========================//
        //Ray is parallel to the plane:

        //TC03: Ray does not intersect the plane (Ray is contained in the Plane)
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 1), new Vector(1, 0, 0))), "there is zero intersections between the Plane and Ray");

        //TC04: Ray does not intersect the plane
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0))), "there is zero intersections between the Plane and Ray");

        //Ray is orthogonal to the plane:

        //TC05: The Ray starts under the plane (1 intersection point)
        assertEquals(List.of(new Point(0, 0, 1)), plane.findIntersections(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1))), "there is one intersection between the Plane and Ray");

        //TC06: The Ray starts on the plane (0 intersection points)
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 1), new Vector(0, 0, 1))), "there is zero intersections between the Plane and Ray");

        //TC07: The Ray starts above the plane (0 intersection point)
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 2), new Vector(0, 0, 1))), "there is zero intersections between the Plane and Ray");


        //Ray is neither orthogonal nor parallel to and begins at the plane:
        //TC08: p0 is in the plane, but not the ray (0 intersection point)
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1))), "there is zero intersections between the Plane and Ray");


        //Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane (Q):
        //TC09: p0 is in the plane, but not the ray (0 intersection point)
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 1), new Vector(1, 1, 1))), "there is zero intersections between the Plane and Ray");
    }
}