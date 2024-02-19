/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(4);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        assertThrows(IllegalArgumentException.class, () -> setA.add(99), "add: adding duplicate element did not throw exception.");
        assertEquals(1, setA.size(), "add: duplicate element added.");

        assertThrows(IllegalArgumentException.class, () -> setA.add(-99), "add: adding negative element did not throw exception.");
        assertEquals(1, setA.size(), "add: negative element added.");

        setA.add(100);
        setA.add(101);
        assertEquals(3, setA.size(), "add: adding 2 more elements did not work.");

        assertThrows(IllegalArgumentException.class, () -> setB.add(25), "add: adding element to full set did not throw exception.");
        assertFalse(setB.contains(25), "add: element added to full set.");
        assertEquals(6, setB.size(), "add: element added to full set.");
    }

    @Test
    public void testAddFromBadArray() {
        // tests adding numbers that are not natural
        int[] elems = new int[]{10, -20, -30};
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));

        // tests adding duplicate numbers
        int[] elems2 = new int[]{2, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems2));

        // tests adding more elements than the set can hold (in this case, 4 or more)
        // we already added 2 elements in the asserts above (10 and 2)
        int[] elems3 = new int[]{4, 5, 6, 7};
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems3));
    }

    @Test
    public void testIntersection() {
        BoundedSetOfNaturals setD = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});

        // test sets that do not intersect
        assertFalse(setA.intersects(setB), "intersects: empty set intersects with non-empty set.");

        // test sets that intersect
        assertTrue(setB.intersects(setC), "intersects: non-empty set does not intersect with non-empty set.");

        // test sets that are equal/themselves
        assertTrue(setB.intersects(setD), "intersects: non-empty set does not intersect with non-empty set.");
        assertTrue(setB.intersects(setB), "intersects: non-empty set does not intersect with itself.");
    }
}
