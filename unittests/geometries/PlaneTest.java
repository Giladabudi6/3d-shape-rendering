package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.Double3;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for Plane class
 */

class PlaneTest {

    // Test method for  constractor of Vector
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

        Point p1 = new Point (1,0,0);
        Point p2 = new Point (0,1,0);
        Point p3 = new Point (0,0,1);

        Plane pla = new Plane(p1,p2,p3);
        assertEquals(new Vector (1,1,1).normalize(), pla.getNormal(p1));
    }
}