import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 * @author Michael Amaya
 *
 */
public class CourseDBManager implements CourseDBManagerInterface {

	private CourseDBStructure structure;
	
	/** Constructor that creates the structure */
	public CourseDBManager() {
		structure = new CourseDBStructure(500);
	}
	
	/** Adds an element to the structure
	 * 
	 *  @param id The course ID
	 *  @param crn The course CRN number
	 *  @param credits The number of credits of the course
	 *  @param roomNum The room number of the course
	 *  @param instructor The instructor of the course
	 */
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		structure.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
	}

	/** Gets an element from the structure
	 * 
	 *  @param crn The crn of the element that you want to get
	 *  
	 *  @return The element with the crn that was passed through
	 */
	@Override
	public CourseDBElement get(int crn) {
		CourseDBElement toReturn = new CourseDBElement("", 0, 0, "", "");
		try {
			toReturn = structure.get(crn);
		} catch (IOException e) {
			System.err.println(crn + " Not found");
		}
		
		return toReturn;
	}

	/** Reads a file and adds everything in that file into the structure
	 * 
	 *  @param input The file to read
	 */
	@Override
	public void readFile(File input) throws FileNotFoundException {
		if(!input.exists())
			throw new FileNotFoundException();
		
		ArrayList<String> dataFromFile = new ArrayList<>();
		
		Scanner fileScanner = new Scanner(input);
		while(fileScanner.hasNextLine()) {
			String next = fileScanner.nextLine();
			if (next != "")
				dataFromFile.add(next);
		}
		
		fileScanner.close();
		
		for (String data : dataFromFile) {
			String[] dataArr = data.split(" ");
			String courseID = dataArr[0];
			int crnNumber = Integer.parseInt(dataArr[1]);
			int numOfCredits = Integer.parseInt(dataArr[2]);
			String roomNumber = dataArr[3];
			String instructor = String.join(" ", Arrays.copyOfRange(dataArr, 4, dataArr.length));
			add(courseID, crnNumber, numOfCredits, roomNumber, instructor);
		}
	}

	/** Gets an element's data
	 * 
	 *  @return An ArrayList with the data of the elements in the structure
	 */
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> data = new ArrayList<>();
		
		for (LinkedList<CourseDBElement> list : structure.hashTable)
			if (list != null)
				for (CourseDBElement listElement : list)
					data.add("\n" + listElement.toString());
		
		return data;
	}

}
