/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;
import java.util.AbstractList;

/**
 * LinkedAbstractList class that could hold courses
 * @author Jeff Li
 * @param <E>
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** front of ListNode */
	private ListNode front;
	/** back of ListNode */
	private ListNode back;
	/** size of ListNode */
	private int size;
	/** max students able to enroll in course */
	private int capacity;
	
	/**
	 * Constructor for LinkedAbstractList
	 * 
	 * @param capacity the max capacity of the List
	 */
	public LinkedAbstractList(int capacity) {
		size = 0;
		front = null;

		if (capacity < 0) {
			throw new IllegalArgumentException("Invalid capacity");
		}
		this.capacity = capacity;

	}
	
	/**
	 * ListNode class
	 * 
	 * @author Jeff Li
	 */
	private class ListNode {

		private E data;
		private ListNode next;

		public ListNode(E data) {
			this(data, null);
		}

		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
	
	/**
	 * Changes the capacity of the list
	 * 
	 * @param capacity the new capacity
	 */
	public void setCapacity(int capacity) {
		if (capacity < size) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}
	
	/**
	 * Gets the object with the given index
	 * 
	 * @param index the index of the object
	 * @return the object with the index
	 */
	@Override
	public E get(int index) {

		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}

		ListNode tmp = front;
		for (int i = 0; i < index; i++) {
			tmp = tmp.next;
		}
		return tmp.data;
	}
	
	/**
	 * Sets the object at the given index to a given value
	 * 
	 * @param idx the index of the object being modified
	 * @param value the new value for the object
	 * @return the old object
	 */
	@Override
	public E set(int idx, E value) {
	

		if (value == null) {
			throw new NullPointerException("Element added is null");
		}
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		ListNode current = front;
		while (current.next != null) {
			if (current.data.equals(value)) {
				throw new IllegalArgumentException("Duplicate value");
			}
			current = current.next;
		}

		current = front;
		for (int i = 0; i < idx; i++) {
			current = current.next;
		}
		E tmp = current.data;
		current.data = value;
		return tmp;

	}
	
	/**
	 * Adds an object to the list. Null and duplicate objects cannot be added
	 * 
	 * @param idx the index that the object is being added to
	 * @param value the object being added
	 */
	@Override
	public void add(int idx, E value) {
		if (size == capacity) {
			throw new IllegalArgumentException("Capacity reached");
		}
		if (value == null) {
			throw new NullPointerException("Element added is null");
		}
		if (idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}

		if (front == null) {
			front = new ListNode(value);
			back = front;
		}

		else {
			ListNode current = front;

			while (current.next != null) {
				if (current.data.equals(value)) {
					throw new IllegalArgumentException("Duplicate value");
				}
				current = current.next;
			}

			current = front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			if (idx == 0) {
				ListNode tmp = new ListNode(value, front);
				front = tmp;
			} else if (idx == size){
				back.next = new ListNode(value, null);
				back = back.next;
				
			} else {
				current.next = new ListNode(value, current.next);
				while (current.next != null) {
					current = current.next;
				}
				back = current;
			}

		}
		size++;
	}
	
	/**
	 * Removes an object from the list
	 * 
	 * @param idx the index of the object being removed
	 * @return the object that was removed
	 */
	@Override
	public E remove(int idx) {
		E tmp;
		
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		if (idx == 0 && size() == 1) {
			tmp = front.data;
			front = null;
		} else if (idx == 0) {
			tmp = front.data;
			front = front.next;
		} else {
			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			tmp = current.next.data;
			current.next = current.next.next;
		}
		size--;
		return tmp;
	}
	
	/**
	 * Gets the number of objects in the list
	 * 
	 * @return the number of objects in the list
	 */
	@Override
	public int size() {
		return size;
	}
}
