/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * Test the linked stack class
 * @author kraig
 *
 */
public class LinkedStackTest {

	/**
	 * Test method for array stack class
	 */
	@Test
	public void testLinkedStack() {
		//make new array stack
		LinkedStack<Integer> stack = new LinkedStack<Integer>(10);
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());
		//push one element
		stack.push(1);
		assertEquals(1, stack.size());
		assertFalse(stack.isEmpty());
		//check with pop
		int pop = stack.pop();
		assertEquals(1, pop);
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());
		//add element back and two more
		stack.push(1);
		stack.push(2);
		stack.push(3);
		//check pops
		assertEquals(3, stack.size());
		pop = stack.pop();
		assertEquals(3, pop);
		assertEquals(2, stack.size());
		pop = stack.pop();
//		assertEquals(2, pop);
		assertEquals(1, stack.size());
		pop = stack.pop();
		assertEquals(1, pop);
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());
		//check exception for push
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
		stack.push(9);
		stack.push(10);
		assertEquals(10, stack.size());
		try {
			stack.push(11);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(10, stack.size());
		}
		//check capacity exception
		try { 
			stack = new LinkedStack<Integer>(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(10, stack.size());
		}
		try {
			stack.setCapacity(-1);
		} catch (IllegalArgumentException e) {
			//skip
		}
		try {
			stack.setCapacity(7);
		} catch (IllegalArgumentException e) {
			//skip
		}
		//reset stack and test pop at size = 0
		stack = new LinkedStack<Integer>(5);
		try {
			stack.pop();
			fail();
		} catch (EmptyStackException e) {
			assertEquals(0, stack.size());
		}
	}
}
