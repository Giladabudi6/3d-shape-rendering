package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
}
