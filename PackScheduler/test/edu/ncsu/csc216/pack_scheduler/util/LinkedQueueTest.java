/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * @author stephenwelsh
 *
 */
public class LinkedQueueTest {

	/**
	 * Test queue constructor
	 */
	@Test
	public void testLinkedQueue() {
		LinkedQueue<String> queue = new LinkedQueue<String>(10);
		assertEquals(0, queue.size());
		assertTrue(queue.isEmpty());
	}

	/**
	 * Test the Enqueue method
	 */
	@Test
	public void testEnqueue() {
		LinkedQueue<String> queue = new LinkedQueue<String>(10);
		assertEquals(0, queue.size());
		assertTrue(queue.isEmpty());
		
		queue.enqueue("a");
		assertEquals(1, queue.size());
		assertFalse(queue.isEmpty());
		
		queue.enqueue("b");
		queue.enqueue("c");
		queue.enqueue("d");
		assertEquals(4, queue.size());
		assertFalse(queue.isEmpty());
		
		queue.setCapacity(5);
		
		queue.enqueue("f");
		
		try {
			queue.enqueue("e");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Capacity reached", e.getMessage());
		}
		
		try {
			queue.setCapacity(2);
			fail();
		} catch(IllegalArgumentException e) {
			assertNull(e.getMessage());
		}
		
		
	}

	/**
	 * Test Dequeue method
	 */
	@Test
	public void testDequeue() {
		LinkedQueue<String> queue = new LinkedQueue<String>(10);
		assertEquals(0, queue.size());
		assertTrue(queue.isEmpty());
		
		queue.enqueue("a");
		assertEquals(1, queue.size());
		assertFalse(queue.isEmpty());
		
		queue.enqueue("b");
		queue.enqueue("c");
		queue.enqueue("d");
		assertEquals(4, queue.size());
		assertFalse(queue.isEmpty());
		
		assertEquals("a", queue.dequeue());
		assertEquals(3, queue.size());
		
		assertEquals("b", queue.dequeue());
		assertEquals("c", queue.dequeue());
		assertEquals("d", queue.dequeue());
		assertEquals(0, queue.size());
		
		try {
			queue.dequeue();
			fail();
			
		} catch(NoSuchElementException e) {
			assertNull(e.getMessage());
		}
	}

}
