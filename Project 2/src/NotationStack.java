import java.util.ArrayList;

/** NotationStack is a data structure that takes in any data and
 *  puts them in a Stack. A stack works with the concept of Last In,
 *  First out. Meaning that when you add something to the stack, it gets
 *  put on the top of the stack and when you want to take something out, 
 *  the top gets taken off. You can work with this data by calling its
 *  toString method. 
 * 
 * @author Michael Amaya
 *
 * @param <T> A DataType you want to store in the NotationStack
 */
public class NotationStack<T> implements StackInterface<T>{
	
	private int numOfElements;
	private final int MAX_SIZE;
	private T[] elements;

	/** Default constructor that initializes the max size with 5 items, with 5 being the default */
	public NotationStack() {
		this(5);
	}
	
	/** Constructor that takes in a size and creates a NotationStack of that size
	 * 
	 * @param size The size desired of the NotationStack
	 */
	@SuppressWarnings("unchecked")
	public NotationStack(int size) {
		MAX_SIZE = size;
		elements = (T[]) new Object[MAX_SIZE];
		numOfElements = 0;
	}
	
	/** Constructor that takes in an ArrayList and adds all items of the ArrayList
	 *  to the stack
	 * 
	 * @param elements ArrayList that you want copied into the Stack 
	 */
	public NotationStack(ArrayList<T> elements) {
		this(elements.size());
		@SuppressWarnings("unchecked")
		ArrayList<T> newElements = (ArrayList<T>) elements.clone();
		for(T element : newElements)
			try {
				push(element);
			} catch (StackOverflowException e) {
				System.err.println(e);
			}
	}
	
	/** Checks if the Stack is empty by checking if the 
	 * number of elements is 0.
	 */
	@Override
	public boolean isEmpty() {
		return numOfElements == 0;
	}

	/** Checks if the stack is full by checking if the 
	 * number of elements is equal to the max size */
	@Override
	public boolean isFull() {
		return numOfElements == MAX_SIZE;
	}

	/** Removes the last item inserted into the stack and
	 *  returns it
	 * 
	 * @return The item removed from the top of the stack
	 * @throws StackUnderflowException  Occurs when you attempt to pop an item off of an empty Stack
	 */
	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();
		
		return elements[--numOfElements];
	}

	/** Returns the topmost, or last item inserted into the stack
	 * 
	 * @return The last item inserted into the stack
	 * @throws StackUnderflowException Occurs when you attempt to check the top of an empty Stack
	 */
	@Override
	public T top() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();
		return elements[numOfElements-1];
	}

	/** Returns the number of elements in the stack
	 * 
	 * @return The number of elements in the stack
	 */
	@Override
	public int size() {
		return numOfElements;
	}

	/** Adds an item to the end of the stack
	 * 
	 * @param e The element you'd like to add to the Stack
	 * 
	 * @return True if the item is added to the stack
	 * @throws StackOverflowException Occurs when you attempt to push onto a full Stack
	 */
	@Override
	public boolean push(T e) throws StackOverflowException {
		if (isFull()) 
			throw new StackOverflowException();
		
		elements[numOfElements++] = e;
		return true;
	}

	/** Returns a string of the items in the Stack without a delimiter
	 * 
	 * @return A string of the items in the stack with no delimiter
	 */
	@Override
	public String toString() {
		StringBuilder finishedString = new StringBuilder();
		for (int i = 0; i < numOfElements; i++)
			finishedString.append(elements[i] == null ? "" : elements[i]);
		
		return finishedString.toString();
	}
	
	/** Returns a string of the items in the Stack with a delimiter, does
	 *  not add the extra delimiter to the end of the string
	 *
	 *	@param delimiter The string you'd like to use to split up each item in the stack
	 *
	 *  @return A string of the items in the stack split by the delimiter provided
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
