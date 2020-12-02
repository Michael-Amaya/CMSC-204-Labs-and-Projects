import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {

	Town town1;
	Town town2;
	Town town3;
	Town town4;
	Town town5;
	
	Road road1;
	Road road2;
	Road road3;
	Road road4;
	Road road5;
	
	@Before
	public void setUp() throws Exception {
		town1 = new Town("Gaithersburg");
		town2 = new Town("Rockville");
		town3 = new Town("Silver Spring");
		town4 = new Town("Clinton");
		town5 = new Town("Waldorf");
		
		road1 = new Road(town1, town2, 3, "Shady Grove Road");
		road2 = new Road(town1, town3, 8, "ICC 200");
		road3 = new Road(town2, town3, 5, "Randolph Road");
		road4 = new Road(town3, town4, 10, "I-495");
		road5 = new Road(town4, town5, 3, "Some road");
	}

	@After
	public void tearDown() throws Exception {
		town1 = town2 = town3 = town4 = town5 = null;
		road1 = road2 = road3 = road4 = road5 = null;
	}

	@Test
	public void containsTest() {
		assertTrue(road1.contains(town1));
		assertTrue(road1.contains(town2));
		assertFalse(road5.contains(town3));
		
		assertTrue(road3.contains(town2));
		assertFalse(road3.contains(town5));
	}
	
	@Test
	public void equalsTest() {
		Road road6 = new Road(town1,town2, 3, "Shady Grove Road");
		assertTrue(road1.equals(road6));
		assertFalse(road3.equals(road1));
		assertFalse(road4.equals(road5));
		assertFalse(road6.equals(road4));
	}
	
	@Test
	public void getDestinationTest() {
		assertTrue(road1.getDestination().getName().equals("Rockville"));
		assertTrue(road2.getDestination().getName().equals("Silver Spring"));
		assertFalse(road3.getDestination().getName().equals("Waldorf"));
	}
	
	@Test
	public void getNameTest() {
		assertTrue(road1.getName().equals("Shady Grove Road"));
		assertTrue(road2.getName().equals("ICC 200"));
		assertFalse(road5.getName().equals("I-495"));
	}
	
	@Test
	public void getSourceTest() {
		assertTrue(road1.getSource().getName().equals("Gaithersburg"));
		assertTrue(road2.getSource().getName().equals("Gaithersburg"));
		assertTrue(road3.getSource().getName().equals("Rockville"));
	}
	
	@Test
	public void getWeightTest() {
		assertEquals(road1.getWeight(), 3);
		assertEquals(road2.getWeight(), 8);
		assertEquals(road5.getWeight(), 3);
		assertEquals(road4.getWeight(), 10);
	}
	
	@Test
	public void compareToTest() {
		assertTrue(road1.compareTo(road2) < 0);
		assertTrue(road4.compareTo(road2) > 0);
		assertFalse(road4.compareTo(road1) < 0);
	}

}
