/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Creates a new queue interface that the queue classes will use to provide functionality 
 * in queues such as a wait list of students.
 * @author stephenwelsh
 *
 * @param <E> The generic parameter passed into Queue
 */
public interface Queue<E> {
	
	/**
	 * Adds element to the back of the queue providing normal queue functionality
	 * @param element the element to be added to the back of the queue
	 */
	void enqueue(E element);
	
	/**
	 * Removes an item from the front of the queue and returns that 
	 * queue item to the user.
	 * @return the first item in the queue
	 */
	E dequeue();
	
	
	/**
	 * Checks to see if the queue is empty or if it has elements in it.
	 * @return true if the queue is empty
	 * 		   false if the queue is not empty
	 */
	boolean isEmpty();
	
	/**
	 * Returns the size of the queue
	 * @return the size 
	 */
	int size();
	
	/**
	 * Sets the maxCapacity of elements that can be in the queue.
	 * @param capacity the max capacity of queue elements
	 */
	void setCapacity(int capacity);
	
}
