package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for Point class
 */
class PointTest {
    // Test method for subtract method
    @Test
    void TestSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // subtracting point u from point v and create a new vector

        Point p1 = new Point(1, 2, 3);
        Vector v1 = new Vector(1, 1, 1);
        assertEquals(new Vector(0, 1, 2), p1.subtract(v1));

        // =============== Boundary Values Tests ==================
        // subtracting point u from point u and create a new vector???
        Point p2 = new Point(1, -1, 3);
        Vector v2 = new Vector(1, -1, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            p2.subtract(v2);
        });
    }


    // Test method for Add method
    @Test
    void TestAdd() {
        // ============ Equivalence Partitions Tests ==============
        // adding point u to vector v
        Point p1 = new Point(1, 1, 1);
        Vector v1 = new Vector(1, 2, 3);
        assertEquals(new Vector(2, 3, 4), p1.add(v1));
    }


    // Test method for DistanceSquared method
    @Test
    void TestDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // Calculate the distance squered between point u and v
        Point p1 = new Point(1, 1, 1);
        Point p2 = new Point(1, 2, 3);
        assertEquals(5, p1.distanceSquared(p2));
    }


    // Test method for TestDistance method
    @Test
    void TestDistance() {
        // ============ Equivalence Partitions Tests ==============
        // Calculate the distance between point u and v
        Point p1 = new Point(1, 1, 1);
        Point p2 = new Point(1, 2, 3);
        assertEquals(Math.sqrt(5), p1.distance(p2));
    }
}