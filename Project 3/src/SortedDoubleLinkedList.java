import java.util.Comparator;
import java.util.ListIterator;

/** SortedDoubleLinkedList is an extension of BasicDoubleLinkedList. It does the same, except
 *  for adding. You cannot add to the end or front, just add and have it be sorted
 *  for you.
 * 
 * @author Michael Amaya
 *
 * @param <T> The datatype the data contains
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	
	private Comparator<T> comparator;
	
	/** Constructor that takes in a comparator used for adding data
	 * 
	 * @param comparator2 The comparator to compare data
	 */
	public SortedDoubleLinkedList(Comparator<T> comparator2) {
		comparator = comparator2;
	}
	
	/** Adds data in a certain order. Order is decided
	 *  with the comparator that used to create an instance of this class
	 * 
	 * @param data The data to be added
	 * @return An instance of this class
	 */
	public SortedDoubleLinkedList<T> add(T data) {
		ListNode<T> newNode = new ListNode<>(data);
		if (head == null)
			head = tail = newNode;
		else {
			ListNode<T> currentNode = head;
			
			do {
				
				if (comparator.compare(newNode.getData(), currentNode.getData()) == 0) {
					newNode.setNext(currentNode.getNext());
					currentNode.setNext(newNode);
					
					newNode.setPrevious(currentNode);
					newNode.getNext().setPrevious(newNode);
					
					// Work is done, so break out of the loop, or it'll go infinitely....
					break;
				} else if (comparator.compare(newNode.getData(), currentNode.getData()) < 0) {
					if (currentNode == head) {
						newNode.setNext(currentNode);
						currentNode.setPrevious(newNode);
						newNode.setPrevious(tail);
						tail.setNext(newNode);
						
						head = newNode;
					} else {
						newNode.setNext(currentNode);
						newNode.setPrevious(currentNode.getPrevious());
						currentNode.setPrevious(newNode);
						newNode.getPrevious().setNext(newNode);
					}
					
					// Work is done, so break out of loop, or it'll go infinitely....
					break;
				} else if (comparator.compare(newNode.getData(), currentNode.getData()) > 0) {
					if (currentNode == tail) {
						tail.setNext(newNode);
						newNode.setPrevious(tail);
						newNode.setNext(head);
						head.setPrevious(newNode);
						
						tail = newNode;
						
						// Work is done, so break out of loop or it'll go infinitely.
						break;
					} else {
						currentNode = currentNode.getNext();
					}
				}
			} while(currentNode != head);
		}
		
		size++;
		return this;
	}
	
	/** This operation is not supported for a sorted list, so an exception is always thrown
	 * 
	 * @return Nothing, just throws an exception
	 */
	@Override
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/** This operation is not supported for a sorted list, so an exception
	 *  is always thrown when attempting to use it
	 * 
	 * @return nothing, just throws an exception
	 */
	@Override
	public BasicDoubleLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/** Iterator used to go through the list
	 * @return The iterator of the superclass
	 */
	@Override
	public ListIterator<T> iterator() {
		return super.iterator();
	}
	
	/** Removes an item in the list
	 * 
	 * @param data The data to remove
	 * @param comparator The comparator used to check if the data matches
	 * @return A reference to this class
	 */
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator){
		super.remove(data, comparator);
		return this;
	}
}
