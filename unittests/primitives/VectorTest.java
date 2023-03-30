package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for Vector class
 */
class VectorTest {

    // Test method for Add method
    @Test
    void TestAdd() {
        // =============== Boundary Values Tests ==================
        // adding vector -u to u
        Vector v1 = new Vector(1,1,1);
        Vector v2 = new Vector(-1,-1,-1);
        assertThrows(IllegalArgumentException.class, () -> {
                    v1.add(v2);
        });
        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(1,2,3);
        Vector v4 = new Vector(0,1,1);
        assertEquals(new Vector (1,3,4),v3.add(v4));
    }

    // Test method for Scale method
    @Test
    void TestScale() {
    }

    // Test method for DotProduct method
    @Test
    void TestDotProduct() {
    }

    // Test method for CrossProduct method
    @Test
    void TestCrossProduct() {
    }

    // Test method for LengthSquared method
    @Test
    void TestLengthSquared() {
    }

    // Test method for Length method
    @Test
    void TestLength() {
    }

    // Test method for Normalize method
    @Test
    void TestNormalize() {
    }
}