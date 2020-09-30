import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** A group of tests that test the NotationQueue class
 * 
 *  @author Unknown professor at Montgomery College
 *  @author Michael Amaya 
 *
 */
public class NotationQueueTest {
	public NotationQueue<String> stringQ;
	public String a="a", b="b", c="c", d="d", e="e", f="f";
	public ArrayList<String> fill = new ArrayList<String>();
	
	// STUDENT: student tests will use the doubleQ
	public NotationQueue<Double> doubleQ;
	// STUDENT: add variables as needed for your student tests

	/** Creates NotationQueue of Strings and doubles
	 *  to do tests with, adds default values to the Queues 
	 * 
	 * @throws Exception Any Exception that NotationQueue may throw
	 */
	@Before
	public void setUp() throws Exception {
		stringQ = new NotationQueue<String>(5);
		stringQ.enqueue(a);
		stringQ.enqueue(b);
		stringQ.enqueue(c);
		
		//STUDENT: add setup for doubleQ for student tests
		doubleQ = new NotationQueue<>(5);
		doubleQ.enqueue(4.6);
		doubleQ.enqueue(88.4);
		doubleQ.enqueue(55.3);
		doubleQ.enqueue(99.4);
	}

	/** Sets the NotationQueues to null to preserve memory
	 * 
	 * @throws Exception Any Exception that NotationQueue may throw
	 */
	@After
	public void tearDown() throws Exception {
		stringQ = null;
		doubleQ = null;
	}

	/** Tests to check if there's nothing in the queue and if it reports
	 *  empty when there's nothing in the queue
	 * 
	 *  @throws QueueUnderflowException Occurs when dequeue is used on
	 *  an empty Queue
	 */
	@Test
	public void testIsEmpty() throws QueueUnderflowException {
		assertEquals(false,stringQ.isEmpty());
		stringQ.dequeue();
		stringQ.dequeue();
		stringQ.dequeue();
		assertEquals(true, stringQ.isEmpty());
	}

