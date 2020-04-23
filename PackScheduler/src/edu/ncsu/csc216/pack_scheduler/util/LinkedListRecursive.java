/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * a linked list that uses recursion in some methods
 * 
 * @author Kraig Denny
 * @param <E> the element type used in list
 */
public class LinkedListRecursive<E> {

	private int size;
	private ListNode front;

	/**
	 * the linked list recursive constructor
	 */
	public LinkedListRecursive() {
		size = 0;
		front = new ListNode(null, null);
	}

	/**
	 * checks if the list is empty or not
	 * 
	 * @return if empty return true otherwise false
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the size of the list
	 * 
	 * @return size of the list
	 */
	public int size() {
		return size;
	}

	/**
	 * adds an element to the end of the list and return if it was able to be added
	 * 
	 * @param element the element to add to the list
	 * @return true if added otherwise false
	 */
	public boolean add(E element) {
		if (isEmpty()) {
			front.add(0, element);
			return true;
		} else {
			front.add(size, element);
			return true;
		}
	}

	/**
	 * adds an element at the given index
	 * 
	 * @param index   the index where to add the element
	 * @param element element to add
	 */
	public void add(int index, E element) {
		if(element == null) {
			throw new NullPointerException();
		} else if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} 
		if (index == 0) {
			if(front != null && front.data != null && front.data.equals(element)) {
				throw new IllegalArgumentException();
			}
			front = new ListNode(element, front);
			size++;
		} else {
			front.add(index--, element);
		}
	}

	/**
	 * gets the element at an index
	 * 
	 * @param index index of the element to get
	 * @return element at the index value
	 */
	public E get(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			return front.data;
		} else {
			return front.get(index);
		}
	}

	/**
	 * removes a given element form the list
	 * 
	 * @param element element to be removed
	 * @return true if removed otherwise false
	 */
	public boolean remove(E element) {
		if (front.data != null && front.data.equals(element)) {
			front = front.next;
			size--;
			return true;
		} else {
			return front.remove(element);
		}
	}

	/**
	 * removes the element at a specified index
	 * 
	 * @param index index to remove the element
	 * @return element at the index that was removed
	 */
	public E remove(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			E remmoveElement = front.data;
			front = front.next;
			size--;
			return remmoveElement;
		} else {
			return front.remove(index - 1);
		}
	}

	/**
	 * sets the element at the given index
	 * 
	 * @param index   index to set the element
	 * @param element element to set to
	 * @return the previous element
	 */
	public E set(int index, E element) {
		if(index == 0 && size == 0) {
			throw new IndexOutOfBoundsException();
		} else if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			if(front.data.equals(element)) {
				throw new IllegalArgumentException();
			}
			E returnElement = front.data;
			front.data = element;
			return returnElement;
		} else {
			return front.set(index, element);
		}
	}

	/**
	 * checks the list to see if it contains an element
	 * 
	 * @param element element to check for
	 * @return if contains then true otherwise false
	 */
	public boolean contains(E element) {
		if (size == 0) {
			return false;
		} else {
			return front.contains(element);
		}
	}

	/**
	 * Inner class for LinkedListRecursive
	 * 
	 * @author Kraig Denny
	 *
	 */
	private class ListNode {

		private E data;
		private ListNode next;

		/**
		 * Constructor for ListNode
		 * 
		 * @param data data of the ListNode
		 * @param next the next ListNode
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}

		/**
		 * recursive method for add keeps calling add until it gets to the right index
		 * then calls not recursive add
		 * 
		 * @param index   desired index to add to
		 * @param element element to be added at index
		 */
		public void add(int index, E element) {
			if (index == 0) {
				if(data != null && data.equals(element)) {
					throw new IllegalArgumentException();
				}
				ListNode current = this;
				this.next = new ListNode(current.data, current.next);
				this.data = element;
				size++;
			} else {
				index--;
				if(data.equals(element)) {
					throw new IllegalArgumentException();
				}
				next.add(index, element);
			}
		}

		/**
		 * recursive get keeps calling itself until it gets to the index
		 * 
		 * @param index index of the element to get
		 * @return the element at the given index
		 */
		public E get(int index) {
			if (index == 0) {
				return this.data;
			} else {
				index--;
				return next.get(index);
			}
		}

		/**
		 * recursive remove keeps calling itself until it gets to the desired index
		 * 
		 * @param index index to remove at
		 * @return element that is removed
		 */
		public E remove(int index) {
			if (next == null) {
				return null;
			}
			if (index == 0) {
				E tempElement = next.data;
				next = next.next;
				size--;
				return tempElement;
			} else {
				index--;
				return next.remove(index);
			}
		}

		/**
		 * recursive remove calls itself until that element equals the element to remove
		 * or till next = null
		 * 
		 * @param element element to remove
		 * @return true if element is removed otherwise false
		 */
		public boolean remove(E element) {

			if (next != null && next.next != null) {
				if (next.data != null && next.data.equals(element)) {
					next = next.next;
					size--;
					return true;
				} else if (next.data != null){
					next.remove(element);
				}
			}
			return false;
		}

		/**
		 * sets the element at an index recursively iterates to the index
		 * 
		 * @param index   index to set element
		 * @param element the previous element
		 */
		public E set(int index, E element) {
			if(index < 0 || index > size) {
				throw new IndexOutOfBoundsException();
			} else if(element == null) {
				throw new NullPointerException();
			}
			if (index == 0) {
				if(data.equals(element)) {
					throw new IllegalArgumentException();
				}
				E elementToReturn = data;
				data = element;
				return elementToReturn;
			} else {
				index--;
				if(data.equals(element)) {
					throw new IllegalArgumentException();
				}
				return next.set(index, element);
			}
		}

		/**
		 * recursively iterates through list and until the end or data at node = the
		 * element
		 * 
		 * @param element element to check for
		 * @return true if contains element otherwise false
		 */
		public boolean contains(E element) {
			if (data == element) {
				return true;
			} else if (next == null) {
				return false;
			}
			return next.contains(element);
		}
	}
}