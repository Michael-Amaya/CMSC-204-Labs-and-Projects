import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MorseCodeTreeTest_STUDENT {
	
	MorseCodeTree tree;
	TreeNode<String> newNode = new TreeNode<>("RANDOM");

	@Before
	public void setUp() throws Exception {
		tree = new MorseCodeTree();
	}

	@After
	public void tearDown() throws Exception {
		tree = null;
	}
	
	@Test
	public void getRootTest() {
		assertTrue(tree.getRoot().getData().equals(""));
		newNode.left = tree.getRoot().left;
		newNode.right = tree.getRoot().right;
		tree.setRoot(newNode);
		assertEquals(tree.getRoot(), newNode);
	}

	@Test
	public void fetchTest() {
		assertTrue(tree.fetch(".-..").equals("l"));
		assertTrue(tree.fetch("...-").equals("v"));
		assertTrue(tree.fetch(".--.").equals("p"));
		assertTrue(tree.fetch("..-.").equals("f"));
		assertTrue(tree.fetch(".---").equals("j"));
		
		assertTrue(tree.fetch("-.-.").equals("c"));
		assertTrue(tree.fetch("--..").equals("z"));
		assertTrue(tree.fetch("---").equals("o"));
		assertTrue(tree.fetch("--.").equals("g"));
		assertTrue(tree.fetch("-..").equals("d"));
	}
	
	@Test
	public void insertTest() {
		tree.insert("----", "NotValidCode, But In Tree!");
		assertTrue(tree.fetch("----").equals("NotValidCode, But In Tree!"));
		tree.insert(".", "Not E"); // Should not overwrite
		assertTrue(tree.fetch(".").equals("e"));
		tree.insert("----.", "Random");
		assertTrue(tree.fetch("----.").equals("Random"));
	}
	
	@Test
	public void toArrayListTest() {
		assertTrue(tree.toArrayList().toString().equals("[h, s, v, i, f, u, e, l, r, a, p, w, j, , b, d, x, n, c, k, y, t, z, g, q, m, o]"));
		tree.insert("----", "AA");
		assertTrue(tree.toArrayList().toString().equals("[h, s, v, i, f, u, e, l, r, a, p, w, j, , b, d, x, n, c, k, y, t, z, g, q, m, o, AA]"));
		tree.insert("---.", "BB");
		assertTrue(tree.toArrayList().toString().equals("[h, s, v, i, f, u, e, l, r, a, p, w, j, , b, d, x, n, c, k, y, t, z, g, q, m, BB, o, AA]"));
	}

}
