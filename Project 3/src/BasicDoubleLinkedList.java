import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/** BasicDoubleLinkedList is a container that holds any datatype.
 *  It's like a chain where each link, or in this case, node, knows the
 *  previous item and the next item in the list.
 * 
 * @author Michael Amaya
 *
 * @param <T> The datatype for the data that BasicDoubleLinkedList contains
 */
public class BasicDoubleLinkedList<T> implements Iterable<T>{

	/** ListNode is a container for the individual chain that knows
	 *  the next item, and the previous item, while also containing the data
	 *  for it's own item
	 *  
	 * @author Michael Amaya
	 *
	 * @param <E> The datatype for the data contained
	 */
	protected class ListNode<E> {
		private E data;
		private ListNode<E> previous;
		private ListNode<E> next;
		
		/** Constructor that sets the data to contain
		 * 
		 * @param data The data to be contained
		 */
		public ListNode(E data) {
			previous = next = null;
			this.data = data;
		}
		
		/** Sets the next node in the list / chain
		 * 
		 * @param next What to set the next node to be
		 */
		public void setNext(ListNode<E> next) {
			this.next = next;
		}
		
		/** Sets the previous node in the list / chain
		 * 
		 * @param previous What to set the previous node to be
		 */
		public void setPrevious(ListNode<E> previous) {
			this.previous = previous;
		}
		
		/** Gets the next node in the list / chain
		 * 
		 * @return The next node in the list / chain
		 */
		public ListNode<E> getNext(){
			return next;
		}

		/** Gets the previous node in the list / chain
		 * 
		 * @return The previous node in the list / chain
		 */
		public ListNode<E> getPrevious(){
			return previous;
		}
		
		/** Gets the data that the node contains 
		 * 
		 * @return The data contained in the node
		 */
		public E getData() {
			return data;
		}
		
		/** For debug purposes, shows the data with the name of the class prepended
		 * 
		 * @return The data contained in the node.
		 */
		public String toString() {
			return " {ListNode} " + data;
		}
	}
	
	/**
	 * 
	 * @author Iterator to go through the list
	 *
	 * @param <E> The datatype of the data contained in the iterator
	 */
	private class BasicDoubleLinkedListIterator<E> implements ListIterator<E> {
		
		private ListNode<E> currentNode;
		private int currentIndex;
		
		/** Constructor that sets the current node to the head
		 *  and the current index set to 0
		 * 
		 * @param head What to set the current node equal to, the head is recommended as a start
		 */
		public BasicDoubleLinkedListIterator(ListNode<E> head) {
			currentNode = head;
			currentIndex = 0;
		}
		
		/** Checks if there is a next item by checking if the index is greater than the size
		 * 
		 * @return if there is a next element, true. If not, false
		 */
		@Override
		public boolean hasNext() {
			return currentIndex < size;
		}

		/** Gets the next item in the list 
		 * 
		 * @return The data of the next node in the list
		 * @throws NoSuchElementException Throws this if there is no next element
		 */
		@Override
		public E next() throws NoSuchElementException {
			if (hasNext()) {
				E dataToReturn = currentNode.getData();
				currentNode = currentNode.getNext();
				currentIndex++;
				return dataToReturn;
			}
			
			throw new NoSuchElementException("There is no such element!");
		}

		/** Checks if there is a previous node in the list
		 * 
		 * @return If there is a previous element, true. If not, false
		 */
		@Override
		public boolean hasPrevious() {
			return currentIndex > 0;
		}

		/** Gets the previous element in the list if it exists
		 * 
		 * @throws NoSuchElementException if there is no previous element.
		 * @return The previous element in the list if it exists
		 */
		@Override
		public E previous() throws NoSuchElementException {
			if (hasPrevious()) {
				currentNode = currentNode.getPrevious();
				currentIndex--;
				return currentNode.getData();
			}
			
			throw new NoSuchElementException("There is no such element!");
		}
		
		/** Remove from the iterator is not supported so an exception is thrown
		 * 
		 * @throws UnsupportedOperationException Always thrown because this operation is not supported
		 */
		@Override
		public void remove() throws UnsupportedOperationException{ 
			throw new UnsupportedOperationException("Operation not supported!");
		}

		@Override
		public int nextIndex() { return 0; }

		@Override
		public int previousIndex() { return 0; }

		@Override
		public void set(E e) { }

