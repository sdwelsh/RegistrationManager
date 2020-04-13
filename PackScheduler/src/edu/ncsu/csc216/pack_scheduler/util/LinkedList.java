package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<E> extends AbstractSequentialList<E>{

	/** The total size of the list */
	private int size;
	
	/** The front of the linked list */
	private ListNode front;
	
	/** The back of the linked list */
	private ListNode back;
	
	/**
	 * Constructor for the linked list class
	 */
	public LinkedList() {
		size = 0;
		front = new ListNode(null);
		back = new ListNode(null, null, front);
		front.next = back;
	}
	
	/**
	 * Returns an Iterator that can go through the linkedList
	 * @return Iterator
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkedListIterator(index);
	}
	
	
	/**
	 * 	Overrides the add method to make sure that there aren't repeats of data being added
	 *  @param index is the index of the element
	 *  @param element is the value of the element
	 */
	@Override
	public void add(int index, E element) {
		for(int i = 0; i < size; i++) {
			E data = super.get(i);
			if(data.equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		super.add(index, element);
	}

	/**
	 * Override the set method to check elements being set for repeats
	 * @param index the index of the value
	 * @param element the value the data is being set to
	 */
	@Override
	public E set(int index, E element) {
		for(int i = 0; i < size; i++) {
			E data = super.get(i);
			if(data.equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		return super.set(index, element);
	}

	/**
	 * Returns the size of the Linked List
	 * @returns size
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * New private class to contain list nodes
	 * @author stephenwelsh
	 */
	private class ListNode {
		/** The data for the list node */
		private E data;
		
		/** The next node in the list */
		private ListNode next;
		
		/** The previous node in the list */
		private ListNode prev;
		
		/**
		 * Creates the list node primary constructor
		 * @param data the data included in the list node
		 */
		public ListNode(E data) {
			this(data, null, null);
		}
		
		/**
		 * Creates the main constructor assigning list nodes
		 * @param data the data of the list node
		 * @param next the next node in the list
		 * @param prev the previous node in the list
		 */
		public ListNode(E data, ListNode next, ListNode prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}
	
	private class LinkedListIterator implements ListIterator<E> {

		/** The previous List node */
		private ListNode previous;
		
		/** The next list node */
		private ListNode next;
		
		/** The index of the previous list node */
		private int previousIndex;
		
		/** The index of the next list node */
		private int nextIndex;
		
		/** The lastRetrieved node */
		private ListNode lastRetrieved;
		
		/**
		 * Constructor for the linked list Iterator
		 * @param index the index to start the iterator at
		 */
		public LinkedListIterator(int index) {
			if(index > size || index < 0) {
				throw new IndexOutOfBoundsException();
			}
			
			ListNode current = front.next;
			if(index == 0) {
				next = front.next;
				previous = front;
				nextIndex = index;
				previousIndex = -1;
			} else if(index == size) {
				next = back;
				previous = back.prev;
				nextIndex = index;
				previousIndex = index - 1;
			} else {
				for(int i = 0; i < index; i ++) {
					current = current.next;
				}
				next = current;
				previous = current.prev;
				nextIndex = index;
			}
			
			lastRetrieved = null;
		}
		
		/**
		 * Returns true if there is a next list node in the list
		 * @return true if the next element exists
		 * 		   false if it doesn't
		 */
		@Override
		public boolean hasNext() {
			if(next.data == null) {
				return false;
			}
			return true;
		}

		/**
		 * Returns the data in the next Node of the list
		 * @returns data in the next node
		 */
		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			ListNode current = next;
			next = next.next;
			previous = current;
			nextIndex++;
			previousIndex++;
			lastRetrieved = current;
			
			return current.data;
		}

		/**
		 * Checks for a previous element in the list
		 * @return true if previous element exists
		 * 		   false if the element doesn't 
		 */
		@Override
		public boolean hasPrevious() {
			if(previous.data == null) {
				return false;
			}
			return true;
		}

		/**
		 * Returns the previous node in the list 
		 * @return the data in the previous node
		 */
		@Override
		public E previous() {
			if(!hasPrevious()) {
				throw new NoSuchElementException();
			}
			
			ListNode current = previous;
			next = previous;
			previous = previous.prev;
			nextIndex--;
			previousIndex--;
			lastRetrieved = current;
			
			return current.data;
		}

		/** 
		 * Returns the next index in the list 
		 * @returns the next index
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/**
		 * Returns the previous index
		 * @return the previous index
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}

		/**
		 * Removes an element from the list by removing its references in the list
		 */
		@Override
		public void remove() {
			if(lastRetrieved == null) {
				throw new IllegalStateException();
			}
			ListNode currentNext = lastRetrieved.next;
			ListNode currentPrev = lastRetrieved.prev;
			
			currentNext.prev = currentPrev;
			currentPrev.next = currentNext;
			size--;
			lastRetrieved = null;
		}

		/** 
		 * Sets the element in the list to the given element
		 * @param e the data the element is being set to
		 */
		@Override
		public void set(E e) {
			if(e == null) {
				throw new NullPointerException();
			}
			if(lastRetrieved == null) {
				throw new IllegalStateException();
			}
			lastRetrieved.data = e;
		}

		/** 
		 * Adds an element to the list
		 * @param e is the element that is added
		 */
		@Override
		public void add(E e) {
			lastRetrieved = null;
			
			if(e == null) {
				throw new NullPointerException();
			}
			
			ListNode newNode = new ListNode(e, next, previous);
			next.prev = newNode;
			previous.next = newNode;
			previous = newNode;
			previousIndex++;
			nextIndex++;
			size++;
		}
		
	}

}
