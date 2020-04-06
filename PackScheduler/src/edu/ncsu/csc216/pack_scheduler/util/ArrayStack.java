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
	
	/**
	 * Constructs the array stack 
	 * @param capacity the max capacity of the stack
	 */
	public ArrayStack(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException();
		}
		stack = new ArrayList<E>();
		setCapacity(capacity);
	}

	/**
	 * adds an element to the top of the stack
	 * @param element the item to be added
	 */
	@Override
	public void push(E element) {
		if (size() == capacity) {
			throw new IllegalArgumentException();
		} else {
			stack.add(element);
		}
		
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
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		} else {
			this.capacity = capacity;
		}
		
	}

}
