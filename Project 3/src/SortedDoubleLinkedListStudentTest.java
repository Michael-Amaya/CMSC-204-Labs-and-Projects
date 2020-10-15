import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** A group of tests that test the SortedDoubleLinkedList class
 * 
 * @author Michael Amaya
 *
 */
public class SortedDoubleLinkedListStudentTest {

	SortedDoubleLinkedList<Integer> listI;
	SortedDoubleLinkedList<Character> listC;
	
	IntegerComparator comparatorI;
	CharacterComparator comparatorC;
	
	Character a = new Character('a');
	Character b = new Character('b');
	Character c = new Character('c');
	Character d = new Character('d');
	Character e = new Character('e');
	Character f = new Character('f');
	
	Integer zero = new Integer(0);
	Integer one = new Integer(1);
	Integer two = new Integer(2);
	Integer three = new Integer(3);
	Integer four = new Integer(4);
	Integer five = new Integer(5);
	Integer six = new Integer(6);
	Integer seven = new Integer(7);
	Integer eight = new Integer(8);
	Integer nine = new Integer(9);
	Integer ten = new Integer(10);
	
	/** Sets up the comparators and the lists
	 * 
	 * @throws Exception Any exception that SortedDoubleLinkedList may throw
	 */
	@Before
	public void setUp() throws Exception {
		comparatorI = new IntegerComparator();
		comparatorC = new CharacterComparator();
		
		listI = new SortedDoubleLinkedList<>(comparatorI);
		listC = new SortedDoubleLinkedList<>(comparatorC);
	}

	/** Tears down the comparators and lists
	 * 
	 * @throws Exception Any exception that SortedDoubleLinkedList may throw
	 */
	@After
	public void tearDown() throws Exception {
		comparatorI = null;
		comparatorC = null;
		listI = null;
		listC = null;
	}

	/** Tests the add method and makes sure that sorting is being done correctly */
	@Test
	public void addTest() {
		listI.add(ten);
		listI.add(two);
		listI.add(five);
		listI.add(one);
		listI.add(four);
		assertTrue("[1, 2, 4, 5, 10]".equals(listI.toArrayList().toString()));
		
		listC.add(c);
		listC.add(b);
		listC.add(a);
		listC.add(f);
		assertTrue("[a, b, c, f]".equals(listC.toArrayList().toString()));
	}
	
	/** Tests that the addToFront method actually throws an exception, as it should */
	@Test
	public void addToFrontTest() {
		try {
			listI.addToFront(one);
			assertTrue("UnsupportedOperationException was not thrown as it should have", false);
		} catch (UnsupportedOperationException uoe) {
			assertTrue("UnsupportedOperationException was thrown as expected", true);
		} catch (Exception e) {
			assertTrue("UnsupportedOperationException was not thrown as it should have", false);
		}
		
		try {
			listC.addToFront(a);
			assertTrue("UnsupportedOperationException was not thrown as it should have", false);
		} catch (UnsupportedOperationException uoe) {
			assertTrue("UnsupportedOperationException was thrown as expected", true);
		} catch (Exception e) {
			assertTrue("UnsupportedOperationException was not thrown as it should have", false);
		}
	}
	
	/** Tests that addToEnd method actually throws an exception, as it should */
	@Test
	public void addToEndTest() {
		try {
			listI.addToEnd(one);
			assertTrue("UnsupportedOperationException was not thrown as it should have", false);
		} catch (UnsupportedOperationException uoe) {
			assertTrue("UnsupportedOperationException was thrown as expected", true);
		} catch (Exception e) {
			assertTrue("UnsupportedOperationException was not thrown as it should have", false);
		}
		
		try {
			listC.addToEnd(a);
			assertTrue("UnsupportedOperationException was not thrown as it should have", false);
		} catch (UnsupportedOperationException uoe) {
			assertTrue("UnsupportedOperationException was thrown as expected", true);
		} catch (Exception e) {
			assertTrue("UnsupportedOperationException was not thrown as it should have", false);
		}
	}
	
	/** Tests to make sure that the remove method is working as it should */
	@Test
	public void removeTest() {
		listI.add(ten);
		listI.add(two);
		listI.add(five);
		listI.add(one);
		listI.add(four);
		listI.remove(five, comparatorI);
		assertTrue("[1, 2, 4, 10]".equals(listI.toArrayList().toString()));
		
		listC.add(c);
		listC.add(b);
		listC.add(a);
		listC.add(f);
		listC.remove(c, comparatorC);
		assertTrue("[a, b, f]".equals(listC.toArrayList().toString()));
	}
	
	/** Comparator that compares characters
	 * 
	 * @author Some Unknown Montgomery College Professor
	 *
	 */
	private class CharacterComparator implements Comparator<Character>
	{

		@Override
		public int compare(Character arg0, Character arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	/** Comparators that compares integers
	 * 
	 * @author Some Unknown Montgomery College Professor
	 *
	 */
	private class IntegerComparator implements Comparator<Integer>
	{

		@Override
		public int compare(Integer arg0, Integer arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}

}
