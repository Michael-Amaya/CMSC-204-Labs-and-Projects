
/** Exception that is thrown when
 *  one attempts to push onto a full stack
 * 
 * @author Michael Amaya
 *
 */
public class StackOverflowException extends Exception {
	
	/** Default constructor that tells the user that the stack is full */
	public StackOverflowException() {
		this("You cannot push on a full stack!");
	}
	
	/** Constructor that takes a custom message
	 *  and shows it to the user if push is used
	 *  on a full stack
	 *  
	 *  @param msg The custom message to show
	 */
	public StackOverflowException(String msg) {
		super(msg);
	}
}
