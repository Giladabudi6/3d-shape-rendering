package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for Sphare Point class
 * @author Yossi Cohen
 */
class SphareTest {
    // Test method for GetNormal method
    @Test
    void TestGetNormal() {
        //TODO check what is sphare

        // ============ Equivalence Partitions Tests ==============
        // Test method for GetNormal method
        Point p1 = new Point(0,0,0);
        Point p2 = new Point(-1,-1,-1);
        Sphare s = new Sphare(p1,1);
        assertEquals(new Vector(1,1,1), s.getNormal(p2));
    }
}