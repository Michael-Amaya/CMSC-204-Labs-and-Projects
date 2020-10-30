import java.io.IOException;
import java.util.LinkedList;

/** A class that uses a Hash Table to hold data and get data
 * 
 * @author Michael Amaya
 *
 */
public class CourseDBStructure implements CourseDBStructureInterface {

	private int tableSize;
	LinkedList<CourseDBElement>[] hashTable;
	
	/** Constructor that sets up the hash table
	 * 
	 *  @param numOfCourses The size of the hash table
	 */
	@SuppressWarnings("unchecked")
	public CourseDBStructure(int numOfCourses) {
		tableSize = numOfCourses;
		hashTable = new LinkedList[tableSize];
	}

	/** Constructor that is only used for testing, 
	 *  doesn't use the String
	 * 
	 *  @param testing Unused Variable
	 *  @param numOfCourses The size of the hash table
	 */
	public CourseDBStructure(String testing, int numOfCourses) {
		this(numOfCourses);
	}

	/** Adds an element to the structure
	 *  
	 *  @param element The CourseDBElement to add
	 */
	@Override
	public void add(CourseDBElement element) {
		boolean placeIntoTable = true;
		int initialPlacement = (int) Math.ceil(element.hashCode() % hashTable.length);
		
		if (hashTable[initialPlacement] == null)
			hashTable[initialPlacement] = new LinkedList<>();
		
		for (CourseDBElement listElement : hashTable[initialPlacement])
			if (listElement.compareTo(element) == 0)
				placeIntoTable = false;
		
		if (placeIntoTable)
			hashTable[initialPlacement].add(element);
	}

	/** Gets an element with the crn number passed through
	 * 
	 *  @param crn The crn to look up
	 *  
	 *  @return The element with hashcode crn
	 */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		CourseDBElement toReturn = null;
		
		for (LinkedList<CourseDBElement> list : hashTable)
			if (list != null)
				for (CourseDBElement listElement : list)
					if (listElement.compareTo(new CourseDBElement("", crn, 0, "", "")) == 0)
						toReturn = listElement;
		
		if (toReturn == null)
			throw new IOException("Couldn't find " + crn);
		
		return toReturn;
	}

	/** Gets the size of the table
	 * 
	 *  @return the table size
	 */
	@Override
	public int getTableSize() {
		return tableSize;
	}

}