		@Override
		public void add(E e) { }
	}
	
	protected ListNode<T> head;
	protected ListNode<T> tail;
	protected int size;
	
	/** Constructor for the BasicDoubleLinkedList, sets the head and tail to null, size to 0 */
	public BasicDoubleLinkedList() {
		head = tail = null;
		size = 0;
	}
	
	/** Adds an element to the end of the list
	 * 
	 * @param data The data to be added to the end of the list
	 * @return A reference to this class
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		ListNode<T> newNode = new ListNode<>(data);
		if(tail == null)
			head = tail = newNode;
		else {
			newNode.setPrevious(tail);
			newNode.setNext(head);
			tail.setNext(newNode);
			tail = newNode;
			head.setPrevious(tail);
		}
		
		size++;
		return this;
	}
	
	/** Adds an element to the front of the list
	 * 
	 * @param data The data to be added to the front of the list
	 * @return A reference to this class
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) {
		ListNode<T> newNode = new ListNode<>(data);
		
		if (head == null)
			head = tail = newNode;
		else {
			newNode.setNext(head);
			newNode.setPrevious(tail);
			head.setPrevious(newNode);
			head = newNode;
			tail.setNext(head);
		}
		
		size++;
		return this;
	}
	
	/** Gets the first element of the list 
	 * 
	 * @return The data of the first node in the list
	 */
	public T getFirst() {
		return head.getData();
	}
	
	/** Gets the last element of the list
	 * 
	 * @return The data of the last element in the list
	 */
	public T getLast() {
		return tail.getData();
	}
	
	/** Gets the size of the list
	 * 
	 * @return The size of the list
	 */
	public int getSize() {
		return size;
	}
	
	/** The iterator for going through the list
	 * 
	 * @return A new instance of BasicDoubleLinkedListIterator.
	 */
	@Override
	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException{
		return new BasicDoubleLinkedListIterator<T>(head);
	}
	
	/** Removes a node from the list
	 * 
	 * @param targetData The data to be removed
	 * @param comparator The comparator used to compare data
	 * @return A reference to this class
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator){
		if (head != null) {
			ListNode<T> currentNodeToCheck = head;
			while(currentNodeToCheck != null && comparator.compare(currentNodeToCheck.getData(), targetData) < 0) {
				currentNodeToCheck = currentNodeToCheck.getNext();
			}
			
			if (currentNodeToCheck != null) {
			
				if (currentNodeToCheck == head)
					retrieveFirstElement();
				else if (currentNodeToCheck == tail)
					retrieveLastElement();
				else {
					currentNodeToCheck.getPrevious().setNext(currentNodeToCheck.getNext());
					currentNodeToCheck.getNext().setPrevious(currentNodeToCheck.getPrevious());
					currentNodeToCheck.setPrevious(null);
					currentNodeToCheck.setNext(null);
					size--;
				}
				
				currentNodeToCheck = null;
			}
		}
		
		return this;
	}
	
	/** Gets the first element of the list and removes it
	 * 
	 * @return The data in the first node of the list
	 */
	public T retrieveFirstElement() {
		if (head == null)
			return null;
		T nodeDataToReturn = head.getData();
		head = head.getNext();
		head.setPrevious(tail);
		tail.setNext(head);
		
		size--;
		return nodeDataToReturn;
	}
	
	/** Gets the last element of the list and removes it
	 * 
	 * @return The data in the last node of the list
	 */
	public T retrieveLastElement() {
		if (tail == null)
			return null;
		T nodeDataToReturn = tail.getData();
		tail = tail.getPrevious();
		tail.setNext(head);
		head.setPrevious(tail);
		
		size--;
		return nodeDataToReturn;
	}
	
	/** Turns the list into an ArrayList
	 * 
	 * @return An ArrayList of the data in the list.
	 */
	public ArrayList<T> toArrayList(){
		ArrayList<T> basicDoubleLinkedListToArrayList = new ArrayList<>();
		ListNode<T> currentNodeToCheck = head;
		do {
			if (size == 1)
				basicDoubleLinkedListToArrayList.add(head.getData());
			else {
				basicDoubleLinkedListToArrayList.add(currentNodeToCheck.getData());
				currentNodeToCheck = currentNodeToCheck.getNext();
			}
		} while(currentNodeToCheck != head);
		return basicDoubleLinkedListToArrayList;
	}
}
