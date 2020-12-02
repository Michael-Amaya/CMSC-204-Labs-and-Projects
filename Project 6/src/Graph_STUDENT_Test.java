import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Graph_STUDENT_Test {
	
	Graph townGraph;

	Town town1;
	Town town2;
	Town town3;
	Town town4;
	Town town5;
	Town town6;
	Town town7;
	Town town8;
	
	@Before
	public void setUp() throws Exception {
		townGraph = new Graph();
		
		town1 = new Town("Gaithersburg");
		town2 = new Town("Rockville");
		town3 = new Town("Silver Spring");
		town4 = new Town("Clinton");
		town5 = new Town("Waldorf");
		town6 = new Town("Ashton");
		town7 = new Town("White Oak");
		town8 = new Town("Burtonsville");
		
		townGraph.addVertex(town1);
		townGraph.addVertex(town2);
		townGraph.addVertex(town3);
		townGraph.addVertex(town4);
		townGraph.addVertex(town5);
		townGraph.addVertex(town6);
		townGraph.addVertex(town7);
		townGraph.addVertex(town8);
		
		townGraph.addEdge(town1, town2, 3, "Shady Grove Road");
		townGraph.addEdge(town1, town3, 8, "ICC 200");
		townGraph.addEdge(town2, town3, 5, "Randolph Road");
		townGraph.addEdge(town3, town4, 10, "I-495");
		townGraph.addEdge(town4, town5, 3, "Some road");
		townGraph.addEdge(town3, town8, 2, "Spencerville Road");
		townGraph.addEdge(town3, town7, 3, "New Hampshire Ave N");
		townGraph.addEdge(town3, town6, 1, "New Hampshire Ave S");
		townGraph.addEdge(town1, town8, 16, "ICC 201");
	}

	@After
	public void tearDown() throws Exception {
		town1 = town2 = town3 = town4 = town5 = town6 = town7 = town8 = null;
		townGraph = null;
	}

	@Test
	public void testGetEdge() {
		assertTrue(townGraph.getEdge(town1, town2).getName().equals("Shady Grove Road"));
		assertTrue(townGraph.getEdge(town3, town4).getName().equals("I-495"));
		assertTrue(townGraph.getEdge(town4, town5).getName().equals("Some road"));
		assertTrue(townGraph.getEdge(town3, town7).getName().equals("New Hampshire Ave N"));
		assertTrue(townGraph.getEdge(town1, town8).getName().equals("ICC 201"));
	}

	@Test
	public void testAddEdge() {
		townGraph.addEdge(town4, town1, 200, "DNE");
		assertTrue(townGraph.getEdge(town4, town1).getName().equals("DNE"));
		
		townGraph.addEdge(town1, town5, 100, "Random");
		assertTrue(townGraph.getEdge(town1, town5).getName().equals("Random"));
		
		townGraph.addEdge(town2, town4, 30, "Wow");
		assertTrue(townGraph.getEdge(town2, town4).getName().equals("Wow"));
		
		townGraph.addEdge(town4, town3, 20, "Nope");
		assertFalse(townGraph.getEdge(town4, town3).getName().equals("Not Nope"));
		
	}

	@Test
	public void testAddVertex() {
		Town tTown1 = new Town("LOLTOWN");
		Town tTown2 = new Town("PS5 SCALPERS");
		
		townGraph.addVertex(tTown1);
		assertTrue(townGraph.containsVertex(tTown1));
		
		assertFalse(townGraph.containsVertex(tTown2));
		
		townGraph.addVertex(tTown2);
		assertTrue(townGraph.containsVertex(tTown2));
	}

	@Test
	public void testContainsEdge() {
		assertTrue(townGraph.containsEdge(town1, town2));
		assertTrue(townGraph.containsEdge(town1, town8));
		assertFalse(townGraph.containsEdge(town4, town6));
	}

	@Test
	public void testContainsVertex() {
		assertTrue(townGraph.containsVertex(town1));
		assertTrue(townGraph.containsVertex(town4));
		assertTrue(townGraph.containsVertex(town3));
		assertTrue(townGraph.containsVertex(town7));
		
		Town tTown1 = new Town("LOLTOWN");
		
		assertFalse(townGraph.containsVertex(tTown1));
	}

	@Test
	public void testEdgeSet() {
		Set<String> tRoad = new HashSet<>();
		for (Road road : townGraph.edgeSet())
			tRoad.add(road.getName());
		
		assertTrue(tRoad.contains("ICC 200"));
		assertTrue(tRoad.contains("Spencerville Road"));
		assertTrue(tRoad.contains("Randolph Road"));
		assertTrue(tRoad.contains("I-495"));
		assertTrue(tRoad.contains("Some road"));
		assertTrue(tRoad.contains("ICC 201"));
		assertTrue(tRoad.contains("New Hampshire Ave N"));
		assertTrue(tRoad.contains("Shady Grove Road"));
		assertTrue(tRoad.contains("New Hampshire Ave S"));
	}

	@Test
	public void testEdgesOf() {
		Set<String> tRoad = new HashSet<>();
		for (Road road : townGraph.edgesOf(town1))
			tRoad.add(road.getName());
		
		assertTrue(tRoad.contains("ICC 200"));
		assertTrue(tRoad.contains("ICC 201"));
		assertTrue(tRoad.contains("Shady Grove Road"));
		
		tRoad.clear();
		for (Road road : townGraph.edgesOf(town2))
			tRoad.add(road.getName());
		
		assertTrue(tRoad.contains("Randolph Road"));
		assertTrue(tRoad.contains("Shady Grove Road"));
		
	}

	@Test
	public void testRemoveEdge() {
		townGraph.removeEdge(town1, town2, 3, "Shady Grove Road");
		assertFalse(townGraph.containsEdge(town1, town2));
		townGraph.removeEdge(town4, town5, 3 , "Some road");
		assertFalse(townGraph.containsEdge(town4, town5));
	}

	@Test
	public void testRemoveVertex() {
		townGraph.removeVertex(town1);
		assertFalse(townGraph.containsVertex(town1));
		assertFalse(townGraph.containsEdge(town1, town2));
	}

	@Test
	public void testVertexSet() {
		Set<Town> townSet = townGraph.vertexSet();
		
		assertTrue(townSet.contains(town1));
		assertTrue(townSet.contains(town2));
		assertTrue(townSet.contains(town3));
		assertTrue(townSet.contains(town4));
		assertTrue(townSet.contains(town5));
		assertTrue(townSet.contains(town6));
		assertTrue(townSet.contains(town7));
		assertTrue(townSet.contains(town8));
	}

	@Test
	public void testShortestPath() {
		ArrayList<String> shortPath = townGraph.shortestPath(town1, town8);
		assertTrue(shortPath.get(0).equals("Gaithersburg via ICC 200 to Silver Spring 8 mi"));
		assertTrue(shortPath.get(1).equals("Silver Spring via Spencerville Road to Burtonsville 2 mi"));
		
		shortPath = townGraph.shortestPath(town4, town1);
		assertTrue(shortPath.get(0).equals("Clinton via I-495 to Silver Spring 10 mi"));
		assertTrue(shortPath.get(1).equals("Silver Spring via ICC 200 to Gaithersburg 8 mi"));
	}

}
