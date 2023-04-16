package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for Tube class
 */

class TubeTest {

    // Test method for GetNormal method
    @Test
    void TestGetNormal() {

        // Test method for GetNormal method

        Point p1 = new Point(0,1,0);
        Vector v1 = new Vector(0,0,1);
        Ray r = new Ray(p1,v1);
        Tube t = new Tube(r,2);

        Point p2 = new Point(0,2,1);  // point on the tube
        Point p3 = new Point(0,2,0);  // point on the tube in front of the Ray


        // ============ Equivalence Partitions Tests ==============
        // normal on point on the tube

        assertEquals(new Vector(0,2,0).normalize(), t.getNormal(p2));

        // =============== Boundary Values Tests ==================
        // normal on point on the tube - in front of the Ray

        assertEquals(new Vector(0,2,0).normalize(), t.getNormal(p3));


    }
}