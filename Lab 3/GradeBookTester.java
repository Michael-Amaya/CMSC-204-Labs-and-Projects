import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**	Test the GradeBook class to make sure
 *  that everything is being processed
 *  correctly.
 *  
 * @author Michael Amaya
 *
 */
public class GradeBookTester {
	
	GradeBook testGradeBook1;
	GradeBook testGradeBook2;

	/** Create GradeBook objects and populate
	 *  then with data
	 * 
	 */
	@Before
	public void setUp() {
		testGradeBook1 = new GradeBook(5);
		testGradeBook2 = new GradeBook(5);
		
		testGradeBook1.addScore(89.6);
		testGradeBook1.addScore(85.3);
		
		testGradeBook2.addScore(93.4);
		testGradeBook2.addScore(66.2);
		testGradeBook2.addScore(85.2);
	}

	/** Delete the GradeBooks to save
	 *  memory and space
	 *  
	 */
	@After
	public void tearDown() {
		testGradeBook1 = testGradeBook2 = null;
	}

	/** Tests the addScore method.
	 *  When as score is added, the toString
	 *  method must send back an array with
	 *  the scores that were added
	 *  
	 */
	@Test
	public void testAddScore() {
		assertEquals(2, testGradeBook1.getScoresSize());
		assertEquals(3, testGradeBook2.getScoresSize());
		
		assertTrue("89.6, 85.3, 0.0, 0.0, 0.0, ".equals(testGradeBook1.toString()));
		assertTrue("93.4, 66.2, 85.2, 0.0, 0.0, ".equals(testGradeBook2.toString()));
		
		testGradeBook1.addScore(34.5);
		testGradeBook2.addScore(100.0);
		
		assertEquals(3, testGradeBook1.getScoresSize());
		assertEquals(4, testGradeBook2.getScoresSize());
		
		assertTrue("89.6, 85.3, 34.5, 0.0, 0.0, ".equals(testGradeBook1.toString()));
		assertTrue("93.4, 66.2, 85.2, 100.0, 0.0, ".equals(testGradeBook2.toString()));
	}

	/** Tests if the sum method works correctly.
	 *  The sum method must add up all the scores
	 * 
	 */
	@Test
	public void testSum() {
		assertEquals(174.9, testGradeBook1.sum(), .0001);
		assertEquals(244.8, testGradeBook2.sum(), .0001);
		
		testGradeBook1.addScore(34.5);
		testGradeBook2.addScore(100.0);
		
		assertEquals(209.4, testGradeBook1.sum(), .0001);
		assertEquals(344.8, testGradeBook2.sum(), .0001);
	}

	/** Tests if the minimum method works correctly.
	 *  The minimum method returns the smallest 
	 *  double in the GradeBook.
	 * 
	 */
	@Test
	public void testMinimum() {
		assertEquals(85.3, testGradeBook1.minimum(), .0001);
		assertEquals(66.2, testGradeBook2.minimum(), .0001);
		
		testGradeBook1.addScore(34.5);
		testGradeBook2.addScore(100.0);
		
		assertEquals(34.5, testGradeBook1.minimum(), .0001);
		assertEquals(66.2, testGradeBook2.minimum(), .0001);
	}

	/** Tests the finalScore method. The
	 *  finalScore method must return the sum of
	 *  all scores, but drop the lowest score.
	 * 
	 */
	@Test
	public void testFinalScore() {
		assertEquals(89.6, testGradeBook1.finalScore(), .0001);
		assertEquals(178.6, testGradeBook2.finalScore(), .0001);
		
		testGradeBook1.addScore(34.5);
		testGradeBook2.addScore(100.0);
		
		assertEquals(174.9, testGradeBook1.finalScore(), .0001);
		assertEquals(278.6, testGradeBook2.finalScore(), .0001);
	}
}
