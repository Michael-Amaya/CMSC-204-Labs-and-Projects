/** This exception is only caused when characters are 
 *  in an invalid sequence.
 *  An invalid sequence is defined as more than two characters.
 *  
 *  Example: teeest, eefrrree, aaand are all invalid because
 * 	they have characters that repeat more than twice.
 *  
 *  @author Michael Amaya
 *
 */
public class InvalidSequenceException extends PasswordException {
	/** Constructor that takes no parameter, gives default error. */
	public InvalidSequenceException() {
		this("The password cannot contain more than two of the same character in sequence.");
	}
	
	/** Constructor that takes in one parameter and
	 *  Creates an exception based off of the parameter.
	 *  
	 *  @param msg The message to show when the exception occurs
	 */
	public InvalidSequenceException(String msg) {
		super(msg);
	}
}
