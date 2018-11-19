/* 
 * The ArrayDeque class implements a non-trivial collection of a deque using an array as the internal 
 * data structure. The deque is "circular", meaning elements can be added and removed from the front 
 * and end while changing the size and starting index. Other behaviors include resizing the array when 
 * it becomes full, clearing out all the elements, representing the deque as a string, iterating through
 * the deque, viewing the first and last elements without removing them, etc.
 */

import java.util.*; // Allows arrays to be used. 

public class ArrayDeque<E> implements Deque<E> {
	private static final int DEFAULT_CAPACITY = 10; // Remembers the default capacity of an array.
	private E[] deque; // Remembers the deque.
	private int size; // Remembers the size of the deque.
	private int front; // Remembers where the front of the deque is located.
	
	// Constructs new deque object with a default capacity and setting the front to the start and the size to zero.
	@SuppressWarnings("unchecked")
	public ArrayDeque() {
		deque = (E[]) (new Object[DEFAULT_CAPACITY]);
		size = 0;
		front = 0;
	}
	
	// Receives an element to add to the front of the deque. Throws a NullPointerException if the element is null.
	public void addFirst(E element) {
		if (element.equals(null))
			throw new NullPointerException();
		if (size == deque.length)
			resize();
		front = (front - 1 + deque.length) % deque.length;
		deque[front] = element;
		size++;
	}
	
	// Receives an element to add to the end of the deque. Throws a NullPointerException if the element is null.
	public void addLast(E element) {
		if (element.equals(null))
			throw new NullPointerException();
		if (size == deque.length)
			resize();
		deque[(size + front) % deque.length] = element;
		size++;
	}
	
	// Clears out the deque and resets the size to zero and the front to the beginning of the deque.
	@SuppressWarnings("unchecked")
	public void clear() {
		deque = (E[]) (new Object[DEFAULT_CAPACITY]);
		size = 0;
		front = 0;
	}
	
	// Returns whether the deque is currently empty.
	public boolean isEmpty() {
		return size == 0;
	}
	
	// Returns an iterator object to iterate through the deque.
	public Iterator<E> iterator() {
		return new ArrayDequeIterator();
	}
	
	// Returns the element at the front of the deque. Throws a NoSuchElementException if the deque does
	// not contain any elements.
	public E peekFirst() {
		if (isEmpty())
			throw new NoSuchElementException();
		return deque[front];
	}
	
	// Returns the element at the front of the deque. Throws a NoSuchElementException if the deque does
	// not contain any elements.
	public E peekLast() {
		if (isEmpty())
			throw new NoSuchElementException();
		return deque[(size + front) % deque.length - 1];
	}
	
	// Removes and returns the first element from the deque to the user.
	public E removeFirst() {
		E element = peekFirst();
		deque[front] = null;
		front = (front + 1) % deque.length;
		size--;
		return element;
	}
	
	// Removes and returns the last element from the deque to the user.
	public E removeLast() {
		E element = peekLast();
		deque[(size + front) % deque.length - 1] = null;
		size--;
		return element;
	}
	
	// Returns the current size of the deque to the user.
	public int size() {
		return size;
	}
	
	// Returns a string representation of the deque to the user.
	public String toString() {
		if (isEmpty())
			return "[]";
		else {
			String elements = "[" + deque[front];
			for (int i = 1; i < size; i++) {
				elements += ", " + deque[(front + i) % deque.length];
			}
			elements += "]";
			return elements;
		}
	}
	
	// Private helper method to resize the deque to twice the previous size.
	@SuppressWarnings("unchecked")
	private void resize() {
		E[] temp = (E[]) (new Object[deque.length * 2]);
		for (int i = 0; i < size; i++)
			temp[i] = deque[(front + i) % deque.length];
		deque = temp;
		front = 0;
	}
	
	// The ArrayDequeIterator class is an iterator object that iterates through the deque.
	private class ArrayDequeIterator implements Iterator<E> {
		private int count; // Remembers the number of calls made to the next method.
		private int index; // Remembers the index of the iterator.
		
		// Constructs a new iterator with the count reset to zero and index to the front of the deque.
		public ArrayDequeIterator() {
			count = 0;
			index = front;
		}
		
		// Returns whether the iterator has a next element in the deque.
		public boolean hasNext() {
			return count < size;
		}
		
		// Returns the next element of the iterator in the deque.
		public E next() {
			E element = deque[index];
			index = (index + 1) % deque.length;
			count++;
			return element;
		}
		
		/**
		 * Removes the most recently returned element.
		 * Not supported. Throws an UnsupportedOperationException when called.
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
