/** An element class that contains information about a course.
 *  It can also be compared to other instances of the same class
 * 
 *  @author Michael Amaya
 *
 */
public class CourseDBElement implements Comparable {

	private String courseID;
	private int crnNumber;
	private int numberOfCredits;
	private String roomNumber;
	private String instructor;
	
	/** Default constructor that sets default values */
	public CourseDBElement() {
		this("", 0, 0, "", "");
	}
	
	/** Constructor that sets values to what was passed through
	 *  
	 *  @param courseID The ID of the course
	 *  @param crn the CRN Number of the course
	 *  @param numberOfCredits The number of credits for the course
	 *  @param roomNumber the number of the room
	 *  @param instructor the instructor
	 */
	public CourseDBElement(String courseID, int crn, int numberOfCredits, String roomNumber, String instructor) {
		this.courseID = courseID;
		this.crnNumber = crn;
		this.numberOfCredits = numberOfCredits;
		this.roomNumber = roomNumber;
		this.instructor = instructor;
	}

	/** Gets the course ID
	 * 
	 * @return The course ID
	 */
	public String getCourseID() {
		return courseID;
	}

	/** Sets the course ID
	 * 
	 * @param courseID The ID of the course
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	/** Gets the Course CRN Number
	 * 
	 * @return The CRN Number of the course
	 */
	public int getCRN() {
		return crnNumber;
	}

	/** Sets the CRN Number
	 * 
	 * @param crnNumber The CRN Number
	 */
	public void setCRN(int crnNumber) {
		this.crnNumber = crnNumber;
	}

	/** Gets the number of credits
	 * 
	 * @return The number of credits
	 */
	public int getNumberOfCredits() {
		return numberOfCredits;
	}

	/** Sets the number of credits
	 * 
	 * @param numberOfCredits The number of credits for the course
	 */
	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	/** Gets the room number
	 * 
	 * @return The room number
	 */
	public String getRoomNumber() {
		return roomNumber;
	}

	/** Sets the room number
	 * 
	 * @param roomNumber The room number
	 */
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	/** Gets the instructor
	 * 
	 * @return The instructor
	 */
	public String getInstructor() {
		return instructor;
	}

	/** Sets the instructor
	 * 
	 * @param instructor The instructor
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	/** Gets the hash code, which is the crn number, converted
	 *  into a String, then calling on the hashCode of that string
	 * 
	 *  @return The hash code of the crn number
	 */
	public int hashCode() {
		return String.valueOf(crnNumber).hashCode();
	}

	/** Compares this class with another class
	 *  
	 *  @param element Another instance of this class
	 *  
	 *  @return positive number if the hashcode of this class
	 *  is greater than the hashcode of element, a negative number if
	 *  the hashcode of this class is less than the hashcode of element,
	 *  and 0 if they're the same
	 */
	@Override
	public int compareTo(CourseDBElement element) {
		return hashCode() - element.hashCode();
	}

	/** Gets the attributes of CourseDBElement
	 * 
	 * @return The info of the CourseDBElement
	 */
	@Override
	public String toString() {
		return "Course:" + courseID + " CRN:" + crnNumber + " Credits:" + numberOfCredits + " Instructor:" + instructor + " Room:" + roomNumber;
	}
}
