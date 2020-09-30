
/** An Exception that is thrown when 
 *  pop or top is used on an empty stack
 * 
 *  @author Michael Amaya
 *
 */
public class StackUnderflowException extends Exception {
	
	/** Default constructor that tells the user a default error
	 *  That the stack is empty */
	public StackUnderflowException() {
		this("The stack is empty!");
	}
	
	/** Constructor to show the user a custom message
	 *  that is passed through
	 *  
	 *  @param msg The custom message to show the user
	 */
	public StackUnderflowException(String msg) {
		super(msg);
	}
}
