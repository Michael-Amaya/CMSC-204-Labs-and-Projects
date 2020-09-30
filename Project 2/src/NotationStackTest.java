import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** A group of tests that test the NotationStack class
 *  
 *  @author Unknown professor at Montgomery College
 *  @author Michael Amaya
 *
 */
public class NotationStackTest {
	public NotationStack<String> stringS;
	public String a="a", b="b", c="c", d="d", e="e", f="f";
	public ArrayList<String> fill = new ArrayList<String>();
	
	// STUDENT: student tests will use the doubleS
	public NotationStack<Double> doubleS;
	
	/** Create NotationStack of Strings and NotationStack
	 *  of doubles to do tests with, adds default values
	 * 
	 * @throws Exception Any exception that may come from creating a NotationStack
	 */
	@Before
	public void setUp() throws Exception {
		stringS = new NotationStack<String>(5);
		stringS.push(a);
		stringS.push(b);
		stringS.push(c);
		
		//STUDENT: add setup for doubleS for student tests
		doubleS = new NotationStack<Double>();
		doubleS.push(3.4);
		doubleS.push(4.5);
		doubleS.push(44.3);
		doubleS.push(8.4);
		
	}

	/** Cleans up NotationStack Objects 
	 * 
	 * @throws Exception Anything that the NotationStack can throw
	 */
	@After
	public void tearDown() throws Exception {
		stringS = null;
		doubleS = null;
	}

	/** Tests if the NotationStack is empty after
	 *  removing a few items
	 * 
	 *  @throws StackUnderflowException Occurs if you pop too many items
	 */
	@Test
	public void testIsEmpty() throws StackUnderflowException {
		assertEquals(false,stringS.isEmpty());
		stringS.pop();
		stringS.pop();
		stringS.pop();
		assertEquals(true, stringS.isEmpty());
	}

	/** Tests if the isFull method is working by adding
	 *  a few items and checking if the stack is full
	 * 
	 *  @throws StackOverflowException Occurs if you push on a full stack
	 */
	@Test
	public void testIsFull() throws StackOverflowException {
		assertEquals(false, stringS.isFull());
		stringS.push(d);
		stringS.push(e);
		assertEquals(true, stringS.isFull());
	}

	/** Tests if pop removes items, and then tests if
	 *  StackUnderFlowException occurs if you try to pop
	 *  when there aren't any items in the Stack.
	 */
	@Test
	public void testPop() {
		try {
			assertEquals(c, stringS.pop());
			assertEquals(b, stringS.pop());
			assertEquals(a, stringS.pop());
			//Queue is empty, next statement should cause QueueUnderFlowException
			stringS.pop();
			assertTrue("This should have caused an StackUnderflowException", false);
		}
		catch (StackUnderflowException e){
			assertTrue("This should have caused an StackUnderflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an StackUnderflowException", false);
		}
	}

	/** Tests to make sure that removing items from the
	 *  Stack actually removes them from the data 
	 * 
	 * @throws StackUnderflowException Occurs if you pop an empty stack
	 */
	@Test
	public void testPopStudent() throws StackUnderflowException {
		doubleS.pop();
		doubleS.pop();
		assertEquals("3.44.5", doubleS.toString());
		doubleS.pop();
		assertEquals("3.4", doubleS.toString());
		doubleS.pop();
		assertEquals("", doubleS.toString());
		try {
			doubleS.pop();
			assertTrue("This should have caused a StackUnderflowException", false);
		} catch (StackUnderflowException e) {
			assertTrue("This caused a StackUnderflowException, and I expected it to", true);
		} catch (Exception e) {
			assertTrue("This should have caused a StackUnderflowException", false);
		}
	}
	
	/** Tests to make sure that top returns the topmost
	 *  item in the stack.
	 * 
	 * @throws StackOverflowException Occurs if you try to push on a full Stack
	 * @throws StackUnderflowException Occurs if you try to top on an Empty stack. 
	 */
	@Test
	public void testTop() throws StackOverflowException, StackUnderflowException {
		assertEquals(c, stringS.top());
		stringS.push(d);
		assertEquals(d, stringS.top());
		stringS.pop();
		stringS.pop();
		assertEquals(b, stringS.top());		
	}

