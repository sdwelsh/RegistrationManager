package edu.ncsu.csc216.pack_scheduler.util;
/**
 * this is an interface for stack methods
 * @author kraig
 *
 * @param <E> the element the stack is made up of
 */
public interface Stack<E> {
	/**
	 * adds an element to the top of the stack
	 * @param element the item to be added
	 */
	void push(E element);
	/**
	 * removes and returns the element on the top of the stack
	 * @return the top item on the stack
	 */
	E pop();
	/**
	 * returns true if the stack is empty
	 * @return whether the stack is empty
	 */
	boolean isEmpty();
	/**
	 * returns the number of elements in the stack
	 * @return the number of elements in the stack
	 */
	int size();
	/**
	 * sets the stack's capacity exception thrown if int is negative or less than capacity
	 * @param capacity the capacity to set to
	 */
	void setCapacity(int capacity);

}
