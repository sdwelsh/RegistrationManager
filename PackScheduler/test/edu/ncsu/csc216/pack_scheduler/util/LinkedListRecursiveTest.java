/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author kraig
 *
 */
public class LinkedListRecursiveTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#LinkedListRecursive()}.
	 */
	@Test
	public void testLinkedListRecursive() {
		LinkedListRecursive<Integer> test = new LinkedListRecursive<Integer>();
		assertEquals(0, test.size());
		assertTrue(test.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		LinkedListRecursive<Integer> test = new LinkedListRecursive<Integer>();
		assertTrue(test.isEmpty());
		test.add(1);
		assertFalse(test.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#size()}.
	 */
	@Test
	public void testSize() {
		LinkedListRecursive<Integer> test = new LinkedListRecursive<Integer>();
		assertEquals(0, test.size());
		test.add(1);
		assertEquals(1, test.size());
		test.add(2);
		assertEquals(2, test.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#add(java.lang.Object)}.
	 */
	@Test
	public void testAddE() {
		LinkedListRecursive<Integer> test = new LinkedListRecursive<Integer>();
		assertEquals(test.size(), 0);
		test.add(0, 1);
		assertEquals(test.size(), 1);
		test.add(1, 2);
		assertEquals(test.size(), 2);
		test.add(2, 3);
		assertEquals(test.size(), 3);
		test.add(4);
		assertEquals(test.size(), 4);
		assertTrue(test.contains(4));
		assertEquals(1, (int) test.get(0));
		assertEquals(2, (int) test.get(1));
		assertEquals(3, (int) test.get(2));
		assertEquals(4, (int) test.get(3));
		assertEquals(4, (int) test.set(3, 5));
		assertEquals(5, (int) test.get(3));
		assertFalse(test.isEmpty());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveE() {
		LinkedListRecursive<Integer> test = new LinkedListRecursive<Integer>();
		assertEquals(test.size(), 0);
		test.add(0, 1);
		assertEquals(test.size(), 1);
		test.add(1, 2);
		assertEquals(test.size(), 2);
		test.add(2, 3);
		assertEquals(test.size(), 3);
		test.add(4);
		assertEquals(3, (int) test.remove(2));
		assertEquals(1, (int) test.get(0));
		assertEquals(2, (int) test.get(1));
		assertEquals(4, (int) test.get(2));
		
		
		LinkedListRecursive<String> test2 = new LinkedListRecursive<String>();
		test2.add(0, "this");
		test2.add(1, "is");
		test2.add(2, "a");
		test2.add(3, "test");
		assertEquals(true, test2.remove("is"));
		assertEquals("this", test2.get(0));
		assertEquals("a", test2.get(1));
		assertEquals("test", test2.get(2));
	}
}
