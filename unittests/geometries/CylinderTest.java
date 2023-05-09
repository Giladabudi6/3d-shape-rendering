package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for Cylinder class
 */
class CylinderTest {
    // Test method for GetNormal method
    @Test
    void TestGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // Test method for GetNormal method

        Point p1 = new Point(0, 0, 0);
        Vector v1 = new Vector(0, 0, 1);
        Ray r = new Ray(p1, v1);
        Tube t = new Tube(r, 1);

        Point p2 = new Point(0, 1, 0);  // point on the cylinder

        assertEquals(new Vector(0, 1, 0), t.getNormal(p2));
    }
}