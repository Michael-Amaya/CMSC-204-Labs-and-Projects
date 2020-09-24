/** This exception is only caused when you have
 *  a string that is not the correct length.
 *  
 *  Example: If you're checking for a length
 *  of 6, the following should fail and cause this exception:
 *  test, hello, under
 * 
 *  @author Michael Amaya
 *
 */
public class LengthException extends PasswordException {
	
	/** Constructor that provides a default exception message */
	public LengthException() { 
		this("The password must be at least 6 characters long");
	}
	
	/** Contructor that allows for a custom message
	 *  For the Exception Message
	 *  
	 * @param msg The message to show when the exception is thrown*--
	 */
	public LengthException(String msg) {
		super(msg);
	}
}