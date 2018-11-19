import java.util.Iterator;

/**
 * Interface Deque represents an abstract data type (ADT) for a double-ended queue.
 * A deque allows efficient insertion and removal at its front and back, combining
 * many of the benefits of stacks and queues into a single collection.
 * 
 * Classes implementing Deque are generally expected to provide the operations
 * below in O(1) constant amortized runtime, except for the toString and clear
 * operations.
 */
public interface Deque<E> extends Iterable<E> {
	
	// Receives an element to add to the front of the deque.
	void addFirst(E element);
	
	// Receives an element to add to the end of the deque.
	void addLast(E element);
	
	// Clears out the deque and resets the size to zero and the front to the beginning of the deque.
	void clear();
	
	// Returns whether the deque is currently empty.
	boolean isEmpty();
	
	// Returns an iterator object to iterate through the deque.
	Iterator<E> iterator();
	
	// Returns the element at the front of the deque. 
	E peekFirst();
	
	// Returns the element at the front of the deque. 
	E peekLast();
	
	// Removes and returns the first element from the deque to the user.
	E removeFirst();
	
	// Removes and returns the last element from the deque to the user.
	E removeLast();
	
	// Returns the current size of the deque to the user.
	int size();
	
	// Returns a string representation of the deque to the user.
	String toString();

}
