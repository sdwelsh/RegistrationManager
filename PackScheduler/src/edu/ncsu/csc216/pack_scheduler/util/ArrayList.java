
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

 

/**
 * 
 * ArrayList class for packscheduler
 * Constructs the arrayList and gives functionality to add and remove items
 * as well as get items at an index in the list
 * @author jon lett
 *
 * @param <E>
 */
public class ArrayList<E> extends AbstractList<E> {
	/** int for the size to construct the list */
	private static final int INT_SIZE = 10;
	/** list of E type */
	private E[] list;
	/** list of E type used in add method */
	private E[] list2;
	/**size of the list */
	private int size;
	
	/** Suppress the warning to create the array list*/ 
	@SuppressWarnings("unchecked")
	/**
	 * constructor for the ArrayList
	 */
	public ArrayList() {
		//E item = (E)(new Object());
		list = (E[])(new Object[INT_SIZE]);
	}
	/**
	 * adds an item to the given index in the list
	 * @param idx int of index
	 * @param item item item to be added to list
	 */
	@SuppressWarnings("unchecked")
	public void add(int idx, E item) {
		if(item == null) {
			throw new NullPointerException();
		}
		for(int i = 0; i < size; i++) {
			if(item.equals(list[i])) {
				throw new IllegalArgumentException();
		}
		
		}
		if(idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}
		if(size >= INT_SIZE) {
			list2 = (E[])(new Object[2 * size]);
			for(int i = 0; i < size; i++) {
				list2[i] = list[i];
			}
			list = list2 ;
		}
		
		if(idx < size) {
			size++;
			for(int i = size - 2; i >= idx; i--) {
				list[i + 1] = list[i];
			}
		} else {
			size++;
		}
		list[idx] = item; 
	}
	
	/**
	 * removes an item to the given index in the list
	 * @param idx int of index
	 * @return v item that is being removed
	 */
	public E remove(int idx) {
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		E v = list[idx];
		list[idx] = null;
		
		if(idx < size) {
			for(int i = idx; i <= size; i++) {
				list[i] = list[i + 1];
				list[i + 1] = null;
			}
		}
		size--;
		return v;
	
	}

	/**
	 * sets an item to the given index in the list
	 * @param idx int of index
	 * @param item item item to be set to list
	 * @return return the object
	 */
	public E set(int idx, E item) {
		if(item == null) {
			throw new NullPointerException();
		}
		for(int i = 0; i <= size; i++) {
			if(item.equals(list[i])) {
				throw new IllegalArgumentException();
		}
		
		}
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		
		E v = list[idx];
		
		list[idx] = item;
		return v;
	}
	
	/**
	 * gets an item at the given index in the list
	 * @param index int of index
	 * @return E item item at index in list
	 */
	@Override
	public E get(int index) {
		
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		return list[index];
	}

	/**
	 * returns the list size
	 * @return size
	 */
	@Override
	public int size() {
		return size;
	}


}
