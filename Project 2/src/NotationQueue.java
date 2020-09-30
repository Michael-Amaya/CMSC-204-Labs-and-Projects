import java.util.ArrayList;

/** NotationQueue is a Data Structure that takes in any data and puts
 *  it in a queue. A queue works with the concept First In, First Out. 
 *  This means that when you add something to the queue, it gets put at the
 *  end, and when attempting to take something out of the queue, the item
 *  that was put in at first will be removed. 
 *   
 *  @author Michael Amaya
 *
 *  @param <T> The Data type you want to store in the NotationQueue
 */
public class NotationQueue<T> implements QueueInterface<T>{

	private final int MAX_SIZE;
	private int numOfElements;
	private T[] elements;
	
	/** Default Constructor that creates a default NotationQueue 
	 *  with 5 elements max, a default number */
	public NotationQueue() {
		this(5);
	}
	
	/** Constructor that creates a NotationQueue with a specified number
	 *  of elements
	 * 
	 * @param size The number of max elements you want the NotationQueue to contain
	 */
	@SuppressWarnings("unchecked")
	public NotationQueue(int size) {
		MAX_SIZE = size;
		numOfElements = 0;
		elements = (T[]) new Object[MAX_SIZE];
	}
	
	/** Constructor that takes in an ArrayList and populates a queue with those
	 *  elements. The size of the NotationQueue is the same size of the ArrayList
	 * 
	 * @param elements The ArrayList to be converted to a NotationQueue
	 */
	public NotationQueue(ArrayList<T> elements) {
		this(elements.size());
		@SuppressWarnings("unchecked")
		ArrayList<T> newElements = (ArrayList<T>) elements.clone();
		for(T element : newElements)
			try {
				enqueue(element);
			} catch (QueueOverflowException e) {
				System.err.println(e);
			}
	}

	/** Checks if the NotationQueue is empty or not
	 * 
	 * @return True if the queue is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return numOfElements == 0;
	}

	/** Checks if the NotationQueue is full or not
	 * 
	 * @return True if the queue is full, false if not
	 */
	@Override
	public boolean isFull() {
		return numOfElements == MAX_SIZE;
	}

	/** Removes the bottom most element of the NotationQueue, or in other
	 *  words, the first item that was put in the queue. When something is removed
	 *  the first item is the next item that was put in.
	 * 
	 * @throws QueueUnderflowException Occurs if you try to dequeue when the queue is empty
	 * @return The element that was removed
	 */
	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty())
			throw new QueueUnderflowException();
		
		T element = elements[0];
		numOfElements--;
		
		for(int i = 0; i < elements.length - 1; i++)
			elements[i] = elements[i + 1];
		
		return element;
	}

	/** Checks the number of elements in the queue
	 * 
	 * @return The number of elements in the queue
	 */
	@Override
	public int size() {
		return numOfElements;
	}

	/** Adds an element to the end of the queue
	 * 
	 * @param e The element you want to enqueue
	 * 
	 * @throws QueueOverflowException Occurs if you try to enqueue when the queue is full
	 * @return True of the element was enqueued
	 */
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if (isFull())
			throw new QueueOverflowException();
		
		elements[numOfElements++] = e;
		return true;
	}

	/** Gets the items in the queue and shows it without a delimiter
	 * 
	 * @return A string of the data in the queue, with no delimiter
	 */
	@Override
	public String toString() {
		StringBuilder finishedString = new StringBuilder();
		for(int i = 0; i < numOfElements; i++)
			finishedString.append(elements[i] == null ? "" : elements[i]);
			
		return finishedString.toString();
	}
	
	/** Gets the items in the queue and shows it with delimiter passed through
	 * 
	 * @param delimiter The delimiter you want the data separated by
	 * 
	 * @return A string of the data in the queue, separated by the delimiter passed through
	 */
	@Override
	public String toString(String delimiter) {
		StringBuilder finishedString = new StringBuilder();
		for (int i = 0; i < numOfElements; i++)
			finishedString.append(elements[i] == null ? "" : elements[i]).append(elements[i] == null ? "" : delimiter);
		
		finishedString.delete(finishedString.length() - delimiter.length() < 0 ? 0 : finishedString.length() - delimiter.length(), finishedString.length());
		return finishedString.toString();
	}

}
