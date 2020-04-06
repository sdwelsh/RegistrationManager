/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
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

	@Override
	public void push(E element) {
		stack.add(element);	
	}

	@Override
	public E pop() {
		if (size() == 0) {
			throw new EmptyStackException();
		}
		return stack.remove(size() - 1);
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public void setCapacity(int capacity) {
		stack.setCapacity(capacity);
	}
}
