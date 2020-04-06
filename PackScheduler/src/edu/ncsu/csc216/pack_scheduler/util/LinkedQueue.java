/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * This is a queue that uses the linkedAbstractList as the base
 * @author stephenwelsh
 *
 * @param <E> generic parameter
 */
public class LinkedQueue<E> implements Queue<E> {

	/** New abstract list for queue */
	private LinkedAbstractList<E> list;
	
	/**
	 * Constructs the LinkedQueue with the capacity
	 * @param capacity the max capacity of the queue
	 */
	public LinkedQueue(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
	}
	
	/**
	 * Adds element to the back of the queue providing normal queue functionality
	 * @param element the element to be added to the back of the queue
	 */
	@Override
	public void enqueue(E element) {
		list.add(list.size(), element);
	}

	/**
	 * Removes an item from the front of the queue and returns that 
	 * queue item to the user.
	 * @return the first item in the queue
	 */
	@Override
	public E dequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return list.remove(0);
	}

	/**
	 * Checks to see if the queue is empty or if it has elements in it.
	 * @return true if the queue is empty
	 * 		   false if the queue is not empty
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Returns the size of the queue
	 * @return the size 
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the maxCapacity of elements that can be in the queue.
	 * @param capacity the max capacity of queue elements
	 */
	@Override
	public void setCapacity(int capacity) {
		list.setCapacity(capacity);
	}

}
