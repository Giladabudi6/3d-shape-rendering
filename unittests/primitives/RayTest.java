package primitives;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {
    // Test method for findClosestPoint method
    @Test
    void TestFindClosestPoint() {
        Ray ray = new Ray(new Point(0,0,0),new Vector(1,1,0));
        List<Point> points1 = new LinkedList<>();
        points1.add(new Point(10,0,0));
        points1.add(new Point(1,0,0));
        points1.add(new Point(5,0,0));

        List<Point> points2 = new LinkedList<>();

        List<Point> points3 = new LinkedList<>();
        points3.add(new Point(1,0,0));
        points3.add(new Point(10,0,0));
        points3.add(new Point(5,0,0));

        List<Point> points4 = new LinkedList<>();
        points4.add(new Point(5,0,0));
        points4.add(new Point(10,0,0));
        points4.add(new Point(1,0,0));


        // ============ Equivalence Partitions Tests ==============
        // The closest point is in middle of the list
        assertEquals(points1.get(1), ray.findClosestPoint(points1),"closest point is wrong");

        // =============== Boundary Values Tests ==================
        // The point list is empty
        assertNull(ray.findClosestPoint(points2),"Wrong! The list is empty");

        // The closest point is the first in the list
        assertEquals(points3.get(0), ray.findClosestPoint(points3),"closest point is wrong");

        // The closest point is last in the list
        assertEquals(points4.get(2), ray.findClosestPoint(points4),"closest point is wrong");
    }
}