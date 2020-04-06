/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * A linked stack that uses a linked list as the base
 * @author kraig
 *
 */
public class LinkedStack<E> implements Stack<E> {
	/**
	 * field for the linked stack
	 */
	private LinkedAbstractList<E> stack;
	
	/**
	 * creates a new linked stack
	 * @param capacity the number of elements that can be in the stack
	 */
	public LinkedStack(int capacity) {
		stack = new LinkedAbstractList<E>(capacity);
	}

	/**
	 * adds an element to the top of the stack
	 * @param element the item to be added
	 */
	@Override
	public void push(E element) {
		stack.add(element);	
	}

	/**
	 * removes and returns the element on the top of the stack
	 * @return the top item on the stack
	 */
	@Override
	public E pop() {
		if (size() == 0) {
			throw new EmptyStackException();
		}
		return stack.remove(size() - 1);
	}

	/**
	 * returns true if the stack is empty
	 * @return whether the stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	/**
	 * returns the number of elements in the stack
	 * @return the number of elements in the stack
	 */
	@Override
	public int size() {
		return stack.size();
	}

	/**
	 * sets the stack's capacity exception thrown if int is negative or less than capacity
	 * @param capacity the capacity to set to
	 */
	@Override
	public void setCapacity(int capacity) {
		stack.setCapacity(capacity);
	}
}
