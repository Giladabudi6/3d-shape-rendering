package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for Sphare Point class
 */
class SphareTest {
    // Test method for GetNormal method
    @Test
    void TestGetNormal() {

        // ============ Equivalence Partitions Tests ==============
        // Test method for GetNormal method
        Point p1 = new Point(0,0,0);
        Point p2 = new Point(0,-2,0);
        Sphare s = new Sphare(p1,2);
        assertEquals(new Vector(0,1,0), s.getNormal(p2));
    }
}