	/** Tests to make sure dequeue returns the right item and
	 *  if you try to dequeue on an empty queue, it should throw a
	 *  QueueUnderflowException */
	@Test
	public void testDequeue() {
		try {
			assertEquals(a, stringQ.dequeue());
			assertEquals(b, stringQ.dequeue());
			assertEquals(c, stringQ.dequeue());
			//Queue is empty, next statement should cause QueueUnderFlowException
			stringQ.dequeue();
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
		catch (QueueUnderflowException e){
			assertTrue("This should have caused an QueueUnderflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
	}
	
	/** Tests dequeue and makes sure that the queue is 
	 *  still reporting items correctly in the right order
	 * 
	 * @throws QueueUnderflowException Occurs when dequeue is used on an empty queue
	 */
	@Test
	public void testDequeueStudent() throws QueueUnderflowException {
		assertEquals("4.6, 88.4, 55.3, 99.4", doubleQ.toString(", "));
		assertEquals(doubleQ.dequeue(), 4.6, .0001);
		assertEquals(doubleQ.dequeue(), 88.4, .0001);
		assertEquals("55.3, 99.4", doubleQ.toString(", "));
		assertEquals(doubleQ.dequeue(), 55.3, .001);
		assertEquals("99.4", doubleQ.toString(", "));
		assertEquals(doubleQ.dequeue(), 99.4, .001);
		assertEquals("", doubleQ.toString(", "));
		
		try {
			doubleQ.dequeue();
			assertTrue("This should have caused a QueueUnderflowException", false);
		} catch (QueueUnderflowException e) {
			assertTrue("This caused a QueueUnderflowException, as expected!", true);
		} catch (Exception e) {
			assertTrue("This should have caused a QueueUnderflowException", false);
		}
	}

	/** Tests to make sure the size of the NotationQueue is correct when 
	 *  enqueue or dequeue is used
	 * 
	 * @throws QueueUnderflowException Occurs when dequeue is used on an empty queue
	 * @throws QueueOverflowException Occurs when enqueue is used on a full queue
	 */
	@Test
	public void testSize() throws QueueUnderflowException, QueueOverflowException {
		assertEquals(3, stringQ.size());
		stringQ.enqueue(d);
		assertEquals(4, stringQ.size());
		stringQ.dequeue();
		stringQ.dequeue();
		assertEquals(2, stringQ.size());
	}

	/** Tests to make sure the enqueue method is working correctly. Checks
	 *  if the size is reflecting correctly.
	 */
	@Test
	public void testEnqueue() {
		try {
			assertEquals(3, stringQ.size());
			assertEquals(true, stringQ.enqueue(d));
			assertEquals(4, stringQ.size());
			assertEquals(true, stringQ.enqueue(e));
			assertEquals(5, stringQ.size());
			//Queue is full, next statement should cause QueueOverFlowException
			stringQ.enqueue(f);
			assertTrue("This should have caused an QueueOverflowException", false);
		}
		catch (QueueOverflowException e){
			assertTrue("This should have caused an QueueOverflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an QueueOverflowException", false);
		}
	}

	/** Tests to make sure that enqueue is working correctly and that an
	 *  exception is thrown if you try to enqueue on a full queue
	 * 
	 * @throws QueueOverflowException Occurs when you use enqueue on a full queue
	 */
	@Test
	public void testEnqueueStudent() throws QueueOverflowException {
		assertEquals("4.6, 88.4, 55.3, 99.4", doubleQ.toString(", "));
		assertTrue(doubleQ.enqueue(55.4));
		assertEquals("4.6, 88.4, 55.3, 99.4, 55.4", doubleQ.toString(", "));
		
		try {
			doubleQ.enqueue(4.0);
			assertTrue("This should have caused a QueueOverflowException", false);
		} catch (QueueOverflowException e) {
			assertTrue("This caused a QueueOverflowException, as expected!", true);
		} catch (Exception e) {
			assertTrue("This should have caused a QueueOverflowException", false);
		}
			
	}

	/** Checks to make sure that if the queue is full, that it reports
	 *  as full
	 * 
	 * @throws QueueOverflowException Occurs when you use enqueue on a full queue
	 */
	@Test
	public void testIsFull() throws QueueOverflowException {
		assertEquals(false, stringQ.isFull());
		stringQ.enqueue(d);
		stringQ.enqueue(e);
		assertEquals(true, stringQ.isFull());
	}

	/** Tests to make sure that the data is shown correctly when 
	 *  toString is called
	 * 
	 * @throws QueueOverflowException Occurs when you try to enqueue on a full queue
	 */
	@Test
	public void testToString() throws QueueOverflowException {
		assertEquals("abc", stringQ.toString());
		stringQ.enqueue(d);
		assertEquals("abcd", stringQ.toString());
		stringQ.enqueue(e);
		assertEquals("abcde", stringQ.toString());
	}
	
	/** Makes sure that whether or not you use enqueue or dequeue, that
	 *  the NotationQueue reports the correct items in the queue
	 * 
	 * @throws QueueOverflowException Occurs when you try to enqueue on a full queue
	 * @throws QueueUnderflowException Occurs when you try to dequeue on an empty queue
	 */
	@Test
	public void testToStringStudent() throws QueueOverflowException, QueueUnderflowException {
		assertEquals("4.6, 88.4, 55.3, 99.4", doubleQ.toString(", "));
		doubleQ.enqueue(83.5);
		assertEquals("4.6, 88.4, 55.3, 99.4, 83.5", doubleQ.toString(", "));
		doubleQ.dequeue();
		assertEquals("88.4, 55.3, 99.4, 83.5", doubleQ.toString(", "));
		doubleQ.dequeue();
		doubleQ.dequeue();
		assertEquals("99.4, 83.5", doubleQ.toString(", "));
		doubleQ.dequeue();
		assertEquals("83.5", doubleQ.toString(", "));
		doubleQ.dequeue();
		assertEquals("", doubleQ.toString());
	}

	/** Tests to make sure toString with a delimiter passed in sends the
	 *  data with the separator passed through
	 * 
	 * @throws QueueOverflowException Occurs when you try to use enqueue on a full queue
	 */
	@Test
	public void testToStringDelimiter() throws QueueOverflowException {
		assertEquals("a%b%c", stringQ.toString("%"));
		stringQ.enqueue(d);
		assertEquals("a&b&c&d", stringQ.toString("&"));
		stringQ.enqueue(e);
		assertEquals("a/b/c/d/e", stringQ.toString("/"));
	}

	/** Tests to make sure that creating a NotationQueue with an
	 *  ArrayList adds each item to the NotationQueue correctly
	 * 
	 * @throws QueueUnderflowException Occurs when you try to use dequeue on an empty Queue
	 */
	@Test
	public void testFill() throws QueueUnderflowException {
		fill.add("apple");
		fill.add("banana");
		fill.add("carrot");
		//start with an empty queue
		stringQ = new NotationQueue<String>(fill);
		assertEquals(3,stringQ.size());
		assertEquals("apple", stringQ.dequeue());
		assertEquals("banana", stringQ.dequeue());
		assertEquals("carrot", stringQ.dequeue());		
	}

}