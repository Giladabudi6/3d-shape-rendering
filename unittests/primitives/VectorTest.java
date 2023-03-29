package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    @Test
    void testVector() {
        // ============ Equivalence Partitions Tests ==============

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, () -> {
            new Vector(0,0,0);
        });

        Double3 D1 = new Double3 (0,0,0);
        assertThrows(IllegalArgumentException.class, () -> {
            new Vector(D1);
        });
    }

    @Test
    void TestAdd() {
        // ============ Equivalence Partitions Tests ==============
        // adding vector u to v
        Vector v3 = new Vector(1,2,3);
        Vector v4 = new Vector(0,1,1);
        assertEquals(new Vector (1,3,4),v3.add(v4));

        // =============== Boundary Values Tests ==================
        // adding vector -u to u
        Vector v1 = new Vector(1,1,1);
        Vector v2 = new Vector(-1,-1,-1);
        assertThrows(IllegalArgumentException.class, () -> {
                    v1.add(v2);
        });

    }

    @Test
    void TestScale() {
        // ============ Equivalence Partitions Tests ==============
        // Multiplier vector by scale
        Vector v1 = new Vector(1,2,2);
        assertEquals(new Vector (2.3,4.6,4.6),v1.scale(2.3));

        // =============== Boundary Values Tests ==================
        // Multiplier vector by zero
        Vector v2 = new Vector(1,2,2);
        assertThrows(IllegalArgumentException.class, () -> {
            v1.scale(0);
        });
        }

    @Test
    void TestDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        //scalar product between u and v
        Vector v1 = new Vector(1,1,0);
        Vector v2 = new Vector(2,1,1);
        assertEquals(3,v1.dotProduct(v2));

        // =============== Boundary Values Tests ==================
        //scalar product between u and v - orthogonal
        Vector v3 = new Vector(1,1,1);
        Vector v4 = new Vector(1,0,-1);
        assertEquals(0,v3.dotProduct(v4));

    }

    @Test
    void TestCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        //CrossProduct between u and v
        Vector v1 = new Vector(1,1,0);
        Vector v2 = new Vector(2,1,1);
        assertEquals(new Vector(1,-1,-1),v1.crossProduct(v2));

        // =============== Boundary Values Tests ==================
        //CrossProduct between u and v - parallel (מקבילים)
        Vector v3 = new Vector(1,2,3);
        Vector v4 = new Vector(1,2,3);
        assertThrows(IllegalArgumentException.class,() -> {
            v3.crossProduct(v4);
        });
    }

    @Test
    void TestLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        //LengthSquared of vector
        Vector v1 = new Vector(1,1,0);
        assertEquals(2,v1.lengthSquared());
    }

    @Test
    void TestLength() {
        // ============ Equivalence Partitions Tests ==============
        //Length of vector
        Vector v1 = new Vector(1,2,2);
        assertEquals(3,v1.length());
    }

    @Test
    void TestNormalize() {
        // ============ Equivalence Partitions Tests ==============
        //Normalize vector - length = 1
        Vector v1 = new Vector(1,2,2);
        assertEquals(1,v1.normalize().length());
    }
}