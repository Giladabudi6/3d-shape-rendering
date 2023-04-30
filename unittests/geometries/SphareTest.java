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
class SphareTest {

    // Test method for GetNormal method
    @Test
    void TestGetNormal() {

        // ============ Equivalence Partitions Tests ==============
        // Test method for GetNormal method
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(0, -2, 0);
        Sphare s = new Sphare(p1, 2);
        assertEquals(new Vector(0, 1, 0), s.getNormal(p2));
    }
        // Test for findIntsersections method
        Point p1 = new Point(1,0,0);
        double radius = 1;
        Sphare sphare = new Sphare(p1 , radius);
    @Test
    void TestIntsersections() {

        // ============ Equivalence Partitions Tests ==============

        // Test method for findIntsersections method

        Point p2 = new Point(2,0,0);
        Vector v2 = new Vector(-3.5,0,1);
        Ray ray = new Ray (p2 , v2);
        Point p3 = new Point(0.0651530772,0,0.3550510257);
        Point p4 = new Point(1.5348469228,0,0.8449489743);

        List<Point> expectedPoints;
        assertEquals(expectedPoints, sphare.findIntsersections(ray));


        Point p2 = new Point(2,0,0);
        Vector v2 = new Vector(-3.5,0,1);
        Ray ray = new Ray (p2 , v2);
        List<Point> expectedPoints = ();
        assertEquals(expectedPoints, sphare.findIntsersections(ray));


        Point p2 = new Point(2,0,0);
        Vector v2 = new Vector(-3.5,0,1);
        Ray ray = new Ray (p2 , v2);
        List<Point> expectedPoints = ();
        assertEquals(expectedPoints, sphare.findIntsersections(ray));


        Point p2 = new Point(2,0,0);
        Vector v2 = new Vector(-3.5,0,1);
        Ray ray = new Ray (p2 , v2);
        List<Point> expectedPoints = ();
        assertEquals(expectedPoints, sphare.findIntsersections(ray));

    }
}

