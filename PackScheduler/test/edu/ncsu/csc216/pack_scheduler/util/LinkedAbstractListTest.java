package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test cases for LinkedAbstracktList
 * 
 * @author Jeff Li
 * 
 */
public class LinkedAbstractListTest {

	/**
     * Test Constructor
     */
    @Test
    public void testLinkedAbstractList() {
        // valid capacity
        LinkedAbstractList<String> a1 = new LinkedAbstractList<String>(0);
        assertNotNull(a1);
        assertEquals(0, a1.size());

        LinkedAbstractList<String> a2 = new LinkedAbstractList<String>(2);
        assertNotNull(a2);
        assertEquals(0, a2.size());

        // invalid capacity
        LinkedAbstractList<String> a3 = null;
        try {
            a2 = new LinkedAbstractList<String>(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(a3);
        }
    }

    /**
     * test setCapacity
     */
    public void testSetCapacity() {
    	LinkedAbstractList<String> a = new LinkedAbstractList<String>(5);
    	
    	try {
    		a.setCapacity(-3);
    		fail();
    	} catch(IllegalArgumentException e) {
    		assertEquals(5, a.size());
    	}
    }
    
    /**
     * Test get()
     */
    @Test
    public void testGet() {
        LinkedAbstractList<String> a = new LinkedAbstractList<String>(5);
        a.add(0, "a");
        assertEquals("a", a.get(0));

        a.add(1, "b");
        assertEquals("a", a.get(0));
        assertEquals("b", a.get(1));

        a.add(1, "c");
        assertEquals("a", a.get(0));
        assertEquals("c", a.get(1));
        assertEquals("b", a.get(2));
    }
    
    /**
     * Tests set()
     */
    @Test
    public void testSet() {
        LinkedAbstractList<String> a = new LinkedAbstractList<String>(5);
        // valid tests
        a.add(0, "a");
        a.add(1, "b");
        a.add(1, "c");
        assertEquals("a", a.get(0));
        assertEquals("c", a.get(1));
        assertEquals("b", a.get(2));

        String tmp = a.set(1, "d");
        assertEquals("c", tmp);
        assertEquals("d", a.get(1));
        assertEquals(3, a.size());

        // invalid tests
        try {
            a.set(3, "d");
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index out of bounds", e.getMessage());
        }
        try {
            a.set(-1, "d");
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index out of bounds", e.getMessage());
        }
        try {
            a.set(5, null);
            fail();
        } catch (NullPointerException e) {
            assertEquals("Element added is null", e.getMessage());
        }
        try {
            a.set(2, "a");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Duplicate value", e.getMessage());
        }
    }
    
    /**
     * Tests add()
     */
    @Test
    public void testAdd() {
        LinkedAbstractList<String> a = new LinkedAbstractList<String>(5);
        // valid tests
        a.add(0, "a");
        assertEquals("a", a.get(0));

        a.add(1, "b");
        assertEquals("a", a.get(0));
        assertEquals("b", a.get(1));

        a.add(1, "c");
        assertEquals("a", a.get(0));
        assertEquals("c", a.get(1));
        assertEquals("b", a.get(2));
       
        a.add(0, "z");
        assertEquals("z", a.get(0));
        assertEquals("a", a.get(1));
        assertEquals("c", a.get(2));
        assertEquals("b", a.get(3));

        // invalid tests
        try {
            a.add(20, "d");
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index out of bounds", e.getMessage());
        }
        try {
            a.add(-1, "d");
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index out of bounds", e.getMessage());
        }
        try {
            a.add(5, null);
            fail();
        } catch (NullPointerException e) {
            assertEquals("Element added is null", e.getMessage());
        }
        try {
            a.add(2, "a");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Duplicate value", e.getMessage());
        }

        // exceed assigned capacity
        a.add(4, "e");
        assertEquals("z", a.get(0));
        assertEquals("a", a.get(1));
        assertEquals("c", a.get(2));
        assertEquals("b", a.get(3));
        assertEquals("e", a.get(4));

        try {
            a.add(5, "f");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Capacity reached", e.getMessage());
        }
    }
    
    /**
     * Tests remove()
     */
    @Test
    public void testRemove() {
        LinkedAbstractList<String> a = new LinkedAbstractList<String>(5);
        a.add(0, "a");
        a.add(1, "b");
        String test = a.remove(0);
        assertEquals("a", test);
        assertEquals("b", a.get(0));
        // check size
        assertEquals(1, a.size());
        
        test = a.remove(a.size() - 1);
        assertEquals("b", test);

        try {
            test = a.remove(-1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index out of bounds", e.getMessage());
        }

        try {
            test = a.remove(1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index out of bounds", e.getMessage());
        }
    }

}
