package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

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

    @Test
    void TestScale() {
    }

    @Test
    void TestDotProduct() {
    }

    @Test
    void TestCrossProduct() {
    }

    @Test
    void TestLengthSquared() {
    }

    @Test
    void TestLength() {
    }

    @Test
    void TestNormalize() {
    }
}