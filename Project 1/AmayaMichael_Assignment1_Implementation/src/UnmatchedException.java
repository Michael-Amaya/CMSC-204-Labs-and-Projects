/** This exception should occur when two strings
 *  do not match, so if the strings are not equal to each other
 *  
 *  Example: "MikE" to "Mike", "Random" to "Something else", "Cool" to "Not"
 * 
 *  @author Michael Amaya
 *
 */
public class UnmatchedException extends PasswordException {
	
	/** Constructor that shows a default message when exception is thrown */
	public UnmatchedException() {
		this("The passwords do not match");
	}
	
	/** Constructor that shows a custom message from
	 *  the parameter provided
	 *  
	 *  @param msg The custom message to be shown when the exception occurs
	 */
	public UnmatchedException(String msg) {
		super(msg);
	}
}
