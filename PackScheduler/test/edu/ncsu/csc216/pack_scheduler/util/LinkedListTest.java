/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.ListIterator;

import org.junit.Test;

/**
 * @author stephenwelsh
 *
 */
public class LinkedListTest {

	/**
	 * Test the initialization of the ArrayList to make sure that the size returned is 0
	 */
	@Test
	public void testLinkedList() {
		LinkedList<String> arrayList = new LinkedList<String>();
		
		assertEquals(0, arrayList.size());
	}
	
	/**
	 * Test the add method for the array list.
	 * Test adding to the front middle and end and test the size of the array list to grow with
	 * the sizes. Also test index out of bounds exceptions, Illegal argument exceptions, and
	 * Null pointer exceptions 
	 */
	@Test
	public void testLinkedListAdd() {
		LinkedList<String> arrayList = new LinkedList<String>();
		assertEquals(0, arrayList.size());
		
		arrayList.add(0, "bananas");
		
		assertEquals(1, arrayList.size());
		assertEquals("bananas", arrayList.get(0));
		
		arrayList.add(1, "apples");
		arrayList.add(2, "grapes");
		
		assertEquals(3, arrayList.size());
		assertEquals("bananas", arrayList.get(0));
		assertEquals("apples", arrayList.get(1));
		assertEquals("grapes", arrayList.get(2));
		
		arrayList.add(0, "blueberrys");
		
		assertEquals(4, arrayList.size());
		assertEquals("blueberrys", arrayList.get(0));
		assertEquals("bananas", arrayList.get(1));
		assertEquals("apples", arrayList.get(2));
		assertEquals("grapes", arrayList.get(3));
		
		arrayList.add(2, "green apples");
		
		assertEquals(5, arrayList.size());
		assertEquals("blueberrys", arrayList.get(0));
		assertEquals("bananas", arrayList.get(1));
		assertEquals("green apples", arrayList.get(2));
		assertEquals("apples", arrayList.get(3));
		assertEquals("grapes", arrayList.get(4));
		
		
		try {
			arrayList.add(2, null);
			fail();
		} catch(NullPointerException e) {
			assertNull(e.getMessage());
			assertEquals(5, arrayList.size());
		}
		
		try {
			arrayList.add(2, "green apples");
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(e.getMessage());
			assertEquals(5, arrayList.size());
		}
		
		try {
			arrayList.add(-1, "red apples");
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
			assertEquals(5, arrayList.size());
		}
		
		try {
			arrayList.add(7, "red apples");
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
			assertEquals(5, arrayList.size());
		}
	}
	
	/**
	 * Test the remove method for the Array list class. The remove method needs to remove from the 
	 * beginning and end while changing the size of the array and 
	 */
	@Test
	public void testArrayListRemove() {
		LinkedList<String> arrayList = new LinkedList<String>();
		assertEquals(0, arrayList.size());
		arrayList.add(0, "bananas");
		arrayList.add(1, "apples");
		arrayList.add(2, "grapes");
		arrayList.add(3, "blueberrys");
		assertEquals(4, arrayList.size());
		
		arrayList.remove(0);
		assertEquals(3, arrayList.size());
		assertEquals("apples", arrayList.get(0));
		assertEquals("grapes", arrayList.get(1));
		assertEquals("blueberrys", arrayList.get(2));
		
		arrayList.remove(2);
		assertEquals(2, arrayList.size());
		assertEquals("apples", arrayList.get(0));
		assertEquals("grapes", arrayList.get(1));
		
		arrayList.add(0, "bananas");
		arrayList.add(3, "blueberrys");
		
		arrayList.remove(2);
		assertEquals(3, arrayList.size());
		assertEquals("bananas", arrayList.get(0));
		assertEquals("apples", arrayList.get(1));
		assertEquals("blueberrys", arrayList.get(2));
		
		try {
			arrayList.remove(3);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals("Index: 3", e.getMessage());
		}

		try {
			arrayList.remove(-1);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
	}
	
	/**
	 * Test the set method for the Array list class to make sure that the array list is setting the methods 
	 * Appropriately and throws exceptions when methods are null, equals, or index is out of bounds 
	 */
	@Test
	public void testSet() {
		LinkedList<String> arrayList = new LinkedList<String>();
		assertEquals(0, arrayList.size());
		arrayList.add(0, "bananas");
		arrayList.add(1, "apples");
		assertEquals(2, arrayList.size());
		arrayList.add(2, "grapes");
		arrayList.add(3, "blueberrys");
		
		arrayList.set(0, "red apples");
		assertEquals("red apples", arrayList.get(0));
		assertEquals("apples", arrayList.get(1));
		assertEquals("grapes", arrayList.get(2));
		assertEquals("blueberrys", arrayList.get(3));
		
		arrayList.set(3, "green apples");
		assertEquals("red apples", arrayList.get(0));
		assertEquals("apples", arrayList.get(1));
		assertEquals("grapes", arrayList.get(2));
		assertEquals("green apples", arrayList.get(3));
		
		try {
			arrayList.set(2, "green apples");
		} catch(IllegalArgumentException e) {
			assertNull(e.getMessage());
			assertEquals("grapes", arrayList.get(2));
		}
		
		try {
			arrayList.set(2, null);
		} catch(NullPointerException e) {
			assertNull(e.getMessage());
			assertEquals("grapes", arrayList.get(2));
		}
		
		try {
			arrayList.set(-1, "blueberrys");
		} catch(IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
		try {
			arrayList.set(4, "blueberrys");
		} catch(IndexOutOfBoundsException e) {
			assertEquals("Index: 4", e.getMessage());
		}
	}
	
	/**
	 * Test the get method only for the exceptions because it is already thoroughly tested above.
	 */
	@Test
	public void testGet() {
		LinkedList<String> arrayList = new LinkedList<String>();
		assertEquals(0, arrayList.size());
		
		try {
			arrayList.get(-1);
		} catch(IndexOutOfBoundsException e) {
			assertNull(e.getMessage());
		}
		
		try {
			arrayList.get(0);
		} catch(IndexOutOfBoundsException e) {
			assertEquals("Index: 0", e.getMessage());
		}
	}
	
	@Test
	public void testIterator() {
		LinkedList<String> arrayList = new LinkedList<String>();
		assertEquals(0, arrayList.size());
		arrayList.add(0, "bananas");
		arrayList.add(1, "apples");
		assertEquals(2, arrayList.size());
		arrayList.add(2, "grapes");
		arrayList.add(3, "blueberrys");
		ListIterator<String> iterator = arrayList.listIterator(2);
		
		assertEquals("apples", iterator.previous());
	}

}
