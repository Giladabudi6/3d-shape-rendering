package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for Sphare Point class
 */

class TriangleTest {
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // Test method for GetNormal method

        Point p1 = new Point (1,0,0);
        Point p2 = new Point (0,1,0);
        Point p3 = new Point (0,0,1);

        Plane pla = new Plane(p1,p2,p3);
        assertEquals(new Vector(1,1,1).normalize(), pla.getNormal(p1));
    }
    @Test
    void testFindIntersections() {
        Triangle triangle = new Triangle(new Point(0, 1, 0), new Point(0, 5, 0), new Point(0, 3, 5));

        // ============ Equivalence Partitions Tests ==============
        // TC01: The intersection point is in the triangle
        assertEquals(List.of(new Point(0, 3, 1)),
                triangle.findIntersections(new Ray(new Point(1, 3, 0), new Vector(-1, 0, 1))),
                "The intersection point is in the triangle");

        // TC02: The intersection point is outside the triangle, against edge
        assertNull(triangle.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-1, 0, 1))),
                "The intersection point supposed to be outside the triangle, against edge");

        // TC03: The intersection point is outside the triangle, against vertex
        assertNull(triangle.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-1, 0.1, -0.1))),
                "The intersection point supposed to be outside the triangle, against vertex");

        // =============== Boundary Values Tests ==================
        // TC10: The point is on edge
        assertNull(triangle.findIntersections(new Ray(new Point(1, 3, 0), new Vector(-1, 0, 0))),
                "The intersection point supposed to be on edge");

        // TC11: The point is in vertex
        assertNull(triangle.findIntersections(new Ray(new Point(1, 1, 0), new Vector(-1, 0, 0))),
                "The intersection point supposed to be in vertex");

        // TC12: The point is on edge's continuation
        assertNull(triangle.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-1, 0.1, 0))),
                "The intersection point supposed to be on edge's continuation");
    }
}
