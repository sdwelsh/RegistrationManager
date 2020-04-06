/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * @author stephenwelsh
 *
 */
public class ArrayQueue<E> implements Queue<E>{

	/** Array list being used for the creation of the Queue */
	private ArrayList<E> list;
	
	/** Sets capacity for the Queue */
	private int capacity;
	
	/**
	 * Constructor for the ArrayQueue that creates a new Queue 
	 * from an array list
	 */
	public ArrayQueue(int capacity) {
		list = new ArrayList<E>();
		this.capacity = capacity;
	}
	
	/**
	 * Adds element to the back of the queue providing normal queue functionality
	 * @param element the element to be added to the back of the queue
	 */
	@Override
	public void enqueue(E element) {
		if(size() == capacity) {
			throw new IllegalArgumentException();
		}
		list.add(element);
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
		if(capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

}
