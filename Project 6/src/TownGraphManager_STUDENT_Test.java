import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TownGraphManager_STUDENT_Test {
	
	TownGraphManager TGM;

	String town1;
	String town2;
	String town3;
	String town4;
	String town5;
	String town6;
	String town7;
	String town8;
	
	@Before
	public void setUp() throws Exception {
		TGM = new TownGraphManager();
		
		town1 = "Gaithersburg";
		town2 = "Rockville";
		town3 = "Silver Spring";
		town4 = "Clinton";
		town5 = "Waldorf";
		town6 = "Ashton";
		town7 = "White Oak";
		town8= "Burtonsville";
		
		TGM.addTown(town1);
		TGM.addTown(town2);
		TGM.addTown(town3);
		TGM.addTown(town4);
		TGM.addTown(town5);
		TGM.addTown(town6);
		TGM.addTown(town7);
		TGM.addTown(town8);
		
		TGM.addRoad(town1, town2, 3, "Shady Grove Road");
		TGM.addRoad(town1, town3, 8, "ICC 200");
		TGM.addRoad(town2, town3, 5, "Randolph Road");
		TGM.addRoad(town3, town4, 10, "I-495");
		TGM.addRoad(town4, town5, 3, "Some road");
		TGM.addRoad(town3, town8, 2, "Spencerville Road");
		TGM.addRoad(town3, town7, 3, "New Hampshire Ave N");
		TGM.addRoad(town3, town6, 1, "New Hampshire Ave S");
		TGM.addRoad(town1, town8, 16, "ICC 201");
		
	}

	@After
	public void tearDown() throws Exception {
		town1 = town2 = town3 = town4 = town5 = town6 = town7 = town8 = null;
		TGM = null;
	}

	@Test
	public void testAddRoad() {
		TGM.addRoad(town4, town1, 200, "DNE");
		assertTrue(TGM.getRoad(town4, town1).equals("DNE"));
		
		TGM.addRoad(town1, town5, 100, "Random");
		assertTrue(TGM.getRoad(town1, town5).equals("Random"));
		
		TGM.addRoad(town2, town4, 30, "Wow");
		assertTrue(TGM.getRoad(town2, town4).equals("Wow"));
		
		TGM.addRoad(town4, town3, 20, "Nope");
		assertFalse(TGM.getRoad(town4, town3).equals("Not Nope"));
	}

	@Test
	public void testGetRoad() {
		assertTrue(TGM.getRoad(town1, town2).equals("Shady Grove Road"));
		assertTrue(TGM.getRoad(town1, town3).equals("ICC 200"));
		assertTrue(TGM.getRoad(town1, town8).equals("ICC 201"));
	}

	@Test
	public void testAddTown() {
		String tTown1 = "LOLTOWN";
		String tTown2 = "SCREW PS5 SCALPERS";
		
		TGM.addTown(tTown1);
		assertTrue(TGM.containsTown(tTown1));
		
		assertFalse(TGM.containsTown(tTown2));
		
		TGM.addTown(tTown2);
		assertTrue(TGM.containsTown(tTown2));
	}

	@Test
	public void testGetTown() {
		assertNotNull(TGM.getTown("Silver Spring"));
		assertNotNull(TGM.getTown("Rockville"));
		assertNotNull(TGM.getTown("Gaithersburg"));
		assertNotNull(TGM.getTown("Waldorf"));
		assertNull(TGM.getTown("Calverton"));
	}

	@Test
	public void testContainsTown() {
		assertTrue(TGM.containsTown(town1));
		assertTrue(TGM.containsTown(town4));
		assertTrue(TGM.containsTown(town6));
		
		assertFalse(TGM.containsTown("Laurel"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertTrue(TGM.containsRoadConnection(town1, town2));
		assertTrue(TGM.containsRoadConnection(town1, town3));
		assertTrue(TGM.containsRoadConnection(town3, town4));
		assertFalse(TGM.containsRoadConnection(town1, town5));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> allRoads = TGM.allRoads();
		assertTrue(allRoads.contains("I-495"));
		assertTrue(allRoads.contains("ICC 200"));
		assertTrue(allRoads.contains("ICC 201"));
		assertTrue(allRoads.contains("New Hampshire Ave N"));
		assertTrue(allRoads.contains("New Hampshire Ave S"));
		assertTrue(allRoads.contains("Randolph Road"));
		assertTrue(allRoads.contains("Shady Grove Road"));
		assertTrue(allRoads.contains("Some road"));
		assertTrue(allRoads.contains("Spencerville Road"));
	}

	@Test
	public void testDeleteRoadConnection() {
		TGM.deleteRoadConnection(town1, town2, "Shady Grove Road");
		assertFalse(TGM.containsRoadConnection(town1, town2));
		
		assertTrue(TGM.containsRoadConnection(town4, town5));
		TGM.deleteRoadConnection(town4, town5, "Some road");
		assertFalse(TGM.containsRoadConnection(town4, town5));
	}

	@Test
	public void testDeleteTown() {
		TGM.deleteTown(town1);
		assertTrue(!TGM.containsTown(town1));
		assertFalse(TGM.containsRoadConnection(town1, town2));
		
		assertTrue(TGM.containsTown(town4));
		TGM.deleteTown(town4);
		assertFalse(TGM.containsTown(town4));
		assertFalse(TGM.containsRoadConnection(town4, town5));
	}

	@Test
	public void testAllTowns() {
		ArrayList<String> allTown = TGM.allTowns();
		assertTrue(allTown.contains("Ashton"));
		assertTrue(allTown.contains("Burtonsville"));
		assertTrue(allTown.contains("Clinton"));
		assertTrue(allTown.contains("Gaithersburg"));
		assertTrue(allTown.contains("Rockville"));
		assertTrue(allTown.contains("Silver Spring"));
		assertTrue(allTown.contains("Waldorf"));
		assertTrue(allTown.contains("White Oak"));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = TGM.getPath(town1, town7);	
		assertTrue(path.get(0).equals("Gaithersburg via ICC 200 to Silver Spring 8 mi"));
		assertTrue(path.get(1).equals("Silver Spring via New Hampshire Ave N to White Oak 3 mi"));
		
		path = TGM.getPath(town5, town1);
		assertTrue(path.get(0).equals("Waldorf via Some road to Clinton 3 mi"));
		assertTrue(path.get(1).equals("Clinton via I-495 to Silver Spring 10 mi"));
		assertTrue(path.get(2).equals("Silver Spring via ICC 200 to Gaithersburg 8 mi"));
	}

	@Test
	public void testPopulateTownGraph() throws FileNotFoundException {
		File newFile = new File("testFile.txt");
		
		PrintWriter pw = new PrintWriter(newFile);
		pw.println("I270-N,14;Frederick;Clarksburg");
		pw.println("MD109,13;Clarksburg;Poolesville");
		pw.println("MD121,8;Clarksburg;Boyds");
		pw.println("I270-NC,5;Clarksburg;Germantown");
		pw.println("MD108,14;Clarksburg;Olney");
		pw.println("MD117W,9;Poolesville;Boyds");
		pw.println("MD28,8;Poolesville;Darnestown");
		pw.println("MD117E,4;Germantown;Boyds");
		pw.println("MD118W,6;Germantown;Darnestown");
		pw.println("I270-C,6;Germantown;Gaithersburg");
		pw.println("MD118E,15;Germantown;Olney");
		pw.println("MD124,7;Gaithersburg;Darnestown");
		pw.println("I270-SC,7;Gaithersburg;Rockville");
		pw.println("BowieMill,11;Gaithersburg;Olney");
		pw.println("MD190W,8;Darnestown;Potomac");
		pw.println("MD97,8;Rockville;Olney");
		pw.println("MD109,13;Clarksburg;Poolesville");
		pw.println("MD189,6;Rockville;Potomac");
		pw.println("I270-S,7;Rockville;Bethesda");
		pw.println("MD190E,7;Bethesda;Potomac");
		
		pw.close();
		
		try {
			TGM.populateTownGraph(newFile);
			assertTrue("No exception thrown", true);
		} catch (Exception e) {
			assertTrue("Exception thrown", false);
		}
		
		assertTrue(TGM.containsTown("Rockville"));
		assertTrue(TGM.containsTown("Gaithersburg"));
		
		newFile.delete();
	}

}
