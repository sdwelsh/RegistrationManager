package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * makes a stack out of an array list
 * @author kraig
 *
 * @param <E> the element the stack handles
 */
public class ArrayStack<E> implements Stack<E> {
	/**
	 * field for the array list
	 */
	private ArrayList<E> stack;
	/**
	 * the capacity of the stack
	 */
	private int capacity;
	
	public ArrayStack(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException();
		}
		stack = new ArrayList<E>();
		setCapacity(capacity);
	}

	@Override
	public void push(E element) {
		if (size() == capacity) {
			throw new IllegalArgumentException();
		} else {
			stack.add(element);
		}
		
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
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		} else {
			this.capacity = capacity;
		}
		
	}

}
