/**	This exception should only be caused when
 *  a string does not contain a digit. 
 *  
 *  Example: hello, world, random
 *  will all fail because there is no digit
 * 
 *  @author Michael Amaya
 *
 */
public class NoDigitException extends PasswordException {
	
	/** Constructor with a default exception message */
	public NoDigitException() {
		this("The password must contain at least one digit");
	}
	
	/** Constructor that takes a custom message
	 * 	
	 *  @param msg The custom message to show
	 */
	public NoDigitException(String msg) {
		super(msg);
	}
}