	/** Tests to make sure that the size of the stack matches the amount of elements
	 *  that I've put in or taken out 
	 * 
	 *  @throws StackUnderflowException Occurs when you try to pop an empty stack
	 *  @throws StackOverflowException Occurs when you try to push on a full stack.
	 */
	@Test
	public void testSize() throws StackUnderflowException, StackOverflowException {
		assertEquals(3, stringS.size());
		stringS.push(d);
		assertEquals(4, stringS.size());
		stringS.pop();
		stringS.pop();
		assertEquals(2, stringS.size());
	}

	/** Tests to make sure that push adds the item to the stack and
	 *  if you push on a full stack, it causes a StackOverflowException
	 */
	@Test
	public void testPush() {
		try {
			assertEquals(3, stringS.size());
			assertEquals(true, stringS.push(d));
			assertEquals(4, stringS.size());
			assertEquals(true, stringS.push(e));
			assertEquals(5, stringS.size());
			//Queue is full, next statement should cause QueueOverFlowException
			stringS.push(f);
			assertTrue("This should have caused an StackOverflowException", false);
		}
		catch (StackOverflowException e){
			assertTrue("This should have caused an StackOverflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an StackOverflowException", false);
		}
	}

	/** Tests to make sure that push adds items to the stack and that the data is being
	 *  reported back correctly. It also tests for a StackOverflowException if you try
	 *  to push on a full Stack.
	 * 
	 * @throws StackOverflowException Occurs if you try to push on a full stack.
	 */
	@Test
	public void testPushStudent() throws StackOverflowException {
		doubleS.push(5.4);
		assertEquals("3.44.544.38.45.4", doubleS.toString());
		
		try {
			doubleS.push(1.0);
			assertTrue("This should have caused a StackOverflowException", false);
		} catch (StackOverflowException e ) {
			assertTrue("This caused a StackOverflowException, as expected", true);
		} catch (Exception e) {
			assertTrue("This should have caused a StackOverflowException", false);
		}
	}
	
	/** Tests to make sure the toString method reports
	 *  the data that the stack contains correctly.
	 * 
	 *  @throws StackOverflowException Occurs if you try to push on a full stack.
	 */
	@Test
	public void testToString() throws StackOverflowException {
		assertEquals("abc", stringS.toString());
		stringS.push(d);
		assertEquals("abcd", stringS.toString());
		stringS.push(e);
		assertEquals("abcde", stringS.toString());
	}

	/** Tests to make sure that the toString method correctly reports back
	 *  the data that the stack contains.
	 * 
	 * @throws StackUnderflowException Occurs if you try to pop an empty Stack
	 * @throws StackOverflowException Occurs if you try to push on a full stack
	 */
	@Test
	public void testToStringStudent() throws StackUnderflowException, StackOverflowException {
		assertEquals("3.4, 4.5, 44.3, 8.4" , doubleS.toString(", "));
		doubleS.pop();
		assertEquals("3.4, 4.5, 44.3", doubleS.toString(", "));
		doubleS.push(5.88);
		assertEquals("3.4, 4.5, 44.3, 5.88", doubleS.toString(", "));
		doubleS.push(64.33);
		assertEquals("3.4, 4.5, 44.3, 5.88, 64.33", doubleS.toString(", "));
	}
	
	/** Tests to make sure that the delimiters are showing correctly
	 *  when set
	 * 
	 * @throws StackOverflowException Occurs if you try to push on a full stack.
	 */
	@Test
	public void testToStringDelimiter() throws StackOverflowException {
		assertEquals("a%b%c", stringS.toString("%"));
		stringS.push(d);
		assertEquals("a&b&c&d", stringS.toString("&"));
		stringS.push(e);
		assertEquals("a/b/c/d/e", stringS.toString("/"));
	}

	/** Tests to make sure that ArrayLists of any type are
	 *  correctly being processed and checking for correct
	 *  data retrieval. 
	 * 
	 * @throws StackUnderflowException Occurs when you try to pop an empty Stack.
	 */
	@Test
	public void testFill() throws StackUnderflowException {
		fill.add("apple");
		fill.add("banana");
		fill.add("carrot");
		//start with an empty queue
		stringS = new NotationStack<String>(fill);
		assertEquals(3,stringS.size());
		assertEquals("carrot", stringS.pop());
		assertEquals("banana", stringS.pop());
		assertEquals("apple", stringS.pop());	
		assertTrue(stringS.isEmpty());
	}

}